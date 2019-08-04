package com.zz.blog.shiro;

import com.google.common.collect.Lists;
import com.zz.blog.user.entity.User;
import com.zz.blog.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<String> permissionList = Lists.newArrayList();
        permissionList.add("uuu");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        LOGGER.info("doGetAuthorizationInfo");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user=userService.getUserByUserName(token.getUsername());
        if (user==null){
            return null;
        }
        LOGGER.info("doGetAuthenticationInfo");
        return new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(), ByteSource.Util.bytes(user.getLoginName()), getName());
    }
}
