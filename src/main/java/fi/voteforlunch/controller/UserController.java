package fi.voteforlunch.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.voteforlunch.domain.User;
import fi.voteforlunch.service.UserService;

@Controller
public class UserController {
  
  @Resource
  private UserService userService;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public @ResponseBody List<User> getUsers() {
    return userService.findAll();
  }
  
  @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
  public @ResponseBody List<User> saveUser(@RequestBody User userForm) {
    User user = new User();
    user.setUserName(userForm.getUserName());
    userService.add(user);
    return userService.findAll();
  }
}
