package com.pepcus.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Data;
@Data
@Entity
@Table(name = "shelves")
public class Shelves {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "shelves_name")
  private String shelves_name;
  
  
  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @Valid
  private List<Book> bookRelation;
  
}
