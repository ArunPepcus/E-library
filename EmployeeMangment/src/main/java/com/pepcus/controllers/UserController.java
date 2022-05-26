package com.pepcus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pepcus.models.Book;
import com.pepcus.models.User;
import com.pepcus.repositorys.UserRepository;
import com.pepcus.services.UserService;

/**
 * @author admin
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserService userService;
  

  /**
   * @param user
   * @return
   */
  @PostMapping
  public ResponseEntity<User> addUsers(@Valid @RequestBody User user) {
    return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
  }
  
  @GetMapping
  public List<User> getAllUsers(List<User> userList) {
    return userService.getAllUsers(userList);
  }


  @PutMapping
  public ResponseEntity<String> issueBookByUser(@Valid @RequestBody List<Book> book, @RequestParam Integer userId) {

    return new ResponseEntity<String>(userService.issueBook(userId, book), HttpStatus.CREATED);
  }

  @PatchMapping("/deactivate")
  public ResponseEntity<User> deactivate(@Valid @RequestParam Integer userId) {
    return new ResponseEntity<User>(userService.deactivateUser(userId), HttpStatus.OK);
  }

  @PatchMapping("/activate")
  public ResponseEntity<User> activate(@Valid @RequestParam Integer userId) {
    return new ResponseEntity<User>(userService.activateUser(userId), HttpStatus.OK);
  }

  /*
   * this handler for return book from user
   */
  @DeleteMapping
  public ResponseEntity<String> returnBookFromUser(@Valid @RequestBody List<Book> book, @RequestParam Integer userId) {
    return new ResponseEntity<String>(userService.returnBooks(userId, book), HttpStatus.OK);
  }

}
