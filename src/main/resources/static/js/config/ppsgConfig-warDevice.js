$(function () {
    $('body').tooltip({
        selector: '[rel=tooltip]'
    });
    warDeviceList();
})


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
                    arr.push([i+1,d[i].name,d[i].forceVal,d[i].intellectVal,d[i].troopsVal,btns])
                }
                $('#data-table-war-device-type').DataTable( {
                    autoWidth: !1,
                    responsive: !0,
                    lengthMenu: [[15, 30, 45, -1], ["15 Rows", "30 Rows", "45 Rows", "Everything"]],
                    language: {searchPlaceholder: "Search for records..."},
                    sDom: '<"dataTables__top"flB<"dataTables_actions">>rt<"dataTables__bottom"ip><"clear">',
                    buttons: [{extend: "excelHtml5", title: "Export Data"}, {
                        extend: "csvHtml5",
                        title: "Export Data"
                    }, {extend: "print", title: "Material Admin"}],
                    destroy:true,
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '战器类型' },
                        { title: '基础武力' },
                        { title: '基础智力' },
                        { title: '基础兵力' },
                        { title: '操作' }
                    ],
                    initComplete: function () {
                        $(".dataTables_actions").html('<i class="zwicon-more-h" data-toggle="dropdown" /><div class="dropdown-menu dropdown-menu-right"><a data-table-action="print" class="dropdown-item">Print</a><a data-table-action="fullscreen" class="dropdown-item">Fullscreen</a><div class="dropdown-divider" /><div class="dropdown-header border-bottom-0 pt-0"><small>Download as</small></div><a data-table-action="excel" class="dropdown-item">Excel (.xlsx)</a><a data-table-action="csv" class="dropdown-item">CSV (.csv)</a></div>')
                    }
                });
            });
        }
    });

    $.ajax({
        url:"/ppsg/config/warDevice/strengthen",
        type:"get",
        data:{},
        dataType:"json",
        success:function (data) {
            mingTools.ajaxResult(data,function () {
                var btns = mingTools.getOperButton();
                var arr = [];
                var d = data.data;
                for(var i=0;i<d.length;i++){
                    arr.push([i+1,d[i].name,d[i].forceVal,d[i].intellectVal,d[i].troopsVal,btns])
                }
                $('#data-table-war-device-type').DataTable( {
                    autoWidth: !1,
                    responsive: !0,
                    lengthMenu: [[15, 30, 45, -1], ["15 Rows", "30 Rows", "45 Rows", "Everything"]],
                    language: {searchPlaceholder: "Search for records..."},
                    sDom: '<"dataTables__top"flB<"dataTables_actions">>rt<"dataTables__bottom"ip><"clear">',
                    buttons: [{extend: "excelHtml5", title: "Export Data"}, {
                        extend: "csvHtml5",
                        title: "Export Data"
                    }, {extend: "print", title: "Material Admin"}],
                    destroy:true,
                    data: arr,
                    columns: [
                        { title: '序号' },
                        { title: '战器类型' },
                        { title: '基础武力' },
                        { title: '基础智力' },
                        { title: '基础兵力' },
                        { title: '操作' }
                    ],
                    initComplete: function () {
                        $(".dataTables_actions").html('<i class="zwicon-more-h" data-toggle="dropdown" /><div class="dropdown-menu dropdown-menu-right"><a data-table-action="print" class="dropdown-item">Print</a><a data-table-action="fullscreen" class="dropdown-item">Fullscreen</a><div class="dropdown-divider" /><div class="dropdown-header border-bottom-0 pt-0"><small>Download as</small></div><a data-table-action="excel" class="dropdown-item">Excel (.xlsx)</a><a data-table-action="csv" class="dropdown-item">CSV (.csv)</a></div>')
                    }
                });
            });
        }
    });
}