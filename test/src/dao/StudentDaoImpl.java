package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.BeanException;
import beans.Student;
import forms.StudentForms;

public class StudentDaoImpl implements Dao<Student> {

	private DaoFactory daoFactory;

	StudentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void add(Student student) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO students(firstname,lastname,serialnumber,birthDate,specialty,classcode) VALUES(?,?,?,?,?,?);");
			preparedStatement.setString(1, student.getFirstname());
			preparedStatement.setString(2, student.getLastname());
			preparedStatement.setString(3, student.getSerialNumber());
			preparedStatement.setString(4, student.getBirthDate().toString());
			preparedStatement.setString(5, student.getSpecialty());
			preparedStatement.setString(6, student.getsClass().toString());

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
	public List<Student> all() throws DaoException {
		List<Student> students = new ArrayList<Student>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT firstname,lastname,serialnumber,birthDate,specialty,classcode FROM students;");

			while (result.next()) {
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String serialNumber = result.getString("serialnumber");
				LocalDate birthDate = new StudentForms().toDate(result.getString("birthDate"));
				String speciality = result.getString("specialty");
				String classCode = result.getString("classcode");

				Student student = new Student(firstname, lastname, serialNumber,birthDate,speciality,daoFactory.getClassDao().find(classCode));
				students.add(student);
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

		return students;
	}

	@Override
	public Student find(String id) throws DaoException {
		Student student = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT firstname,lastname,serialnumber,birthDate,specialty,classcode FROM students WHERE serialnumber = '" + id + "' ;");

			while (result.next()) {
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String serialNumber = result.getString("serialnumber");
				LocalDate birthDate = new StudentForms().toDate(result.getString("birthDate"));
				String speciality = result.getString("specialty");
				String classCode = result.getString("classcode");

				student = new Student(firstname, lastname, serialNumber,birthDate,speciality,daoFactory.getClassDao().find(classCode));

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
		return student;
	}
	
	@Override
	public void delete(String id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("DELETE FROM students WHERE serialnumber = "+id);
			

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
	public void update(Student student) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE students SET firstname = ? , lastname = ? , birthDate = ? , specialty = ? , classcode = ?  WHERE serialnumber = ?;");
			preparedStatement.setString(1, student.getFirstname());
			preparedStatement.setString(2, student.getLastname());
			preparedStatement.setString(3, student.getBirthDate().toString());
			preparedStatement.setString(4, student.getSpecialty());
			preparedStatement.setString(5, student.getsClass().toString());
			preparedStatement.setString(6, student.getSerialNumber());


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
	public List<Student> getwhere(String attribute, String value) throws DaoException {
		List<Student> students = new ArrayList<Student>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT firstname,lastname,serialnumber,birthDate,specialty,classcode FROM students WHERE "+attribute+" = '"+value+"' ;");

			while (result.next()) {
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String serialNumber = result.getString("serialnumber");
				LocalDate birthDate = new StudentForms().toDate(result.getString("birthDate"));
				String speciality = result.getString("specialty");
				String classCode = result.getString("classcode");

				Student student = new Student(firstname, lastname, serialNumber,birthDate,speciality,daoFactory.getClassDao().find(classCode));
				students.add(student);
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

		return students;
	}

	@Override
	public List<Student> getwhere(String attribute_1, String value_1, String attribute_2, String value_2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
