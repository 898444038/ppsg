$(function () {
    $('body').tooltip({
        selector: '[rel=tooltip]'
    });
    generalsList()
})


function generalsList() {
    var $body = $("body");
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
                        { title: '国家名称' },
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
                        { title: '星级' },
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
                        { title: '兵种' },
                        { title: '科技武力加成' },
                        { title: '科技智力加成' },
                        { title: '科技兵力加成' },
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
        url:"/ppsg/config/generalsType/list",
        type:"POST",
        async:false,
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
                        { title: '武将类型' },
                        { title: '武力成长' },
                        { title: '智力成长' },
                        { title: '兵力成长' },
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
