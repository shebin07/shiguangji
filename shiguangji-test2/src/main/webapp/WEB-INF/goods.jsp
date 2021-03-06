<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/19 0019
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--引入jstl的标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--引入jstl的格式化标签库--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--引入spring的表单标签库--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>后台管理</title>
    <meta charset="UTF-8"/>
    <base target="_self"/>

    <meta http-equiv="Content-Type" ; content="multipart/form-data; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- 引入 Bootstrap -->
    <link href="${app}/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${app}/static/css/animate.css" rel="stylesheet"/>
    <link href="${app}/static/css/font-awesome.css" rel="stylesheet"/>
    <link href="${app}/static/css/custom.css" rel="stylesheet"/>

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" rel="external nofollow"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js" rel="external nofollow"></script>
    <![endif]-->
</head>
<body>

<!--提示消息框-->
<div class="alert"></div>
<!-- 模态框 -->
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加新商品</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="${app}/goodsrest/opt" enctype="multipart/form-data" class="form-horizontal" role="form">
                    <%--input type="hidden" name="_method" value="POST" /--%>
                    <div class="form-group">
                        <label>gname:</label><input type="text" class="form-control" name="gname"
                                                    placeholder="请输入商品名称"/>
                    </div>
                    <div class="form-group">
                        <label>gdes:</label><textarea class="form-control" name="gdes" placeholder="商品描述"></textarea>
                    </div>
                    <div class="form-group">
                        <label>gprice:</label><input type="text" class="form-control" name="gprice" placeholder="商品价格"/>
                    </div>
                    <div class="form-group">
                        <label>gavatar:</label>
                        <img data-my="disAvatar" src="" style="width: 100px;height: 100px;"/>
                        <input style="display: none;" type="file" class="form-control" data-my="inputAvatar"
                               name="file"/>
                    </div>
                    <div class="form-group">
                        <label>fbid:</label><input list="blist" type="text" class="form-control" name="fbid"
                                                   placeholder="所属商户"/>
                        <datalist id="blist">
                        </datalist>
                    </div>
                    <div class="form-group">
                        <label>ftid:</label><input list="tlist" type="text" class="form-control" name="ftid"
                                                   placeholder="所属类别"/>
                        <datalist id="tlist" class="custom-select-sm">
                        </datalist>
                    </div>

                    <div class="form-group">
                        <button id="addObjBtn" type="button" class="btn btn-block btn-primary">添加</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">修改信息</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="${app}/goodsrest/opt" enctype="multipart/form-data" class="form-horizontal" role="form">
                    <%--如果使用rest风格的方式用put更新数据,记得需要添加下行转换的代码,并且需要在springmvc中添加相应配置--%>
                    <input type="hidden" name="_method" value="PUT" />
                    <div class="form-group">
                        <label>gid:</label><input readonly="readonly" type="text" class="form-control" name="gid"
                                                  placeholder="请输入商品id"/>
                    </div>
                    <div class="form-group">
                        <label>gname:</label><input type="text" class="form-control" name="gname"
                                                    placeholder="请输入商品名称"/>
                    </div>
                    <div class="form-group">
                        <label>gdes:</label><textarea class="form-control" name="gdes" placeholder="商品描述"></textarea>
                    </div>
                    <div class="form-group">
                        <label>gprice:</label><input type="text" class="form-control" name="gprice" placeholder="商品价格"/>
                    </div>
                    <div class="form-group">
                        <label>addTime:</label><input type="datetime" class="form-control" name="addTime"
                                                      placeholder="添加时间"/>
                    </div>
                    <div class="form-group">
                        <label>gavatar:</label>
                        <img data-my="disAvatar" src="" style="width: 100px;height: 100px;"/>
                        <input style="display: none;" type="file" class="form-control" data-my="inputAvatar"
                               name="file"/>
                    </div>
                    <div class="form-group">
                        <label>fbid:</label><input list="ublist" type="text" class="form-control" name="fbid"
                                                   placeholder="所属商户"/>
                        <datalist id="ublist">
                        </datalist>
                    </div>
                    <div class="form-group">
                        <label>ftid:</label><input list="utlist" type="text" class="form-control" name="ftid"
                                                   placeholder="所属类别"/>
                        <datalist id="utlist">
                        </datalist>
                    </div>

                    <div class="form-group">
                        <button id="updateObjBtn" type="button" class="btn btn-block btn-primary">修改</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>


