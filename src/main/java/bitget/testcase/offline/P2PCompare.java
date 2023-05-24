package bitget.testcase.offline;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parallel.leverage.utils.FileOperation;

import java.util.List;
import java.util.Map;

public class P2PCompare extends Basic_DB {

    /**
     * 1.梳理一批有数据的user_id [shoudong]
     * 2.P2P 类型宽表数据查询，组装sql
     * 3.链接业务表-指标sql组装
     * 4.执行sql，比对结果
     */
    public static void main(String[] args) {
            String aa = "{\"referer\":\"upex-newwallet-job-test\",\"amount\":0.1000000000000000,\"address\":\"0xd7a367e24936d3f6c5772062ec0c5ce9a2b9269b82ef36d323fc2c07f9456960\",\"actualAmount\":0.1000000000000000,\"ip\":\"127.0.0.1\",\"addressTag\":\"\",\"userAgent\":\"Java/11.0.16\",\"userId\":\"1304626433\",\"chainCoinId\":5523,\"deviceId\":\"\",\"platform\":\"1\",\"deviceToken\":\"\",\"coinId\":148,\"appname\":\"bitget\",\"osVersion\":\"\",\"bizId\":\"1043294699021946880\",\"gas\":0E-16,\"fromAddress\":\"0x1e13b4a85e01796585bc326d6433ac750db0197ff77fddea6ced75f5dc882900\",\"applyTime\":\"1684466795044\"}";
        String file = "/Users/wangjian/Documents/bitget/workspace/parallel-leverage-test/src/main/resources/user_id";
        List<String> list = FileOperation.readFileByLineString(file);
        List<String> trueList = Lists.newArrayList();
        Map<String, String> errMap = Maps.newHashMap();
        String feature = "first_p2p_in_time";
        list.stream().forEach(l -> {
            Map<String, String> userMaps = buildSqlP2P(l);
            JSONArray awsBody =md_aws9.select(userMaps.get("aws"),conn_aws9);
            JSONArray p2pBody = md_test9.select(userMaps.get("p2p"),conn_test9);
//                System.out.println(p2pBody);
            if (awsBody.size() == 0) {
                errMap.put(l.toString(), "数据无返回-aws");
                return;
            }
            if (p2pBody.size() == 0) {
//                    String keyAwstmp = p2pBody.getJSONObject(0).getString("first_p2p_in_time");
                String aws_value = awsBody.getJSONObject(0).getString(feature) != null ? awsBody.getJSONObject(0).getString(feature) : "null";
                if (aws_value.equals("null")) {
                    trueList.add(l);
                    return;
                }
                errMap.put(l.toString(), "数据无返回-p2p");
                return;
            }
            String keyAws = p2pBody.getJSONObject(0).getString("b_code");
            String keyP2P = p2pBody.getJSONObject(0).getString("b_code");
            String aws_value = awsBody.getJSONObject(0).getString(keyAws) != null ? awsBody.getJSONObject(0).getString(keyAws) : "null";
            String p2p_value = p2pBody.getJSONObject(0).getString(keyP2P) != null ? p2pBody.getJSONObject(0).getString(keyP2P) : "null";
            if (keyP2P.contains("_time")) {
                keyP2P = "update_time";
                p2p_value = p2pBody.getJSONObject(0).getString(keyP2P) != null ? p2pBody.getJSONObject(0).getString(keyP2P) : "null";
            }

            if (!p2p_value.equals(aws_value)) {
                System.out.println(l + "--p2p_value--" + p2p_value + "--aws_value--" + aws_value);
                errMap.put(l.toString(), "指标不匹配");
            } else {
                trueList.add(l);
            }

        });
        if (trueList.size() > 0) {
            System.out.println("通过userid【" + trueList.size() + "】个");
        } else {
            System.err.println("=============================一个都没通过=============================");
        }
        errMap.forEach((k, v) -> {
            if (v.contains("数据无返回")) {
                System.err.println(k + "==" + v);
            }
        });
        errMap.forEach((k, v) -> {
            if (v.equals("指标不匹配")) {
                System.out.println(k + "==" + v);
            }
        });
    }

