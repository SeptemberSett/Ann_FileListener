package com.mao.test.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @Author Mr mao
 * @Date 16:45
 * @Version 1.0
 * @Description
 */
public class RandomNumber {

    public static void main(String[] args) throws Exception {
        set3();
    }
    public static void set3() throws IOException {
        for(int h=0;h<150000;h++) {

            File file = new File("D://test/mzl"+h+".txt");
            File parentFile = file.getParentFile();
            if(!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            List<String> actions = Arrays.asList("riding", "running", "swimming");
            List<String> userNames = Arrays.asList("lily", "tom", "bob");
            List<String> times = Arrays.asList("2020-1-1", "2020-2-2");
            for(int i= 0; i<10; i++) {
                for(String username: userNames) {
                    for(String action: actions) {
                        for(String time: times) {
                            String request = username+" is "+action+" on "+time+"\r\n";
                            bw.write(request);
                        }
                    }
                }
            }
            bw.flush();
            bw.close();
            fw.close();
        }
    }

}