<form id="searchForm" method="get" action="${app}/goodsrest/list">

    <input name="gid" type="text" value="" placeholder="gid"/>
    <input type="text" placeholder="gname" name="gname" value=""/>
    <input type="text" name="minPrice" value="0.00"/>
    <input type="text" name="maxPrice" value="999.99"/>
    <input type="date" name="startDate" value="2020-10-01"/>
    <input type="date" name="endDate" value="2099-11-12"/>
    <input class="btn btn-secondary" type="button" id="searchCleanBtn" value="清除条件"/>
    <input class="btn btn-primary" type="button" id="searchBtn" value="查询"/>
</form>
<div>
    <!-- 按钮：用于打开模态框 -->
    <button id="openAddModalBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">新增
    </button>
    <input class="btn btn-danger" type="button" action="${app}/goodsrest/opt" id="deletesBtn" value="删除所选"/>
</div>
<table id="objTable" class="table table-striped table-bordered table-hover">
    <thead>
    <tr class="bg-primary text-white">
        <th>
            <input type="checkbox" id="choiceToggle"/>
            <input class="btn btn-sm btn-warning" type="button" id="reverseBtn" value="反选"/>
        </th>
        <th>序号#</th>
        <th>商品id(gid)</th>
        <th>名称(gname)</th>
        <th>描述(gdes)</th>
        <th>价格(gprice)</th>
        <th>照片(gavatar)</th>
        <th>所属商户(fbid)</th>
        <th>所属种类(ftid)</th>
        <th>创建时间(addTime)</th>
        <th>操作(修改)</th>
        <th>操作(删除)</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
<div class="row">
    <div id="pageList" class="col-12 col-md-8">

    </div>
    <div id="pageTips" class="col-12 col-md-4">

    </div>
