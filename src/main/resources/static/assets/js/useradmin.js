    function adduserTypeToBox(data,length)
{
    var boxHTML = "";
    var jiluHTML= "";
    for(var i = 0;i<length;i++)
    {
        var b = JSON.stringify(data[i].userID);
        var a=i+1;
        boxHTML+='<tr ><td><input type="checkbox" id=""comicTypeGroup_'+i+'" /></td><td>'+a+'</td><td><a href="#">'+data[i].userName+'</a></td>' +
            '<td>'+data[i].address+'</td>' +
            '<td>'+data[i].phonenumber+'</td><td>'+data[i].qq+'</td><td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs">' +
            '<button type="button" onclick="Updata('+b+')" class="am-btn am-btn-default"><span class="am-icon-archive"></span> ' +
            '编辑</button>' +
            '<button type="button" onclick="Delete('+b+')" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> ' +
            '删除</button>' +
            '</div></div></td></tr>&nbsp;';
        if(i!=0 && i%6==0)
            boxHTML+='<br/>';
    }
    jiluHTML='<p>共 '+length+' 条记录</p>';
    var groupdiv = document.getElementById('view_user');
    groupdiv.innerHTML = boxHTML;
    var jiludiv = document.getElementById('div_jilu');
    jiludiv.innerHTML = jiluHTML;
}

    function Updata(b) {
        window.location.href="http://localhost:8081/meme/userupdate.html?id="+b;
    }



    function Delete(b) {
        $.ajax({
            type: "post", //请求方式
            url: "./entityuser/dele/"+b, //路径
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (redata) {
                if(redata.toString()==0){
                    alert("删除成功");
                    window.location.href = "http://localhost:8081/meme/admin-user.html"; //删除成功刷新界面
                }
            }
        });
    }
//获取用户数据库的函数
function getusers(){
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/data",    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        // data:{"userID":['userID'],"username":['username'],"password":['password'],"address":['address'],"phonenumber":['phonenumber'],"avatar":['avatar'],"qq":['qq'],"introduction":['introduction']},
        cache:false,
        success: function (data) {
           // alert(JSON.stringify(data));
            var length = data.length;
            adduserTypeToBox(data,length);
        },
        error:function() {
            alert("listComicType error");
        }
    });
}

function searchusers(){
    $.ajax({
        type: "get",
        url: "entityuser/"+document.getElementById('search').value.toString(),    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        cache:false,
        success: function (data) {
            // alert(JSON.stringify(data));
            var length = data.length;
            adduserTypeToBox(data,length);
        }
        // error:function() {
        //     alert("listComicType error");
        // }
    });
}
//usermanage点击事件
function usermanage() {
    window.location.href="http://localhost:8081/meme/admin-user.html";
}

//添加用户点击事件
function adduser() {
    window.location.href="http://localhost:8081/meme/useradd.html";
}

//显示内容
// document.write(data[i].userID);
// document.write(data[i].password);
// document.write(data[i].phonenumber);
// document.write(data[i].address);
// document.write(data[i].avatar);
// document.write(data[i].introduction)
// document.write(data[i].username);

//按钮ID点击事件
// $(document).ready(function () {
//     $("#shuaxin").submit(function (event) {
//         //stop submit the form, we will post it manually.
//         event.preventDefault();
//         test2();   });
//
// });
