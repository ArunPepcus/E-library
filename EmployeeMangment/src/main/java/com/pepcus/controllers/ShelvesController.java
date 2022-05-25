package com.pepcus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.models.Book;
import com.pepcus.models.Shelves;
import com.pepcus.repositorys.ShelvesRepository;
import com.pepcus.services.ShelvesService;

@RestController
@RequestMapping("/shelves")
public class ShelvesController {
  @Autowired
  private ShelvesRepository shelvesRepository;
  @Autowired
  private ShelvesService shelvesService;

  @PostMapping
  public ResponseEntity<Shelves> saveShelves(@Valid @RequestBody Shelves shelves) {

    return new ResponseEntity<Shelves>(shelvesService.saveShelves(shelves), HttpStatus.CREATED);
  }

  /*
   * this handler for add book in shelf
   */
  @PatchMapping("/{shelvesId}")
  public ResponseEntity<Shelves> addBook(@RequestBody List<Book> book, @PathVariable("shelvesId") Integer shelvesId) {

    return new ResponseEntity(shelvesService.addBookInShelves(book, shelvesId), HttpStatus.ACCEPTED);
  }
}
