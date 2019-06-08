package com.project.ecommerce.repository;

import com.project.ecommerce.bean.User;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper 数据访问类
 */
public interface UserMapper {

    @Select("select * from ec_user where login_name = #{dfff}")
    User login(String loginName);
}