package com.mao.test.demo;

/**
 * @Author Mr mao
 * @Date 9:30
 * @Version 1.0
 * @Description
 * @param: inputFile  待解压文件夹/文件
 * @param: destDirPath  解压路径
 */
import java.io.*;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class zipUncompress {

    public static void zipUncompress(String inputFile,String destDirPath) throws Exception {
        File srcFile = new File(inputFile);//获取当前压缩文件
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        ZipFile zipFile = new ZipFile(srcFile);//创建压缩文件对象
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                String dirPath = destDirPath + "/" + entry.getName();
                srcFile.mkdirs();
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("解压完成，耗时：" + (end - start) +" ms");
    }
    public static void main(String[] args) {
        try {
//            zipUncompress("D:\\Users\\admin\\BST_test.zip","D:\\Users\\admin\\unzip");
            SecureRandom random = new SecureRandom();
            for (int i = 0; i < 5; i++) {
                int num = random.nextInt(200000);
                change(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void change(int number) throws IOException {
        String path = "D:/test/mzl%s.txt";
        path = String.format(path, number);
        try(FileWriter fw = new FileWriter(path)) {
            fw.append("\n1235555555555");
        }
        System.out.printf("%s文件被修改了\n", path);

    }
}

