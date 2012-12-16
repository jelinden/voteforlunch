package fi.voteforlunch.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

  private Long userId;
  
  private String userName;
  
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", userName=" + userName + "]";
  }
}