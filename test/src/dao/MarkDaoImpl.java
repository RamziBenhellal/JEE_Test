package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.BeanException;
import beans.Mark;
import beans.Module;
import beans.Student;

public class MarkDaoImpl implements Dao<Mark> {
   
	private DaoFactory daoFactory;

	MarkDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void add(Mark mark) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO marks(serialnumber,modulecode,attendance,test_1,test_2,exam,total) VALUES(?,?,?,?,?,?,?);");
			preparedStatement.setString(1, mark.getStudent().getSerialNumber());
			preparedStatement.setString(2, mark.getModule().toString());
			preparedStatement.setFloat(3,  mark.getAttendance());
			preparedStatement.setFloat(4, mark.getTest_1());
			preparedStatement.setFloat(5, mark.getTest_2());
			preparedStatement.setFloat(6, mark.getExam());
			preparedStatement.setFloat(7, mark.getTotal());

			preparedStatement.executeUpdate();
			//connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();

			} catch (SQLException ex) {
				throw new DaoException("The connection to the database is broken  ");
			}
			throw new DaoException("The connection to the database is broken !! ");	
			
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
	public List<Mark> all() throws DaoException {
		List<Mark> marks = new ArrayList<Mark>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT serialnumber,modulecode,attendance,test_1,test_2,exam,total FROM marks;");

			while (result.next()) {
				Student student = daoFactory.getStudentDao().find(result.getString("serialnumber"));
				Module module = daoFactory.getModuleDao().find(result.getString("modulecode"));
				Float attendance = result.getFloat("attendance");
				Float test_1 = result.getFloat("test_1");
				Float test_2 = result.getFloat("test_2");
				Float exam = result.getFloat("exam");
				Float total = result.getFloat("total");

				Mark mark = new Mark(student, module, attendance, test_1, test_2,exam, total);
				marks.add(mark);
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

		return marks;
	}

	@Override
	public Mark find(String id) throws DaoException {
		Mark mark = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT serialnumber,modulecode,attendance,test_1,test_2,exam,total FROM marks WHERE serialnumber = '" + id + "' ;");

			while (result.next()) {
				Student student = daoFactory.getStudentDao().find(result.getString("serialnumber"));
				Module module = daoFactory.getModuleDao().find(result.getString("modulecode"));
				Float attendance = result.getFloat("attendance");
				Float test_1 = result.getFloat("test_1");
				Float test_2 = result.getFloat("test_2");
				Float exam = result.getFloat("exam");
				Float total = result.getFloat("total");

				mark = new Mark(student, module, attendance, test_1, test_2,exam, total);


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
		return mark;
	}

	@Override
	public void update(Mark mark) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE marks SET  attendance = ? , test_1 = ? , test_2 = ? , exam = ? , total = ?  WHERE serialnumber = ? AND modulecode = ?;");
			
			preparedStatement.setFloat(1,  mark.getAttendance());
			preparedStatement.setFloat(2, mark.getTest_1());
			preparedStatement.setFloat(3, mark.getTest_2());
			preparedStatement.setFloat(4, mark.getTotal());
			preparedStatement.setFloat(5, mark.getExam());
			preparedStatement.setString(6, mark.getStudent().getSerialNumber());
			preparedStatement.setString(7, mark.getModule().toString());

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
					.prepareStatement("DELETE FROM marks WHERE serialnumber = "+id);
			

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
	public List<Mark> getwhere(String attribute, String value) throws DaoException {
		List<Mark> marks = new ArrayList<Mark>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT serialnumber,modulecode,attendance,test_1,test_2,exam,total FROM marks WHERE "+attribute+" = '"+value+"' ;");

			while (result.next()) {
				Student student = daoFactory.getStudentDao().find(result.getString("serialnumber"));
				Module module = daoFactory.getModuleDao().find(result.getString("modulecode"));
				Float attendance = result.getFloat("attendance");
				Float test_1 = result.getFloat("test_1");
				Float test_2 = result.getFloat("test_2");
				Float exam = result.getFloat("exam");
				Float total = result.getFloat("total");

				Mark mark = new Mark(student, module, attendance, test_1, test_2,exam, total);
				marks.add(mark);
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

		return marks;
	}
	
	@Override
	public List<Mark> getwhere(String attribute_1 ,String value_1,String attribute_2 ,String value_2) throws DaoException {
		List<Mark> marks = new ArrayList<Mark>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			
			result = statement.executeQuery("SELECT serialnumber,modulecode,attendance,test_1,test_2,exam,total FROM marks WHERE "+attribute_1+" = '"+value_1+"' "
					+ "AND "+attribute_2+" = '"+value_2+"' ;");

			while (result.next()) {
				Student student = daoFactory.getStudentDao().find(result.getString("serialnumber"));
				Module module = daoFactory.getModuleDao().find(result.getString("modulecode"));
				Float attendance = result.getFloat("attendance");
				Float test_1 = result.getFloat("test_1");
				Float test_2 = result.getFloat("test_2");
				Float exam = result.getFloat("exam");
				Float total = result.getFloat("total");

				Mark mark = new Mark(student, module, attendance, test_1, test_2,exam, total);
				marks.add(mark);
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

		return marks;
	}

}
