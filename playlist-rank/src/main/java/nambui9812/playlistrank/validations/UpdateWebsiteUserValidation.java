package nambui9812.playlistrank.validations;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateWebsiteUserValidation {
  @NotNull(message = "User's id is mandatory")
  private String id;

  private String firstName;
  private String lastName;

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

  public UpdateWebsiteUserValidation(
    String id,
    String firstName,
    String lastName,
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
    String status
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
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
  }

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
}
