
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function add() {

    if(document.getElementById("meme_name").value.length!=0 && document.getElementById("meme_classes").value!=0 && document.getElementById("meme_author").value.length!=0
        && document.getElementById("meme_src").value.length!=0 && document.getElementById("meme_intro").value.length!=0){

        var a=document.getElementById("meme_name").value;
        var memeName= JSON.stringify(a);
        var b=document.getElementById("meme_classes").value;
        var classis=JSON.stringify(b);
        var c=document.getElementById("meme_author").value;
        var author=JSON.stringify(c);
        var d=document.getElementById("meme_src").value;
        var src=JSON.stringify(d);
        var e=document.getElementById("meme_intro").value;
        var memeIntro =JSON.stringify(e);


        $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
            type:"post", //请求方式
            url:"./entityuser", //路径
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            data:'{"memeName":'+memeName+',"classis":'+classis+',"author":'+author+',"src":'+src+',"cover":"2.img","memeIntro":'+memeIntro+'}',
            success:function(){
                window.location.href= "http://localhost:8081/meme/admin-user.html"; //添加成功就跳转到login.html
            }
        });

        alert("添加成功");
    }

    else if(document.getElementById("memeName").value.length==0)
        alert("名称不能为空");
    else if(document.getElementById("classis").value.length==0)
        alert("分类不能为空");
    else if(document.getElementById("author").value.length==0)
        alert("作者不能为空");
    else if(document.getElementById("src").value.length==0)
        alert("路径不能为空");
    else if(document.getElementById("memeIntro").value.length==0)
        alert("描述不能为空");
    else
        alert("填写格式错误")
};
