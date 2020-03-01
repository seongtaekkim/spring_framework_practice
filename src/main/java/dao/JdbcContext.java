package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// template (context)
	public void workWithStatament(StatementStrategy stmt) throws SQLException {
		Connection c= null;
		PreparedStatement ps = null;
		try {
			c = this.dataSource.getConnection();
			ps = stmt.makePreparedStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if (c != null) { try {c.close(); } catch (SQLException e) {} }
		}

	}
	// client & callback
	public void excuteSql(final String query) throws SQLException {
		workWithStatament(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c)
						throws SQLException {
					return c.prepareStatement(query);
				}
			}
		);
	}
	
	public int update(final String sql) throws DataAccessException, SQLException {
		class UpdateStatementCallback implements StatementCallback<Integer>, SqlProvider {
			@Override
			public Integer doInStatement(Statement stmt) throws SQLException {
				int rows = stmt.executeUpdate(sql);
				return rows;
			}
			@Override
			public String getSql() {
				return sql;
			}
		}
		return execute(new UpdateStatementCallback());
	}
	
	//-------------------------------------------------------------------------
	// Methods dealing with static SQL (java.sql.Statement)
	//-------------------------------------------------------------------------

	public <T> T execute(StatementCallback<T> action) throws DataAccessException, SQLException {
		Assert.notNull(action, "Callback object must not be null");

		Connection con = this.dataSource.getConnection();
		Statement stmt = null;
		try {
			Connection conToUse = con;
//			if (this.nativeJdbcExtractor != null &&
//					this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeStatements()) {
//				conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
//			}
			stmt = conToUse.createStatement();
		//	applyStatementSettings(stmt);
			Statement stmtToUse = stmt;
//			if (this.nativeJdbcExtractor != null) {
//				stmtToUse = this.nativeJdbcExtractor.getNativeStatement(stmt);
//			}
			T result = action.doInStatement(stmtToUse);
			//handleWarnings(stmt);
			return result;
		}
		catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool deadlock
			// in the case when the exception translator hasn't been initialized yet.
			JdbcUtils.closeStatement(stmt);
			stmt = null;
			//DataSourceUtils.releaseConnection(con, this.dataSource.getConnection());
			con = null;
			//throw getExceptionTranslator().translate("StatementCallback", getSql(action), ex);
		}
		finally {
			JdbcUtils.closeStatement(stmt);
			//DataSourceUtils.releaseConnection(con, getDataSource());
		}
		return null;
	}
}
