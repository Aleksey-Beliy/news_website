package edu.web.news.dao;

import edu.web.news.bean.AuthInfo;
import edu.web.news.bean.RegistrationInfo;
import edu.web.news.bean.User;

public interface UserDao {

	 User checkAuth(AuthInfo authInfo) throws DaoException;

	 void registrationUser(RegistrationInfo regInfo) throws DaoException;

	 boolean userExists(String email) throws DaoException;

	 void assignRole(String email, String role) throws DaoException;

	 User findUserByEmail(String email) throws DaoException;
}
