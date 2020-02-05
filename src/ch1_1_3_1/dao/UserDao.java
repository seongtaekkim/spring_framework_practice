package ch1_1_3_1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch1_1_3_1.domain.User;

public class UserDao {
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDao() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	public void add(User user) throws ClassNotFoundException, SQLException {
		 
		Connection c = simpleConnectionMaker.makeNewConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		
		Connection c = simpleConnectionMaker.makeNewConnection();	
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("kim2");
		user.setName("kim");
		user.setPassword("1234");
		
		dao.add(user);
		
		System.out.println(user.getId() + " 등록성공");
		
		User user2 = dao.get(user.getId());
		
		System.out.println(user2.getId());
		System.out.println("조회성공");
	}
}
