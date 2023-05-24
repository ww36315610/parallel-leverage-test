package bitget.testcase.offline;

import bitget.dao.MysqlDao;
import bitget.dao.MysqlDaoImp;
import bitget.utils.db.MysqlOperation;
import bitget.utils.file.ConfigTools;

import java.sql.Connection;

public class Basic_DB {
    static MysqlOperation mcbp_test9;
    static Connection conn_test9;
    static MysqlDao md_test9;
    static String driver_test9;
    static String url_test9;
    static MysqlOperation mcbp_aws9;
    static Connection conn_aws9;
    static MysqlDao md_aws9;
    static String driver_aws9;
    static String url_aws9;
    static String user;
    static String pass;

    static {
        driver_test9 = ConfigTools.config.getString("mysql_test9.jdbc.dbDriver");
        url_test9 = ConfigTools.config.getString("mysql_test9.jdbc.dbUrl");
        user = ConfigTools.config.getString("mysql_test9.jdbc.username");
        pass = ConfigTools.config.getString("mysql_test9.jdbc.password");
        mcbp_test9 = MysqlOperation.getInstance(driver_test9, url_test9, user, pass);
        System.out.println("+++" + mcbp_test9);
        conn_test9 = (Connection) mcbp_test9.getConnection();

        md_test9 = new MysqlDaoImp();

        driver_aws9 = ConfigTools.config.getString("mysql_aws9.jdbc.dbDriver");
        url_aws9 = ConfigTools.config.getString("mysql_aws9.jdbc.dbUrl");
        user = ConfigTools.config.getString("mysql_aws9.jdbc.username");
        pass = ConfigTools.config.getString("mysql_aws9.jdbc.password");
        mcbp_aws9 = MysqlOperation.getInstance(driver_aws9, url_aws9, user, pass);
        System.out.println("+++" + mcbp_aws9);
        conn_aws9 = (Connection) mcbp_aws9.getConnection();
        md_aws9 = new MysqlDaoImp();
    }
}
