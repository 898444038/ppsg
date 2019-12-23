var mingTools = {
    init: function () {
        //loadUtil.loadCssFile('/static/plugins/util/ming.css');
    },
    //动态加载js文件css文件
    loadFile: {
        attribuleName: 'load-file',
        attribuleVaues: ['load-js', 'load-css'],
        loadjscssfile: function (fileurl, filetype) {
            if (filetype === "js") {
                var fileJs = document.createElement('script');
                fileJs.setAttribute("type", "text/javascript");
                fileJs.setAttribute("src", fileurl);
            } else if (filetype === "css") {
                var fileCss = document.createElement('link');
                fileCss.setAttribute("rel", "stylesheet");
                fileCss.setAttribute("type", "text/css");
                fileCss.setAttribute("href", fileurl);
            }
            if (typeof fileCss !== "undefined") {
                document.getElementsByTagName("head")[0].appendChild(fileCss);
            } else if (typeof fileJs !== "undefined") {
                document.getElementsByTagName("head")[0].appendChild(fileJs);
            } else {
                alert("loadjscssfile method error!");
            }
        },
        loadJsFile: function (fileurl) {
            if (this.hasJsfile(fileurl)) {
                return false;
            }
            var fileJs = document.createElement('script');
            fileJs.setAttribute("type", "text/javascript");
            fileJs.setAttribute("src", fileurl);
            fileJs.setAttribute(this.attribuleName, this.attribuleVaues[0]);
            var jfile = this.getJsfile("jquery");
            if (jfile == null) {
                document.getElementsByTagName("head")[0].appendChild(fileJs);
            } else {
                $(jfile).after($(fileJs));
            }
        },
        loadJsFileFunc: function (fileurl, func) {
            if (this.hasJsfile(fileurl)) {
                return false;
            }
            var fileJs = document.createElement('script');
            fileJs.setAttribute("type", "text/javascript");
            fileJs.setAttribute("src", fileurl);
            fileJs.setAttribute(this.attribuleName, this.attribuleVaues[0]);
            var jfile = this.getJsfile("jquery");
            if (jfile == null) {
                document.getElementsByTagName("head")[0].appendChild(fileJs);
            } else {
                $(jfile).after($(fileJs));
            }
            setTimeout(func, 0);
        },
        loadCssFile: function (fileurl) {
            if (this.hasCssfile(fileurl)) {
                return false;
            }
            var fileCss = document.createElement('link');
            fileCss.setAttribute("rel", "stylesheet");
            fileCss.setAttribute("type", "text/css");
            fileCss.setAttribute("href", fileurl);
            fileCss.setAttribute(this.attribuleName, this.attribuleVaues[1]);
            document.getElementsByTagName("head")[0].appendChild(fileCss);
        },
        loadLink: function (rel, file, id) {
            var fileCss = document.createElement('link');
            fileCss.setAttribute("rel", rel);
            fileCss.setAttribute("href", file);
            fileCss.setAttribute("id", id);
            fileCss.setAttribute(this.attribuleName, this.attribuleVaues[1]);
            document.getElementsByTagName("head")[0].appendChild(fileCss);
        },
        hasJsfile: function (fileurl) {
            var scripts = document.getElementsByTagName("script");
            for (var i = 0, len = scripts.length; i < len; i++) {
                var src = scripts[i].getAttribute("src");
                if (src != null && src.indexOf(fileurl) >= 0) {
                    return true;
                    /*存在*/
                }
            }
            return false;
            /*不存在*/
        },
        getJsfile: function (fileurl) {
            var scripts = document.getElementsByTagName("script");
            var arr = [];
            for (var i = 0, len = scripts.length; i < len; i++) {
                var src = scripts[i].getAttribute("src");
                if (src != null && src.indexOf(fileurl) >= 0) {
                    arr.push(scripts[i]);
                    /*存在*/
                }
            }
            if (arr.length > 0) {
                return arr[arr.length - 1];
            }
            return null;
            /*不存在*/
        },
        hasCssfile: function (fileurl) {
            var links = document.getElementsByTagName("link");
            for (var i = 0, len = links.length; i < len; i++) {
                var href = links[i].getAttribute("href");
                if (href != null && href.indexOf(fileurl) >= 0) {
                    return true;
                    /*存在*/
                }
            }
            return false;
            /*不存在*/
        },
        removeJsFile: function (file) {
            var urls = [];
            if (typeof file === 'undefined') {
                urls.push([this.attribuleName, this.attribuleVaues[0]]);
            } else if (typeof file === 'string') {
                urls.push(['src', file]);
            } else if (typeof file === 'object') {
                for (var j = 0, lens = file.length; j < lens; j++) {
                    urls.push(['src', file[j]]);
                }
            }
            for (var i = 0, len = urls.length; i < len; i++) {
                $("script[" + urls[i][0] + "='" + urls[i][1] + "']").remove();
            }
        },
        isJsFile: function (file) {
            if (this.isFile(file) === 1) {
                return true;
            }
            return false;
        },
        isCssFile: function (file) {
            if (this.isFile(file) === 2) {
                return true;
            }
            return false;
        },
        isFile: function (file) {
            if (file.split(".")[1].indexOf("js") >= 0) {
                return 1;
            }
            if (file.split(".")[1].indexOf("css") >= 0) {
                return 2;
            }
            return -1;
        },
        removeCssFile: function (file) {
            var urls = [];
            if (typeof file === 'undefined') {
                urls.push([this.attribuleName, this.attribuleVaues[1]]);
            } else if (typeof file === 'string') {
                urls.push(['href', file]);
            } else if (typeof file === 'object') {
                for (var j = 0, lens = file.length; j < lens; j++) {
                    urls.push(['href', file[j]]);
                }
            }
            for (var i = 0, len = urls.length; i < len; i++) {
                $("link[" + urls[i][0] + "='" + urls[i][1] + "']").remove();
            }
        },
        isRemoveJsCss: function (file) {
            if (this.isJsFile(file)) {
                this.removeJsFile(file);
            }
            if (this.isCssFile(file)) {
                this.removeCssFile(file);
            }
        },
        remove: function (file) {
            var urls = [];
            if (typeof file === 'undefined') {
                $("script[" + this.attribuleName + "='" + this.attribuleVaues[0] + "']").remove();
                $("link[" + this.attribuleName + "='" + this.attribuleVaues[1] + "']").remove();
            } else if (typeof file === 'string') {
                this.isRemoveJsCss(file);
            } else if (typeof file === 'object') {
                for (var j = 0, lens = file.length; j < lens; j++) {
                    this.isRemoveJsCss(file[j]);
                }
            }
        }
        , loadFrame: function (obj) {
            var url = obj.contentWindow.location.href;
            if (url.indexOf("login.jsp") != -1) {
                window.location.href = "/login.jsp";
            }
        }
    },
    ajaxResult: function (data, func) {
        if (data.code == 1) {//成功
            if (func) {
                setTimeout(func, 0);
            }
        } else if (data.code == 0) {//失败

        }
        if (data.code == -1) {//异常

        }
    },
    //转树结构
    createMenuTree: function (el, arr, rootId) {
        var ul = '<li class="navigation__active"><a href="index.html"><i class="zwicon-home"></i> 主页</a></li>';
        if (arr != null) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].parentId == rootId) {
                    ul += '<li class="navigation__sub">';
                    ul += '<a href="#"><i class="' + arr[i].menuIcon + '"></i> ' + arr[i].menuName + '</a>';
                    ul += '<ul>';
                    for (var j = 0; j < arr.length; j++) {
                        if (arr[j].parentId == arr[i].id) {
                            ul += '<li><a class="menu-item" href="#" data-url="' + arr[j].url + '"><i class="' + arr[j].menuIcon + '"></i> ' + arr[j].menuName + '</a></li>';
                        }
                    }
                    ul += '</ul>';
                    ul += '</li>';
                }
            }
        }
        $(el).html(ul);
        $(".menu-item").click(function () {
            mingTools.loading();
            $("iframe").attr("src", $(this).attr("data-url"));
            $("#menuList").find("li").removeClass("navigation__active");
            $(this).closest("li").addClass("navigation__active");
            setTimeout(function () {
                var html = $("iframe").contents().find(".content-root").html();
                var events = $("iframe").contents().find("content-event");
                $(".content").html(html);
                if (events) {
                    var func = eval(events.attr("event"));
                    setTimeout(func, 50);
                }
                mingTools.hideLoading();
            }, 1000);
        });
        $(document).on("click", "#btn-add", function () {
            mingTools.loading();
            $("iframe").attr("src", $(this).attr("data-url"));
            setTimeout(function () {
                var html = $("iframe").contents().find(".content-root").html();
                $(".content").html(html);
                mingTools.hideLoading();
            }, 1000);
        });
        return ul;
    },
    loading: function () {
        $(".page-loader").fadeIn();
    },
    hideLoading: function () {
        $(".page-loader").fadeOut();
    },
    getOperButton: function () {
        var detailBtn = '<button class="btn btn-theme btn--icon"><i class="zwicon-search"></i></button>';
        var editBtn = '<button class="btn btn-theme btn--icon" style="margin-left: 10px;"><i class="zwicon-edit-square"></i></button>';
        var deleteBtn = '<button class="btn btn-danger btn--icon" style="margin-left: 10px;"><i class="zwicon-close"></i></button>';
        return detailBtn+editBtn+deleteBtn;
    }
}

