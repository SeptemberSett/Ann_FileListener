package com.mao.test.file;

import com.mao.test.dao.PPProDocPathMapper;
import com.mao.test.entity.PPProDocPathDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author Mr mao
 * @Date 10:10
 * @Version 1.0
 * @Description
 */

@Service
public class File_Listener implements ApplicationRunner {

    @Value("${sysconfig.prodocpdownloadprefix}")
    private String prodocpdownloadprefix;

    @Autowired
    PPProDocPathMapper ppproDocPathMapper;

    public void fileMonitor() throws Exception {

        System.out.println("========正在启动监控======");
        String filePath = ("D:\\test0");
        //获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get(filePath).register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);

        File file = new File(filePath);
        LinkedList<File> fList = new LinkedList<File>();
        fList.addLast(file);
        while (fList.size() > 0) {
            File f = fList.removeFirst();
            if (f.listFiles() == null)
                continue;
            for (File file2 : f.listFiles()) {
                if (file2.isDirectory()) {//下一级目录
                    fList.addLast(file2);
                    //依次注册子目录
                    Paths.get(file2.getAbsolutePath()).register(watchService
                            , StandardWatchEventKinds.ENTRY_CREATE
                            , StandardWatchEventKinds.ENTRY_MODIFY
                            , StandardWatchEventKinds.ENTRY_DELETE);
                }
            }
        }

        while (true) {
            // 获取下一个文件改动事件
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                //key.cancel();
                //System.out.println(event.context() + " --> " + event.kind());
                //System.out.println(file.getName() +"    " + filePath);

                /**
                 * 利用文件时间戳，防止触发两次
                 * created by maozl
                 */
                //Path path = Paths.get(configFilePath);
                Path path = Paths.get(filePath);
                Path changed = (Path) event.context();
                Path absolute = path.resolve(changed);
                //发生内容变化的文件
                File configFile = absolute.toFile();
                //if (configFileName.equals(configFile.getName())) {
                if (event.context().equals(configFile.getName())) {
                    long lastModified = configFile.lastModified();
                    // 利用文件时间戳，防止触发两次
                    //if (lastModified != configFileModifiedTime && configFile.length() > 0) {
                    if (lastModified != file.lastModified() && configFile.length() > 0) {
                        //configFileModifiedTime = lastModified;
                        file.setLastModified(lastModified);
                        //addDirWatch(configFile);
                    }
                }

                if(event.kind().name().equals("ENTRY_CREATE")){
                    System.out.println(event.context() +" 判断为--> " + event.kind());

                    WatchEvent<Path> watchEvent = (WatchEvent<Path>) event;
                    Path watchable = ((Path) key.watchable()).resolve(watchEvent.context());
                    if(Files.isDirectory(watchable)) {
                        watchable.register(watchService
                                , StandardWatchEventKinds.ENTRY_CREATE
                                , StandardWatchEventKinds.ENTRY_MODIFY
                                , StandardWatchEventKinds.ENTRY_DELETE);
                    }else{
                        //新增
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            PPProDocPathDTO proDocPathDto =new PPProDocPathDTO();

                            //proDocPathDto.setId(UUID.randomUUID().toString());
                            proDocPathDto.setStatus("1");

                            proDocPathDto.setCreate_By("system");
                            proDocPathDto.setCreate_Time(new Date());
                            proDocPathDto.setFile_Name(event.context().toString().toUpperCase());
                            //获取文件路径
                            proDocPathDto.setFile_Path(filePath);
                            String fileTyle=event.context().toString().substring(event.context().toString().lastIndexOf("."));
                            proDocPathDto.setFile_Type(fileTyle);
                            proDocPathDto.setRevision("20");
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeInMillis(file.lastModified());
                            String downUrl=prodocpdownloadprefix + "?downsource=pp&id="+proDocPathDto.getFile_Name();
                            proDocPathDto.setDownLoad_Doc_Address(downUrl);
                            proDocPathDto.setRemarks("文件最后修改日期="+ formatter.format(cal.getTime()));
                            ppproDocPathMapper.insert(proDocPathDto);
                            System.out.println("往数据库里新增了一条数据");
                        }catch (Exception e){
                            System.out.println("插入操作出现异常" + e.getMessage());
                        }
                    }
                }else if(event.kind().name().equals("ENTRY_DELETE")) {
                    System.out.println(event.context() +" 判断为--> " + event.kind());
                    //删除
                    try {
                        Map<String, Object> map = new HashMap<>();
                        map.put("file_Name", event.context().toString().toUpperCase());
                        map.put("delete_Time", new Date());
                        map.put("delete_By", "system");
                        ppproDocPathMapper.deleteProdocInfo(map);
                        System.out.println("删除了一条数据");
                    }catch (Exception e){
                        System.out.println("删除操作出现异常" + e.getMessage());
                    }
            }else{
                    System.out.println(event.context() +" 判断为--> " + event.kind());

                    WatchEvent<Path> watchEvent = (WatchEvent<Path>) event;
                    Path watchable = ((Path) key.watchable()).resolve(watchEvent.context());
                    if(!Files.isDirectory(watchable)) {
                        //先查再改
                        try {
                            PPProDocPathDTO ppProDocPathDTO = ppproDocPathMapper.queryByName((event.context()).toString().toUpperCase());
                            ppProDocPathDTO.setUpdate_Time(new Date());
                            ppProDocPathDTO.setUpdate_By("system");
                            ppproDocPathMapper.updateById(ppProDocPathDTO);
                            System.out.println("修改了一条数据");
                        }catch (Exception e){
                            System.out.println("修改操作出现异常" + e.getMessage());
                        }
                    }else{
                        System.out.println("子文件夹修改导致的目录变化，不做处理！");
                    }
                }
            }

                // 重设WatchKey
                boolean valid = key.reset();
                // 如果重设失败，退出监听
                if (!valid) {
                    break;
                }
            }
        }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.fileMonitor();
    }
}
