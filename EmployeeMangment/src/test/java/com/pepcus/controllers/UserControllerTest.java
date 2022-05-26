package com.pepcus.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.pepcus.models.User;
import com.pepcus.services.UserService;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  @InjectMocks
  UserController userController;

  @Mock
  UserService userService;

  @Test
  public void testAddUser() {
    User user = new User();
    user.setName("Arun");
    user.setBookList(null);
    Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
    ResponseEntity<User> response = userController.addUsers(user);
    Assert.assertNotNull(response);
    Assert.assertEquals(response.getBody().getName(), user.getName());
  }

  @Test
  public void testGetAllUsers() {
    User user1 = new User();
    user1.setId(1);
    user1.setName("Martin Bingel");
    user1.setDeactivateOn(new Date());
    user1.setRegistrationDate(new Date());
    user1.setBookList(null);

    User user2 = new User();
    user2.setId(2);
    user2.setName("Arun soni");
    user2.setDeactivateOn(new Date());
    user2.setRegistrationDate(new Date());
    user2.setBookList(null);
    List<User> userList = new ArrayList<>();
    userList.add(user1);
    userList.add(user2);

    Mockito.when(userService.getAllUsers(userList)).thenReturn(userList);
    List<User> response = userController.getAllUsers(userList);
    System.out.println(response);
    Assert.assertNotNull(response);
    assertThat(response).isEqualTo(user1);

  }

  @Test
  public void deactivate() {
    User user=new User(1,"arun",new Date(),new Date(),null);
    Mockito.when(userService.deactivateUser(1)).thenReturn(user);
    ResponseEntity<User> response = userController.deactivate(user.getId());
    Assert.assertNotNull(response);
    Assert.assertEquals(response.getBody().getDeactivateOn(),user.getDeactivateOn());
  }
  @Test
  public void activate() {
    User user=new User(1,"arun",new Date(),new Date(),null);
    Mockito.when(userService.activateUser(1)).thenReturn(user);
    ResponseEntity<User> response = userController.deactivate(user.getId());
    Assert.assertNotNull(response);
    Assert.assertNotNull(user.getDeactivateOn());
  }
}
