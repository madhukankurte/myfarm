package com.fram.myfram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {

	public static Connection getConnection() throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			Context context = (Context) initialContext.lookup("java:comp/env");
			// The JDBC Data source that we just created
			DataSource ds = (DataSource) context.lookup("connpool");
			Connection connection = ds.getConnection();

			return connection;
		} catch (Exception e) {
			throw e;
		}

	}

	public static int executeUpdate(String query) {

		Connection connnection = null;
		Statement stmt = null;
		int result = 0;

		try {

			connnection = getConnection();
			stmt = connnection.createStatement();
			result = stmt.executeUpdate(query);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (connnection != null) {
					connnection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;
	}

}