    String aa = "{\"amountToU\":\"18.33734\",\"appName\":\"BITGET\",\"applyId\":\"1022707885689446400\",\"billType\":\"BUY\",\"cardBank\":\"BANK OF BEIJING\",\"cardBin\":\"621468\",\"cardCountry\":\"CN\",\"cardHolderName\":\"\",\"cardId\":\"src_65muh3xmtkbuzfmdmzpka4n7li\",\"cardNoLastFour\":\"8612\",\"cardOrganization\":\"002\",\"cardType\":\"DEBIT\",\"channelProvider\":\"CHECKOUT\",\"deviceId\":\"6f090220d1ecc9f41c60e2f538c78003\",\"deviceToken\":\"641c0593afUWte1MpvcOCDRZrn9EgCZHTEt7Mfa1\",\"eventTime\":\"2023-04-15 16:01:22\",\"expiryMonth\":\"12\",\"expiryYear\":\"2025\",\"fee\":\"0.36\",\"feeToU\":\"0.33007212\",\"ip\":\"127.0.0.1\",\"legalTenderAmount\":\"20\",\"legalTenderType\":\"EUR\",\"platform\":\"WEB\",\"userAgent\":\" \",\"userId\":\"1584210239\",\"valuationTenderAmount\":\"20.59827864\",\"valuationTenderType\":\"USDT\",\"countryCode\":\"USA\"}";

