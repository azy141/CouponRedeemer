package com.fdmgroup.OneDayProjectCoupon;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	@GetMapping("/takemehome")
	public String logoutSession(HttpSession sess) {

		sess.invalidate();

		return "redirect:/";
	}

}
