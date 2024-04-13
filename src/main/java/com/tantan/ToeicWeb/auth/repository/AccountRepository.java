package com.tantan.ToeicWeb.auth.repository;

import com.tantan.ToeicWeb.auth.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String username);
}
