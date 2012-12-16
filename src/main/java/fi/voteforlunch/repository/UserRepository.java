package fi.voteforlunch.repository;

import java.util.List;

import fi.voteforlunch.domain.User;

public interface UserRepository {
  public User add(User user);
  public List<User> findAll();
}
