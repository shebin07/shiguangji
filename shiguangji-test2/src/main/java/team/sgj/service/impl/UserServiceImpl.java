package team.sgj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sgj.dao.UserMapper;
import team.sgj.domain.ListItemExample;
import team.sgj.domain.User;
import team.sgj.domain.UserExample;
import team.sgj.service.UserService;
import team.sgj.utils.MD5Util;

import java.util.List;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/10/19 0019
 * @描述
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public long countByExample(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserExample example) {
        return userMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(User record) {
        //对传递过来的明文密码加密为密文然后比对
        record.setPassword(MD5Util.getMD5(record.getPassword()));
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        //对传递过来的明文密码加密为密文然后比对
        record.setPassword(MD5Util.getMD5(record.getPassword()));
        return userMapper.insertSelective(record);
    }

    @Override
    public List<User> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByExampleSelective(User record, UserExample example) {
        //对传递过来的明文密码加密为密文然后比对
        record.setPassword(MD5Util.getMD5(record.getPassword()));
        return userMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(User record, UserExample example) {
        //对传递过来的明文密码加密为密文然后比对
        record.setPassword(MD5Util.getMD5(record.getPassword()));
        return userMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        //对传递过来的明文密码加密为密文然后比对
        System.out.print("================"+record.getPassword());
        if(record.getPassword()==null){
            record.setPassword(userMapper.selectByPrimaryKey(record.getUid()).getPassword());
            System.out.println("password is null");
        }else
            record.setPassword(MD5Util.getMD5(record.getPassword()));

        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        //对传递过来的明文密码加密为密文然后比对
        record.setPassword(MD5Util.getMD5(record.getPassword()));
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public String selectUidByOpenId(String openId) {
        System.out.println("UserService:selecrUidByOpenId\n");
        return  userMapper.selectUidByOpenId(openId);
    }

}
