/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.service;

import java.awt.image.BufferedImage;

/**
 * Service - 验证码
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface CaptchaService {

	/**
	 * 创建验证码图片
	 * 
	 * @param captchaId
	 *            验证码ID
	 * @return 验证码图片
	 */
	BufferedImage createImage(String captchaId);

	/**
	 * 验证码验证
	 * 
	 * @param captchaId
	 *            验证码ID
	 * @param captcha
	 *            验证码(忽略大小写)
	 * @return 验证码验证是否通过
	 */
	boolean isValid(String captchaId, String captcha);

}