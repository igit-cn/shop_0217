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
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.service.OrderService;

/**
 * Controller - 打印
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("adminPrintController")
@RequestMapping("/admin/print")
public class PrintController extends BaseController {

	@Inject
	private OrderService orderService;

	/**
	 * 订单打印
	 */
	@GetMapping("/order")
	public String order(Long id, ModelMap model) {
		model.addAttribute("order", orderService.find(id));
		return "admin/print/order";
	}

}