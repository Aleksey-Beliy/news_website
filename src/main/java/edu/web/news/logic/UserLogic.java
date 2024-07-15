package edu.web.news.logic;

import edu.web.news.bean.AuthInfo;
import edu.web.news.bean.RegistrationInfo;
import edu.web.news.bean.User;

public interface UserLogic {

	User checkAuth(AuthInfo authInfo) throws LogicException;

	boolean registrationUser(RegistrationInfo regInfo) throws LogicException;
	
	User findUserByEmail(String email) throws LogicException;
	
	void assignRole(String email, String role) throws LogicException;

	User rememberMe(String token);

}
