/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.controller.shop;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.service.ProductCategoryService;

/**
 * Controller - 商品分类
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("shopProductCategoryController")
@RequestMapping("/product_category")
public class ProductCategoryController extends BaseController {

	@Inject
	private ProductCategoryService productCategoryService;

	/**
	 * 首页
	 */
	@GetMapping
	public String index(ModelMap model) {
		model.addAttribute("rootProductCategories", productCategoryService.findRoots());
		return "shop/product_category/index";
	}

}