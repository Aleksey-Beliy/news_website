package edu.web.news.logic.impl;

import java.util.List;
import edu.web.news.bean.News;
import edu.web.news.dao.DaoException;
import edu.web.news.dao.DaoProvider;
import edu.web.news.dao.NewsDao;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.NewsLogic;

public class NewsLogicImpl implements NewsLogic {

	private NewsDao dao = DaoProvider.getInstance().getNewsDao();

	public boolean addNews(News news) throws LogicException {
		try {
			dao.newsSave(news);
			return true;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void deleteNews(int id) throws LogicException {
		try {
			dao.newsDelete(id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void editNews(News news, int id) throws LogicException {
		try {
			dao.editNews(news, id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}

	}

	@Override
	public List<News> getNews() throws LogicException {
		List<News> lastNews;
		try {
			lastNews = dao.lastNews();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return lastNews;
	}

	@Override
	public News findNewsById(int id) throws LogicException {
		News news = null;
		try {
			news = dao.findNewsById(id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return news;
	}

	@Override
	public List<News> getNewsByCategory(String category) throws LogicException {
		try {
			List<News> news = dao.getNewsByCategory(category);
			return news;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
