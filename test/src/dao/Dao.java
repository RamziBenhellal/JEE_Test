package dao;

import java.util.List;



public interface Dao<T> {
	
	void add( T type ) throws DaoException;
    List<T> all() throws DaoException;
    T find(String id) throws DaoException;
    void update(T type) throws DaoException;
    void delete(String id) throws DaoException;
    List<T> getwhere(String attribute , String value) throws DaoException;
	List<T> getwhere(String attribute_1, String value_1, String attribute_2, String value_2) throws DaoException;
    
}
