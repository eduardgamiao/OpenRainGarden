package models;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory storage of UserInfo
 * @author Kyle
 *
 */
public class UserInfoDB {
	private static Map<String, UserInfo> userDB = new HashMap<String, UserInfo>();
	
	/**
	 * Adds given user info to the userDB
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public static void addUserInfo(String firstName, String lastName, String email, String telephone, String password) {
		userDB.put(email, new UserInfo(firstName, lastName, email, telephone, password));
	}
	/**
	 * Adds given user info to the userDB
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public static void addAdmin(String firstName, String lastName, String email, String telephone, String password) {
		userDB.put(email, new UserInfo(firstName, lastName, email, telephone, password, true));
	}
	
	/**
	 * Returns whether given email is in the userDB
	 * @param email
	 * @return
	 */
	public static boolean isUser(String email) {
		return userDB.containsKey(email);
	}
	
	/**
	 * Returns the UserInfo based on the given email
	 * @param email
	 * @return
	 */
	public static UserInfo getUser(String email) {
		if (email != null)
			return userDB.get(email);
		else
			return null;
	}
	
	/**
	 * Returns whether the given email and password pair are valid
	 * @param email
	 * @param password
	 * @return
	 */
	public static boolean isValid(String email, String password) {
		if (email != null && password != null) {
			if (isUser(email) == true) {
				if (getUser(email).getPassword().equals(password) == true)
					return true;
			}
		}
		return false;
	}
}
