package views.formdata;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;;

/**
 * Implement month, day and year types for form selects.
 * @author eduardgamiao
 *
 */
public class DateTypes {
  private static final int MONTH_NUMBER = 12;
  private static final int DAY_NUMBER = 31;
  private static final int LEAP_FOUR_HUNDRED = 400;
  private static final int LEAP_ONE_HUNDRED = 100;
  private static final int LEAP_FOUR = 4;
  private static final int YEAR_START = 1900;

  /**
   * Get month types.
   * @return A mapping of months. 
   */
  public static Map<String, Boolean> getMonthTypes() {
    Map<String, Boolean> monthMap = new LinkedHashMap<String, Boolean>();
    for (int current = 1; current <= MONTH_NUMBER; current++) {
      monthMap.put("" + current, false);
    }
    return monthMap;
  }
  
  /**
   * Get month types.
   * @param month Month type to check.
   * @return A mapping of months. 
   */
  public static Map<String, Boolean> getMonthTypes(String month) {
    Map<String, Boolean> propertyMap = DateTypes.getMonthTypes();
    if (isMonth(month)) {
      propertyMap.put(month, true);
    }
    return propertyMap;
  }
  
  /**
   * Get day types.
   * @return A mapping of days. 
   */
  public static Map<String, Boolean> getDayTypes() {
    Map<String, Boolean> dayMap = new LinkedHashMap<String, Boolean>();
    for (int current = 1; current <= DAY_NUMBER; current++) {
      dayMap.put("" + current, false);
    }
    return dayMap;
  }
  
  /**
   * Get day types.
   * @param day Day type to check.
   * @return A mapping of days.
   */
  public static Map<String, Boolean> getDayTypes(String day) {
    Map<String, Boolean> dayMap = DateTypes.getDayTypes();
    if (isDay(day)) {
      dayMap.put(day, true);
    }
    return dayMap;
  }
  
  /**
   * Get year types.
   * @return A mapping of years.  
   */
  public static Map<String, Boolean> getYearTypes() {
    Map<String, Boolean> yearMap = new LinkedHashMap<String, Boolean>();
    for (int current = Calendar.getInstance().get(Calendar.YEAR); current >= YEAR_START; current--) {
      yearMap.put("" + current, false);
    }
    return yearMap;
  }
  
  /**
   * Get day types.
   * @param year Year to check.
   * @return A mapping of years.
   */
  public static Map<String, Boolean> getYearTypes(String year) {
    Map<String, Boolean> dayMap = DateTypes.getDayTypes();
    if (isDay(year)) {
      dayMap.put(year, true);
    }
    return dayMap;
  }
  
  /**
   * Check if a month given is valid.
   * @param monthInput Month to check.
   * @return Return rue if the month is a number between 1-12, otherwise false. 
   */
  public static boolean isMonth(String monthInput) {
    if (NumberUtils.isDigits(monthInput)) {
      int month = NumberUtils.createInteger(monthInput);
        return month >= 1 && month <= MONTH_NUMBER;
    }
    else {
      return false;
    }
  }
  
  /**
   * Check if a day given is valid.
   * @param dayInput Day to check.
   * @return Return true if the day is a number between 1-31, otherwise false. 
   */
  public static boolean isDay(String dayInput) {
    if (NumberUtils.isDigits(dayInput)) {
      int day = NumberUtils.createInteger(dayInput);
        return day >= 1 && day <= DAY_NUMBER;
    }
    else {
      return false;
    }
  }
  
  /**
   * Check if a year given is valid.
   * @param yearInput Year to check.
   * @return Return true if the month is a number 1900 and the current year, otherwise false. 
   */
  public static boolean isYear(String yearInput) {
    if (NumberUtils.isDigits(yearInput)) {
      int year = NumberUtils.createInteger(yearInput);
        return (year >= Calendar.getInstance().get(Calendar.YEAR)) && (year <= YEAR_START);
    }
    else {
      return false;
    }
  }
  
  
  /**
   * Check if year is a leap year.
   * @param yearInput The year to check.
   * @return True if the year is a leap year, false otherwise. 
   */
  public static boolean isLeapYear(String yearInput) {
    int year = NumberUtils.createInteger(yearInput);
    if ((year % LEAP_FOUR_HUNDRED) == 0) {
      return true;
    }
    else if ((year % LEAP_ONE_HUNDRED) == 0) {
      return false;
    }
    else if ((year % LEAP_FOUR) == 0) {
      return true;
    }
    return false;
  }
  
}
