package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import lombok.Data;

import org.springframework.data.annotation.Id;

class User {
  private @Id String id;
  private String firtsName;
  private String lastName;
  private String email;
  private String username;
  private String password;
  private String dateOfBirth;
  private String monthOfBirth;
  private String yearOfBirth;
  private String street;
  private String optionalStreet;
  private String city;
  private String province;
  private String country;
  private String zipcode;
  private String phone;
  private String status;
  private ArrayList<String> followingList;
  private ArrayList<String> followerList;
  private ArrayList<String> followRequests;
  private String accountType;

  // Default constructor
  public User() {}

  // Custom constructor
  public User(
    String firstName,
    String lastName,
    String email,
    String username,
    String password,
    String dateOfBirth,
    String monthOfBirth,
    String yearOfBirth,
    String street,
    String optionalStreet,
    String city,
    String province,
    String country,
    String zipcode,
    String phone,
    String status,
    ArrayList<String> followingList,
    ArrayList<String> followerList,
    ArrayList<String> followRequests,
    String accountType
  ) {
    this.firtsName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.monthOfBirth = monthOfBirth;
    this.yearOfBirth = yearOfBirth;
    this.street = street;
    this.optionalStreet = optionalStreet;
    this.city = city;
    this.province = province;
    this.country = country;
    this.zipcode = zipcode;
    this.phone = phone;
    this.status = status;
    this.followingList = followingList;
    this.followerList = followerList;
    this.followRequests = followRequests;
    this.accountType = accountType;
  }
}