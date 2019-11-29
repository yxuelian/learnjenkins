package com.topcoder.commons.utils;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Types;

import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * @author yangxuelian
 */
public class TestJDBCUtility extends AbstractDbUnitTestCase {

	@Before
	public void setUpBeforeClass() throws Exception {
		backupOneTable("user");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		resumeTable();
	}

//	@Test
//	public void testExecuteQuery() throws Exception {
//		IDataSet ds = createDataSet("user");
//		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
//		
//		Integer integer = new Integer(0);
//		String string = new String();
//		Date date = new Date(0);
//		Double double1 = new Double(0);
//		Boolean boolean1 = new Boolean(false);
//		
//		int[] argumentTypes = new int[] {Types.INTEGER, Types.VARCHAR, Types.DATE, Types.DOUBLE, Types.BOOLEAN};
//		Object[] queryArgs = new Object[] {integer, string, date, double1, boolean1};
//		Class<?>[] columnTypes = new Class<?>[] {Integer.class, String.class, Date.class, Double.class, Boolean.class};
//		JDBCUtility.executeQuery(connection, "select * from User", argumentTypes, queryArgs, columnTypes, Exception.class);
//	}

}
