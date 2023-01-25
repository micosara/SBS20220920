package com.jsp.datasource;

import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class MySqlSessionFactory implements SqlSessionFactory{
	
	private SqlSessionFactory sqlSessionFactory;

	{
		String config = "com/jsp/mybatis/config/sqlConfig.xml";

		try {
			Reader reader = Resources.getResourceAsReader(config);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();

			System.out.println("sqlSessionFactory 성공했습니다.");
		} catch (Exception e) {
			System.out.println("sqlSessionFactory 실패했습니다.");
			e.printStackTrace();
		}
	}
	
		
	@Override
	public Configuration getConfiguration() {		
		return sqlSessionFactory.getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return sqlSessionFactory.openSession(true);
	}

	@Override
	public SqlSession openSession(boolean arg0) {		
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(Connection arg0) {		
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, boolean arg1) {	
		return sqlSessionFactory.openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, TransactionIsolationLevel arg1) {		
		return sqlSessionFactory.openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, Connection arg1) {		
		return sqlSessionFactory.openSession(arg0, arg1);
	}

}
