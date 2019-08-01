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
public class CouponController {
	@Autowired
	UserAccountRepo urep;

	@Autowired
	CouponRepo crep;

	@GetMapping("redeemCoupons")
	public String redeemcoupons() {

		return "RedeemPage";
	}

	@GetMapping("goToCreateCoupon")
	public String createcouponlink() {

		return "CreateCoupon";
	}

	@GetMapping("viewCouponsOtherUsers")
	public String viewCouponsOtherUsers() {
		return "ViewOtherCoupons";
	}

	// function to allow you to create the coupon
	@GetMapping("CreateCoupon")
	public String createCoupon(@ModelAttribute Coupon coupon, Model model, HttpSession sess,
			@RequestParam("userid") int id) {

		Optional<UserAccount> opt = urep.findByUserId(id);

		if (opt.isPresent()) {
			UserAccount ux = opt.get();
			ux.addToCouponList(coupon);
			coupon.setUserAcount(ux);
			urep.save(ux);

			model.addAttribute("User", ux);
			model.addAttribute("errorMessage", "Coupon Created Successfully");
			return "CreateCoupon";
		} else {
			System.out.println("User Doesnt Exist");
			model.addAttribute("errorMessage", "Unable to Find User");
		}
		return "CreateCoupon";

	}

	// view the list of coupons assigned to your account
	@GetMapping("viewCoupons")
	public String viewCoupons(Model model, UserAccount user, HttpSession sess) {

		UserAccount user1 = (UserAccount) sess.getAttribute("uCurrent");
		int userid = user1.getUserId();

		// Optional<Coupon> listOfCoupons = crep.findByUserid(4);
		List<Coupon> couponList = crep.findByUserid(userid);

		System.out.println(couponList);

		model.addAttribute("CouponList", couponList);

		return "getCouponList";
	}

	// Redeem the coupons
	@GetMapping("Redeem")
	public String RedeemCoupons(Model model, UserAccount user, HttpSession sess, @RequestParam("couponid") int id) {

		Optional<Coupon> cpt = crep.findById(id);

		if (cpt.isPresent()) {
			Coupon coupon = cpt.get();
			int usesremaining = coupon.getUsesRemaining();
			if (usesremaining <= 0) {
				model.addAttribute("UsesRemaining", "You Have No Uses Remaining!");
				return "RedeemPage";
			} else if (usesremaining >= 1) {
				model.addAttribute("UsesRemaining", "You Redeemed your Coupon!");
				coupon.useCoupon();
				if (coupon.getUsesRemaining() == 0) {
					crep.delete(coupon);
					return "RedeemPage";
				} else {
					crep.delete(coupon);
					crep.save(coupon);
				}
				return "RedeemPage";
			}
		} else {
			model.addAttribute("UsesRemaining", "Coupon Does Not Exist!");
		}
		return "RedeemPage";
	}

	// view the list of coupons assigned to your account
	@GetMapping("viewotherCoupons")
	public String viewOtherCoupons(@RequestParam("userid") int id, UserAccount user, HttpSession sess, Model model) {

		Optional<UserAccount> opt = urep.findByUserId(id);
		if (opt.isPresent()) {
			List<Coupon> couponList = crep.findByUserid(id);
			model.addAttribute("CouponListother", couponList);
			return "getCouponListother";
		} else if (!opt.isPresent()) {
			model.addAttribute("CouponListother", "");
			model.addAttribute("errorMessage", "User ID Not Found!");
			return "ViewOtherCoupons";
		} else
			return "ViewOtherCoupons";
	}
}
