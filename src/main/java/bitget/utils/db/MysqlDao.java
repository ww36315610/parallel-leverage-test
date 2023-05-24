package bitget.utils.db;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class MysqlDao {
    private static MysqlOperation mcbp;
    private static Connection conn;
    private static MysqlDao md;
    static String driver;
    static String url;
    static String user;
    static String pass;

    static {
        driver = "com.mysql.cj.jdbc.Driver";
        //test8
//        url = "jdbc:mysql://eks-mysql.test8.bitget.tools:3306/upex_data_risk";
        //大数据
        url ="jdbc:mysql://aws-warehouse-sync.test.bitget.tools:3306/warehouse_sync";
        user = "test_public";
        pass = "YT=SDQ#YxLWsSi)$O$F]V0tOW6U1+KzV";
        mcbp = MysqlOperation.getInstance(driver, url, user, pass);
        conn = mcbp.getConnection();
    }

    public JSONArray select(String sql) throws SQLException {
        JSONArray jsonArry = new JSONArray();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
            while (rs.next()) {
                JSONObject josnObject = new JSONObject();
                for (int i = 1; i <= col; i++) {
                    String columnName = data.getColumnName(i);
                    // // 获取mysql列名称跟数据类型
                    // String columnClassName = data.getColumnClassName(i);
                    // josnObject.put(columnName,
                    // columnClassName.split("\\.")[2]);
                    String getValue = rs.getString(i);
                    String value = getValue == null ? "null" : getValue.toString();
                    josnObject.put(columnName, value);
                }
                if(josnObject.get("parent_user_id").equals("8767165056")){
                    System.out.println("--------------"+"0E-18".equals(josnObject.getString("p2p_out_amt_30d")));
                }
                jsonArry.add(josnObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArry;
    }

    public JSONArray select(String sql,Connection conn) throws SQLException {
        JSONArray jsonArry = new JSONArray();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
            while (rs.next()) {
                JSONObject josnObject = new JSONObject();
                for (int i = 1; i <= col; i++) {
                    String columnName = data.getColumnName(i);
                    String getValue = rs.getString(i);
                    String value = getValue == null ? "null" : getValue.toString();
                    josnObject.put(columnName, value);
                }
                jsonArry.add(josnObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArry;
    }

    public static void main(String[] args) {
        String sql ="";
        MysqlDao mysqlDao = new MysqlDao();
        try {
            mysqlDao.select(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
