package fi.voteforlunch.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fi.voteforlunch.domain.User;
import fi.voteforlunch.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Resource
  private UserRepository userRepository;
  
  public User add(User user) {
    return userRepository.add(user);
  }
  
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
