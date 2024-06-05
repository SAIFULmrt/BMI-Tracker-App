package utilities;

import java.time.LocalDate;

import exceptions.*;

public class DataValidator {
  private static final int VALID_NAME_LENGTH  = 3;
  
  public static boolean isValidData(String firstName, 
                                    String lastName,
                                    LocalDate date,
                                    String weight, 
                                    String height, 
                                    String pathToProfilePicture) throws Exception {
    if (!isNameNotNull(firstName)) {
      throw new InvalidNameException(firstName + " is not valid. Names must have at least " + VALID_NAME_LENGTH + " characters.");
    }
    
    if (!isNameNotNull(lastName)) {
      throw new InvalidNameException(lastName + " is not valid. Names must have at least " + + VALID_NAME_LENGTH + " characters.");
    }
    
    if (!isDouble(weight)) {
      throw new InvalidHeightOrWeightException(weight + " kg is an invalid value for weight.");
    }
    
    if (!isDouble(height)) {
      throw new InvalidHeightOrWeightException(height + " cm is an invalid value for height.");
    }
    
    if (isNull(pathToProfilePicture)) {
      throw new NullException("Invalid path to profile picture. Maybe you didn't select a path?");
    }
    
    if (isNull(date)) {
      throw new NullException("Date must be chosen. Probably you did not choose a valid date of birth?");
    }
    
    return true;
  }
  
  public static <T> boolean isNull(T data) { 
    return data == null;
  }
  
  public static boolean isNameNotNull(String value) {
    return !isNull(value) && value.length() >= 3;
  }
  
  public static boolean isNotNullOrEmptyString(String string) {
    return !isNull(string) && string.length() > 0;
  }
  
  public static boolean isDouble(String supposedlyNumericData) {
    try {
      double data = Double.parseDouble(supposedlyNumericData);
      
      if (data <= 0) {
        return false;
      }
    } catch (Exception parseException) {
      return false;
    }
    
    return true;
  }
}
