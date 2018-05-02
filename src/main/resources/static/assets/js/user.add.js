
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function add() {
    var avatarpicture=null;
    var file=document.getElementById('file');
    if (file.files && file.files[0]) {
        avatarpicture = file.files[0];
    }
    var formdata = new FormData();
    formdata.append('file',avatarpicture);
if(avatarpicture==null)
    alert("请上传头像");
    if(document.getElementById("password1").value==document.getElementById("password2").value&&document.getElementById("user_name").value.length!=0
        &&document.getElementById("password1").value.length!=0&&document.getElementById("user-email").value.length!=0&&document.getElementById("user-phone").value.length!=0&&document.getElementById("user-QQ").value.length!=0
        &&document.getElementById("user-intro").value.length!=0){
        var userName=document.getElementById("user_name").value;
        var password1=document.getElementById("password1").value;
        var useremail=document.getElementById("user-email").value;
        var userphone=document.getElementById("user-phone").value;
        var userQQ=document.getElementById("user-QQ").value;
        var userintro=document.getElementById("user-intro").value;
        formdata.append('user_name',userName);
        formdata.append('password1',password1);
        formdata.append('user-email',userphone);
        formdata.append('user-phone',useremail);
        formdata.append('user-QQ',userQQ);
        formdata.append('user-intro',userintro);


        $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
            type:"post", //请求方式
            url:"./entityuser", //路径
            dataType:"json",
            // contentType: "application/json; charset=utf-8",
            data:formdata,
            processData:false,//用于对data参数进行序列化处理 这里传文件必须false
            contentType:false,
            // data:'{"userName":'+userName+',"password":'+password1+',"address":'+useremail+',"phonenumber":'+userphone+',"avatar":"1.img","qq":'+userQQ+',"introduction":'+userintro+'}',
            success:function(redata){
            if(redata.toString()==0)
            {
                alert("用户名已经存在");
            }
            else{
            window.location.href= "http://localhost:8081/meme/admin-user.html"; //添加成功就跳转到admin-user.html
            }
            }
        });
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
