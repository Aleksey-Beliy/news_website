package edu.web.news.logic;

import edu.web.news.logic.impl.NewsLogicImpl;
import edu.web.news.logic.impl.UserLogicImpl;

public final class LogicProvider {

	private static final LogicProvider instance = new LogicProvider();

	private LogicProvider() {
	}

	private NewsLogic newsLogic = new NewsLogicImpl();
	private UserLogic userLogic = new UserLogicImpl();

	public NewsLogic getNewsLogic() {
		return newsLogic;
	}
	
	public UserLogic getUserLogic() {
		return userLogic;
	}

	public static LogicProvider getInstance() {
		return instance;
	}
}
