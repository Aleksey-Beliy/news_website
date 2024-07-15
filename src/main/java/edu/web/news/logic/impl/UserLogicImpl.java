package edu.web.news.logic.impl;

import edu.web.news.bean.AuthInfo;
import edu.web.news.bean.RegistrationInfo;
import edu.web.news.bean.User;
import edu.web.news.dao.DaoException;
import edu.web.news.dao.DaoProvider;
import edu.web.news.dao.UserDao;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.UserLogic;

public class UserLogicImpl implements UserLogic {

	private UserDao dao = DaoProvider.getInstance().getUserDao();

	@Override
	public User checkAuth(AuthInfo authInfo) throws LogicException {

		User user = null;
		try {
			user = dao.checkAuth(authInfo);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return user;
	}

	@Override
	public boolean registrationUser(RegistrationInfo regInfo) throws LogicException {
		try {
			if (dao.userExists(regInfo.getEmail())) {

				return true;
			} else {
				dao.registrationUser(regInfo);
				return false;
			}

		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public User rememberMe(String token) {
		
		return null;
	}

	@Override
	public User findUserByEmail(String email) throws LogicException {
		User user;
		try {
			user = dao.findUserByEmail(email);
			return user;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void assignRole(String email, String role) throws LogicException {
	try {
		dao.assignRole(email, role);
	}catch (DaoException e) {
		throw new LogicException(e);
	}
		
	}

}
