package com.taek.ctr;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taek.calculator.originLogic.CalculatorOrigin;
import com.taek.dao.UserDao;
import com.taek.vo.CalcVo;
import com.taek.vo.User;

@Controller
public class UserCtr {

	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value="/retrieveUser", method=RequestMethod.GET)
	public String retrieveUser(User user) throws SQLException {
		
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource);
		
		
		userDao.add(user);
		
		return "/user/retrieveUser";
	}
	
	@RequestMapping(value="/initUser", method=RequestMethod.GET)
	public String initUser() throws SQLException {
		
		return "/user/registUser";
	}
	
	
	@RequestMapping(value="/registUser", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> registUser(@RequestBody User user) throws SQLException {
		
		Map<String,Object> map = new HashMap<String,Object>();

		// client 에서 context생성 및 datasource를 DI 한다.
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource);
		
		// client에서 context의 전략메서드를 사용한다.
		userDao.add(user);
		
		User resUser = userDao.get(user.getId());
		
		map.put("res", resUser);
		
		return map;
	}
	
	
}
