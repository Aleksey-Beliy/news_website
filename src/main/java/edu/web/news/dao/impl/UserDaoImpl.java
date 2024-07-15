package edu.web.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.web.news.bean.AuthInfo;
import edu.web.news.bean.RegistrationInfo;
import edu.web.news.bean.User;
import edu.web.news.dao.DaoException;
import edu.web.news.dao.UserDao;
import edu.web.news.dao.connectionPool.ConnectionPool;
import edu.web.news.dao.connectionPool.ConnectionPoolException;

public class UserDaoImpl implements UserDao {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public User checkAuth(AuthInfo authInfo) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
			statement.setString(1, authInfo.getLogin());
			statement.setString(2, authInfo.getPassword());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				String email = resultSet.getString(2);
				String role = rolesHasUsers(id);

				return new User(id, email, role);
			} else {
				return null;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private String INSERT_USER = "INSERT INTO users (email, name, password) VALUES(?, ?, ?);";
	private String INSERT_ROLES = "INSERT INTO roles_has_users(roles_id, users_id) VALUES(?,?)";

	@Override
	public void registrationUser(RegistrationInfo regInfo) throws DaoException {
		int idUser = 0;
		try (Connection connection = connectionPool.takeConnection()) {
			connection.setAutoCommit(false);

			try (PreparedStatement statement = connection.prepareStatement(INSERT_USER,
					PreparedStatement.RETURN_GENERATED_KEYS);
					PreparedStatement statementRole = connection.prepareStatement(INSERT_ROLES)) {

				statement.setString(1, regInfo.getEmail());
				statement.setString(2, regInfo.getName());
				statement.setString(3, regInfo.getPassword());

				int affectedRows = statement.executeUpdate();

				if (affectedRows == 0) {
					connection.rollback();
					throw new DaoException("Creating user failed, no rows affected.");
				}
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						idUser = generatedKeys.getInt(1);
					} else {
						connection.rollback();
						throw new DaoException("Creating user failed, no ID obtained.");
					}
				}
				statementRole.setInt(1, 4);
				statementRole.setInt(2, idUser);
				statementRole.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				throw new DaoException(e);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private String QUERY = "SELECT COUNT(*) FROM users WHERE email = ?";

	@Override
	public boolean userExists(String email) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY)) {

			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return false;
	}

	private String SELECT_ROLES_ID = "SELECT r.title \r\n" + "FROM roles r\r\n"
			+ "JOIN roles_has_users rhu ON r.id = rhu.roles_id\r\n" + "JOIN users u ON u.id = rhu.users_id\r\n"
			+ "WHERE u.id = ?";

	public String rolesHasUsers(int userId) throws DaoException {
		String role = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ROLES_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				role = resultSet.getString("title");
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return role;
	}

	String SELECT_ID_USER = "SELECT id FROM users WHERE email";

	public int getUserId(String email) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ID_USER)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return -1;
	}

	private String CHECK_EXISTING_ROLE = "SELECT COUNT(*) FROM roles_has_users WHERE users_id = (SELECT id FROM users WHERE email = ?)";
	private String UPDATE_ROLE = "UPDATE roles_has_users SET roles_id = (SELECT id FROM roles WHERE title = ?) WHERE users_id = (SELECT id FROM users WHERE email = ?)";
	private String INSERT_ROLE = "INSERT INTO roles_has_users (roles_id, users_id) VALUES ((SELECT id FROM roles WHERE title = ?), (SELECT id FROM users WHERE email = ?))";

	@Override
	public void assignRole(String email, String role) throws DaoException {
		try (Connection connection = connectionPool.takeConnection()) {
			connection.setAutoCommit(false);
			try (PreparedStatement checkStatement = connection.prepareStatement(CHECK_EXISTING_ROLE)) {
				checkStatement.setString(1, email);
				ResultSet resultSet = checkStatement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				if (count > 0) {
					try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_ROLE)) {
						updateStatement.setString(1, role);
						updateStatement.setString(2, email);
						updateStatement.executeUpdate();
					}
				} else {
					try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_ROLE)) {
						insertStatement.setString(1, role);
						insertStatement.setString(2, email);
						insertStatement.executeUpdate();
					}
				}
			}

			connection.commit();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Error assigning role to user", e);
		}
	}

	private String FIND_USER_BY_EMAIL = "SELECT u.id, u.email, u.name, r.title AS role\r\n" + "FROM users u\r\n"
			+ "LEFT JOIN roles_has_users ru ON u.id = ru.users_id\r\n" + "LEFT JOIN roles r ON ru.roles_id = r.id\r\n"
			+ "WHERE u.email = ?";

	@Override
	public User findUserByEmail(String email) throws DaoException {
		User user = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String userEmail = rs.getString("email");
				String role = rs.getString("role");
				user = new User(id, userEmail, role);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return user;
	}
}