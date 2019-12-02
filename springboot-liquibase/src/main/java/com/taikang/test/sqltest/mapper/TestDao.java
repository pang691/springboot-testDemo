package com.taikang.test.sqltest.mapper;

import com.taikang.test.sqltest.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TestDao {
    void foreachupdate(@Param("userList") List<User> userList);
}
