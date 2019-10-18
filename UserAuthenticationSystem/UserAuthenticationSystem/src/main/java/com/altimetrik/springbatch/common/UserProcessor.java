package com.altimetrik.springbatch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.altimetrik.model.UserEntity;


public class UserProcessor implements ItemProcessor<UserEntity, UserEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProcessor.class);

    @Override
    public UserEntity process(UserEntity users) throws Exception {
        LOGGER.info("Processing user information: {}", users);
        return users;
    }
}
