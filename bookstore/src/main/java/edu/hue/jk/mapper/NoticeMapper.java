package edu.hue.jk.mapper;

import edu.hue.jk.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Select("select n_id nId, title, details, n_time nTime from notice")
    List<Notice> findAll();

    @Select("select n_id nId, title, details, n_time nTime from notice where n_id = #{nId}")
    Notice findById(int nId);

    @Update("update notice set title = #{title}, details = #{details}, n_time = #{nTime} where n_id = #{nId}")
    int update(Notice notice);

    @Insert("insert into notice (title, details, n_time) values (#{title}, #{details}, #{nTime})")
    int insert(Notice notice);

    @Delete("delete from notice where n_id = #{nId}")
    int delete(int nId);
}
