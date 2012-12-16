package fi.voteforlunch.service;

import java.util.List;

import fi.voteforlunch.domain.User;

public interface UserService {
  public User add(User user);
  public List<User> findAll();
}
