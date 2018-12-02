package com.m2i.flexiflex.entity;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public UserEntity findByUuid(String uuid);
}
