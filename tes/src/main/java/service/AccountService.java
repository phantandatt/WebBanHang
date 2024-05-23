package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Account;
import respository.AccountRespository;

@Service
@Transactional
public class AccountService {
	@Autowired(required = true)
	private AccountRespository accountRespository;

	public List<Account> getAll() {
		return accountRespository.findAll();
	}

	public long totalAccount() {
		return accountRespository.count();
	}

}
