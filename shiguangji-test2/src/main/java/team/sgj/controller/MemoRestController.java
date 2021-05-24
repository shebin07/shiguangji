package team.sgj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.sgj.domain.ListItemExample;
import team.sgj.domain.Memo;
import team.sgj.domain.MemoExample;
import team.sgj.service.MemoService;
import team.sgj.utils.MessageAndData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/memorest")
public class MemoRestController {
    @Autowired
    private MemoService memoService;

    //根据m_id获取
    @ResponseBody
    @RequestMapping(value = "/opt/{id}", method = RequestMethod.GET)
    public MessageAndData optSelectPrimaryKey(@PathVariable("id") Integer id) {
        Memo obj = memoService.selectByPrimaryKey(id);
        return MessageAndData.success("查询成功").add("obj", obj);
    }


    //根据uid查询
    @ResponseBody
    @RequestMapping(value = "/optu/{uid}", method = RequestMethod.GET)
    public MessageAndData optSelectUserId(@PathVariable("uid") Integer id) {
        List<Memo> obj = memoService.selectByUserId(id);
        Collections.reverse(obj);
        return MessageAndData.success("查询成功").add("obj", obj);
    }

    //插入memo项
    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.POST)
    public MessageAndData optInsert(Memo obj) throws IOException {
        obj.setmAddTime(new Date());
        Integer i = memoService.insertSelective(obj);
        if (i > 0) {
            return MessageAndData.success("成功添加"+i+"条memo");
        }else {
            return MessageAndData.error("添加失败");
        }
    }

    //修改memo项
    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.PUT)
    public MessageAndData optUpdateRest(Memo obj) throws IOException {
        System.out.println("obj="+obj);
        System.out.println("obj.info="+obj.getmInfo());
        int i = memoService.updateByPrimaryKeySelective(obj);
        if (i > 0) {
            return MessageAndData.success("成功修改" + i + "条记录");
        } else {
            return MessageAndData.error("修改失败");
        }
    }

    //删除memo项
    @ResponseBody
    @RequestMapping(value = "/opt/{id}", method = RequestMethod.DELETE)
    public MessageAndData deletes(@PathVariable("id") String id) {

        Integer iId = Integer.parseInt(id);
        Integer i=null;
        i = memoService.deleteByPrimaryKey(iId);
        if(i==1){
            return MessageAndData.success("删除成功" + i + "条memo记录").add("num", i);
        }else {
            return MessageAndData.error("删除失败");
        }

    }

}
