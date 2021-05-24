package team.sgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.sgj.domain.ListItem;
import team.sgj.domain.ListItemExample;

public interface ListItemMapper {
    long countByExample(ListItemExample example);

    int deleteByExample(ListItemExample example);

    int deleteByPrimaryKey(Integer liId);

    int insert(ListItem record);

    int insertSelective(ListItem record);

    List<ListItem> selectByExample(ListItemExample example);

    ListItem selectByPrimaryKey(Integer liId);

    int updateByExampleSelective(@Param("record") ListItem record, @Param("example") ListItemExample example);

    int updateByExample(@Param("record") ListItem record, @Param("example") ListItemExample example);

    int updateByPrimaryKeySelective(ListItem record);

    int updateByPrimaryKey(ListItem record);

    List<ListItem> selectByUserId(Integer uid);

    List<Integer> selectExampleNumAll(ListItemExample example);

    List<Integer> selectExampleNumFinish(ListItemExample example);

}