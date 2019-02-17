/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.controller.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.igomall.Message;
import com.igomall.Pageable;
import com.igomall.entity.Admin;
import com.igomall.entity.Business;
import com.igomall.entity.BusinessDepositLog;
import com.igomall.security.CurrentUser;
import com.igomall.service.BusinessDepositLogService;
import com.igomall.service.BusinessService;

/**
 * Controller - 商家预存款
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("adminBusinessDepositController")
@RequestMapping("/admin/business_deposit")
public class BusinessDepositController extends BaseController {

	@Inject
	private BusinessDepositLogService businessDepositLogService;
	@Inject
	private BusinessService businessService;

	/**
	 * 检查商家
	 */
	@GetMapping("/check_business")
	public @ResponseBody Map<String, Object> checkBusiness(String username) {
		Map<String, Object> data = new HashMap<String, Object>();
		Business business = businessService.findByUsername(username);
		if (business == null) {
			data.put("message", Message.warn("admin.businessDeposit.businessNotExist"));
			return data;
		}
		data.put("message", SUCCESS_MESSAGE);
		data.put("balance", business.getBalance());
		return data;
	}

	/**
	 * 调整
	 */
	@GetMapping("/adjust")
	public String adjust() {
		return "admin/business_deposit/adjust";
	}

	/**
	 * 调整
	 */
	@PostMapping("/adjust")
	public String adjust(String username, BigDecimal amount, String memo, @CurrentUser Admin currentUser, RedirectAttributes redirectAttributes) {
		Business business = businessService.findByUsername(username);
		if (business == null) {
			return ERROR_VIEW;
		}
		if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
			return ERROR_VIEW;
		}
		if (business.getBalance() == null || business.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
			return ERROR_VIEW;
		}
		businessService.addBalance(business, amount, BusinessDepositLog.Type.adjustment, memo);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:log";
	}

	/**
	 * 记录
	 */
	@GetMapping("/log")
	public String log(Long businessId, Pageable pageable, ModelMap model) {
		Business business = businessService.find(businessId);
		if (business != null) {
			model.addAttribute("business", business);
			model.addAttribute("page", businessDepositLogService.findPage(business, pageable));
		} else {
			model.addAttribute("page", businessDepositLogService.findPage(pageable));
		}
		return "admin/business_deposit/log";
	}

}