/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.igomall.Message;
import com.igomall.Pageable;
import com.igomall.entity.Consultation;
import com.igomall.service.ConsultationService;

/**
 * Controller - 咨询
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("adminConsultationController")
@RequestMapping("/admin/consultation")
public class ConsultationController extends BaseController {

	@Inject
	private ConsultationService consultationService;

	/**
	 * 编辑
	 */
	@GetMapping("/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("consultation", consultationService.find(id));
		return "admin/consultation/edit";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public String update(Long id, @RequestParam(defaultValue = "false") Boolean isShow, RedirectAttributes redirectAttributes) {
		Consultation consultation = consultationService.find(id);
		if (consultation == null) {
			return ERROR_VIEW;
		}
		if (isShow != consultation.getIsShow()) {
			consultation.setIsShow(isShow);
			consultationService.update(consultation);
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", consultationService.findPage(null, null, null, null, pageable));
		return "admin/consultation/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public @ResponseBody Message delete(Long[] ids) {
		if (ids != null) {
			consultationService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

}