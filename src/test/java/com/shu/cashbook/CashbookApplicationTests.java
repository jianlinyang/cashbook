package com.shu.cashbook;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CashbookApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Resource
    DataSource dataSource;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSelect() {
        List<User> users=userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }
    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void test03(){
        stringRedisTemplate.opsForValue().append("msg","hello");
    }

}

