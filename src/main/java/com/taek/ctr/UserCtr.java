package com.taek.ctr;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String registUser(User user) throws SQLException {
		
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource);
		
		
		userDao.add(user);
		
		return "/retrieveUser";
	}
	
	@RequestMapping(value="/registUser", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> retrieveUser(User user) throws SQLException {
		
		Map<String,Object> map = new HashMap<String,Object>();

		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource);
		
		userDao.add(user);
		
		User resUser = userDao.get(user.getId());
		
		map.put("res", resUser);
		
		return map;
	}
	
	
}
