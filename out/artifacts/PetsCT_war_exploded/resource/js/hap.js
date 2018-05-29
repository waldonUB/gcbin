/*!
 * Creater: zhoujie
 * Createtime:2017-04-01 18:38:54
 */

/**
 * 计算日期时间差，到天
 * @param strDate 字符串日期 -> yyyy-MM-dd
 */
function getGapOfStrDate(strDate) {
    if (strDate)
        return getGapOfDateTime(Date.parse(strDate.replace(/-/gi, "/")), true);
    else
        return "";
}

/**
 * 计算日期时间差，到分
 * @param strDateTime 字符串日期 -> yyyy-MM-dd HH:mm:ss
 */
function getGapOfStrDateTime(strDateTime) {
    if (strDateTime)
        return getGapOfDateTime(Date.parse(strDateTime.replace(/-/gi, "/")), false);
    else
        return "";
}

/**
 * 计算日期时间差
 * @param dateTime 毫秒数
 * @param isDate 是否只比较日期，true:日期，不计算时分秒;false:按时间比较，计算时分秒
 */
function getGapOfDateTime(dateTime, isDate) {
    var minute = 1000 * 60;
    var hour = minute * 60;
    var day = hour * 24;
    var month = day * 30;
    var year = day * 365;


    var now = new Date().getTime();
    var diffValue = now - dateTime;

    if (isDate)//按日期比较的，需要去掉时分秒。
        diffValue = now - now % day - (dateTime - dateTime % day);

    var yearC = Math.abs(diffValue / year);
    var monthC = Math.abs(diffValue / month);
    var weekC = Math.abs(diffValue / (7 * day));
    var dayC = Math.abs(diffValue / day);
    var hourC = Math.abs(diffValue / hour);
    var minC = Math.abs(diffValue / minute);

    var str = "前";
    if (diffValue < 0) {//传入时间比当前时间大。
        str = "后";
    }


    if (yearC >= 1) {
        result = "" + parseInt(yearC) + "年" + str;
    } else if (monthC >= 1) {
        result = "" + parseInt(monthC) + "个月" + str;
    }
    else if (weekC >= 1 && (weekC % 1) < 0.4) {//不超过半周，超过的用天显示
        result = "" + parseInt(weekC) + "周" + str;
    }
    else if (dayC >= 1) {
        result = "" + parseInt(dayC) + "天" + str;
    } else if (isDate && dayC == 0) {
        result = "今天";
    }
    else if (hourC >= 1) {
        result = "" + parseInt(hourC) + "小时" + str;
    }
    else if (minC >= 1) {
        result = "" + parseInt(minC) + "分钟" + str;
    } else {
        result = "刚刚";
    }

    return result;
}

/**
 * 获取Cookie
 * @param cname
 * @returns {*}
 */
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0)
            return c.substring(name.length, c.length);
    }
    return "";
}

function writeCookie(name,value,days){
    var expires="";
    if(days){
        var date=new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        expires=";expires="+date.toGMTString();
    }
    document.cookie=name + "=" + value + expires +";path=/;";
}

function getContextPath() {
    return  getCookie('contextPath');
}
// function getLoginUserName() {
//     return getCookie('userName');
// }
function getSystemTitle() {
    return  decodeURIComponent(getCookie('systemTitle'));
}
// setTitleWithDefaultSuffix
function setSSCTitle(title) {
    var t = getSystemTitle();
    if(title.endsWith(" - " +t)){
        document.title = title;
        return;
    }
    document.title=title+" - " +t;
}

/**
 *
 * @param s queryString like '?a=1&b=2#sth'
 * @returns {{}} {a:'1', b:'2'}
 */
