package com.pepcus.services;

import java.util.Date;
import java.util.List;

import com.pepcus.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.exception.ResourceNotFoundException;
import com.pepcus.models.Book;
import com.pepcus.models.User;
import com.pepcus.repositorys.BookRepository;
import com.pepcus.repositorys.UserRepository;

@Service
public class UserServices {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BookRepository bookRepository;

  /*
   * method for save user in db
   */
  public User saveUser(User user) {
    user.setRegistrationDate(new Date());
    userRepository.save(user);

    return user;
  }
  /*
  get all user from db
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public String issueBook(Integer userId, List<Book> books) {
    String result=null;
    User existingUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Please register yourself first", "Id", userId));
    List<Book> existingBook = existingUser.getBookList();
    if (existingUser.getDeactivateOn() == null) {
      for (Book book : books) {

        if (bookRepository.existsById(book.getId())) {
          Book bookcontain = bookRepository.getById(book.getId());
          if (bookcontain.getIssueOn() != null) {
            result="this " + bookcontain.getName() + " book is already issued by another user....... " ;
          } else {
            bookcontain.setIssueOn(new Date());
            existingBook.add(bookcontain);
          }
          existingUser.setBookList(existingBook);
          } else {
            result="this book in not available in the library";
          }
        }
      }else {
      result="Please register yourself first then issue book ";
    }

   userRepository.save(existingUser);
   return result;
  }


  /*
   * method for deactivated user
   */
  public User deactivateUser(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    List<Book> existBook = user.getBookList();

    if (existBook.isEmpty()) {
      if (user.getDeactivateOn() == null) {
        user.setDeactivateOn(new Date());
      }

    } else {
      throw new ResourceNotFoundException("You are issued book from library please return then decative", "id", userId);
    }

    return userRepository.save(user);
  }

  /*
   * method for activate user
   */
  public User activateUser(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    if (user.getDeactivateOn() != null) {
      user.setDeactivateOn(null);
    } else {
      throw new ResourceNotFoundException("You are already activated", "id", user);
    }
    return userRepository.save(user);
  }

  public User returnBooks(Integer userId, List<Book> books) {
    // check whether a employee exist in a DB or not
    User existingUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));

    if (existingUser.getDeactivateOn() == null) {

      List<Book> existingBook = existingUser.getBookList();
      List<Book> bookList = bookRepository.findAll();
      for (Book book : books) {
        for (Book tablesBook : bookList) {
          if (tablesBook.getId().equals(book.getId())) {
            if (existingBook.contains(book)) {
              System.out.println("Your are already issued book plz return....... " + book.getName());
            } else {

              existingBook.clear();
            }
            existingUser.setBookList(existingBook);
          } else {
            System.out.println("please activationOn" + book.getId());
          }
        }

      }

    }
    return userRepository.save(existingUser);

  }
}