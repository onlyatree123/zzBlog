package com.zz.blog.user.service;

import com.zz.blog.base.constants.BusinessStatus;
import com.zz.blog.base.constants.MobileKey;
import com.zz.blog.base.service.BaseService;
import com.zz.blog.base.util.BeanMapper;
import com.zz.blog.base.util.Digests;
import com.zz.blog.user.entity.User;
import com.zz.blog.user.repository.UserDao;
import com.zz.blog.user.vo.UserVo;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.zz.blog.base.constants.MobileKey.HASH_INTERATIONS;

@Service
public class UserService extends BaseService{
    @Autowired
    private UserDao userDao;

    public User getUserById(Long userId){
        return  userDao.findById(userId).get();
    }

    public void registerUser(UserVo vo){
        checkRegisterUser(vo);
        User user=BeanMapper.map(entryptPassword(vo),User.class);
        user.setCretateDate(new Date());
        user.setUpdateDate(new Date());
        userDao.save(user);
    }

    public void checkRegisterUser(UserVo vo){
          if (vo.getLoginName()==null){
              throwException(BusinessStatus.ERROR,"password is null");
          }
          if (vo.getAliasName()==null){
              throwException(BusinessStatus.ERROR,"aliasName is null");
          }
          if (userDao.findUserByLoginName(vo.getLoginName())!=null){
              throwException(BusinessStatus.ERROR,"user already exists");
          }
    }

    public User getUserByUserName(String loginName){
        return  userDao.findUserByLoginName(loginName);
    }

    public static UserVo entryptPassword(UserVo user){
        byte[] salt= Digests.generateSalt(MobileKey.SALT_NUM);
        user.setSalt(Hex.encodeHexString(salt));
        byte[] hashPassword = Digests.sha1(user.getLoginPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Hex.encodeHexString(hashPassword));
        return user;
    }

    public Map<String,String> userLogin(UserVo vo){
        try {
            UsernamePasswordToken token=new UsernamePasswordToken();
            token.setUsername(vo.getLoginName());
            token.setPassword(vo.getLoginPassword().toCharArray());
            SecurityUtils.getSubject().login(token);
        }catch (){

        }
    }
}
