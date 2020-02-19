package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class User {
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

  // All get methods
  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firtsName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public String getMonthOfBirth() {
    return monthOfBirth;
  }

  public String getYearOfBirth() {
    return yearOfBirth;
  }

  public String getStreet() {
    return street;
  }

  public String getOptionalStreet() {
    return optionalStreet;
  }

  public String getCity() {
    return city;
  }

  public String getProvince() {
    return province;
  }

  public String getCountry() {
    return country;
  }

  public String getZipcode() {
    return zipcode;
  }

  public String getPhone() {
    return phone;
  }

  public String getStatus() {
    return status;
  }

  public ArrayList<String> getFollowingList() {
    return followingList;
  }

  public ArrayList<String> getFollowerList() {
    return followerList;
  }

  public ArrayList<String> getFollowRequests() {
    return followRequests;
  }

  public String getAccountType() {
    return accountType;
  }
}
