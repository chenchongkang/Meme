
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function add() {

    if(document.getElementById("password1").value==document.getElementById("password2").value&&document.getElementById("user_name").value.length!=0
        &&document.getElementById("password1").value.length!=0&&document.getElementById("user-email").value.length!=0&&document.getElementById("user-phone").value.length!=0&&document.getElementById("user-QQ").value.length!=0
        &&document.getElementById("user-intro").value.length!=0){
        var user_name = document.getElementById("user_name").value;
        var a=document.getElementById("user_name").value;
        var userName= JSON.stringify(a);
        var b=document.getElementById("password1").value;
        var password1=JSON.stringify(b);
        var c=document.getElementById("user-email").value;
        var useremail=JSON.stringify(c);
        var d=document.getElementById("user-phone").value;
        var userphone=JSON.stringify(d);
        var e=document.getElementById("user-QQ").value;
        var userQQ   =JSON.stringify(e);
        var f=document.getElementById("user-intro").value;
        var userintro=JSON.stringify(f);

        $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
            type:"post", //请求方式
            url:"./entityuser", //路径
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            data:'{"userName":'+userName+',"password":'+password1+',"address":'+useremail+',"phonenumber":'+userphone+',"avatar":"1.img","qq":'+userQQ+',"introduction":'+userintro+'}',
            success:function(){
                window.location.href= "http://localhost:8081/meme/admin-user.html"; //添加成功就跳转到login.html
            }
        });

        alert("添加成功");
    }

    else if(document.getElementById("password1").value!=document.getElementById("password2").value)
        alert("两次密码输入不一致");
    else if(document.getElementById("user_name").value.length==0)
        alert("昵称不能为空");
    else if(document.getElementById("password1").value.length==0)
        alert("密码不能为空");
    else if(document.getElementById("user-email").value.length==0)
        alert("邮箱不能为空");
    else if(document.getElementById("user-phone").value.length==0)
        alert("手机号不能为空");
    else if(document.getElementById("user-QQ").value.length==0)
        alert("QQ号不能为空");
    else if(document.getElementById("user-intro").value.length==0)
        alert("简介不能为空");
    else
        alert("填写格式错误")
};
