package team.sgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.sgj.domain.Memo;
import team.sgj.domain.MemoExample;

public interface MemoMapper {
    long countByExample(MemoExample example);

    int deleteByExample(MemoExample example);

    int deleteByPrimaryKey(Integer mId);

    int insert(Memo record);

    int insertSelective(Memo record);

    List<Memo> selectByExample(MemoExample example);

    Memo selectByPrimaryKey(Integer mId);

    int updateByExampleSelective(@Param("record") Memo record, @Param("example") MemoExample example);

    int updateByExample(@Param("record") Memo record, @Param("example") MemoExample example);

    int updateByPrimaryKeySelective(Memo record);

    int updateByPrimaryKey(Memo record);

    List<Memo> selectByUserId(int uid);
}