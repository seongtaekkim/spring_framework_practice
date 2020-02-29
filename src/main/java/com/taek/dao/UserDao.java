package com.taek.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.taek.vo.User;

import dao.JdbcContext;
import dao.StatementStrategy;


public class UserDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private JdbcContext jdbcContext;
	/*	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}*/
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;
		
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void add(final User user) throws SQLException {
		
		// JdbcTemplate class를 이용한 방식
		/*this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)"
				,user.getId(),user.getName(),user.getPassword());*/
		 
		// JdbcContext class를 이용한 방식
		this.jdbcContext.workWithStatament(
			new StatementStrategy() {
				@Override
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = 
							c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
					ps.setString(1, user.getId());
					ps.setString(2, user.getName());
					ps.setString(3, user.getPassword());

					return ps;
				}
		});
	}


	public User get(String id) throws SQLException {
		return this.jdbcTemplate.queryForObject("select * from users where id =?"
				, new Object[] {id},
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	}

	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from users");
	}


	public int getCount() throws SQLException  {
		//return this.jdbcTemplate.queryForInt("select count(*) from user");
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				return con.prepareStatement("select count(*) from user");
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}
		});
	}
}
