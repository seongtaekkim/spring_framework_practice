import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taek.dao.UserDao;
import com.taek.service.UserService;
import com.taek.vo.Level;
import com.taek.vo.User;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/test-applicationContext.xml"})*/
public class UserDaoTest {

	
	private User user1;
	private User user2;
	private User user3;
	
	@Autowired
	UserDao dao;
	
	@Autowired
	UserService service;
	
	@Autowired
	DataSource dataSource;
	
	@Before
	@Test
	public void setUp() {
		System.out.println("service : " + dataSource);
		System.out.println("service : " + service);
		System.out.println("service : " + dao);
		this.user1=	new User("kim", "김성택","1234",Level.BASIC,1,0);
		this.user2=	new User("kim2", "김성택","1234",Level.BASIC,1,0);
		this.user3=	new User("kim3", "김성택","1234",Level.BASIC,1,0);
	}
	/*
	private void checkSanmeUser(User user1, User user2) {
		//Assert.assertThat(user1.getId(), is(user2.getId()));
	}
	
	@Test public void addAndGet() throws SQLException {
		User userget1 = dao.get(user1.getId());
		checkSanmeUser(userget1, user1);
		
		User userget2 = dao.get(user2.getId());
		checkSanmeUser(userget2, user2);
	}
	
	@Test public void update() throws SQLException {
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		
		user1.setName("김성택");
		user1.setPassword("1234");
		user1.setLevel(Level.GOLD);
		user1.setRecommend(999);
		user1.setLogin(1000);
		
		//dao.update(user1);
		
		User user1update = dao.get(user1.getId());
		checkSanmeUser(user1, user1update);
		User user2same = dao.get(user2.getId());
		checkSanmeUser(user2, user2same);
			
	}*/
}










