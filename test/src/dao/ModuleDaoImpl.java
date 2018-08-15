package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import beans.BeanException;
import beans.Module;



public class ModuleDaoImpl implements Dao<Module> {

	private DaoFactory daoFactory;

	ModuleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void add(Module module) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO modules(modulecode,module,coefficient,specialty) VALUES(?,?,?,?);");
			preparedStatement.setString(1, module.getModuleCode());
			preparedStatement.setString(2, module.getModule());
			preparedStatement.setInt(3, module.getCoefficient());
			preparedStatement.setString(4, module.getSpecialty());

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
	public List<Module> all() throws DaoException {
		List<Module> modules = new ArrayList<Module>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT modulecode,module,coefficient,specialty FROM modules;");

			while (result.next()) {
				String moduleCode  = result.getString("modulecode");
				String module = result.getString("module");
				Integer coefficient = result.getInt("coefficient");
				String specialty = result.getString("specialty");
				
				Module Module = new Module(moduleCode, module, coefficient, specialty);

				modules.add(Module);
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

		return modules;
	}

	@Override
	public Module find(String id) throws DaoException {
		Module Module = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery(
					"SELECT modulecode,module,coefficient,specialty FROM modules WHERE modulecode = '" + id + "' ;");

			while (result.next()) {
				String moduleCode  = result.getString("modulecode");
				String module = result.getString("module");
				Integer coefficient = result.getInt("coefficient");
				String specialty = result.getString("specialty");
				
				Module = new Module(moduleCode, module, coefficient, specialty);


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
		return Module;
	}

	@Override
	public void update(Module module) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("UPDATE modules SET module = ? , coefficient = ? , specialty = ?  WHERE modulecode = ? ;");
			preparedStatement.setString(1, module.getModule());
			preparedStatement.setInt(2, module.getCoefficient());
			preparedStatement.setString(3, module.getSpecialty());
			preparedStatement.setString(4, module.getModuleCode());


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
					.prepareStatement("DELETE FROM modules WHERE modulecode = '"+id+"' ;");
			

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
	public List<Module> getwhere(String attribute, String value) throws DaoException {
		List<Module> modules = new ArrayList<Module>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT modulecode,module,coefficient,specialty FROM modules WHERE "+attribute+" = '"+value+"' ;");

			while (result.next()) {
				String moduleCode  = result.getString("modulecode");
				String module = result.getString("module");
				Integer coefficient = result.getInt("coefficient");
				String specialty = result.getString("specialty");
				
				Module Module = new Module(moduleCode, module, coefficient, specialty);

				modules.add(Module);
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

		return modules;
	}

	@Override
	public List<Module> getwhere(String attribute_1, String value_1, String attribute_2, String value_2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}


}
