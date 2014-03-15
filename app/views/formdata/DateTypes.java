package views.formdata;

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
    Map<String, Boolean> propertyMap = DateTypes.getDayTypes();
    if (isDay(day)) {
      propertyMap.put(day, true);
    }
    return propertyMap;
  }
  
  /**
   * Check if a month given is valid.
   * @param monthInput Month to check.
   * @return Return rue if the month is a number 1-12, otherwise false. 
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
   * @return Return rue if the month is a number 1-31, otherwise false. 
   */
  public static boolean isDay(String dayInput) {
    if (NumberUtils.isDigits(dayInput)) {
      int month = NumberUtils.createInteger(dayInput);
        return month >= 1 && month <= DAY_NUMBER;
    }
    else {
      return false;
    }
  }
  
}
