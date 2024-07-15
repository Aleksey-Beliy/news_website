package edu.web.news.dao;

import edu.web.news.dao.impl.NewsDaoImpl;
import edu.web.news.dao.impl.UserDaoImpl;

public class DaoProvider {

	private static final DaoProvider INSTANCE = new DaoProvider();

	private DaoProvider() {
	}

	private NewsDao newsDao = new NewsDaoImpl();
	private UserDao userDao = new UserDaoImpl();

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public static DaoProvider getInstance() {
		return INSTANCE;
	}
}
