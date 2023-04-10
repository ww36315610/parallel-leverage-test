package com.parallel.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadDir {

    public static void main(String[] args) {
        String path = "/Users/apple/Documents/parallel/other/ts";
        ReadDir rd =  new ReadDir();
        rd.getFileNames(path).forEach(s->{
            System.out.println(s);
        });
    }
    /**
     * 得到文件名称
     *
     * @param path 路径
     * @return {@link List}<{@link String}>
     */
    public List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(file, fileNames);
    }

    /**
     * 得到文件名称
     *
     * @param file      文件
     * @param fileNames 文件名
     * @return {@link List}<{@link String}>
     */
    private List<String> getFileNames(File file, List<String> fileNames) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileNames(f, fileNames);
            } else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }

}
