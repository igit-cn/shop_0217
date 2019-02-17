/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.entity.Admin;
import com.igomall.security.CurrentUser;

/**
 * Controller - 管理员登录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController extends BaseController {

	/**
	 * 登录跳转
	 */
	@GetMapping({ "", "/" })
	public String index() {
		return "redirect:/admin/login";
	}

	/**
	 * 登录页面
	 */
	@GetMapping("/login")
	public String index(@CurrentUser Admin currentUser, ModelMap model) {
		return currentUser != null ? "redirect:/admin/index" : "admin/login/index";
	}

}