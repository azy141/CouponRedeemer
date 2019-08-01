
package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserAccountsTable")
public class UserAccount {

	@Id
	int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;

	// private ArrayList<Coupon> couponList;

	@OneToMany(mappedBy = "userAcount", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Coupon> listOfCoupons = new ArrayList<Coupon>();

	public List<Coupon> getListOfCoupons() {
		return listOfCoupons;
	}

	public void setListOfCoupons(List<Coupon> listOfCoupons) {
		this.listOfCoupons = listOfCoupons;
	}

	public UserAccount(int userId, String firstName, String lastName, String email, String userName, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public UserAccount() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
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
		UserAccount other = (UserAccount) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", userName=" + userName + ", password=" + password + "]";
	}

	public void addToCouponList(Coupon coupon) {
		listOfCoupons.add(coupon);
	}

}
// public void removeFromTheCouponList(Coupon coupon) {
// couponList.remove(coupon);
// }
//
// public ArrayList<Coupon> getCouponList() {
// return couponList;
// }
//
// public void setCouponList(ArrayList<Coupon> couponList) {
// this.couponList = couponList;
// }
