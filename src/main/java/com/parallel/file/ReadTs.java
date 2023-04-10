package com.parallel.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadTs {
    static String A = "makeSuite(";
    static String B = "it(";

    public static void main(String[] args) {

        String path = "/Users/apple/Documents/parallel/workspace/core_now/paraspace-core/test-suites/";
        ReadDir rd = new ReadDir();
        rd.getFileNames(path).forEach(s -> {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(s);
            isLegalMagicSquare(path + s);
        });
//        isLegalMagicSquare("/Users/apple/Documents/parallel/other/ts/acl-manager.spec.ts");
    }

    public static void isLegalMagicSquare(String fileName) {
        try {
            File myFile = new File(fileName);//通过字符串创建File类型对象，指向该字符串路径下的文件
            if (myFile.isFile() && myFile.exists()) { //判断文件是否存在
                InputStreamReader Reader = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
                //考虑到编码格式，new FileInputStream(myFile)文件字节输入流，以字节为单位对文件中的数据进行读取
                //new InputStreamReader(FileInputStream a, "编码类型")
                //将文件字节输入流转换为文件字符输入流并给定编码格式

                BufferedReader bufferedReader = new BufferedReader(Reader);
                //BufferedReader从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
                //通过BuffereReader包装实现高效读取

                if (!fileName.contains("aaa.spec.ts")) return;

                String lineTxt = null;
//                System.out.println("------" + fileName + "------");
                System.out.println("TestCase: " + fileName.substring(fileName.lastIndexOf("/") + 1));

//                if (fileName.contains("marketplace.spec.ts")) return;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //buffereReader.readLine()按行读取写成字符串
                    if (lineTxt.contains("//"))
                        continue;
//                    if(lineTxt.contains("supplyERC721(")){
//                        System.out.println(lineTxt);
//                    }
//                    if(lineTxt.contains("mint(")){
//                        System.out.println(lineTxt);
//                    }
                    if (lineTxt.contains(B))

                    System.out.println(lineTxt);
//                    if (lineTxt.contains("it(")) {
//                        System.out.println(lineTxt);
//                    }
//
//                    if (lineTxt.contains("expect(") && lineTxt.contains(".to.")) {
//                        if (lineTxt.contains(".to.be.eq") || lineTxt.contains("to.be.equal") || lineTxt.contains("to.be.lt(")
//                                || lineTxt.contains("to.be.closeTo") || lineTxt.contains("to.be.true") || lineTxt.contains("to.be.fals")
//                                || lineTxt.contains("to.equal")|| lineTxt.contains("to.be.reverted")|| lineTxt.contains("to.be.revertedWith")|| lineTxt.contains("to.not.be.eq"))
//                            continue;
//                        System.out.println(lineTxt);
//                    }

//                    if (lineTxt.contains(A)) {
//                        System.out.println(" describe= " + lineTxt.split("\"")[1]);
//                    }
//                    if (lineTxt.contains(B)) {
//                        System.out.println("  It= " + lineTxt.split("\"")[1]);
//                    }
//                    getMethod(lineTxt);
                }
                Reader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }


    public static void getMethod(String line) {
        boolean flag = false;
        List<String> words = new ArrayList<String>();
        boolean flagE = false;
        //const以后flag=true 开始收集到words中
        if (line.contains("const {")) {
            flag = true;
        }
        //testEnv最后一行后置为false后面的不要了
        if (line.contains(" = testEnv")) {
            flag = false;
        }
        if (line.contains("const") && line.contains("testEnv")) {
            flagE = true;
            String lintText = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
//            System.out.println("00---==" + lintText);
            if (lintText.contains(",")) {
                for (String k : lintText.split(",")) {
                    words.add(k.trim());
                }
            } else {
                words.add(lintText.trim());
            }
        }
        if (flag && !line.contains("const")) {
            words.add(line.trim());
        }
        String key = null;

        for (int i = 0; i < words.size(); i++) {
            key = words.get(i).split(":")[0].toString();
            if (key.contains(","))
                key = key.substring(0, key.indexOf(","));
            if (SolMap.getValues(key) == null) continue;
            System.out.println("    contract= " + SolMap.getValues(key));
        }
    }


    public static void isLegalMagicSquareExcel(String fileName) {
        try {
            File myFile = new File(fileName);//通过字符串创建File类型对象，指向该字符串路径下的文件
            if (myFile.isFile() && myFile.exists()) { //判断文件是否存在
                InputStreamReader Reader = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(Reader);
                String lineTxt = null;
//                System.out.println("------" + fileName + "------");
                System.out.println("TestCase: " + fileName.substring(fileName.lastIndexOf("/") + 1));
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //buffereReader.readLine()按行读取写成字符串
                    if (lineTxt.contains("//"))
                        continue;
                    if (lineTxt.contains("Cannot withdraw ERC-721 from collateral without repaying debt")) {
                        System.out.println(lineTxt);
                    }

                }
                Reader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    static String fileName = "/Users/apple/Documents/parallel/other/ts/";

    public static List<String> getSheetList() {
        String path = "/Users/apple/Documents/parallel/other/ts/";
        ReadDir rd = new ReadDir();
        List<String> sheetList = new ArrayList<>();
        rd.getFileNames(path).forEach(s -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            String newS = s.contains("spec.ts") ? s.substring(0, s.indexOf(".")) : "";
            if ("".equals(s)) {
                return;
            }
            System.out.println(newS);
            if (newS.equals("configurator-liquidation-protocol-fee") || newS.equals("user-configurator-used-as-collateral") || newS.equals("paraspace-protocol-data-provider"))
                return;
            sheetList.add(s);
        });

        return sheetList;
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
//                System.out.println("------" + fileName + "------");
                System.out.println("TestCase: " + fileName.substring(fileName.lastIndexOf("/") + 1));
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //buffereReader.readLine()按行读取写成字符串
                    if (lineTxt.contains("//"))
                        continue;
                    if (lineTxt.contains(B)) {
                        System.out.println(lineTxt);
                        listRead.add(lineTxt);
                    }
                }
                Reader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
            title = list.toArray(new String[list.size()]);
            list.add(title);
            //4.遍历title数组
            for (String string : title) {
                System.out.println(string);
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, List<String[]>> getCellList() {
        String path = "/Users/apple/Documents/parallel/other/ts/";
        ReadDir rd = new ReadDir();

        Map<String, List<String[]>> map = new HashMap<>();
        rd.getFileNames(path).forEach(s -> {
            List<String[]> cellList = new ArrayList<>();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            String newS = s.contains("spec.ts") ? s.substring(0, s.indexOf(".")) : "";
            if ("".equals(s)) {
                return;
            }
            System.out.println(newS);
            if (newS.equals("configurator-liquidation-protocol-fee") || newS.equals("user-configurator-used-as-collateral") || newS.equals("paraspace-protocol-data-provider"))
                return;
            map.put(s, isLegalMagicSquare00(s));
        });
        return map;
    }
}