    //2.P2P 类型宽表数据查询，组装sql
    static Map<String, String> buildSqlP2P(String user_id) {
        String awsStart = "select parent_user_id as '父账号id',p2p_in_cnt_30d as '30日内p2p入金次数',p2p_in_amt_30d as '30日内p2p入金金额', first_p2p_in_time as 'p2p首次买币时间', p2p_in_coin_cnt_7d as '7日内p2p入金币种数', p2p_io_coin_cnt_7d as '7日内p2p出入金币种数', p2p_out_cnt_30d as '30日内p2p出金次数', p2p_out_amt_30d as '30日内p2p出金金额',p2p_out_coin_cnt_7d as '7日内p2p出金币种数', first_p2p_out_time as 'p2p首次出金时间' from `warehouse_sync`.`ads_usr_offline_features_df` where parent_user_id ='";
        String awsEnd = "';";
        String awsSql = awsStart + user_id + awsEnd;

//      #p2p_in_cnt_30d as '30日内p2p入金次数'
        String p2pSql = "select 'p2p_in_cnt_30d' as 'b_code','30日内p2p入金次数' as 'b_name',`order_type` as '1买2卖',`buy_user_id` as '买家',count(`state`) as 'p2p_in_cnt_30d'\n" +
                "from upex_otc.c2c_order\n" +
                "where buy_user_id = '" + user_id + "' and order_type = 1 and state = 3 and update_time between '2023-04-19 00:00:00.000' and '2023-05-19 00:00:00.000';";

        //#p2p_in_amt_30d as '30日内p2p入金金额'
        p2pSql = "select 'p2p_in_amt_30d' as 'b_code','30日内p2p入金次数' as 'b_name',`order_type` as '1买2卖',`buy_user_id` as '买家',sum(`amount`*`price`) as 'p2p_in_amt_30d'\n" +
                "from upex_otc.c2c_order\n" +
                "where buy_user_id = '" + user_id + "' and order_type = 1 and state = 3 and update_time between '2023-04-19 00:00:00.000' and '2023-05-19 00:00:00.000';\n";
/**
 //#first_p2p_in_time as 'p2p首次买币时间'
 p2pSql = "select 'first_p2p_in_time' as 'b_code','p2p首次买币时间' as 'b_name',`order_type` as '1买2卖',`buy_user_id` as '买家',`update_time` as 'first_p2p_in_time'\n" +
 "from upex_otc.c2c_order\n" +
 "where buy_user_id = '" + user_id + "' and order_type = 1 and state = 3 order by update_time limit 1;";

 //p2p_in_coin_cnt_7d as '7日内p2p入金币种数'
 p2pSql = "select 'p2p_in_coin_cnt_7d' as 'b_code','7日内p2p入金币种数' as 'b_name',`order_type` as '1买2卖',`buy_user_id` as '买家',`coin_code` as '币种',count(distinct `coin_code`) as 'p2p_in_coin_cnt_7d'\n" +
 "from upex_otc.c2c_order\n" +
 "where buy_user_id = '" + user_id + "' and order_type = 1 and state = 3 and update_time between '2023-05-12 00:00:00.000' and '2023-05-19 00:00:00.000';";

 //p2p_io_coin_cnt_7d as '7日内p2p出入金币种数'
 p2pSql = "select 'p2p_io_coin_cnt_7d' as 'b_code','7日内p2p入金币种数' as 'b_name',`order_type` as '1买2卖',`buy_user_id` as '买家',`sell_user_id` as '卖家',count(distinct`coin_code`) as 'p2p_io_coin_cnt_7d'\n" +
 "from upex_otc.c2c_order\n" +
 "where (buy_user_id = '" + user_id + "' or sell_user_id = '" + user_id + "' ) and order_type in (1,2) and state = 3 and update_time between '2023-05-12 00:00:00.000' and '2023-05-19 00:00:00.000';";

 //卖-出金:order_type=2[卖] sell_user_id=userId state=3[交易完成] pay_time[统计区间] update_time[首次出金时间]
 //p2p_out_cnt_30d as '30日内p2p出金次数'
 p2pSql = "select 'p2p_out_cnt_30d' as 'b_code','30日内p2p出金次数' as 'b_name',`order_type` as '1买2卖',`sell_user_id` as '卖家',count(`state`) as 'p2p_out_cnt_30d'\n" +
 "from upex_otc.c2c_order\n" +
 "where sell_user_id = '" + user_id + "' and order_type = 2 and state = 3 and update_time between '2023-04-19 00:00:00.000' and '2023-05-19 00:00:00.000';";

 //p2p_out_amt_30d as '30日内p2p出金金额'
 p2pSql = "select 'p2p_out_amt_30d' as 'b_code','30日内p2p出金金额' as 'b_name',`order_type` as '1买2卖',`sell_user_id` as '卖家',sum(`amount`*`price`) as 'p2p_out_amt_30d'\n" +
 "from upex_otc.c2c_order\n" +
 "where sell_user_id = '" + user_id + "' and order_type = 2 and state = 3 and update_time between '2023-04-19 00:00:00.000' and '2023-05-19 00:00:00.000';";
 /**
 //p2p_out_coin_cnt_7d as '7日内p2p出金币种数'
 p2pSql = "select 'p2p_out_coin_cnt_7d' as 'b_code','7日内p2p出金币种数' as 'b_name',`order_type` as '1买2卖',`sell_user_id` as '卖家',`coin_code` as '币种',count(distinct `coin_code`) as 'p2p_out_coin_cnt_7d'\n" +
 "from upex_otc.c2c_order\n" +
 "where sell_user_id = '" + user_id + "' and order_type = 2 and state = 3 and update_time between '2023-05-12 00:00:00.000' and '2023-05-19 00:00:00.000';";

 //first_p2p_out_time as 'p2p首次出金时间'
 p2pSql = "select 'first_p2p_out_time' as 'b_code','p2p首次出金时间' as 'b_name',`order_type` as '1买2卖',`sell_user_id` as '卖家',`update_time` as 'first_p2p_out_time'\n" +
 "from upex_otc.c2c_order\n" +
 "where sell_user_id = '" + user_id + "' and order_type = 2 and state = 3 order by update_time limit 1;";
 **/
        Map<String, String> mapSql = Maps.newHashMap();
        mapSql.put("aws", awsSql);
        mapSql.put("p2p", p2pSql);
        return mapSql;
    }

}
