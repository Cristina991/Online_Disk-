package cn.wust.com.demo.dao;

import cn.wust.com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    int addUser(User user);
    int deleteUser(int id);
    int updateUser(User user);
    User findUserByName(String username);
    List<User> findAll();
    User findUserByNameAndPass(User user);
}
