package com.pepcus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "address")
public class Address {
  @Id
  private long id;
  @NotEmpty
  @Column(name = "addressType")
  @Size(min = 2, message = "name should not be less then two characters")
  private String addressType;

  @Column(name = "street1", nullable = false)
  private String street1;

  @Column(name = "street2", nullable = false)
  private String street2;

  @Column(name = "city")
  @Size(min = 2, message = " city name should not be less then two characters")
  private String city;

  @Column(name = "state")
  @Size(min = 2, message = "name should not be less then two characters")
  private String state;

  @Column(name = "country")
  @Size(min = 2, message = "name should not be less then two characters")
  private String country;

}
