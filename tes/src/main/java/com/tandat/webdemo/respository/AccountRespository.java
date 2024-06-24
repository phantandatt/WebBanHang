
package com.tandat.webdemo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tandat.webdemo.model.Account;

@Repository
public interface AccountRespository extends JpaRepository<Account, String> {
	boolean existsByUsernameAndPassword(String username, String password);
	
	Account findByUsername(String username);

	boolean existsByEmail(String email);
}
