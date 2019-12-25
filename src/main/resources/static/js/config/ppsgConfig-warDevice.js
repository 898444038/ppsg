function warDeviceList() {
    $(document).on("click",".btn-change",function () {
        $(".card").find(".nav-tabs").toggleClass("nav-fill");
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
                $('#data-table-war-device-type').DataTable( {
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