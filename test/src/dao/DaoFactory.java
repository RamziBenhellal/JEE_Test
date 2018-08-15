package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import beans.Mark;
import beans.Module;
import beans.Student;
import beans.Teacher;
import beans.User;

public class DaoFactory {
	
	private final static String database = "schooloffice";
	
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/"+database, "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(url, username, password);
    }

    // get Data Access Object
    public Dao<Student> getStudentDao() {
        return new StudentDaoImpl(this);
    }
    
    public Dao<User> getUserDao() {
        return new UserDaoImpl(this);
    }
    
    public Dao<Module> getModuleDao() {
        return new ModuleDaoImpl(this);
    }
    
    public Dao<beans.Class> getClassDao() {
        return new ClassDaoImpl(this);
    }
    
    public Dao<Teacher> getTeacherDao() {
        return new TeacherDaoImpl(this);
    }
    
    public Dao<Mark> getMarkDao() {
        return new MarkDaoImpl(this);
    }

}
