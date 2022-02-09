package com.truper.springboot.mongo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.truper.springboot.mongo.documents.UserEntity;

public interface UserEntityRepository extends MongoRepository<UserEntity, String> {

	Optional<UserEntity> findByUsername(String username);

}
