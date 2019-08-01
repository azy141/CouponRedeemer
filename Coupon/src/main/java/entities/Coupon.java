package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Coupons")
public class Coupon {
	@Id
	int couponId;
	private String couponName;
	private double value;
	private int usesRemaining;
	private int userid;
	private String currency;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_userId")
	private UserAccount userAcount;

	// public List<UserAccount> getListOfUsers() {
	// return listOfUsers;
	// }

	public Coupon(int couponId, String couponName, double value, String currency, int usesRemaining, int userid) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.value = value;
		this.usesRemaining = usesRemaining;
		this.userid = userid;
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Coupon() {

	}

	public UserAccount getUserAcount() {
		return userAcount;
	}

	public void setUserAcount(UserAccount userAcount) {
		this.userAcount = userAcount;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	// public void setListOfUsers(UserAccount user) {
	// listOfUsers.add(user);
	// }

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public int getUsesRemaining() {
		return usesRemaining;
	}

	public void setUsesRemaining(int usesRemaining) {
		this.usesRemaining = usesRemaining;
	}

	public void useCoupon() {
		usesRemaining--;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", value=" + value + ", usesRemaining="
				+ usesRemaining + ", userid=" + userid + ", currency=" + currency + ", userAcount=" + userAcount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + couponId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (couponId != other.couponId)
			return false;
		return true;
	}

}
