package com.pepcus.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "book_name")
  @NotEmpty
  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date addedOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date modifiedOn=new Date();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date issueOn;
  
}
