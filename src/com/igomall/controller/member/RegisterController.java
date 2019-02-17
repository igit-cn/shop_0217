/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.controller.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igomall.Results;
import com.igomall.Setting;
import com.igomall.entity.BaseEntity;
import com.igomall.entity.Member;
import com.igomall.entity.MemberAttribute;
import com.igomall.entity.MemberExtra;
import com.igomall.entity.SocialUser;
import com.igomall.security.UserAuthenticationToken;
import com.igomall.service.MemberAttributeService;
import com.igomall.service.MemberExtraService;
import com.igomall.service.MemberRankService;
import com.igomall.service.MemberService;
import com.igomall.service.SocialUserService;
import com.igomall.service.UserService;
import com.igomall.util.SystemUtils;

/**
 * Controller - 会员注册
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("memberRegisterController")
@RequestMapping("/member/register")
public class RegisterController extends BaseController {

	@Inject
	private UserService userService;
	@Inject
	private MemberService memberService;
	@Inject
	private MemberRankService memberRankService;
	@Inject
	private MemberAttributeService memberAttributeService;
	@Inject
	private SocialUserService socialUserService;
	
	@Autowired
	private MemberExtraService memberExtraService;

	/**
	 * 检查用户名是否存在
	 */
	@GetMapping("/check_username")
	public @ResponseBody boolean checkUsername(String username) {
		return StringUtils.isNotEmpty(username) && !memberService.usernameExists(username);
	}

	/**
	 * 检查E-mail是否存在
	 */
	@GetMapping("/check_email")
	public @ResponseBody boolean checkEmail(String email) {
		return StringUtils.isNotEmpty(email) && !memberService.emailExists(email);
	}

	/**
	 * 检查手机是否存在
	 */
	@GetMapping("/check_mobile")
	public @ResponseBody boolean checkMobile(String mobile) {
		return StringUtils.isNotEmpty(mobile) && !memberService.mobileExists(mobile);
	}

	/**
	 * 注册页面
	 */
	@GetMapping
	public String index(Long socialUserId, String uniqueId, ModelMap model) {
		if (socialUserId != null && StringUtils.isNotEmpty(uniqueId)) {
			SocialUser socialUser = socialUserService.find(socialUserId);
			if (socialUser == null || socialUser.getUser() != null || !StringUtils.equals(socialUser.getUniqueId(), uniqueId)) {
				return UNPROCESSABLE_ENTITY_VIEW;
			}
			model.addAttribute("socialUserId", socialUserId);
			model.addAttribute("uniqueId", uniqueId);
		}
		model.addAttribute("genders", Member.Gender.values());
		return "member/register/index";
	}

	/**
	 * 注册提交
	 */
	@PostMapping("/submit")
	public ResponseEntity<?> submit(String paretnUsername,String username, String password, String email, String mobile, Long socialUserId, String uniqueId, HttpServletRequest request) {
		Setting setting = SystemUtils.getSetting();
		if (!ArrayUtils.contains(setting.getAllowedRegisterTypes(), Setting.RegisterType.member)) {
			return Results.unprocessableEntity("member.register.disabled");
		}
		
		Member parent = null;
		if(StringUtils.isNotEmpty(paretnUsername)) {
			parent = memberService.findByUsername(paretnUsername);
			if(parent==null) {
				return Results.unprocessableEntity("推荐人不存在");
			}
		}
		
		
		if (!isValid(Member.class, "username", username, BaseEntity.Save.class) || !isValid(Member.class, "password", password, BaseEntity.Save.class) || !isValid(Member.class, "email", email, BaseEntity.Save.class) || !isValid(Member.class, "mobile", mobile, BaseEntity.Save.class)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (memberService.usernameExists(username)) {
			return Results.unprocessableEntity("member.register.usernameExist");
		}
		if (memberService.emailExists(email)) {
			return Results.unprocessableEntity("member.register.emailExist");
		}
		if (StringUtils.isNotEmpty(mobile) && memberService.mobileExists(mobile)) {
			return Results.unprocessableEntity("member.register.mobileExist");
		}

		Member member = new Member();
		member.removeAttributeValue();
		for (MemberAttribute memberAttribute : memberAttributeService.findList(true, true)) {
			String[] values = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
			if (!memberAttributeService.isValid(memberAttribute, values)) {
				return Results.UNPROCESSABLE_ENTITY;
			}
			Object memberAttributeValue = memberAttributeService.toMemberAttributeValue(memberAttribute, values);
			member.setAttributeValue(memberAttribute, memberAttributeValue);
		}

		member.setUsername(username);
		member.setPassword(password);
		member.setEmail(email);
		member.setMobile(mobile);
		member.setPoint(0L);
		member.setBalance(BigDecimal.ZERO);
		member.setAmount(BigDecimal.ZERO);
		member.setIsEnabled(true);
		member.setIsLocked(false);
		member.setLockDate(null);
		member.setLastLoginIp(request.getRemoteAddr());
		member.setLastLoginDate(new Date());
		member.setSafeKey(null);
		member.setMemberRank(memberRankService.findDefault());
		member.setCart(null);
		member.setOrders(null);
		member.setPaymentTransactions(null);
		member.setMemberDepositLogs(null);
		member.setCouponCodes(null);
		member.setReceivers(null);
		member.setReviews(null);
		member.setConsultations(null);
		member.setProductFavorites(null);
		member.setProductNotifies(null);
		member.setInMessages(null);
		member.setOutMessages(null);
		member.setSocialUsers(null);
		member.setPointLogs(null);
		userService.register(member);
		
		MemberExtra memberExtra = new MemberExtra();
		memberExtra.setMember(member);
		if(parent!=null&&parent.getMemberExtra()!=null) {
			memberExtra.setParent(parent.getMemberExtra());
			memberExtra.setTreePath(parent.getMemberExtra().getTreePath()+parent.getId()+",");
			memberExtra.setGrade(parent.getMemberExtra().getGrade()+1);
		}
		memberExtraService.save(memberExtra);
		userService.login(new UserAuthenticationToken(Member.class, username, password, false, request.getRemoteAddr()));
		return Results.ok("member.register.success");
	}

}