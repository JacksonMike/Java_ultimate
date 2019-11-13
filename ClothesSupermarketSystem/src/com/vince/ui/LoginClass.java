package com.vince.ui;

import com.vince.bean.User;
import com.vince.service.UserService;
import com.vince.utils.BusinessException;


public class LoginClass extends com.vince.framework.BaseClass {
    private UserService userService;
    public LoginClass(){
        userService = (UserService) beanFactory.getBean("userService");
    }

    public void login()throws BusinessException{
        println(getString("input.username"));
        String username = input.nextLine();//接收控制台输入username
        println(getString("input.password"));
        String password = input.nextLine();//接收控制台输入password

//        UserService userService = new UserServiceImpl();
        User user = userService.login(username,password);//调用登录业务方法

        if(user!=null){
            currUser = user;
        }else{
            throw new BusinessException("login.error");
        }
    }
}
