package dataclass;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import utilities.DateConverter;

public class Person implements Serializable {

  private static final long serialVersionUID = -6820912904576758442L;

  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private double heightInCm;
  private double weight;
  private String pathToProfilePicture;

  private int age;
  private String bmiStatus;
  private double bmi;

  public Person(String firstName, String lastName, Date dateOfBirth, double heightInCm, double weight,
      String pathToProfilePicture) {
    this.firstName = firstName;
    this.lastName = lastName;

    this.dateOfBirth = dateOfBirth;
    this.setAge(Period.between(DateConverter.convertDateToLocalDate(dateOfBirth), LocalDate.now()).getYears());

    this.heightInCm = heightInCm;
    this.weight = weight;
    this.bmi = this.calculateBMI();
    this.bmiStatus = this.generateBmiStatus();

    this.pathToProfilePicture = pathToProfilePicture;
  }

  // taken from
  // https://www.ncbi.nlm.nih.gov/books/NBK278991/table/diet-treatment-obes.table4clas/
  private String generateBmiStatus() {
    if (this.bmi < 18.5) {
      return "Underweight";
    } else if (this.bmi >= 18.5 && this.bmi < 25) {
      return "Normal weight";
    } else if (this.bmi >= 25 && this.bmi < 30) {
      return "Overweight";
    } else if (this.bmi >= 30 && this.bmi < 35) {
      return "Obesity Class 1";
    } else if (this.bmi >= 35 && this.bmi < 40) {
      return "Obesity Class 2";
    } else {
      return "Extreme Obesity Class 3";
    }
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

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public double getHeight() {
    return heightInCm;
  }

  public void setHeight(double height) {
    this.heightInCm = height;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getPathToProfilePicture() {
    return pathToProfilePicture;
  }

  public void setPathToProfilePicture(String pathToProfilePicture) {
    this.pathToProfilePicture = pathToProfilePicture;
  }

  public double getBMI() {
    return this.bmi;
  }

  private double calculateBMI() {
    double bmi = this.weight / Math.pow((this.heightInCm / 100), 2);
    return bmi;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getBmiStatus() {
    return bmiStatus;
  }

  @Override
  public String toString() {
    return this.firstName + " " + this.lastName + ", " + "BMI: " + this.bmi;
  }
}
