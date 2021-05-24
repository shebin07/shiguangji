package team.sgj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sgj.domain.User;
import team.sgj.domain.UserCondition;
import team.sgj.domain.UserExample;
import team.sgj.service.UserService;
import team.sgj.utils.MdzwUtils;
import team.sgj.utils.MessageAndData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/10/19 0019
 * @描述
 */
@Controller
@RequestMapping("/userrest")
public class UserrestController {
    @Autowired
    private UserService userService;
    private static UserService myService;

    @RequestMapping(value = "/index")
    public String index() {
        return "forward:/WEB-INF/user.jsp";
//        return "user";
    }
    @RequestMapping(value = "/index1")
    public String index1() {
//        return "forward:/WEB-INF/user.jsp";
        return "forward:/WEB-INF/pages/userrest.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public MessageAndData list(
            UserCondition condition,/*检索条件*/
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) throws ParseException {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        String userName = "";
        if (condition.getUsername() != null && !condition.getUsername().equals("")) {
            userName = "%" + condition.getUsername() + "%";
            criteria.andUsernameLike(userName);
        }

        Integer uidC = condition.getUidCondition();
        if (uidC != null && uidC != -1 && condition.getUid() != null) {//不限定条件
            if (uidC == 0) {
                criteria.andUidGreaterThan(condition.getUid());
            }
            if (uidC == 1) {
                criteria.andUidEqualTo(condition.getUid());
            }
            if (uidC == 2) {
                criteria.andUidLessThan(condition.getUid());
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate1 = dateFormat.parse("1970-01-01");
        Date endDate1 = dateFormat.parse("2999-12-31");

        Date startDate = condition.getStartDate() == null ? startDate1 : condition.getStartDate();
        Date endDate = condition.getEndDate() == null ? endDate1 : condition.getEndDate();
        if (startDate.after(endDate)) {
            Date tempDate = startDate;
            startDate = endDate;
            endDate = tempDate;
        }

        criteria.andAddTimeBetween(startDate, endDate);


        //初始化,约束
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userService.selectByExample(example);
        //使用pageHelper的方式封装数据,默认的导航列表长度为8
        PageInfo pageInfo = new PageInfo(lists, 8);
        return MessageAndData.success("").add("pageInfo", pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/opt/{id}", method = RequestMethod.GET)
    public MessageAndData optSelectPrimaryKey(@PathVariable("id") Integer id) {
        User obj = userService.selectByPrimaryKey(id);
        return MessageAndData.success("查询成功").add("obj", obj);
    }


    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.POST)
    public int optInsert(User obj) {
        //MessageAndData.success("=========================================="+obj.getUsername());
        //MessageAndData.success(obj.getPassword());
        System.out.println("obj.openId="+obj.getOpenId());
        Integer i = userService.insertSelective(obj);
        System.out.println("插入用户数据了,"+i+"条");

        if (i > 0) {
            System.out.println("成功添加" + i + "条记录");
            String suid = userService.selectUidByOpenId(obj.getOpenId());
            System.out.println("获取到的uid为："+suid);
            int uid= Integer.parseInt(suid);
            return uid;
        } else {
            System.out.println("添加失败");
            return -1;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/opt/{ids}", method = RequestMethod.DELETE)
    public MessageAndData deletes(@PathVariable("ids") String ids) {
        //获取传递过来的uid列表,分解为一个集合对象
        List<Integer> iIds = new ArrayList<Integer>();
        String splitSymbol = "\\D";
        String[] sIds = ids.split(splitSymbol);
        Integer i = null;
        for (String sId : sIds) {
            iIds.add(Integer.parseInt(sId));
        }
        if (iIds.size() > 1) {//删除多条记录
            UserExample example = new UserExample();
            example.createCriteria().andUidIn(iIds);
            i = userService.deleteByExample(example);
        } else {//删除一条记录
            i = userService.deleteByPrimaryKey(iIds.get(0));
        }
        return MessageAndData.success("删除成功" + i + "条记录").add("num", i);
    }


    //    如果使用put方法,记得要在web.xml中添加相应过滤器,对象不能封装
    //
    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.PUT)
    public MessageAndData optUpdate(User obj) {
        int i = userService.updateByPrimaryKeySelective(obj);
        if (i > 0) {
            return MessageAndData.success("成功修改" + i + "条记录");
        } else {
            return MessageAndData.error("修改失败");
        }
    }

    public static  Map<String, Object> getOpenId(String code,String APPID,String APPSecret){
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append(APPID);
        url.append("&secret=");//secret设置
        url.append(APPSecret);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            //把信息封装到map
            map = MdzwUtils.parseJSON2Map(res);//这个小工具的代码在下面
        } catch (Exception e) { e.printStackTrace(); }
        String openid = (String) map.get("openid");
        return map;
    }
    //  登录操作
    @ResponseBody
    @RequestMapping(value = "/login/{code}${APPID}${APPSecret}", method = {RequestMethod.POST})
    public Map<String, Object> getWxUserOpenid(@PathVariable("code")String code, @PathVariable("APPID")String APPID, @PathVariable("APPSecret")String APPSecret) {

        String openid = (String) this.getOpenId(code,APPID,APPSecret).get("openid");
        String suid = userService.selectUidByOpenId(openid);
        int uid;
        if(suid==null){
            uid = -1;
        }else uid = Integer.parseInt(suid);
        Map<String, Object> map = new HashMap<String , Object>();
        map.put("uid",uid);
        map.put("openid",openid);
        System.out.println("到这里了");
        return map;
    }

}
