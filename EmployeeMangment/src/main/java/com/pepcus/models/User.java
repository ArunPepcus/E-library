package com.pepcus.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  @Column(name = "users_name")
  private String name;
  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date registrationDate;
  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date deactivateOn;

//public User(String name){
//  this.name = name;
//}


//  public User(Integer id) {
//    this.id = id;
//    this.name = name;
//    this.registrationDate = registrationDate;
//    this.deactivateOn = deactivateOn;
//    this.bookList = bookList;
//  }

  @Valid
  @OneToMany(cascade =CascadeType.ALL)
  private List<Book> bookList;
  
}
