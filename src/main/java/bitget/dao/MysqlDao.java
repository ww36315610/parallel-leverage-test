package bitget.dao;

import com.alibaba.fastjson.JSONArray;

import java.sql.Connection;
import java.sql.SQLException;

public interface MysqlDao {
	public int createTable(String sql, Connection conn) throws SQLException;

	public int insert(String sql, Connection conn);

	public int delete(String sql, Connection conn);

	public int update(String sql, Connection conn);

	public JSONArray select(String sql, Connection conn);

}