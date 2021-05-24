package team.sgj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sgj.dao.ListItemMapper;
import team.sgj.domain.ListItem;
import team.sgj.domain.ListItemExample;
import team.sgj.service.ListItemService;

import java.util.List;

@Service(value = "ListItemService")
public class ListItemServiceImpl implements ListItemService {
    @Autowired
    private ListItemMapper itemMapper;

    @Override
    public long countByExample(ListItemExample example) {
        return itemMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ListItemExample example) {
        return itemMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer gid) {
        return itemMapper.deleteByPrimaryKey(gid);
    }

    @Override
    public int insert(ListItem record) {
        return itemMapper.insert(record);
    }

    @Override
    public int insertSelective(ListItem record) {
        return itemMapper.insertSelective(record);
    }

    @Override
    public List<ListItem> selectByExample(ListItemExample example) {
        return itemMapper.selectByExample(example);
    }

    @Override
    public ListItem selectByPrimaryKey(Integer gid) {
        return itemMapper.selectByPrimaryKey(gid);
    }

    @Override
    public List<ListItem> selectByUserId(Integer uid) {
        return itemMapper.selectByUserId(uid);
    }
    /*@Override
    public List<ListItem> selectByExampleWithObject(ListItemExample example) {
        return itemMapper.selectByExampleWithObject(example);
    }*/

   /* @Override
    public ListItem selectByPrimaryKeyWithObject(Integer gid) {
        return itemMapper.selectByPrimaryKeyWithObject(gid);
    }*/

    @Override
    public int updateByExampleSelective(ListItem record, ListItemExample example) {
        return itemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(ListItem record, ListItemExample example) {
        return itemMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(ListItem record) {
        return itemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ListItem record) {
        return itemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> selectExampleNumAll(ListItemExample example) {
        return itemMapper.selectExampleNumAll(example);
    }

    @Override
    public List<Integer> selectExampleNumFinish(ListItemExample example) {
        return itemMapper.selectExampleNumFinish(example);
    }
}
