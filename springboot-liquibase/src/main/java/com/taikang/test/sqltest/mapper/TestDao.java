package com.taikang.test.sqltest;

import org.jboss.logging.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TestDao {
    void foreachupdate(@Param List<User> userList);
}
