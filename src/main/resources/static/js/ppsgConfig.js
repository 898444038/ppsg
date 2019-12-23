function countryList() {
    $(document).on("click",".btn-change",function () {
        $(".card").find(".nav-tabs").toggleClass("nav-fill");
    });

    $.ajax({
        url:"/ppsg/config/country/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];
                for(var i=0;i<data.data.length;i++){
                    arr.push([i+1,data.data[i].countryName,btns])
                }

                $('#data-table-country').DataTable( {
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '国家名称' },
                        { title: '操作' }
                    ]
                });
            });
        }
    });

    $.ajax({
        url:"/ppsg/config/star/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];

                for(var i=0;i<data.data.length;i++){
                    arr.push([i+1,data.data[i].starName,btns])
                }
                $('#data-table-star').DataTable( {
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '星级' },
                        { title: '操作' }
                    ]
                });
            });
        }
    });

    $.ajax({
        url:"/ppsg/config/arms/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];
                var d = data.data;
                for(var i=0;i<d.length;i++){
                    arr.push([i+1,d[i].armsName,d[i].forceRate,d[i].intellectRate,d[i].troopsRate,btns])
                }
                $('#data-table-arms').DataTable( {
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '兵种' },
                        { title: '科技武力加成' },
                        { title: '科技智力加成' },
                        { title: '科技兵力加成' },
                        { title: '操作' }
                    ]
                });
            });
        }
    });

    $.ajax({
        url:"/ppsg/config/generalsType/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];
                var d = data.data;
                for(var i=0;i<d.length;i++){
                    arr.push([i+1,d[i].generalsTypeName,d[i].forceGrowth,d[i].intellectGrowth,d[i].troopsGrowth,btns])
                }
                $('#data-table-generals-type').DataTable( {
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '武将类型' },
                        { title: '武力成长' },
                        { title: '智力成长' },
                        { title: '兵力成长' },
                        { title: '操作' }
                    ]
                });
            });
        }
    });

    $.ajax({
        url:"/ppsg/config/warDevice/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];
                var d = data.data;
                for(var i=0;i<d.length;i++){
                    arr.push([i+1,d[i].warDeviceName,btns])
                }
                $('#data-table-war-device').DataTable( {
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '战器类型' },
                        { title: '操作' }
                    ]
                });
            });
        }
    });
}