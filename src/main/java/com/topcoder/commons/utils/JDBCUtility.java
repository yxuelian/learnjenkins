package com.topcoder.commons.utils;

import java.lang.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a utility class that provides static methods for executing retrieval
 * and DML queries, committing and rolling back transactions. If SQLException is
 * thrown, this utility wraps it to the persistence exception specified by the
 * caller.
 * 
 * Thread Safety: This class is immutable and thread safe when connection and
 * array parameters passed to it are used by the caller in thread safe manner.
 * 
 * @author yangxuelian
 */
public class JDBCUtility {
	/**
	 * Empty private constructor.
	 */
	private JDBCUtility() {
	}

	/**
	 * Executes the SQL query and parses the query result from the ResultSet.
	 * 
	 * @throws T
	 *             if some error occurred when executing query or parsing result
	 * @param queryArgs
	 *            the query argument values
	 * @param persistenceExceptionClass
	 *            the class of persistence exception to be thrown if some error
	 *            occurs
	 * @param columnTypes
	 *            the result column types (can be one of Date.class,
	 *            Double.class, Float.class, Long.class, Integer.class,
	 *            String.class, Boolean.class, Object.class)
	 * @param connection
	 *            the database connection to be used
	 * @param argumentTypes
	 *            the argument types (see java.sql.Types)
	 * @param queryString
	 *            the query string
	 * @return the SQL query result; outer array represents rows; inner arrays
	 *         represent columns (not null)
	 */
	public static <T extends Throwable> Object[][] executeQuery(Connection connection, String queryString,
			int[] argumentTypes, Object[] queryArgs, Class<?>[] columnTypes, Class<T> persistenceExceptionClass) throws T {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Object> result = null;
		
		try {
			preparedStatement = connection.prepareStatement(queryString);
			for (int i = 0; i < queryArgs.length; i++) {
				preparedStatement.setObject(i + 1, queryArgs[i], argumentTypes[i]);
			}
			resultSet = preparedStatement.executeQuery();
			result = new ArrayList<Object>();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			if (columnTypes.length != columnCount) {
				throw ExceptionHelper.constructException(persistenceExceptionClass, "The column types length ["
						+ columnTypes.length + "] does not match the result set column count[" + columnCount + "].");
			}
			while (resultSet.next()) {
				Object[] rowData = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					Object cellValue;
					if (columnTypes[i] == Date.class) {
						Timestamp timestamp = resultSet.getTimestamp(i + 1);
						cellValue = timestamp == null ? null : new Date(timestamp.getTime());
					} else if (columnTypes[i] == Double.class) {
						cellValue = resultSet.getDouble(i + 1);
					} else if (columnTypes[i] == Float.class) {
						cellValue = resultSet.getFloat(i + 1);
					} else if (columnTypes[i] == Long.class) {
						cellValue = resultSet.getLong(i + 1);
					} else if (columnTypes[i] == Integer.class) {
						cellValue = resultSet.getInt(i + 1);
					} else if (columnTypes[i] == String.class) {
						cellValue = resultSet.getString(i + 1);
					} else if (columnTypes[i] == Boolean.class) {
						cellValue = resultSet.getBoolean(i + 1);
					} else if (columnTypes[i] == Object.class) {
						cellValue = resultSet.getObject(i + 1);
					} else {
						throw ExceptionHelper.constructException(persistenceExceptionClass,
								"Unsupported column type is used: " + columnTypes[i].getName());
					}
					rowData[i] = cellValue;
				}
				result.add(rowData);
			} 
		} catch (SQLException ex) {
			throw ExceptionHelper.constructException(persistenceExceptionClass, "Error occurred while executing query [" + queryString + "] using the query arguments " + Arrays.asList(queryArgs).toString() + ".", ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (result != null) {
			return result.toArray(new Object[][]{});
		} 
		return null;
	}

	/**
	 * Executes the DML SQL query.
	 * 
	 * @throws T
	 *             if some error occurred when executing query
	 * @param queryArgs
	 *            the query argument values
	 * @param persistenceExceptionClass
	 *            the class of persistence exception to be thrown if some error
	 *            occurs
	 * @param connection
	 *            the database connection to be used
	 * @param argumentTypes
	 *            the argument types (see java.sql.Types)
	 * @param queryString
	 *            the query string
	 * @return the number of affected rows
	 */
	public static <T extends Throwable> int executeUpdate(Connection connection, String queryString,
			int[] argumentTypes, Object[] queryArgs, Class<T> persistenceExceptionClass) throws T {
		
		PreparedStatement preparedStatement = null;
		int result = -1;
		
		try {
			preparedStatement = connection.prepareStatement(queryString);
			for (int i = 0; i < argumentTypes.length; i++) {
				preparedStatement.setObject(i + 1, queryArgs[i], argumentTypes[i]);
			}
			result = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			ExceptionHelper.constructException(persistenceExceptionClass, "Error occurred while executing query [" + queryString + "] using the query arguments " + Arrays.asList(queryArgs).toString() + ".", ex);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	/**
	 * Commits the transaction.
	 * 
	 * @throws T
	 *             if some error occurred
	 * @param persistenceExceptionClass
	 *            the class of persistence exception to be thrown if some error
	 *            occurs
	 * @param connection
	 *            the database connection to be used
	 */
	public static <T extends Throwable> void commitTransaction(Connection connection,
			Class<T> persistenceExceptionClass) throws T {
		try {
			connection.commit();
		} catch (SQLException ex) {
			throw ExceptionHelper.constructException(persistenceExceptionClass, "Error occurred when committing the transaction.", ex);
		}
	}

	/**
	 * Performs a rollback operation for the transaction. Does nothing if
	 * connection is null.
	 * 
	 * @throws T
	 *             if some error occurred
	 * @param persistenceExceptionClass
	 *            the class of persistence exception to be thrown if some error
	 *            occurs
	 * @param connection
	 *            the database connection to be used
	 */
	public static <T extends Throwable> void rollbackTransaction(Connection connection,
			Class<T> persistenceExceptionClass) throws T {
		if (connection == null) {
			return;
		}
		
		try {
			connection.rollback();
		} catch (SQLException ex) {
			throw ExceptionHelper.constructException(persistenceExceptionClass, "Error occurred when rolling back the transaction.", ex);
		}
	}
}
