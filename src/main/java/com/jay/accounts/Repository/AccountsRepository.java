package com.jay.accounts.Repository;

import com.jay.accounts.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    public Optional<Accounts> findByCustomerId(Long customerId);

    @Modifying // this tells spring that this method will be modifying data in the table hence,
    @Transactional // please do it with transaction and only commit if there is no error
    public void deleteByCustomerId(Long customerId);
}
