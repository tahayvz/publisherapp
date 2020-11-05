package com.tahayvz.publisherapp.repositories;

import com.tahayvz.publisherapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String Name);

    User findByEmail(String Email);
}
