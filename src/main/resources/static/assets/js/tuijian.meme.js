function addmemeTypeToBox(data,length)
{
    var boxHTML = "";
    var jiluHTML= "";
    for(var i = 0;i<length;i++) {
        var a = i + 1;
        var b = JSON.stringify(data[i].memeID);
        boxHTML += '<tr ><td><input type="checkbox" id="comicTypeGroup_' + i + '" /></td>' +
            '<td>' + a + '</td>' +
            '<td><a href="#">' + data[i].memeName + '</a></td>' +
            '<td>' + data[i].classis + '</td>' +
            '<td>' + data[i].author + '</td><td>' + data[i].upTime + '</td><td>' +
            '<div class="am-btn-toolbar">' +
            '<div class="am-btn-group am-btn-group-xs">' +
            '<button type="button" onclick="Updata('+b+')" class="am-btn am-btn-default"><span class="am-icon-archive"></span> ' +
            '编辑</button>' +
            '<button type="button" onclick="Delete('+b+')" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> ' +
            '删除</button>' +
            '</div>' +
            '</div>' +
            '</td>' +
            '</tr>&nbsp;';
        if (i != 0 && i % 6 == 0)
            boxHTML += '<br/>';
    }

    jiluHTML='<p>共 '+length+' 条记录</p>';
    var groupdiv = document.getElementById('view_meme');
    groupdiv.innerHTML = boxHTML;
    var jiludiv = document.getElementById('div_jilu');
    jiludiv.innerHTML = jiluHTML;
}

function Updata(b) {
    window.location.href="http://localhost:8081/meme/memeupdate.html?id="+b;
}

function Delete(b) {
    $.ajax({
        type: "post", //请求方式
        url: "./entitymemedele/"+b, //路径
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (redata) {
            if(redata.toString()==0){
                alert("删除成功");
                window.location.href = "http://localhost:8081/meme/admin-meme.html"; //删除成功刷新界面
            }
        }
    });
}
// //获取用户数据库的函数
// function getmeme(){
//     $.ajax({
//         type: "post",
//         url: "./entitymemelist",    //向后端请求数据的url
//         dataType:"json",
//         contentType: "application/json; charset=utf-8",
//         cache:false,
//         success: function (data) {
//             var length = data.length;
//             addmemeTypeToBox(data,length);
//         },
//         error:function() {
//             alert("listComicType error");
//         }
//     });
// }
// function selectclassis(obj) {
//     if (obj.value.toString()=="type1"){
//         getmeme();
//     } else
//     {   $.ajax({
//         type: "get",
//         url: "entitymemeclassis/" + obj.value.toString(),    //向后端请求数据的url
//         dataType: "json",
//         contentType: "application/json; charset=utf-8",
//         cache: false,
//         success: function (data) {
//             // alert(JSON.stringify(data));
//             var length = data.length;
//             addmemeTypeToBox(data, length);
//         }
//         // error:function() {
//         //     alert("listComicType error");
//         // }
//     });
//     }
// }

function searchusers(){
    if(document.getElementById('search').value.length==0)
    {alert("请输入表情包id")}
    $.ajax({
        type: "post",
        url: "recommendlists/"+document.getElementById('search').value,    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        cache:false,
        success: function (data) {
            // alert(JSON.stringify(data));
            var length = data.length;
            addmemeTypeToBox(data,length);
        }
        // error:function() {
        //     alert("listComicType error");
        // }
    });
}

function addmeme() {
    window.location.href="http://localhost:8081/meme/memeadd.html";
}
function mememanage() {
    window.location.href="http://localhost:8081/meme/admin-meme.html"
}