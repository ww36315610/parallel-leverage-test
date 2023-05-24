package bitget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhanghao
 * @date 2021/7/7
 */
public class GetIP {
    static String[] cmdParts1 = {"curl", "-H", "Host: www.chineseconverter.com", "-H", "Cache-Control: max-age=0", "--compressed", "https://www.chineseconverter.com/zh-cn/convert/chinese-stroke-order-tool"};
    static String[] cmdParts2 = {"curl", "-v", "--stderr", "--compressed", "http://upex-cloud-eureka.test8.bitget.tools/","-G","grep","upex-data-risk-rest-test"};
    static String[] cmdParts3 = {"curl -v --stderr - ", "http://upex-cloud-eureka.test8.bitget.tools/","|","grep","'upex-data-risk-rest-test'"};
    public static void main(String[] args) {
        // 成功
//        System.out.println(execCmdParts(cmdParts1));
        // 成功
//        System.out.println(execCmdParts(cmdParts2));
        // 失败
//        System.out.println(execCmd(String.join(" ", cmdParts1)));
        // 成功
        String curlJson = execCmd(String.join(" ", cmdParts2));
        curlJson = execCmd("curl -v --stderr -I http://upex-cloud-eureka.test8.bitget.tools/ | grep upex-data-feature-rest-test");
        System.out.println(curlJson);

    }

    public static String execCmdParts(String[] cmdParts) {
        ProcessBuilder process = new ProcessBuilder(cmdParts);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }

            return builder.toString();
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }

        return null;
    }

    private static String execCmd(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println("line=" + line);
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}
