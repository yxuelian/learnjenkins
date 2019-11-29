package com.topcoder.commons.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * @author yangxuelian
 */
public class DBUtil {

	/**
	 * 获取与数据库的连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://10.82.59.22:3306/test", "root", "123456");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param stm
	 * @param rs
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
