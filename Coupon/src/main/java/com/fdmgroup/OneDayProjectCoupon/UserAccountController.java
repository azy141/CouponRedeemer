package com.fdmgroup.OneDayProjectCoupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import entities.Coupon;
import entities.UserAccount;
import repos.CouponRepo;
import repos.UserAccountRepo;

@Controller
@SessionAttributes({ "userSess", "currentUser", "uCurrent" })
public class UserAccountController {
	@Autowired
	UserAccountRepo urep;

	@Autowired
	CouponRepo crep;

	@GetMapping("populate")
	public String populateDatabasemapping() {
		populateDatabase();
		System.out.println("Database Has Been Populated");
		return "index";

	}

	@GetMapping("registermapping")
	public String createUser() {

		return "register";
	}

	@GetMapping("registerlink")
	public String registerlink() {

		return "register";
	}

	@GetMapping("loggedin")
	public String goTonewLoggedinindex() {

		return "LoggedInIndex";
	}

	@GetMapping("/logout")
	public String logoutOfSession() {

		return "redirect:takemehome";

	}

	@PostMapping("getUser")
	public String createUserData(@ModelAttribute UserAccount user, String password2, Model model) {

		String email = user.getEmail();
		// check whether the email is in the database
		boolean userExists = urep.existsByEmail(email);

		if (userExists) {

			// return create account page with an error message
			String repeatEmail = "An account with that email already exists";
			model.addAttribute("userCreationError", repeatEmail);
			return "register";
		} else {

			// compare passwords
			if (!user.getPassword().equals(password2)) {

				// return create account page with an error message
				model.addAttribute("userCreationError", "Passwords do not match");
				return "register";
			} else {

				// add user to the database
				urep.save(user);
				model.addAttribute("User", user);
				return "userCreated";
			}
		}
	}

	@GetMapping("checkLogin")
	public String logincheck(UserAccount user, Model model, HttpSession sess) {

		String email = user.getEmail();
		String password = user.getPassword();

		// check if email exists in database
		Optional<UserAccount> upt = urep.findByEmail(email);

		// checks if user is in database
		if (upt.isPresent()) {

			if (upt.get().getPassword().equals(password)) {

				UserAccount ux = upt.get();

				// get the user and bind it to the session

				model.addAttribute("currentUser", ux);
				sess.setAttribute("uCurrent", ux); // uIndex
				return "LoggedInIndex";

			} else {
				// entered wrong password
				String invalidPassword = "Incorrect password";
				model.addAttribute("errorMessage", invalidPassword);
				return "index";
			}
		}

		else {
			// entered wrong username
			String invalidLogin = "Incorrect username";
			model.addAttribute("errorMessage", invalidLogin);
			return "index";
		}
	}

	@GetMapping("userDetails")
	public String userDetails(@ModelAttribute(name = "currentUser") UserAccount currentUser, UserAccount user,
			Model model, HttpSession sess) {

		model.addAttribute("User", currentUser);

		return "userDetails";
	}

	public void populateDatabase() {

		// create user accounts in database
		UserAccount user = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "12345");

		UserAccount user2 = new UserAccount(1, "Aaron", "Kashab", "a@a.com", "azy141", "1234");

		// create two coupons with the user assigned
		Coupon coupon = new Coupon(1, "couponCheck", 3.99, "GBP", 5, 1);
		Coupon c2 = new Coupon(2, "couponCheck2", 10.99, "GBP", 5, 5);

		// add the coupons to the user list
		user.addToCouponList(coupon);
		user2.addToCouponList(c2);

		// save users with their coupons to the database
		urep.save(user);
		urep.save(user2);
	}

}
