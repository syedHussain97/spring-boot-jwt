package com.hussain.securewebcrawler.dao;

import com.hussain.securewebcrawler.model.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<DAOUser, Integer> {

    DAOUser findByUsername(String username);

}