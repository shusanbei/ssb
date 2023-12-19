package edu.hue.jk.mapper;

import edu.hue.jk.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface ProductMapper {
    class Provider {
        public String filter(@Param("id") String id, @Param("category") String category, @Param("name") String name,
                              @Param("lowPrice") Double lowPrice, @Param("highPrice") Double highPrice) {
            SQL sql = new SQL();

            sql.SELECT("* from products");

            if (id != null && id.trim().length() > 0) {
                sql.WHERE("id = #{id}");
            }
            if (category != null && category.trim().length() > 0) {
                sql.WHERE("category = #{category}");
            }
            if (name != null && name.trim().length() > 0) {
                sql.WHERE("name like concat('%', #{name}, '%')");
            }
            if (lowPrice != null) {
                sql.WHERE("price >= #{lowPrice} ");
            }
            if (highPrice != null) {
                sql.WHERE("price <= #{highPrice} ");
            }

            return sql.toString();
        }
    }

    @SelectProvider(type = Provider.class, method = "filter")
    List<Product> findAll(@Param("id") String id, @Param("category") String category, @Param("name") String name,
                          @Param("lowPrice") Double lowPrice, @Param("highPrice") Double highPrice);

    @Select("select * from products where id = #{id}")
    Product findById(String id);

    @Update("update products set " +
            "name = #{name}, price = #{price}, category = #{category}, " +
            "pnum = #{pnum}, imgurl = #{imgurl}, description = #{description} " +
            "where id = #{id}")
    int update(Product product);

    @Insert("insert into products " +
            "(id, name, price, category, pnum, imgurl, description) values " +
            "(#{id}, #{name}, #{price}, #{category}, #{pnum}, " +
            "#{imgurl}, #{description})")
    int insert(Product product);

    @Delete("delete from products where id = #{id}")
    int delete(String id);
}
