package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.BeanException;
import beans.Teacher;

public class TeacherDaoImpl implements Dao<Teacher> {
	
	private DaoFactory daoFactory;

	TeacherDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void add(Teacher teacher) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO teachers(codeteacher,firstname,lastname,modulecode,classcode) VALUES(?,?,?,?,?);");
			preparedStatement.setString(1, teacher.getCodeTeacher());
			preparedStatement.setString(2, teacher.getFirstname());
			preparedStatement.setString(3, teacher.getLastname());
			preparedStatement.setString(4, teacher.getModule().toString());
			preparedStatement.setString(5, teacher.gettClass().toString());

			preparedStatement.executeUpdate();
			//connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();

			} catch (SQLException ex) {
				throw new DaoException("Teacher The connection to the database is broken");
			}
			throw new DaoException("Teacher The connection to the database is broken");	
			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e2) {
					throw new DaoException("Teacher The connection to the database is broken");
				}
		}
	}

	@Override
	public List<Teacher> all() throws DaoException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT codeteacher,firstname,lastname,modulecode,classcode FROM teachers;");

			while (result.next()) {
				String codeTeacher = result.getString("codeteacher");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String moduleCode = result.getString("modulecode");
				String classCode = result.getString("classcode");

				Teacher teacher = new Teacher(codeTeacher,firstname, lastname,daoFactory.getModuleDao().find(moduleCode),daoFactory.getClassDao().find(classCode));
				teachers.add(teacher);
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

		return teachers;
	}

	@Override
	public Teacher find(String id) throws DaoException {
		Teacher teacher = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT codeteacher,firstname,lastname,modulecode,classcode FROM teachers WHERE codeteacher = '" + id + "' ;");

			while (result.next()) {
				String codeTeacher = result.getString("codeteacher");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String moduleCode = result.getString("modulecode");
				String classCode = result.getString("classcode");

				teacher = new Teacher(codeTeacher,firstname, lastname,daoFactory.getModuleDao().find(moduleCode),daoFactory.getClassDao().find(classCode));

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
		return teacher;
	}

	@Override
	public void update(Teacher teacher) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE teachers SET firstname = ? , lastname = ? , modulecode = ? , classcode = ?  WHERE codeteacher = ? ;");
			preparedStatement.setString(1, teacher.getFirstname());
			preparedStatement.setString(2, teacher.getLastname());
			preparedStatement.setString(3, teacher.getModule().toString());
			preparedStatement.setString(4, teacher.gettClass().toString());
			preparedStatement.setString(5, teacher.getCodeTeacher());


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
					.prepareStatement("DELETE FROM teachers WHERE codeteacher = '"+id+"'");
			

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
	public List<Teacher> getwhere(String attribute, String value) throws DaoException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT codeteacher,firstname,lastname,modulecode,classcode FROM teachers WHERE "+attribute+" = '"+value+"' ; ");

			while (result.next()) {
				String codeTeacher = result.getString("codeteacher");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String moduleCode = result.getString("modulecode");
				String classCode = result.getString("classcode");

				Teacher teacher = new Teacher(codeTeacher,firstname, lastname,daoFactory.getModuleDao().find(moduleCode),daoFactory.getClassDao().find(classCode));
				teachers.add(teacher);
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

		return teachers;
	}

	@Override
	public List<Teacher> getwhere(String attribute_1, String value_1, String attribute_2, String value_2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
