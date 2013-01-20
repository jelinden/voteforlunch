package fi.voteforlunch.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import org.mockito.runners.MockitoJUnitRunner;

import fi.voteforlunch.domain.User;
import fi.voteforlunch.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  @InjectMocks
  private UserController userController = new UserController();

  @Mock
  private UserService userService;

  @Test
  public void testGetUsers() throws Exception {
    when(userService.findAll()).thenReturn(userListForTest());
    assertResult(userController.getUsers());
  }
  
  @Test
  public void testSaveUser() throws Exception {
    when(userService.add(any(User.class))).thenReturn(getUser());
    when(userService.findAll()).thenReturn(userListForTest());
    assertResult(userController.saveUser(getUser()));
  }
  
  private void assertResult(List<User> result) {
    assertTrue(result.get(0).getUserName().equals("testName"));
  }
  
  private List<User> userListForTest() {
    List<User> users = new ArrayList<User>();
    users.add(getUser());
    return users;
  }
  
  private User getUser() {
    User user = new User();
    user.setUserId(1234L);
    user.setUserName("testName");
    return user;
  }
}