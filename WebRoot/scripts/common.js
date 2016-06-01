/// <reference path="/js/jquery.min.js" />

function rGet(cqstr, name) {
    if (cqstr == '') cqstr = location.search;
    name += '=';
    var value = cqstr.replace('?' + name, '&' + name);
    name = '&' + name;
    if (value.indexOf(name) > -1) {
        value = value.substring(value.indexOf(name) + name.length);
        if (value.indexOf('&') > -1) value = value.substring(0, value.indexOf('&'));
        if (value.indexOf('#') > -1) value = value.substring(0, value.indexOf('#'));
    } else value = '';
    return value;
}

String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}

var curl = top.location.toString();
if (curl.indexOf("&rn=") > 0) curl = curl.substring(0, curl.indexOf("&rn="));
var cAction = rGet(curl, 'c');

function getUnixTime(time) {
    var yt = new Date(time * 1000);
    var nt = yt.getFullYear() + '-' + (yt.getMonth() + 1) + '-' + yt.getDate() + ' ' + yt.getHours() + ':' + yt.getMinutes() + ':' + yt.getSeconds();
    return nt;
}

function ajaxLoading(str) {
    if (str == undefined) str = 'Loading...';
    return '&nbsp;<br /><p style="text-align:center;line-height:30px;">' + str + '<br /><img src="/images/loading.gif" alt="Loading..."/></p>';
}

function processing(str) {
    $("#processing").html(ajaxLoading(str));
    $("#processing").dialog({ title: "", autoOpen: false, resizable: false, modal: true }).dialog("open");
}

function showResults(str, delay, cmd) {
    $("#processing p:first").html(str);
    switch (cmd) {
        case 'close':
            cmd = '$("#processing").dialog("close");';
            break;
        case 'reload':
            cmd = 'top.location.reload();';
            break;
    }
    if (delay > 0) setTimeout(cmd, delay);
}

function htmlEncode(html) {
    var temp = document.createElement("div");
    (temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
    var output = temp.innerHTML;
    temp = null;
    return output;
}

function htmlDecode(text) {
    var temp = document.createElement("div");
    temp.innerHTML = text;
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
}

function getPrice(pprice, psconfig, pcycle) {
    var pstr = psconfig.pricedes == undefined ? '' : psconfig.pricedes;
    if (pstr == undefined) pstr = '';
    //if (pstr != '') alert(psconfig.pricedes);
    if (pstr == '') {
        var pps = pprice.cprice.split(',');
        var pcs = pprice.cycle.split(',');
        var p;
        var cps = '';
        for (p = 0; p < pps.length; p++) {
            if (pps[p] != '0') {
                cps = '<span>' + pps[p] + '</span>元/';
                switch (pcs[p]) {
                    case '1': cps += '月'; break;
                    case '3': cps += '季'; break;
                    case '6': cps += '半年'; break;
                    case '12': cps += '年'; break;
                    case '24': cps += '2年'; break;
                }
                if (parseInt(pcs[p]) == pcycle || (pcycle < 0 && pcycle == -(p+1))) {
                    pstr = cps;
                    break;
                }
                pstr += cps + '&nbsp;&nbsp;&nbsp;';
            }
        }

    }
    return pstr;
}
