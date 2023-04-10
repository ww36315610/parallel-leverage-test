package com.parallel.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static void main(String[] args) throws IOException {
        String path = "/Users/apple/Documents/parallel/other/one/addresses-provider-registry.spec.ts";
//        path = "/Users/apple/Documents/parallel/other/one/liquidation-underlying.spec.ts";
        FileInputStream fis = new FileInputStream(new File(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        boolean flag = false;

        List<String> words = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            boolean flagE = false;
            //const以后flag=true 开始收集到words中
            if (line.contains("//"))
                continue;
            if (line.contains("const {")) {
                flag = true;
            }
            //testEnv最后一行后置为false后面的不要了
            if (line.contains(" = testEnv")) {
                flag = false;
            }
            if (line.contains("const")&&line.contains("testEnv")) {
                flagE = true;
               String lintText = line.substring(line.indexOf("{")+1,line.indexOf("}"));
                System.out.println("00---=="+lintText);
                if (lintText.contains(",")){
                   for (String k :lintText.split(",")) {
                       words.add(k.trim());
                   }
               }else{
                   words.add(lintText.trim());
               }
            }
            if (flag && !line.contains("const")) {
                words.add(line.trim());
            }
        }
        String key = null;

        for (int i = 0; i < words.size(); i++) {
            key = words.get(i).split(":")[0].toString();
            if (key.contains(","))
                key = key.substring(0, key.indexOf(","));
            if(SolMap.getValues(key)==null) continue;
            System.out.println(key+"------------"+SolMap.getValues(key));
        }
//        words.forEach(x ->
//                System.err.println(x));
        br.close();
    }
}
