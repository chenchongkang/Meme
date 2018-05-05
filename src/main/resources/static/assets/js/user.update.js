
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function updateuserEdt(){
    var result;
    var url=window.location.search;
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/entityuserfindid/"+result,    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        cache:false,
        success: function (data) {
            var length = data.length;
            xiugai(data);
        },
        error:function() {
            alert("listComicType error");
        }
    });

}
// var transid=$.query.get("id");


function xiugai(data) {
    var img = null;
    var str = '';
    var div = document.getElementById("image");
    if(data.avatar!=null)
    {
        str+= ' <img id="pic" class="am-img-circle am-img-thumbnail" alt="" style="width:100px;height:100px;"/>';
        div.innerHTML = str;
        img = document.getElementById("pic");
        img.src = "getuseravatar/"+data.userID;
    }
    document.getElementById("user_name").value=data.userName.toString();
    document.getElementById("password1").value=data.password.toString();
    document.getElementById("password2").value=data.password.toString();
    document.getElementById("user-email").value=data.address.toString();
    document.getElementById("user-phone").value=data.phonenumber.toString();
    document.getElementById("user-QQ").value=data.qq.toString();
    document.getElementById("user-intro").value=data.introduction.toString();
    document.getElementById("user_avatar").value=data.avatar.toString();
}

function tijiao() {
    var result;
    var url = window.location.search;
    if (url.indexOf("?") != -1) {
        result = url.substr(url.indexOf("=") + 1);
    }
    var formdata = new FormData();
    var avatarpicture = null;
    var file = document.getElementById('file');
    var picture=document.getElementById('pic');
    if (document.getElementById("password1").value == document.getElementById("password2").value && document.getElementById("user_name").value.length != 0
        && document.getElementById("password1").value.length != 0 && document.getElementById("user-email").value.length != 0 && document.getElementById("user-phone").value.length != 0 && document.getElementById("user-QQ").value.length != 0
        && document.getElementById("user-intro").value.length != 0) {
        var userName = document.getElementById("user_name").value;
        var password1 = document.getElementById("password1").value;
        var useremail = document.getElementById("user-email").value;
        var userphone = document.getElementById("user-phone").value;
        var userQQ = document.getElementById("user-QQ").value;
        var userintro = document.getElementById("user-intro").value;
        formdata.append('user_name', userName);
        formdata.append('password1', password1);
        formdata.append('user-email', useremail);
        formdata.append('user-phone', userphone);
        formdata.append('user-QQ', userQQ);
        formdata.append('user-intro', userintro);
        if (file.files && file.files[0]) {
            avatarpicture = file.files[0]
            formdata.append('file', avatarpicture);
        }
        else {
            var userAvatar=document.getElementById("user_avatar").value;
            formdata.append('user_avatar',userAvatar);
        }
        //用ajax来实现不刷新网页的基础上更新数据
        $.ajax({
            type: "post", //请求方式
            url: "http://localhost:8081/meme/entityuserupdate/" + result, //路径
            dataType: "json",
            data: formdata,
            //用于对data参数进行序列化处理 这里传文件必须false
            processData: false,
            contentType: false,
            success: function (redata) {
                if (redata.toString() == 0) {
                    alert("用户名已经存在");
                }
                else {
                    window.location.href = "http://localhost:8081/meme/admin-user.html"; //修改成功就跳转到admin-user.html
                    alert("修改成功");
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