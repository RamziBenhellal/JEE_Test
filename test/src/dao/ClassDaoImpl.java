package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.BeanException;
import beans.Class;

public class ClassDaoImpl implements Dao<Class> {
	
	private DaoFactory daoFactory;

	ClassDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void add(Class m_class) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO classes(classcode,studentsnumber,specialty) VALUES(?,?,?) ;");
			preparedStatement.setString(1, m_class.getClassCode());
			preparedStatement.setInt(2, m_class.getStudentsNumber());
			preparedStatement.setString(3, m_class.getSpecialty());
			
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
	public List<Class> all() throws DaoException {
		List<Class> classes = new ArrayList<Class>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT classcode,studentsnumber,specialty FROM classes;");

			while (result.next()) {
				String classCode = result.getString("classcode");
				Integer studentsNumber = result.getInt("studentsnumber");
				String specialty = result.getString("specialty");

				Class m_class = new Class(classCode, studentsNumber, specialty);
				classes.add(m_class);
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

		return classes;
	}

	@Override
	public Class find(String id) throws DaoException {
		Class m_class = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT classcode,studentsnumber,specialty FROM classes WHERE classcode = '" + id + "' ;");

			while (result.next()) {
				String classCode = result.getString("classcode");
				Integer studentsNumber = result.getInt("studentsnumber");
				String specialty = result.getString("specialty");

				m_class = new Class(classCode, studentsNumber, specialty);

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
		return m_class;
	}

	@Override
	public void update(Class m_class) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE classes SET studentsnumber = ? , specialty = ?  WHERE classcode = ?;");
			preparedStatement.setInt(1, m_class.getStudentsNumber());
			preparedStatement.setString(2, m_class.getSpecialty());
			preparedStatement.setString(3, m_class.getClassCode());
			


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
					.prepareStatement("DELETE FROM classes WHERE classcode = '"+id+"'");
			

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
	public List<Class> getwhere(String attribute, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class> getwhere(String attribute_1, String value_1, String attribute_2, String value_2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
