package com.fdmgroup.OneDayProjectCoupon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entities.Coupon;
import entities.UserAccount;
import repos.CouponRepo;
import repos.UserAccountRepo;

public class CouponProjectTests {

	@Autowired
	UserAccountRepo urep;

	@Autowired
	CouponRepo crep;

	@Test
	public void testToCheckWhetherTheUserAccountObjectIsCreatedSuccessfully() {

		UserAccount user = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		assertEquals(5, user.getUserId());

	}

	@Test
	public void testToCheckWhetherTheCouponObjectIsCreatedSuccessfullyandAddedToList() {

		Coupon coupon = new Coupon();
		UserAccount user = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		user.addToCouponList(coupon);
		assertEquals(1, user.getListOfCoupons().size());

	}

	@Test
	public void testToCheckWhetherTheCouponsareCreatedSuccessfullyandAddedToList() {

		Coupon coupon = new Coupon();
		Coupon coupon2 = new Coupon();
		UserAccount user = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		user.addToCouponList(coupon);
		user.addToCouponList(coupon2);
		assertEquals(2, user.getListOfCoupons().size());

	}

	@Test
	public void testToSeeIfCouponQuantityReducesAfterUsage() {
		Coupon coupon = new Coupon(152, "couponCheck", 10, "GBP", 5, 456);
		coupon.useCoupon();
		assertEquals(4, coupon.getUsesRemaining());
	}

	@Test
	public void testToSeeIfTwoUsersWithSameIDAreEqualToEachOther() {

		UserAccount user = new UserAccount(5, "Aaron", "vfsdgv", "bdbvhb@gmail.com", "dfdv", "password");
		UserAccount user2 = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		assertEquals(user, user2);
	}

	@Test
	public void testToSeeIfTwoCouponWithSameIDAreEqualToEachOther() {

		Coupon coupon = new Coupon(152, "gdfgdfgdg", 10, "GBP", 5, 456);
		Coupon coupon2 = new Coupon(152, "couponCheck", 10, "GBP", 5, 456);

		assertEquals(coupon, coupon2);
	}

	@Test
	public void testToSeeIfTwoCouponWithDifferentIDsAreNotEqualToEachOther() {

		Coupon coupon = new Coupon(43, "couponCheck", 10, "GBP", 5, 456);
		Coupon coupon2 = new Coupon(152, "couponCheck", 10, "GBP", 5, 456);

		assertNotEquals(coupon, coupon2);
	}

	@Test
	public void testToSeeIfTwoUsersWithDifferentIDsAreNotEqualToEachOther() {

		UserAccount user = new UserAccount(52334, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		UserAccount user2 = new UserAccount(5, "Aaron", "Kashab", "aaronkashab@gmail.com", "azy141", "password");
		assertNotEquals(user, user2);
	}
}
