package com.luke.hrms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		//Create properties object to access the property file
		Properties p=new Properties();
		//get the io stream
		InputStream is=JdbcUtil.class.getResourceAsStream("/db.properties");
		try {
			//load property file
			p.load(is);
			//get jdbc value
			driver=p.getProperty("driver");
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");
			//load driver
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Create Connection object
	public static Connection getConnection(){
		Connection conn=null;
		try {
			 conn=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	//Create PreparedStatement object
	public static PreparedStatement getPreparedStatement(String sql,Connection conn){
		
		PreparedStatement ps=null;
		try {
			ps =conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
		
	}
	
	//Create Statement object
	public static Statement getStatement(Connection conn){
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
		
	}
	
	//close all
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
		try {
			rs.close();
		} catch (Exception e) {
			
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			
		}
		try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}
	
	//Encapsulate the sql execution function
	public static int executeDML(String sql,Object...objs){
		//
		Connection conn=getConnection();
		//Create sql statement object
		PreparedStatement ps=JdbcUtil.getPreparedStatement(sql, conn);
		//assign value to placeholders
			try {
				conn.setAutoCommit(false);
				for(int i=0;i<objs.length;i++){
					ps.setObject(i+1, objs[i]);
				}
				int i=ps.executeUpdate();
				conn.commit();
				return i;
			} catch (Exception e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}finally{
				//close all resources
				JdbcUtil.closeAll(null, ps, conn);
			}
			//return result
			return -1;
	}
}
