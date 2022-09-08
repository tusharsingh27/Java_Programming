package com.bank.repository;

import com.bank.entity.Accounts;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Integer> {

    @Query("select amount from Accounts where aid = ?1")
    public Integer findAmountByAid(Integer aid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Accounts set amount = amount+?2 where aid=?1")
    public void saveAmountByAid(Integer aid, Integer amount);

}