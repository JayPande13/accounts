package com.jay.accounts.Repository;

import com.jay.accounts.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    public Optional<Accounts> findByCustomerId(Long customerId);
}
