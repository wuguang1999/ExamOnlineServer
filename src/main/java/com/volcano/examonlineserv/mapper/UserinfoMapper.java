package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.RankingResponse;
import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.bean.UserinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserinfoMapper {
    long countByExample(UserinfoExample example);

    int deleteByExample(UserinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoExample example);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    @Select("SELECT id, phone, userName, avatar, accumulate FROM userinfo ORDER BY accumulate DESC")
    List<RankingResponse> getRankings();

    @Select("SELECT * from userinfo WHERE phone = #{phone,jdbcType=VARCHAR}")
    Userinfo selectByPhone(@Param("phone") String phone);
}