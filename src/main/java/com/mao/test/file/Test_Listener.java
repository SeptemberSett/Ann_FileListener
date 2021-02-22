//package com.mao.test.file;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.io.watch.SimpleWatcher;
//import cn.hutool.core.io.watch.WatchMonitor;
//import cn.hutool.core.io.watch.Watcher;
//import cn.hutool.core.io.watch.watchers.DelayWatcher;
//import cn.hutool.core.lang.Console;
//import com.mao.test.dao.PPProDocPathMapper;
//import com.mao.test.entity.PPProDocPathDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.WatchEvent;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @Author Mr mao
// * @Date 15:23
// * @Version 1.0
// * @Description
// */
////@Service
//public class Test_Listener implements ApplicationRunner {
//
//    @Value("${sysconfig.prodocpdownloadprefix}")
//    private String prodocpdownloadprefix;
//
//    @Autowired
//    PPProDocPathMapper ppproDocPathMapper;
//
//    public void testMonitor(){
//        Watcher watcher = new Watcher(){
//            @Override
//            public void onCreate(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("文件判定为创建：{}-> {}", currentPath, obj);
//                //System.out.println(event);
//                try {
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        PPProDocPathDTO proDocPathDto =new PPProDocPathDTO();
//
//                        proDocPathDto.setId(UUID.randomUUID().toString());
//                        proDocPathDto.setStatus("1");
//
//                        proDocPathDto.setCreate_By("system");
//                        proDocPathDto.setCreate_Time(new Date());
//                        proDocPathDto.setFile_Name(event.context().toString().toUpperCase());
//                        //获取文件路径
//                        proDocPathDto.setFile_Path((""+currentPath + "\\"+event.context()));
//                        String fileType=event.context().toString().substring(event.context().toString().lastIndexOf("."));
//                        proDocPathDto.setFile_Type(fileType);
//                        proDocPathDto.setRevision("20");
//                        Calendar cal = Calendar.getInstance();
//                        //cal.setTimeInMillis();
//                        //String downUrl=prodocpdownloadprefix+"?downsource=pp&id="+proDocPathDto.getFileName();
//                        String downUrl=prodocpdownloadprefix+"?downsource=pp&id="+proDocPathDto.getFile_Name();
//                        proDocPathDto.setDownLoad_Doc_Address(downUrl);
//                        proDocPathDto.setRemarks("文件最后修改日期="+ formatter.format(cal.getTime()));
//                        ppproDocPathMapper.insert(proDocPathDto);
//                        System.out.println("往数据库里新增了一条数据");
//                    }catch (Exception e){
//                        System.out.println("插入操作出现异常" + e.getMessage());
//                    }
//            }
//            @Override
//            public void onModify(WatchEvent<?> event, Path currentPath) {
//                String str = event.context().toString();
//                Console.log("文件判定为修改：{}-> {}", currentPath, str);
//                //如果是目录则不进行查找和修改工作
//                if(str.contains(".")){
//                //先查再改
//                    try {
//                        PPProDocPathDTO ppProDocPathDTO = ppproDocPathMapper.queryByName((event.context()).toString().toUpperCase());
//                        //String nName = event.context().toString().toUpperCase();
//                        //System.out.println(nName);
//                        ppProDocPathDTO.setUpdate_Time(new Date());
//                        ppProDocPathDTO.setUpdate_By("system");
//                        ppproDocPathMapper.updateByName(ppProDocPathDTO);
//                        System.out.println("修改了一条数据");
//                    }catch (Exception e){
//                        System.out.println("修改操作出现异常" + e.getMessage());
//                    }
//                }else{
//                    System.out.println("子文件夹修改导致的目录变化，不做处理！");
//                }
//            }
//            @Override
//            public void onDelete(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("文件判定为删除：{}-> {}", currentPath, obj);
//                try {
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("file_Name", event.context().toString().toUpperCase());
//                        map.put("delete_Time", new Date());
//                        map.put("delete_By", "system");
//                        ppproDocPathMapper.deleteProdocInfo(map);
//                        System.out.println("删除了一条数据");
//                    }catch (Exception e){
//                        System.out.println("删除操作出现异常" + e.getMessage());
//                    }
//            }
//            @Override
//            public void onOverflow(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("Overflow：{}-> {}", currentPath, obj);
//                //补偿措施
//            }
//        };
//        WatchMonitor watchMonitor = WatchMonitor.createAll("D:\\test0", new DelayWatcher(watcher, 500));
//        watchMonitor.setWatcher(watcher);
//        watchMonitor.setMaxDepth(5);
//        watchMonitor.start();
//        System.out.println("========正在启动监控======");
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        this.testMonitor();
//    }
//}
