package com.capgemini.wallet_services.repo;

import com.capgemini.wallet_services.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
