package com.weishuyu.service;

import com.weishuyu.dao.LinkedDao;
import com.weishuyu.domain.User;
import com.weishuyu.exception.MsgException;

import java.util.LinkedHashMap;

public class UserService {
    LinkedDao linkedDao=new LinkedDao();
    /**
     * 此层逻辑处理：用户是否存在
     * @param user 用户数据信息
     */
    public void registUser(User user) {
        //用户是否存在
        boolean existUser=linkedDao.reasonableUser(user);
        if (existUser){
            throw new MsgException("用户名已经存在");
        }else {
            //不存在，则可以注册
            LinkedDao.addDatebase(user);
        }

    }

    /**
     * 把值传递给Dao层，检查是否匹配
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username, String password) {
      User user=linkedDao.checkUsernameAndPassword(username,password);
      if (user!=null){
          return user;
      }else {
          throw new MsgException("用户名和密码不匹配");
      }
    }
}
