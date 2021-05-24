package team.sgj.utils;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/10/27 0027
 * @描述 重写CommonsMultipartResolver中的isMultipart实现PUT请求上传文件
 * 1.默认情况下，SpringMVC只能响应前端发来的GET以及POST请求。
 * 2.默认情况下，SpringMVC配合MultipartFile接受文件上传只能通过POST的方式发送。
 * 默认情况下 restful风格不能通过put上传文件,所以自定义解析器处理,需要在配置文件中注入该bean
 * 本例中是在springmvc.xml文件中配置文件解析器,切记如果使用rest风格的put方式上传文件需要做相应配置
 * 有篇文章写得不错,可以参考   https://blog.csdn.net/qq_40780805/article/details/107561465
 */
public class ExtendMultipartResolver extends CommonsMultipartResolver {




    public static final boolean isMultipartContent(HttpServletRequest request) {
        HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());
        if (HttpMethod.POST != httpMethod && HttpMethod.PUT != httpMethod) {
            return false;
        }
        return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
    }

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        return (request != null && isMultipartContent(request));
    }

    /*====================================也可以使用下面的方法==============================================================*/

    /*    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        return !(POST_METHOD.equalsIgnoreCase(request.getMethod())||PUT_METHOD.equalsIgnoreCase(request.getMethod()))
                ?false
                :FileUploadBase.isMultipartContent(new ServletRequestContext(request));
    }*/

}
