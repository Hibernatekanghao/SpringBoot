package com.kh.helloworld.mapper;


import com.kh.helloworld.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into USER (name,account_id ,token,gmt_create ,gmt_modified) values (#{name,},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
//类的时候可以识别，但是是单个参数可能无法识别需要加 @Param
    @Select("select *  from USER WHERE   token = #{token} ")
    User findByToken(@Param("token") String token);
}
