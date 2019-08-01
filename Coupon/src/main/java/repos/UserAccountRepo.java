package repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.UserAccount;



public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	public Optional<UserAccount> findByEmail(String Email);
	public boolean existsByEmail(String Email);
	public Optional<UserAccount> findByPassword(String Password);
	public Optional<UserAccount> findByUserId(int userId);
	public boolean existsByUserId(int userId);
}
