package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In memory storage of UserInfo
 * @author Kyle
 *
 */
public class UserInfoDB {
	
	/**
	 * Adds given user info to the userDB
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public static void addUserInfo(String firstName, String lastName, String email, String telephone, String password,
	    boolean isAdmin) {
		UserInfo userInfo = new UserInfo(firstName, lastName, email, telephone, password, isAdmin);
		userInfo.save();
	}
	
	/**
	 * Returns whether given email is in the userDB
	 * @param email
	 * @return
	 */
	public static boolean isUser(String email) {
		return (UserInfo.find().where().eq("email", email) != null);
	}
	
	/**
	 * Returns the UserInfo based on the given email
	 * @param email
	 * @return
	 */
	public static UserInfo getUser(String email) {
		return UserInfo.find().where().eq("email", email).findUnique();
	}
	
	/**
	 * Return list of all users.
	 * @return A list of all users.
	 */
	public static List<UserInfo> getUsers() {
	  return UserInfo.find().all();
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
