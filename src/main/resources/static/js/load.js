(function ($) {
    $.fn.endlessScroll = function (options) {
        var defaults = {
            bottomPixels: 50,
            fireOnce: true,
            fireDelay: 150,
            loader: "<br />Loading...<br />",
            data: "",
            insertAfter: "div:last",
            resetCounter: function () {
                return false;
            },
            callback: function () {
                return true;
            },
            ceaseFire: function () {
                return false;
            }
        };
        var options = $.extend(defaults, options);
        var firing = true;
        var fired = false;
        var fireSequence = 0;
        if (options.ceaseFire.apply(this) === true) {
            firing = false;
        }
        if (firing === true) {
            $(window).scroll(function () {
                if ($(document).height() - $(window).height() <= $(window).scrollTop() + options.bottomPixels) {
                    if ((options.fireOnce == false || (options.fireOnce == true && fired != true))) {
                        if (options.resetCounter.apply(this) === true) {
                            fireSequence = 0;
                        }
                        fired = true;
                        fireSequence++;
                        $(options.insertAfter).after("<div id=\"endless_scroll_loader\">" + options.loader + "</div>");
                        if (typeof options.data == 'function') {
                            data = options.data.apply(this);
                        }
                        else {
                            data = options.data;
                        }
                        if (data !== false) {
                            $("div#endless_scroll_loader").remove();
                            $(options.insertAfter).after("<div id=\"endless_scroll_data\">" + data + "</div>");
                            $("div#endless_scroll_data").hide().fadeIn();
                            $("div#endless_scroll_data").removeAttr("id");
                            var args = new Array();
                            args[0] = fireSequence;
                            options.callback.apply(this, args);
                            if (options.fireDelay !== false || options.fireDelay !== 0) {
                                $("body").after("<div id=\"endless_scroll_marker\"></div>");
                                $("div#endless_scroll_marker").fadeTo(options.fireDelay, 1, function () {
                                    $(this).remove();
                                    fired = false;
                                });
                            }
                            else {
                                fired = false;
                            }
                        }
                    }
                }
            });
        }
    };
})(jQuery);