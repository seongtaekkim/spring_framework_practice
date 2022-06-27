package ch1_1_3_4.dao;

public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
	/*public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}
	
	public MessageDao accountDao() {
		return new MessageDao(connectionMaker());
	}*/
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
