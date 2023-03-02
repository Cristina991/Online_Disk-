package cn.wust.com.demo.controller;

import hdfsDao.HdfsDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class UpAndLoad {

    @RequestMapping("/upload")
    public String getUpload(String url, MultipartFile file, HttpSession session){
        HdfsDao hdfsDao = new HdfsDao();
        if(!file.isEmpty()&&file.getSize()>0){
            //文件存在  获取文件的名字 相对路径
            String filename = file.getOriginalFilename();
            //获取文件的绝对路径
            String realPath = session.getServletContext().getRealPath("upload");
            //增加一个中转站，先把数据放进到中转站
            String destPath = realPath +"/"+filename;
            try {
                file.transferTo(new File(destPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //调用hdfsdao的上传方法
            hdfsDao.copyFileToHdfs(destPath,url);

        }
        //处理url
        String encode=null;
        try {
            encode = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:readdir?url="+encode;
    }

    @RequestMapping("/download")
    public void getDownload(String fileName, HttpServletResponse response){
        response.setHeader("Content-Disposition","attachment;filename="+fileName);

//        HdfsDao hdfsDao = new HdfsDao();
//        //把文件下载到本地
//        hdfsDao.copyFileToLocal(FileName,"D:\\IdeaProjects\\wust_随存通\\src\\main\\webapp\\download");
         //使用IO流自己去完成，先打开hdfs输出流
        HdfsDao hdfsDao = new HdfsDao();
        InputStream is = hdfsDao.getInputFromHdfs(fileName);
        //获取输出流
        try {
            ServletOutputStream os = response.getOutputStream();
            //读写流程
            byte[] buff = new byte[1024];
            int i =0;
            while((i=is.read(buff))!=-1){
                os.write(buff,0,buff.length);
                os.flush();
            }
            //关流
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
