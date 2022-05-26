package com.pepcus.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.pepcus.models.User;
import com.pepcus.repositorys.UserRepository;
import com.pepcus.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @InjectMocks
  UserService userService;

  @Mock
  UserRepository userRepository;

  @Test
  public void testAddUser() {
    User user = new User();
    user.setName("Arun");
    user.setBookList(null);
    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
    User response = userService.addUser(user);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getRegistrationDate());
    Assert.assertEquals(response.getName(), user.getName());
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

    Mockito.when(userRepository.findAll()).thenReturn(userList);
    List<User> response = userService.getAllUsers(userList);
    System.out.println(response);
    Assert.assertNotNull(response);
    assertThat(response).isEqualTo(userList);

  }

  @Test
  public void deactivateUser() {
    User user=new User(1,"arun",new Date(),new Date(),null);
    Assertions.assertThrows(IllegalArgumentException.class,
            ()->  userRepository.existsById(user.getId()));

  }
}
