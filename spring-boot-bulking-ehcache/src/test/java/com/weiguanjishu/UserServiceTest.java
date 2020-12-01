package com.weiguanjishu;

import com.weiguanjishu.domain.model.User;
import com.weiguanjishu.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 微信公众号：微观技术
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Resource
    private UserService userService;


    @Test
    public void test1_getUserById() {
        // 第一次查询，缓存预热
        User user = userService.getUserById(11L);
        System.out.println(user);

        // 第二次查询，命中缓存
        user = userService.getUserById(11L);
        System.out.println(user);
    }


    @Test
    public void test2_updateUser() {
        // 第一次更新，同步更新缓存
        User user = User.builder().id(11L).userName("雪糕").address("杭州").age(2).build();
        User newUser = userService.updateUser(user);
        System.out.println(newUser);

        // 第二次查询，命中缓存
        user = userService.getUserById(11L);
        System.out.println(user);
    }


    @Test
    public void test3_deleteById() {
        // 第一次查询
        User user = userService.getUserById(11L);
        System.out.println(user);


        // 删除，同步删除缓存
        userService.deleteById(11L);

        // 第二次查询
        user = userService.getUserById(11L);
        System.out.println(user);
    }
}
