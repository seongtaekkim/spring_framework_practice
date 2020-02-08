package ch1_1_7.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker {

	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}
}
