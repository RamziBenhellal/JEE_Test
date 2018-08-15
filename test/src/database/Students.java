package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.BeanException;
import beans.Student;

public class Students extends Database {
	
	
	// table name:
	public final String table = "students";
	
	// table attributes:
	public final String firstname = "firstname";
	public final String lastname = "lastname";
	public final String serialnumber = "serialnumber";
	public final String created_at = "created_at";
	
	// primary key:
	public final String primary_key = "serialnumber";
	
	public ArrayList<String> attributes(){
		ArrayList<String> attributes = new ArrayList<>();
		attributes.add(firstname);
		attributes.add(lastname);
		attributes.add(serialnumber);
		attributes.add(created_at);
		return attributes;
	}

	public List<Student> getStudents() throws BeanException{
		
		
		
		List<Student> students = new ArrayList<Student>();

		Statement statement = null;
		ResultSet result = null;
		
		loadDatabase();
		
		try {
			statement = (Statement) connection.createStatement();
			
			// request execution
			result = statement.executeQuery("SELECT firstname,lastname,serialnumber FROM students;");
			
			// get data
			while(result.next()) {
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String serialNumber = result.getString("serialnumber");
				
				Student student = null; //= new Student(firstname, lastname, serialNumber);
				
				students.add(student);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			// close connection
			try {
				if(result != null)
					result.close();
				if(statement != null)
					statement.close();
				if(connection != null)
					connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
		
		return students;
	}
	
	// save new student
	public void saveStudent(Student student) {
		loadDatabase();
		try {
			PreparedStatement preparedStatment = (PreparedStatement) connection.prepareStatement("INSERT INTO `students`(`firstname`, `lastname`, `serialnumber`) VALUES (?,?,?);");
			preparedStatment.setString(1, student.getFirstname());
			preparedStatment.setString(2, student.getLastname());
			preparedStatment.setString(3, student.getSerialNumber());
			
			preparedStatment.executeUpdate();
			preparedStatment.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			// close connection
			try {
				
				if(connection != null)
					connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

}
