package cn.wust.com.demo.dao;

import cn.wust.com.demo.entity.User;
import cn.wust.com.demo.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        String sql="insert into user values(null,?,?)";
        int num = DBUtils.executeUpdate(sql, user.getUsername(),user.getPassword());
        return num;
    }

    @Override
    public int deleteUser(int id) {
        String sql = "delete from user where id=?";
        int num = DBUtils.executeUpdate(sql, id);
        return num;
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set username=?,password=? where id =?";
        int num = DBUtils.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getId());
        return num;
    }

    @Override
    public User findUserByName(String username) {
        User user = null;
        String sql = "select * from user where username=?";
        ResultSet resultSet = DBUtils.executeQuery(sql, username);

            try {
                while(resultSet.next()){
                    user  = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return user;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from user";
        ResultSet resultSet = DBUtils.executeQuery(sql);
        try {
            while(resultSet.next()){
               User user =  new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
               list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findUserByNameAndPass(User user) {
        User u = null;
        String sql = "select * from user where username=? and password = ?";
        ResultSet resultSet = DBUtils.executeQuery(sql,user.getUsername(),user.getPassword() );

        try {
            while(resultSet.next()){
                u  = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
