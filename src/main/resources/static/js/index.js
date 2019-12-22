$(function () {
    //mingTools.loadFile.loadJsFile();

    $.ajax({
        url:"/getMenus",
        type:"post",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                mingTools.createMenuTree("#menuList",data.data,0);
            })
        }
    });


});