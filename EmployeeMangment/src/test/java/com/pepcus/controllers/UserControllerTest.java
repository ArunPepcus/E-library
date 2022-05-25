package com.pepcus.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pepcus.models.Book;
import com.pepcus.models.User;
import com.pepcus.services.UserServices;

@RunWith(MockitoJUnitRunner.class)

class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;
    @MockBean
    private UserServices userServices;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

  @Before
  public void setUp(){
      MockitoAnnotations.initMocks(this);
      this.mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
  }
    @Test
    void getAllUsers() {

    }
    @Test
    void addUsers() throws Exception{
      Book bookList=new Book();
      bookList.setId(1);
      bookList.setAddedOn(new Date());
      bookList.setName("maths");
      bookList.setIssueOn(new Date());
      List<Book> books =new ArrayList<>();
      books.add(bookList);
          User userList=new User();
    userList.setId(1);
    userList.setName("arun");
    userList.setDeactivateOn(new Date());
    userList.setBookList(books);
        String inputInJson = this.mapToJson(userList);

        String URI = "/users";

        Mockito.when(userServices.saveUser(Mockito.any(User.class))).thenReturn(userList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
//    User user=User.builder()
//            .id(1)
//            .name("arun")
//            .deactivateOn(new Date())
//            .registrationDate(new Date())
//            .bookList(null)
//            .build();
//
//        Mockito.when(userServices.saveUser(Mockito.any(User.class))).thenReturn(user);
//        String content=objectWriter.writeValueAsString(user);
//        MockHttpServletRequestBuilder mockB= MockMvcRequestBuilders.post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(content);
//        mockMvc.perform(mockB)
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
//                .andExpect((ResultMatcher) jsonPath("$.name",is("arun")));
    }

    @Test
    void issueBookByUser() {
    }

    @Test
    void deactivate() {
    }

    @Test
    void activate() {
    }

    @Test
    void returnBookFromUser() {
    }
    
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}