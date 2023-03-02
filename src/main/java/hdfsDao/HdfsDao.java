package hdfsDao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class HdfsDao {

    public static String hdfsPath = "hdfs://192.168.245.110:9000/cristina/";

    //创建目录
    public void createDir(String newpath) {
        String line = hdfsPath + newpath;
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(line), conf, "root");
            fileSystem.mkdirs(new Path(line));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteDir(String path) {
        Configuration conf = new Configuration();
        //
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(path), conf, "root");
            fileSystem.delete(new Path(path), true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //获取hdfs集群的所有数据的对象 FileStatus[] filelist
    public FileStatus[] getDirFromHdfs() {
        Configuration conf = new Configuration();
        FileStatus[] fileStatuses = null;
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf, "root");
            //获取所有的文件路径
            fileStatuses = fileSystem.listStatus(new Path(hdfsPath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileStatuses;
    }

    public FileStatus[] getDirFromHdfs(String url) {
        Configuration conf = new Configuration();
        //开始的默认路径
        String dst = hdfsPath;
        FileStatus[] fileStatuses = null;
        try {
            if (url.length() >= 0) {
                dst = url;
            }
            FileSystem fileSystem = FileSystem.get(URI.create(dst), conf, "root");
            //获取所有的文件路径
            fileStatuses = fileSystem.listStatus(new Path(dst));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileStatuses;
    }

    //从本地上传文件到hdfs集群
    public void copyFileToHdfs(String local, String dest) {
        if (dest.equals("/")) {
            dest = hdfsPath;
        }
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(dest), conf, "root");
            //上传
            fileSystem.copyFromLocalFile(new Path(local), new Path(dest));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //从hdfs集群获取输入流
    public InputStream getInputFromHdfs(String filename) {
        Configuration conf = new Configuration();
        FSDataInputStream open=null;
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(filename), conf, "root");
            //获取输入流
            open = fileSystem.open(new Path(filename));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            return open;
    }
}



