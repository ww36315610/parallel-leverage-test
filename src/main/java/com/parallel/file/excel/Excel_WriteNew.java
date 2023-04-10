package com.parallel.file.excel;

import com.parallel.file.ReadTsNew;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Excel_WriteNew {
    public static void main(String[] args) throws Exception {
        Excel_WriteNew ew = new Excel_WriteNew();
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
        //todo：根据sheetName获取不同的内容
        Map<String, List<String[]>> mapList = ReadTsNew.getCellList();
        Excel_WriteNew ew = new Excel_WriteNew();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        mapList.forEach((k,v)->{
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
            row = xWorkbook.getSheet(sheetNames).createRow(0);
            //这是设置单元格的大小
            row.setHeight((short) (20 * 20));

            //插入表头
            for (short j = 0; j < titleRow.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(titleRow[j]);
                cell.setCellStyle(cellStyle);
            }
            for (int k = 0; k < arrayList.size(); k++) {
                XSSFRow contentRow = null;
//                contentRow = xWorkbook.getSheet(sheetNames).createRow(k + 1);
                String[] array = arrayList.get(k);
                System.out.println("->>>> "+array.length);
                for (int j = 0; j < array.length; j++) {
                    contentRow = xWorkbook.getSheet(sheetNames).createRow(j + 1);
                    System.out.println("------==----"+array[j]);
                    XSSFCell cell = contentRow.createCell(3);
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
}
