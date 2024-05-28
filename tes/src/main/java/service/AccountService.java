package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Account;
import respository.AccountRespository;

@Service
@Transactional
public class AccountService {
	@Autowired(required = true)
	private AccountRespository accountRepository;

	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	public long totalAccount() {
		return accountRepository.count();
	}

	public boolean validateAccount(String username, String password) {
		return accountRepository.existsByUsernameAndPassword(username, password);
	}

	public Optional<Account> getAccountById(String id) {
		return accountRepository.findById(id);
	}

	public Account savePerson(Account person) {
		return accountRepository.save(person);
	}

	public void deleteAccount(String id) {
		accountRepository.deleteById(id);
	}

	public Account getAcc(String id) {
		return accountRepository.findById(id).get();
	}
}
