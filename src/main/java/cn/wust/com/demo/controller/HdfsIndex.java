package cn.wust.com.demo.controller;

import hdfsDao.HdfsDao;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class HdfsIndex {
    //读取文件
    @RequestMapping("/readdir")
    public String getReaddir(String url, Model model){
        HdfsDao hdfsDao = new HdfsDao();
        FileStatus[] list= null;
        if(url!=null){
            if(url.equals("/")) {
                list = hdfsDao.getDirFromHdfs();
            }else{
                list = hdfsDao.getDirFromHdfs(url);
            }
            model.addAttribute("filelist",list);
            model.addAttribute("currenturl",url);
            model.addAttribute("currenturl1",url.replace(HdfsDao.hdfsPath,""));
            return "index";
        }
        return null;

    }

    @RequestMapping("/createdir")
    public String getCreatedir(String newdirname){
        String newpath = newdirname;
        HdfsDao hdfsDao = new HdfsDao();
        if(newdirname.length()>0){

            if(newdirname.contains("hdfs://192.168.245.110:9000/cristina")){
                 newpath = newdirname.substring(36);
            }
            hdfsDao.createDir(newpath);

        }
        String path=newpath.substring(0,newpath.lastIndexOf("/"));
        //编码
        String encode = null;
        try {
            encode = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //newdirname /bb
        String newpathss = HdfsDao.hdfsPath+encode;
        return "redirect:readdir?url="+newpathss;
    }

    @RequestMapping("/delete")
    public String getDelete(String filePath){
        HdfsDao hdfsDao = new HdfsDao();
        if(filePath.length()>0){
            hdfsDao.deleteDir(filePath);

        }
        String substring = filePath.substring(0, filePath.lastIndexOf("/"));
        String encode= null;
        //URL编码
        try {
            encode = URLEncoder.encode(substring, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/readdir?url="+encode;
    }

}
