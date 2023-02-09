package com.spring.main;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Main {

	
	public static void main(String[] args)  {
		 //Gugudan gugudan = new Gugudan();
		ApplicationContext ctx = new  GenericXmlApplicationContext("classpath:com/spring/context/root-context.xml");
		
//		Gugudan gugudan = ctx.getBean("gugudan",Gugudan.class);
//		gugudan.execute();
		
		DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			 conn = dataSource.getConnection();
			 stmt = conn.createStatement();
			String sql = "select * from member where id = 'mimi' ";
			 rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("pwd"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("phone"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("authority"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
				try {
					if(rs!=null)
					rs.close();
					if(stmt !=null)
						stmt.close();
					if(conn !=null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}
