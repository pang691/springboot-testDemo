package com.taikang.test.sqltest;

import com.taikang.test.sqltest.mapper.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestDao testDao;
    @Override
    public void foreachupdate() {
        List<User> userList = new ArrayList<>();
        User user = new User("1","2");
        User user2 = new User("22","3");
        userList.add(user);
        userList.add(user2);
        testDao.foreachupdate(userList);
    }
}
