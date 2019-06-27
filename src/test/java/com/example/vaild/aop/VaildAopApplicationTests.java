package com.example.vaild.aop;

import com.example.vaild.aop.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VaildAopApplicationTests {

    @Test
    public void contextLoads() {
        User user=new User();

        user.setId(1);
        user.setName("'");
        user.setPassword("哈哈");
    }

}
