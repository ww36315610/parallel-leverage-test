package bitget.testcase.offline;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import bitget.utils.db.MysqlDao;

import java.sql.SQLException;
import java.util.*;

public class DataCompare {
    private static final String ESUSEERIDS = "7940904480,8175606709,7859154176,5298989293,1753404761,6687754181,2877333035,2679925087,4804444042,3108925896,9280836108,2514795364,9509424777,2645245065,4138817947,3252852425,1596553261,2112904434,8339332895,1012319099,5530183739,9931716410,1215509405,6976150877,1190279180,4712833401,7291602899,2415899216,3290810070,6348855229,8901667157,9497203427,3967885355,6665882954,148553856,4398058638,4926827587,8181444038,2969714530,4187754587,8519338704,9667104450,2199898921,9181717753,5960078961,7903790553,7754950405,1352529596,8512007140,5465645918,9305313174,2736318425,1565673595,4803242421,8767165056,5642320161,3984091284,8908416830,8981269320,5199599480,3519528446,2004025132,2867755163,9663227172,6571802716,2135959209,4529439426,6116561124,3944070170,7558064221,7664209140,3050807175,4028600650,7119242597,4849620762,4956597225,4214858484,6094368823,5103782800,2730124555,7864217190,8418783059,5251954656,9497203447,3622359887,8917815709,4807035839,9909965460,4029493998,8066665400,8474992719,2271494821,7710008355,4432433482,2226737365";

    public static void main(String[] args) {
        DataCompare dc = new DataCompare();
        //查询活跃userid
        List<String> list = dc.getUserid();

        //查询宽表数据
        String sql = dc.buildSql(list);
        JSONArray sqlData = dc.getDataFromBigData(sql);

        Map<String, Object> mapUserID = Maps.newHashMap();

        //查询活跃feature数据

        //宽表数据 & feature 准确性比对
    }

    //组装feartur接口入参

    //调用mysql查询宽表数据
    JSONArray getDataFromBigData(String sql) {
        JSONArray ja = new JSONArray();
        MysqlDao mysqlDao = new MysqlDao();
        try {
            ja = mysqlDao.select(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ja.size());
        ja.forEach(js -> {
//            System.out.println(js);
        });
        return ja;
    }



    //spilit userID
    List<String> getUserid() {
        List<String> list = new ArrayList<String>();
        list.add(Arrays.toString(ESUSEERIDS.split(",")));
        list = Arrays.asList(ESUSEERIDS.split(","));
        Collections.sort(list);
        System.out.println(list.size());
        return list;
    }

    //组装sql--宽表
    String buildSql(List<String> list) {
        StringBuilder sqlinData = new StringBuilder();
        list.forEach(l -> {
            sqlinData.append(l);
            sqlinData.append("','");
        });
        String sqlStart = "select * from `warehouse_sync`.`ads_usr_offline_features_df` where parent_user_id in ('";
        //P2P
        sqlStart ="select parent_user_id as '父账号id', p2p_in_cnt_30d as '30日内p2p入金次数', p2p_in_amt_30d as '30日内p2p入金金额', first_p2p_in_time as 'p2p首次买币时间', p2p_in_coin_cnt_7d as '7日内p2p入金币种数', p2p_io_coin_cnt_7d as '7日内p2p出入金币种数', p2p_out_cnt_30d as '30日内p2p出金次数', p2p_out_amt_30d as '30日内p2p出金金额',p2p_out_coin_cnt_7d as '7日内p2p出金币种数', first_p2p_out_time as 'p2p首次出金时间' from `warehouse_sync`.`ads_usr_offline_features_df` where parent_user_id in ('";
        String sqlEnd = ");";
        //sqlinData = new StringBuilder(sqlinData.substring(0, sqlinData.lastIndexOf(",")));
        String sql = sqlStart + sqlinData.substring(0, sqlinData.lastIndexOf(",")) + sqlEnd;
        System.out.println(sql);
        return sql;
    }



}
