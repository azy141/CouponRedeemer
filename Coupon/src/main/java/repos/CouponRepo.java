package repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	public Optional<Coupon> findByCouponName(String CouponName);
	//public Optional<Coupon> findsByUserid(int id);
	public List<Coupon> findByUserid(int id);
}