function parseSearch(s){
    s = (s || window.location.search).substring(1);
    s = s.replace(/#.*$/, '');
    var arr = s.split('&');
    var map = {};
    for(var i=0;i<arr.length;i++){
        if(arr[i]){
            var kv = arr[i].split('=');
            map[kv[0]] = kv[1];
        }
    }
    return map;
}


//下面是64个基本的编码
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = [
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1];

//编码的方法
function base64_encode(str) {
    var out, i, len;
    var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}

//解码的方法
function base64_decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;
    len = str.length;
    i = 0;
    out = "";
    while (i < len) {

        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c1 == -1);
        if (c1 == -1)
            break;

        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c2 == -1);
        if (c2 == -1)
            break;
        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if (c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while (i < len && c3 == -1);
        if (c3 == -1)
            break;
        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if (c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while (i < len && c4 == -1);
        if (c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}

function utf16to8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        }
    }
    return out;
}

function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;
    out = "";
    len = str.length;
    i = 0;
    while (i < len) {
        c = str.charCodeAt(i++);
        switch (c >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                // 0xxxxxxx
                out += str.charAt(i - 1);
                break;
            case 12:
            case 13:
                // 110x xxxx   10xx xxxx
                char2 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
                break;
            case 14:
                // 1110 xxxx  10xx xxxx  10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) |
                    ((char2 & 0x3F) << 6) |
                    ((char3 & 0x3F) << 0));
                break;
        }
    }
    return out;
}



function urlsafe_b64encode(string) {
    var data = base64_encode(utf16to8(string));
    data = data.replace(/[+]/g, '-').replace(/[/]/g, '_').replace(/[=]/g, ''); // str_replace(array('+', '/', '='), array('-', '_', ''), $data);
    return data;
}

function urlsafe_b64decode(string) {
    var data =  string.replace(/[-]/g, '+').replace(/[_]/g, '/'); // str_replace(array('-', '_'), array('+', '/'), $string);
    var mod4 = data.length % 4; //strlen($data) % 4;
    if (mod4) {
        data += '===='.substring(0, 4-mod4) // substr('====', $mod4);
    }
    return utf8to16(base64_decode(data));
}


/*begin depends base64 and utf8&16*/

/**
 * {billtype="单据类型",billid="单据ID",usercode="当前登录用户usercode",time='时间Long类型'}
 * =>
 * {"bt":"F5-Cxx-03","id":"1001D310000000H8MY4A","ti":1509076347339,"uc":"xiaoqi"}
 */
/**
 *
 * @param billtype 单据类型
 * @param billid 单据ID
 * @return base64_encode(JSON.stringify({billtype="单据类型",billid="单据ID",usercode="当前登录用户usercode",time='时间Long类型'}));
 */
function encodeBill(billtype, billid){
    var bill = {bt:billtype, id:billid, ti:Date.now()};
    if(ssc_nav_top_data.user && ssc_nav_top_data.user.hasOwnProperty('usercode')){
        bill['uc'] = ssc_nav_top_data.user.usercode;
    }
    return urlsafe_b64encode(JSON.stringify(bill));
}
function decodeBill(str) {
    return JSON.parse(urlsafe_b64decode(str));
}

/*// var t_bill = {"bt":"F5-Cxx-03","id":"1001D310000000H8MY4A","ti":1509076347339,"uc":"xiaoqi"};
//编码
var src = 'F5-Cxx-03测试文本';
var es = base64_encode(utf16to8(src));
console.log(es);
//解码
var ds = utf8to16(base64_decode(es));
console.log(ds);*/

/*end depends base64 and utf8&16*/



// $('body').each(function () { this.className = 'layout-top-nav skin-black'; });
$(function () {
    // $('body').each(function () { this.className = 'sidebar-mini skin-black'; });
    // $('body').each(function () { this.className = 'layout-top-nav skin-black'; });
    setSSCTitle(document.title);

    // $('body').each(function () { this.className = 'layout-top-nav skin-black-light'; });

    // highlight currentpage.ancestor
    // nav navbar-nav
    (function () {
        var lis = $('.navbar-nav>li');
        lis.each(function () {
            if($('.currentpage', this).length>0){
                $(this).addClass('currentpage-ancestor');
                // return;
            }
        });
    })();


    if(typeof swal !== 'undefined' && typeof swal.setDefaults === 'function'){
        swal.setDefaults({
            cancelButtonText: '取消',
            confirmButtonText: '确定',
            reverseButtons:true
        });
    }



});
