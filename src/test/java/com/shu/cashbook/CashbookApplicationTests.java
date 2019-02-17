package com.shu.cashbook;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CashbookApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users=userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

}

