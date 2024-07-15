package edu.web.news.controller.listener;

import edu.web.news.dao.connectionPool.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool.getInstance().dispose();
	}
}
