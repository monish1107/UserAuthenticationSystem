package com.altimetrik.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.exception.RecordNotFoundException;
import com.altimetrik.model.UserEntity;
import com.altimetrik.repository.UserRepository;

 
@Service
public class UserService {
     
    @Autowired
    UserRepository repository;
     
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> userList = repository.findAll();
         
        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    public UserEntity getUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);
         
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }
     
    public UserEntity createOrUpdateUser(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(entity.getId());
         
        if(user.isPresent())
        {
            UserEntity newEntity = user.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);
         
        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }
}