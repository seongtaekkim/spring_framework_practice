package com.taek.service;

import com.taek.dao.UserDao;

public class UserService {
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
