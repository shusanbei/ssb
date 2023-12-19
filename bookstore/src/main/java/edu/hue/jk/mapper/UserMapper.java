package edu.hue.jk.mapper;

import edu.hue.jk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{user_id}")
    User findByUserId(int user_id);

    @Select("select * from user where state = 1 and username = #{username}")
    User findByUsername(String username);
}
