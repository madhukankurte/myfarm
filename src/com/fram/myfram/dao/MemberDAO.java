package com.fram.myfram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fram.myfram.dao.Database;
import com.fram.myfram.dto.Member;


public class MemberDAO {

	public static final String TABLE_NAME = "Members";
	
public static int saveMemer(Member member) throws Exception {


		
		int result = 0;

		Connection connnection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO " + TABLE_NAME
				+ " (name,mobileNumber,emailId,addarNumber,address,date,status,image,description)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			connnection = Database.getConnection();
			preparedStatement = connnection.prepareStatement(query);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getMobileNumber());
			preparedStatement.setString(3, member.getEmailId());
			preparedStatement.setString(4, member.getAddernumber());
			preparedStatement.setString(5, member.getAddress());
			preparedStatement.setLong(6, member.getDate());
			preparedStatement.setBoolean(7, member.isStatus());
			preparedStatement.setString(8, member.getImage());
			preparedStatement.setString(9, member.getDesc());
			
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			int MYSQL_DUPLICATE_PK = 1062;
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				// duplicate primary key
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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

public static int updateMember(Member member) {
	int result = 0;

	Connection connnection = null;
	PreparedStatement preparedStatement = null;

	String query = "Update  " + TABLE_NAME
			+ " SET name='"+member.getName()+"',mobileNumber='"+member.getMobileNumber()+"',emailId='"+member.getEmailId()+"',address='"+member.getAddress()+"',date="+member.getDate()+",description='"+member.getDesc()+"'"
			+ " Where userId = "+member.getUserId();

	try {
		connnection = Database.getConnection();
		preparedStatement = connnection.prepareStatement(query);
		
		
		
		System.out.println(preparedStatement);
		result = preparedStatement.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
		int MYSQL_DUPLICATE_PK = 1062;
		if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
			// duplicate primary key
			result = 1;
		}
	} catch (Exception e) {
		e.printStackTrace();
		result = 0;
	} finally {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
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

	
public static List<Member> readAll() {

	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	String query;

	List<Member> entries = new ArrayList<>();

	try {

		connection = Database.getConnection();
		statement = connection.createStatement();


			query = "SELECT userId,name,mobileNumber,emailId,addarNumber,address,date,status,image,description FROM " + TABLE_NAME ;
		
		System.out.println(query);
		rs = statement.executeQuery(query);
		while (rs.next()) {
			Member crockery = new Member();
			crockery.setUserId(rs.getInt(1));
			crockery.setName(rs.getString(2));
			crockery.setMobileNumber(rs.getString(3));
			crockery.setEmailId(rs.getString(4));
			crockery.setAddernumber(rs.getString(5));
			crockery.setAddress(rs.getString(6));
			crockery.setDate(rs.getLong(7));
			crockery.setStatus(rs.getBoolean(8));
			crockery.setImage(rs.getString(9));
			crockery.setDesc(rs.getString(10));
			entries.add(crockery);

		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException se) {
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	return entries;
}

public static int deleteMemer(String userId) {
	
	int result = 0;

	Connection connnection = null;
	PreparedStatement preparedStatement = null;
	
	Statement statement=null;

	String query = "delete from " + TABLE_NAME
			+ " where userId = "+ userId;

	try {
		connnection = Database.getConnection();
		
		//preparedStatement = connnection.prepareStatement(query);
		
		statement=connnection.createStatement();
		result=statement.executeUpdate(query);
		
		System.out.println(query);
		
		//result = preparedStatement.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
		int MYSQL_DUPLICATE_PK = 1062;
		if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
			// duplicate primary key
			result = 1;
		}
	} catch (Exception e) {
		e.printStackTrace();
		result = 0;
	} finally {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
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
