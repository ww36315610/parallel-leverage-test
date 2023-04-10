package com.parallel.file.excel;

import com.parallel.file.ReadTs;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Excel_Write {
    public static void main(String[] args) throws Exception {
        Excel_Write ew = new Excel_Write();
//        ew.createExcel(filePath, sheetList, title, arrayList);
        ew.writeSheet();
    }

    public void writeSheet() throws Exception {
        //首先先判断该路径的Excel表是否存在,不存在则新建一个文件
        String filePath = "/Users/apple/Documents/parallel/case/Contract/" + "demo.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        String titleRow[] = {"ID", "Tags", "Priority", "Scenarios", "Verification", "Data", "Case Owner", "Case Reviewer", "Script Owner", "Script Path", "Script Reviewer", "Status", "Comment"};
        //TODO:1)sheetName list
        //定义sheet名和表头信息 这里表头信息用数组进行存储,sheet表名用List存储
        List<String> sheetList = ReadTs.getSheetList();


        System.out.println("==== "+sheetList.size());
        //整理要写入Excel表中的数据 一般一行就是一个对象,因此我们要写入Excel表的对象字段先存到一个数组中,再把多个对象信息存储到List中.

        //这里假设的数据 userList 使用过程中记得换成自己的数据集
        //todo：根据sheetName获取不同的内容
        Map<String, List<String[]>> arrayList = ReadTs.getCellList();

        Excel_Write ew = new Excel_Write();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        arrayList.forEach((k,v)->{
            ew.createExcel(filePath, k, titleRow, v, xssfWorkbook);
        });
    }

    public void createExcel(String filePath, String sheetNames, String titleRow[], List<String[]> arrayList, XSSFWorkbook xWorkbook) {

        //新建文件
        FileOutputStream fileOutputStream = null;
        XSSFRow row = null;
        //HSSFRow row = null;
        try {

            XSSFCellStyle cellStyle = xWorkbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
            //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
            XSSFSheet sheet = xWorkbook.createSheet(sheetNames);
            //添加表头
            System.out.println(sheetNames);
            row = xWorkbook.getSheet(sheetNames).createRow(0);
            //这是设置单元格的大小
            row.setHeight((short) (20 * 20));

            //插入表头
            for (short j = 0; j < titleRow.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(titleRow[j]);
                cell.setCellStyle(cellStyle);
            }
            //插入内容
            for (int k = 10; k < arrayList.size(); k++) {
                XSSFRow contentRow = null;
                contentRow = xWorkbook.getSheet(sheetNames).createRow(k + 1);
                String[] array = arrayList.get(k);
                for (int j = 0; j < array.length; j++) {
                    XSSFCell cell = contentRow.createCell(j);
                    cell.setCellValue(array[j]);
                    cell.setCellStyle(cellStyle);
                }
            }
            fileOutputStream = new FileOutputStream(filePath);
            xWorkbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void writeSheet001() throws IOException {
        //首先先判断该路径的Excel表是否存在,不存在则新建一个文件
        String filePath = "/Users/apple/Documents/parallel/case/Contract/" + "demo.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        //定义sheet名和表头信息 这里表头信息用数组进行存储,sheet表名用List存储
        String title[] = {"姓名", "性别", "年龄"};
        List<String> sheetList = new ArrayList<>();
        sheetList.add("没有腹肌的程序猿");
        sheetList.add("aaaaa");

        //整理要写入Excel表中的数据 一般一行就是一个对象,因此我们要写入Excel表的对象字段先存到一个数组中,再把多个对象信息存储到List中.
        List<String[]> arrayList = new ArrayList<>();
        //这里假设的数据 userList 使用过程中记得换成自己的数据集
        for (int i = 0; i < arrayList.size(); i++) {
            //这里定义数组大小根据自己需要存多少字段进行决定
            String userArr[] = new String[3];
            userArr[0] = "userName";
            userArr[1] = "userSex";
            userArr[2] = "userAge";
            //将数组存储arrayList中
            arrayList.add(userArr);
        }
    }

    public void createExcel(String filePath, List<String> sheetNames, String titleRow[], List<String[]> arrayList) {
        XSSFWorkbook xWorkbook = new XSSFWorkbook();
        //新建文件
        FileOutputStream fileOutputStream = null;
        XSSFRow row = null;
        //HSSFRow row = null;
        try {

            XSSFCellStyle cellStyle = xWorkbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
            //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
            for (int i = 0; i < sheetNames.size(); i++) {
                XSSFSheet sheet = xWorkbook.createSheet(sheetNames.get(i));
                //添加表头
                row = xWorkbook.getSheet(sheetNames.get(i)).createRow(0);
                //这是设置单元格的大小
                row.setHeight((short) (20 * 20));

                //插入表头
                for (short j = 0; j < titleRow.length; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(titleRow[j]);
                    cell.setCellStyle(cellStyle);
                }
                //插入内容
                for (int k = 10; k < arrayList.size(); k++) {
                    XSSFRow contentRow = null;
                    contentRow = xWorkbook.getSheet(sheetNames.get(i)).createRow(k + 1);
                    String[] array = arrayList.get(k);
                    for (int j = 0; j < array.length; j++) {
                        XSSFCell cell = contentRow.createCell(j);
                        cell.setCellValue(array[j]);
                        cell.setCellStyle(cellStyle);
                    }
                }
                fileOutputStream = new FileOutputStream(filePath);
                xWorkbook.write(fileOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
