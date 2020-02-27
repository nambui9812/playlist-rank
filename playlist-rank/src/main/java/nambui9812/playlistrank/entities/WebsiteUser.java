package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class WebsiteUser {
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
  public WebsiteUser() {}

  // Custom constructor
  public WebsiteUser(
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

  public void setFirstName(String firstName) {
    this.firtsName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getMonthOfBirth() {
    return monthOfBirth;
  }

  public void setMonthOfBirth(String monthOfBirth) {
    this.monthOfBirth = monthOfBirth;
  }

  public String getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(String yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getOptionalStreet() {
    return optionalStreet;
  }

  public void setOptionalStreet(String optionalStreet) {
    this.optionalStreet = optionalStreet;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ArrayList<String> getFollowingList() {
    return followingList;
  }

  public void setFollowingList(ArrayList<String> followingList) {
    this.followingList = followingList;
  }

  public ArrayList<String> getFollowerList() {
    return followerList;
  }

  public void setFollowerList(ArrayList<String> followerList) {
    this.followerList = followerList;
  }

  public ArrayList<String> getFollowRequests() {
    return followRequests;
  }

  public void setFollowRequests(ArrayList<String> followRequests) {
    this.followRequests = followRequests;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
}
