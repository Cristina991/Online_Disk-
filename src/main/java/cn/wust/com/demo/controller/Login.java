package cn.wust.com.demo.controller;


import cn.wust.com.demo.dao.UserDao;
import cn.wust.com.demo.dao.UserDaoImpl;
import cn.wust.com.demo.entity.User;
import hdfsDao.HdfsDao;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
public class Login {
//    @Resource
//    UserDao userDao;
    @RequestMapping("/")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister(){return "zhucei";}

    //注册
    @RequestMapping("/userRegister")
    public void getUserRegister(User user, HttpServletResponse response) throws Exception {
        //处理中文乱码的问题
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取Writer对象
        PrintWriter writer = response.getWriter();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        //这种方法需要get和set方法
        String username =user.getUsername();
        String password = user.getPassword();
        if(username.length()>=6 &&password.length()>=6){
            UserDaoImpl userDao = new UserDaoImpl();
            User u = userDao.findUserByName(username);
            if(u==null){
                userDao.addUser(user);
                //添加成功之后，页面应该跳转到登录界面
                writer.println("<script>alert('用户注册成功，请登录!!!');window.location='/'</script>");
            }else{
                //用户存在，证明重新注册
                writer.println("<script>alert('用户已经存在，请重新注册!!!');window.location='/register'</script>");
            }

        }else{
            writer.println("<script>alert('账户或者密码不符合规范，请重新注册!!!');window.location='/register'</script>");
        }
    }

    @RequestMapping("/userlogin")
    public String getUserLogin(User user, Model model,HttpSession session){
        UserDaoImpl userDao = new UserDaoImpl();
        User user1 = userDao.findUserByNameAndPass(user);
        if(user1!=null){
            //获取hdfs的所有路径信息的对象
            HdfsDao hdfsDao = new HdfsDao();
            FileStatus[] list = hdfsDao.getDirFromHdfs();
            //设置作用域
            session.setAttribute("user",user);
            model.addAttribute("filelist",list);
            return "index";
        }else{
            return "login";
        }
    }

}


