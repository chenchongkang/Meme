    function adduserTypeToBox(data,length)
{
    var boxHTML = "";
    var jiluHTML= "";
    for(var i = 0;i<length;i++)
    {
        var a=i+1;
        boxHTML+='<tr ><td><input type="checkbox" id=""comicTypeGroup_'+i+'" /></td><td>'+a+'</td><td><a href="#">'+data[i].userName+'</a></td>' +
            '<td>'+data[i].address+'</td>' +
            '<td>'+data[i].phonenumber+'</td><td>'+data[i].qq+'</td><td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs">' +
            '<button class="am-btn am-btn-default am-btn-xs am-text-secondary">' +
            '<span class="am-icon-pencil-square-o"></span> 编辑</button>' +
            '<button class="am-btn am-btn-default am-btn-xs am-text-danger">' +
            '<span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>&nbsp;';
        if(i!=0 && i%6==0)
            boxHTML+='<br/>';
    }
    jiluHTML='<p>共 '+length+' 条记录</p>';
    var groupdiv = document.getElementById('view_user');
    groupdiv.innerHTML = boxHTML;
    var jiludiv = document.getElementById('div_jilu');
    jiludiv.innerHTML = jiluHTML;
    // checkboxOnClick(length);//必须加在这里
}
//获取用户数据库的函数
function getusers(){
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/hello/data",    //向后端请求数据的url
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
