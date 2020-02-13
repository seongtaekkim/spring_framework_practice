package ch3_6.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch3_3.domain.User;


public class UserDao {
	private JdbcContext jdbcContext;
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	// UserDao -> JdbcContext -> DataSource 순서로 의존관계 형성
	/*	public void setDataSource(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}*/

	// UserDao -> Datasource 
	// JdbcContext -> UserDao 순서로 의존관계 형성
	// UserDao에 주입된 dataSource를 jdbcContext에 주입한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatament(
				new StatementStrategy() {			
					public PreparedStatement makePreparedStatement(Connection c)
							throws SQLException {
						PreparedStatement ps = 
								c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
						ps.setString(1, user.getId());
						ps.setString(2, user.getName());
						ps.setString(3, user.getPassword());

						return ps;
					}
				}
				);
	}


	/*	public User get(String id) throws SQLException {
		Connection c = this.dataSource.getConnection();
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		c.close();

		if (user == null) throw new EmptyResultDataAccessException(1);

		return user;
	}
	 */
	public void deleteAll() throws SQLException {
		this.jdbcContext.excuteSql("delete from users");
	}


	/*public int getCount() throws SQLException  {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select count(*) from users");

		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		rs.close();
		ps.close();
		c.close();

		return count;
	}*/
}
