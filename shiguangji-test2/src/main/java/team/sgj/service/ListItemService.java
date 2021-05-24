package team.sgj.service;

import team.sgj.domain.ListItem;
import team.sgj.domain.ListItemExample;

import java.util.List;

public interface ListItemService {

    long countByExample(ListItemExample example);

    int deleteByExample(ListItemExample example);

    int deleteByPrimaryKey(Integer gid);

    int insert(ListItem record) ;

    int insertSelective(ListItem record);

    List<ListItem> selectByExample(ListItemExample example) ;

    ListItem selectByPrimaryKey(Integer gid) ;

    List<ListItem> selectByUserId(Integer uid) ;
/*
    List<ListItem> selectByExampleWithObject(ListItemExample example) ;

    ListItem selectByPrimaryKeyWithObject(Integer gid);*/

    int updateByExampleSelective(ListItem record, ListItemExample example);

    int updateByExample(ListItem record, ListItemExample example) ;

    int updateByPrimaryKeySelective(ListItem record);

    int updateByPrimaryKey(ListItem record);


    List<Integer> selectExampleNumAll(ListItemExample example);

    List<Integer> selectExampleNumFinish(ListItemExample example);

}