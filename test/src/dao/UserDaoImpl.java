package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import beans.BeanException;
import beans.User;

public class UserDaoImpl implements Dao<User> {
	
	private DaoFactory daoFactory;

	UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void add(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO users(code,username,password,type) VALUES(?,?,?,?) ;");
			preparedStatement.setString(1, user.getCode());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getType());
			
			preparedStatement.executeUpdate();
			//connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();

			} catch (SQLException ex) {
				throw new DaoException("The connection to the database is broken");
			}
			throw new DaoException("The connection to the database is broken");	
			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e2) {
					throw new DaoException("The connection to the database is broken");
				}
		}
		
	}

	@Override
	public List<User> all() throws DaoException {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT username,password,type FROM users;");

			while (result.next()) {
				String username = result.getString("username");
				String password = result.getString("password");
				String type = result.getString("type");

				User user = new User(username,password,type);
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException("The connection to the database is broken");
		} catch (BeanException e) {
			throw new DaoException("Invalid Data");
		}

		finally {
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DaoException("The connection to the database is broken");
			}
		}

		return users;
	}

	@Override
	public User find(String id) throws DaoException {
		User user = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT code , username , password , type FROM users WHERE username = '" + id + "' ;");

			while (result.next()) {
				String username = result.getString("username");
				String password = result.getString("password");
				String type = result.getString("type");

				user = new User(username,password,type);
				user.setCode(result.getString("code"));

			}
		} catch (SQLException e) {
			throw new DaoException("The connection to the database is broken");
		} catch (BeanException e) {
			throw new DaoException("Invalid Data");
		}

		finally {
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DaoException("The connection to the database is broken");
			}
		}
		return user;
	}

	@Override
	public void update(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE users SET username = ? , password = ?  WHERE code = ?;");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getCode());
			


			preparedStatement.executeUpdate();
			//connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();

			} catch (SQLException ex) {
				throw new DaoException("The connection to the database is broken");
			}
			throw new DaoException("The connection to the database is broken");
			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e2) {
					throw new DaoException("The connection to the database is broken");
				}
		}
	}

	@Override
	public void delete(String id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("DELETE FROM users WHERE code = '"+id+"'");
			

			preparedStatement.executeUpdate();
			//connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();

			} catch (SQLException ex) {
				throw new DaoException("The connection to the database is broken");
			}
			throw new DaoException("The connection to the database is broken");
			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e2) {
					throw new DaoException("The connection to the database is broken");
				}
		}		
		
	}

	@Override
	public List<User> getwhere(String attribute, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getwhere(String attribute_1, String value_1, String attribute_2, String value_2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
