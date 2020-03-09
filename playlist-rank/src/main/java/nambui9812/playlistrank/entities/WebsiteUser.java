package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class WebsiteUser {
  private @Id String id;

  private String firstName = "";
  private String lastName = "";

  @NotNull(message = "Email is mandatory.")
  @Email(message = "Email is invalid.")
  @Indexed(unique = true)
  private String email;

  @NotNull(message = "Username is mandatory.")
  @Size(min = 4, max = 20, message = "Username must be from 4 to 20 in size.")
  @Indexed(unique = true)
  private String username;

  @NotNull(message = "Password is mandatory.")
  @Size(min = 10, message = "Password must be at least 10 characters in size.")
  private String password;

  private String dateOfBirth = "";
  private String monthOfBirth = "";
  private String yearOfBirth = "";
  private String street = "";
  private String optionalStreet = "";
  private String city = "";
  private String province = "";
  private String country = "";
  private String zipcode = "";
  private String phone = "";
  private String status = "";

  private ArrayList<String> followingList = new ArrayList<>();
  private ArrayList<String> followerList = new ArrayList<>();

  @NotNull(message = "Account type is mandatory.")
  @NotBlank
  private String accountType = "basic";

  // Default constructor
  public WebsiteUser() {}

  // Custom constructor
  public WebsiteUser(
    String email,
    String username,
    String password
  ) {
    this.email = email;
    this.username = username;
    this.password = password;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
}
