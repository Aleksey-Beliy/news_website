package edu.web.news.dao.connectionPool;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

	ConnectionPoolException(Exception e) {
		super(e);
	}
}
