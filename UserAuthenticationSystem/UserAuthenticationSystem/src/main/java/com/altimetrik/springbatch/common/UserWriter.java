package com.altimetrik.springbatch.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.altimetrik.exception.RecordNotFoundException;
import com.altimetrik.model.UserEntity;
import com.altimetrik.service.UserService;

/**
 * This custom {@code ItemWriter} writes the information of the student to
 * the log.
 */
public class UserWriter implements ItemWriter<UserEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWriter.class);

    @Autowired
	UserService service;
    
    @Override
    public void write(List<? extends UserEntity> users) throws Exception {
        LOGGER.info("Received the information of {} users", users.size());

        users.forEach(user -> {
			try {
				user.setId((long)0);
				service.createOrUpdateUser(user);
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}
