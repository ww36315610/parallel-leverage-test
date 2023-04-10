package com.parallel.leverage.utils;

import dnl.utils.text.table.TextTable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextTableUtils {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("key1","1");
        map.put("key2","2");
//        tableInput(map);
       String[] b = new String[]{"zahngsan", "lisi", "wangwu"};
        TextTable tt = new TextTable(b, getTwoArrayObject(map));
        tt.printTable();
//        getTwoArrayObject(map);
    }

    public static Object[][] getTwoArrayObject(Map<String,Object> map) {
        Object[][] object = null;
        if (map != null && !map.isEmpty()) {
            int size = map.size();
            object = new Object[size][2];

            Iterator iterator = map.entrySet().iterator();
            for (int i = 0; i < size; i++) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                object[i][0] = key;
                object[i][1] = value;
            }
        }
        return object;
    }
    public static void tableOut(String[] key,Object[][] value){
        TextTable tt = new TextTable(key, value);
//        tt.getTableModel();
//        tt.setAddRowNumbering(true);
//        tt.setSort(1);
        tt.printTable();
    }
}