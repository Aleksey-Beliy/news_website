package edu.web.news.dao;

import java.util.List;
import edu.web.news.bean.News;

public interface NewsDao {

	boolean newsSave(News news) throws DaoException;

	void newsDelete(int id) throws DaoException;

	void editNews(News news, int id) throws DaoException;

	News findNewsById(int id) throws DaoException;

	List<News> lastNews() throws DaoException;

	List<News> getNewsByCategory(String category) throws DaoException;

}
