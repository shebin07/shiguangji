package team.sgj.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sgj.dao.MemoMapper;
import team.sgj.domain.Memo;
import team.sgj.domain.MemoExample;
import team.sgj.service.MemoService;

import java.util.List;

@Service(value = "MemoService")
public class MemoServiceImpl implements MemoService {
    @Autowired
    private MemoMapper memoMapper;

    @Override
    public long countByExample(MemoExample example) {
        return memoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MemoExample example) {
        return memoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer mId) {
        return memoMapper.deleteByPrimaryKey(mId);
    }

    @Override
    public int insert(Memo record) {
        return memoMapper.insert(record);
    }

    @Override
    public int insertSelective(Memo record) {
        return memoMapper.insertSelective(record);
    }

    @Override
    public List<Memo> selectByExample(MemoExample example) {
        return memoMapper.selectByExample(example);
    }

    @Override
    public Memo selectByPrimaryKey(Integer mId) {
        return memoMapper.selectByPrimaryKey(mId);
    }

    @Override
    public int updateByExampleSelective(Memo record, MemoExample example) {
        return updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Memo record, MemoExample example) {
        return memoMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Memo record) {
        return memoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Memo record) {
        return memoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Memo> selectByUserId(int uid) {
        return memoMapper.selectByUserId(uid);
    }
}
