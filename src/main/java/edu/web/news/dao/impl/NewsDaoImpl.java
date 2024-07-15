package edu.web.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.web.news.bean.News;
import edu.web.news.dao.DaoException;
import edu.web.news.dao.NewsDao;
import edu.web.news.dao.connectionPool.ConnectionPool;
import edu.web.news.dao.connectionPool.ConnectionPoolException;

public class NewsDaoImpl implements NewsDao {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private String insert = "INSERT INTO news (title, category, content, imgPath) VALUES(?, ?, ?, ?)";

	@Override
	public boolean newsSave(News news) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(insert,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getCategory());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getImgPath());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new DaoException("Creating user failed");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private String DELETE_ARTICLE_SQL = "DELETE FROM news WHERE id = ?";

	@Override
	public void newsDelete(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTICLE_SQL)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private String EDIT_NEWS = "UPDATE news SET title = ?, category = ?, content = ? WHERE id = ?";

	@Override
	public void editNews(News news, int id) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(EDIT_NEWS)) {
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getCategory());
			statement.setString(3, news.getContent());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}

	}

	private String SELECT = "SELECT *  FROM news ORDER BY ID ASC";

	@Override
	public List<News> lastNews() throws DaoException {
		List<News> lastNews = new ArrayList<>();

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT);
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				lastNews.add(
						new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return lastNews;
	}

	private String FIND_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";

	@Override
	public News findNewsById(int id) throws DaoException {
		News news = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_NEWS_BY_ID)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					news = new News();
					news.setId(resultSet.getInt("id"));
					news.setTitle(resultSet.getString("title"));
					news.setCategory(resultSet.getString("category"));
					news.setContent(resultSet.getString("content"));
					news.setImgPath(resultSet.getString("imgPath"));
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return news;
	}

	private String SELECT_NEWS_BY_CATEGORY = "SELECT id, title, content, imgPath FROM news WHERE category = ?";

	@Override
	public List<News> getNewsByCategory(String category) throws DaoException {
		List<News> newsList = new ArrayList<>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_NEWS_BY_CATEGORY)) {
			statement.setString(1, category);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String imgPath = resultSet.getString("imgPath");
					News news = new News(id, title, category, content, imgPath);
					newsList.add(news);
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return newsList;
	}

}