</div>

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${app}/static/js/jquery-3.3.1.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${app}/static/js/bootstrap.js"></script>
<script src="${app}/static/js/vue.js"></script>
<script src="${app}/static/js/vue-resources.js"></script>
<script src="${app}/static/js/vue-router.js"></script>
<script src="${app}/static/js/axios.js"></script>
<script src="${app}/static/js/custom.js"></script>
<script>
    //为了跳转页面方便,设置全局变量保存当前页和最大页码数
    var currentPage = 1;//当前页码
    var maxPages = 1;//最大页码

    $(document).ready(function () {
        //页面加载时向远端获取所有数据,页面定位在第1页
        gotoPage();
        //页面加载时给全选和反选按钮绑定事件
        mulCheck();
        //给查询按钮绑定事件
        $("#searchBtn").click(search);
        //给清除查询条件按钮绑定事件
        $("#searchCleanBtn").click(searchClean);
        //给删除多条记录的按钮绑定事件
        $("#deletesBtn").click(deleteMuliRecord);
        //给添加按钮绑定事件
        $("#openAddModalBtn").click(addForm);
        //点击添加按钮将新增数据存放到数据库
        $("#addObjBtn").click(addObj);
        //给每条记录的修改按钮添加事件
        $(document).on("click", ".upBtn", updateForm);
        //给每条记录的修改按钮添加事件
        $("#updateObjBtn").click(updateObj);
        //给每条记录的删除按钮添加事件
        $(document).on("click", ".delBtn", deleteSingleRecord);
        //给需要点击之后上传图片的区域添加点击事件,确保能够调用文件域的点击事件
        $('[data-my="disAvatar"]').click(function (eve) {
            $(eve.target).next('[type="file"]').click();
        });
        $('[data-my="inputAvatar"]').change(choiceAvatar);
        //给点击获取相关信息的链接打开模态框
        $(document).on("click", ".disBtn", displayInfo);
        //给点击切换状态的连接添加事件
        $(document).on("click",".changeBtn",changeStatus);
    });

    //页面专用的代码从这里开始

    $("#openAddModalBtn").click(function () {
        getAndFill("${app}/businessrest/listJSON", $('#addModal [id="blist"]'), "bid", "bname");
        getAndFill("${app}/typesrest/listJSON", $('#addModal [id="tlist"]'), "tid", "tname");
    });


    //修改信息时从远端获取数据并填入表单
    function updateForm(ele) {
        //声明变量用以接收原始值,主要用于填写下拉列表
        var choice1;
        var choice2;

        //打开模态框
        $("#updateModal").modal({backdrop: "static"});
        //将表单中原有数据清空,包括数据区内容
        $("#updateModal form").get(0).reset();
        //从服务器获取信息填入修改表单中
        $.ajax({
            url: ele.target.href,
            type: "GET",
            success: function (result) {
                //回填数据
                $('#updateModal [name="gid"]').val(result.dataZone.obj.gid);
                $('#updateModal [name="gname"]').val(result.dataZone.obj.gname);
                $('#updateModal [name="gdes"]').val(result.dataZone.obj.gdes);
                $('#updateModal [name="gprice"]').val(result.dataZone.obj.gprice);
                $('#updateModal [data-my="disAvatar"]').attr('src', result.dataZone.obj.gavatar == '' ? '/upload/null.png' : result.dataZone.obj.gavatar);
                $('#updateModal [name="fbid"]').val(result.dataZone.obj.fbid);
                choice1 = result.dataZone.obj.fbid;
                $('#updateModal [name="ftid"]').val(result.dataZone.obj.ftid);
                choice2 = result.dataZone.obj.ftid;
                $('#updateModal [name="addTime"]').val(new Date(result.dataZone.obj.addTime).Format("yyyy-MM-dd"));

            },
            error: function () {
            }
        });

        //填充列表
        getAndFill("${app}/businessrest/listJSON", $('#updateModal [id="ublist"]'), "bid", "bname", choice1);
        getAndFill("${app}/typesrest/listJSON", $('#updateModal [id="utlist"]'), "tid", "tname", choice2);

        return false;//取消超链接的默认跳转
    }

    //解析数据并渲染
    function parseDataAndShow(result) {
        $("#objTable tbody").empty();
        // 获取数据集合
        let lists = result.dataZone.pageInfo.list;
        $.each(lists, function (index, item) {
            //构建行
            var uTr = $("<tr></tr>");
            //构建多个单元格
            var checkboxTh = $('<th><input type="checkbox" name="choiceList" value="${item.gid}"/></th>');
            var countTh = $('<th></th>').text(index + 1);
            var td1 = $('<td></td>').text(item.gid);
            var td2 = $('<td></td>').text(item.gname);
            var td3 = $('<td></td>').text(item.gdes);
            var td4 = $('<td></td>').text(item.gprice.toFixed(2));
            var td5 = $('<td></td>').text(item.gavatar);
            var td6 = $('<td></td>').text(item.business.bname);
            var td7 = $('<td></td>').text(item.types.tname);
            var addTimeTd = $('<td></td>').text(new Date(item.addTime).Format("yyyy-MM-dd HH:mm:ss"));
            var upBtnTd = $('<td></td>').html('<a class="upBtn btn btn-info btn-sm" href="${app}/goodsrest/opt/' + item.gid + '">修改</a>');
            var delBtnTd = $('<td></td>').html('<a class="delBtn btn btn-danger btn-sm" href="${app}/goodsrest/opt/' + item.gid + '">删除</a>');
            //将单元格追加到行中
            uTr.append(checkboxTh).append(countTh)
                .append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7)
                .append(addTimeTd)
                .append(upBtnTd).append(delBtnTd);
            // 将行追加到表体中
            $("#objTable tbody").append(uTr);
        });
    }

</script>
</body>
</html>
