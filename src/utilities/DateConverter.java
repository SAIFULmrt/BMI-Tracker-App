package utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
  // converts LocalDate object to Date object
  public static Date convertLocalDateToDate(LocalDate localDate) {
    Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    return Date.from(instant);
  }

  // converts Date to LocalDate object
  public static LocalDate convertDateToLocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
