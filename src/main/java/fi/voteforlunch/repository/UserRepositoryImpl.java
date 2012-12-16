package fi.voteforlunch.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import fi.voteforlunch.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Resource
  private RedisTemplate<String, User> userTemplate;
  
  @Resource
  private RedisAtomicLong userIdCounter;
  
  public User add(User user) {
    persist(user);
    userTemplate.opsForSet().add("users", user);
    return user;
  }
  
  public List<User> findAll() {
    Collection<User> users = userTemplate.opsForSet().members("users");
    List<User> userList = new ArrayList<User>(users);
    Collections.sort(userList, new Comparator<User>() {
        public int compare(User a, User b) {
            return a.getUserName().toLowerCase().compareTo(b.getUserName().toLowerCase());
        }        
    });
    return userList;
  }
  
  private void persist(User user) {
    Long id = user.getUserId();
    if(id == null) {
      id = userIdCounter.incrementAndGet();
      user.setUserId(id);
    }
    String key = buildKey(user.getUserId());
    userTemplate.opsForValue().set(key, user);
  }
  
  private String buildKey(Long userId) {
    return "user" + userId;
  }
  
}
