package com.altimetrik.springbatch.excelparsing;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.altimetrik.model.UserEntity;

public class UserExcelRowMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(RowSet rowSet) throws Exception {
        UserEntity user = new UserEntity();

        user.setId((long) 0);
        user.setFirstName(rowSet.getColumnValue(0));
        user.setLastName(rowSet.getColumnValue(1));
        user.setEmail(rowSet.getColumnValue(2));

        return user;
    }
}
