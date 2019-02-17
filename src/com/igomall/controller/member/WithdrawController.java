
package com.igomall.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.entity.Member;
import com.igomall.security.CurrentUser;

/**
 * Controller - 提现
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("memberWithdrawController")
@RequestMapping("/member/withdraw")
public class WithdrawController extends BaseController {

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Integer pageNumber, @CurrentUser Member currentUser, ModelMap model) {
		return "member/withdraw/list";
	}
}