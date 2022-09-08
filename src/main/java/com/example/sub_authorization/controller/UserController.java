package com.example.sub_authorization.controller;

import com.example.sub_authorization.Exception.UserNotFoundException;
import com.example.sub_authorization.entity.User;
import com.example.sub_authorization.responseBody.UserResponseMsg;
import com.example.sub_authorization.service.UserService;
import com.example.sub_authorization.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/user/register")
    @ResponseBody
    public UserResponseMsg addUser(@RequestBody User user){
        userService.addUser(user);
        UserResponseMsg msg = new UserResponseMsg();
        msg.setStatus("success");
        msg.setMsg("successfully registered");
        return msg;
    }

    @RequestMapping(path="/user/toLogin", method = RequestMethod.GET)
    @ResponseBody
    public UserResponseMsg toLogin(){
        UserResponseMsg msg = new UserResponseMsg();
        msg.setStatus("success");
        msg.setMsg("redirecting to login page");
        return msg;
    }

    @RequestMapping(path="/user/doLogin", method = RequestMethod.POST)
    public String doLogin (@RequestBody User user,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception{
        User verifiedUser = userService.getUserByUsername(user.getUsername());
        try{
            if(verifiedUser == null){
                response.setHeader("LoginFailDetail", "wrong username");
                throw new UserNotFoundException("user does not in database");
            }
        }catch(UserNotFoundException e){
            throw e;
        }

        if(!verifiedUser.getPassword().equals(user.getPassword())){
            response.setHeader("LoginFailDetail", "wrong password");
            return "redirect:http://localhost:8081/user/toLogin";
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        String token = JWTUtils.getToken(map);
        Cookie cookie = new Cookie("JWTToken", token);
        cookie.setPath("/");
        /*cookie.setDomain("localhost");*/
        response.addCookie(cookie);
        return "redirect:http://localhost:8080/book/listBook";
    }

    @RequestMapping(path="/helloworld")
    @ResponseBody
    public String helloWorld(){
        return "helloworld";
    }
}
