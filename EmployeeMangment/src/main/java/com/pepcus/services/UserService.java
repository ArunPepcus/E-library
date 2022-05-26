package com.pepcus.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.exception.ResourceNotFoundException;
import com.pepcus.models.Book;
import com.pepcus.models.User;
import com.pepcus.repositorys.BookRepository;
import com.pepcus.repositorys.UserRepository;

/**
 * @author admin
 *
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  /**
   * @param user
   * @return
   */
  public User addUser(User user) {
    user.setRegistrationDate(new Date());
    return userRepository.save(user);
  }

  /**
   * @return
   * @param userList
   */
  public List<User> getAllUsers(List<User> userList) {
    return userRepository.findAll();
  }

  /**
   * @param userId
   * @param books
   * @return
   */
  public String issueBook(Integer userId, List<Book> books) {
    String result = null;
    User existingUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Please register yourself first", "Id", userId));
    List<Book> existingBook = existingUser.getBookList();
    if (existingUser.getDeactivateOn() == null) {
      for (Book book : books) {
        if (bookRepository.existsById(book.getId())) {
          Book bookcontain = bookRepository.getById(book.getId());
          if (bookcontain.getIssueOn() != null) {
            result = "this " + bookcontain.getName() + " book is already issued by another user....... ";
          } else {
            bookcontain.setIssueOn(new Date());
            existingBook.add(bookcontain);
          }
          existingUser.setBookList(existingBook);
        } else {
          result = "this book in not available in the library";
        }
      }
    } else {
      result = "Please register yourself first then issue book ";
    }

    userRepository.save(existingUser);
    return result;
  }

  /**
   * @param userId
   * @param books
   * @return
   */
  public String returnBooks(Integer userId, List<Book> books) {
    String msg = null;
    // check whether a employee exist in a DB or not
    User existingUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Pleases Register yourself first", "Id", userId));
    List<Book> existingBook = existingUser.getBookList();
    // List<Book> bookList = bookRepository.findAll();
    if (existingUser.getDeactivateOn() == null) {
      for (Book book : books) {
        if (bookRepository.existsById(book.getId())) {
          Book book1 = bookRepository.getById(book.getId());
          if (book1.getIssueOn() == null) {
            msg = "Your are not issue book from library  first addOn then return....... ";
          } else {
            book1.setIssueOn(null);
            existingBook.remove(book1);
          }
          existingUser.setBookList(existingBook);
        } else {
          msg = "this book in not available in the library";
        }
      }

    } else {
      msg = "first active then return book";
    }
    userRepository.save(existingUser);
    return msg;
  }

  /**
   * @param userId
   * @return
   */
  public User deactivateUser(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    List<Book> existBook = user.getBookList();

    if (existBook.isEmpty()) {
      if (user.getDeactivateOn() == null) {
        user.setDeactivateOn(new Date());
      }
    } else {
      throw new ResourceNotFoundException("You are issued book from library please return then deactivate", "id", userId);
    }

    return userRepository.save(user);
  }

  /**
   * @param userId
   * @return
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

}