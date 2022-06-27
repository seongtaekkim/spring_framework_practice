package ch1_1_3_4.dao;

import java.sql.SQLException;

import ch1_1_3_3.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		UserDao userDao = new DaoFactory().userDao();
		
		User user = new User();
		user.setId("kim2");
		user.setName("kim");
		user.setPassword("1234");
		
		userDao.add(user);
		
		System.out.println(user.getId() + " 등록성공");
		
		User user2 = userDao.get(user.getId());
		
		System.out.println(user2.getId());
		System.out.println("조회성공");
		
	}

}
