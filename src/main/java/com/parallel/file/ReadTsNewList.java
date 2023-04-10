package com.parallel.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadTsNewList {
    static String B = "it(";

    public static void main(String[] args) {
        getCellList();
    }
    public static Map<String, List<String[]>> getCellList() {
//        String path = "/Users/apple/Documents/parallel/other/ts/";
        String path = "/Users/apple/Documents/parallel/workspace/core_now/paraspace-core/test-suites/";
        ReadDir rd = new ReadDir();

        Map<String, List<String[]>> map = new HashMap<>();
        rd.getFileNames(path).forEach(s -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!s.startsWith("_")||s.startsWith("_base")||!s.contains("spec.ts"))
                return;

//            if (s.startsWith("_"))
                System.out.println("sheet::: "+s);
            map.put(s, isLegalMagicSquare00(path + s));

        });
        System.out.println("==== ==== ==== ==== ==== ==== ==== "+map.size());
        return map;
    }

    public static List<String[]> isLegalMagicSquare00(String fileName) {
        List<String[]> list = new ArrayList<>();
        List<String> listRead = new ArrayList<>();
        String[] title = null;
        try {
            File myFile = new File(fileName);//通过字符串创建File类型对象，指向该字符串路径下的文件
            if (myFile.isFile() && myFile.exists()) { //判断文件是否存在
                InputStreamReader Reader = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(Reader);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //buffereReader.readLine()按行读取写成字符串
                    if (lineTxt.contains("//"))
                        continue;
                    if (lineTxt.contains(B)) {
                        System.out.println(lineTxt.split("\"")[1]);
//                        listRead.add(lineTxt.split("\"")[1]);
                    }
                }
                Reader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
            title = listRead.toArray(new String[listRead.size()]);
            list.add(title);
            System.out.println("");
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }


}
