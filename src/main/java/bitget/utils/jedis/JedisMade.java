package bitget.utils.jedis;

import redis.clients.jedis.Jedis;

public class JedisMade {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("eks-redis.test8.bitget.tools",6379);
//        Jedis jedis = new Jedis("127.0.0.1",6379);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
//         jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
//        System.out.println("服务正在运行: " + jedis.ping());
        String key = "UserLoginSuccessBizIdCountWills#33d8a5241f3c75b34b20413193de4510"; // key的名称
//        key ="abc";
        String value = jedis.get(key);
        System.out.println("redis中查询到的   "+value);

        if (jedis.exists(key)) {
            String s = jedis.get(key);
            System.out.println("redis中查询到的");
        } else {
            String s = "应用名";
            jedis.set(key,s);
            System.out.println("数据库中查询");
        }

        jedis.close();
    }
}
