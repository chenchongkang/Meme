document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function updateEdt(){
    var result;
    var url=window.location.search;
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/entitymemefindid/"+result,    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        cache:false,
        success: function (data) {
            // alert(JSON.stringify(data));
            var length = data.length;
            xiugai(data);
            // window.location.href = "http://localhost:8081/meme/memeupdate.html";
        },
        error:function() {
            alert("listComicType error");
        }
    });
}
    // var transid=$.query.get("id");


function xiugai(data) {
    var b;
    var a=data.classis.toString();
    if(a=="type1")
        b=0;
    if(a=="type2")
        b=1;
    if(a=="type3")
        b=2;
    document.getElementById("meme_name").value=data.memeName.toString();
    document.getElementById("meme_classes")[b].selected=true;
    document.getElementById("meme_author").value=data.author.toString();
    document.getElementById("meme_src").value=data.memeSrc.toString();
    document.getElementById("meme_intro").value=data.memeIntro.toString();
    var img = null;
    var str = '';
    var div = document.getElementById("image");
    if(data.cover!=null)
    {
        str+= ' <img id="id_license_img" class="am-img-circle am-img-thumbnail" alt="" style="width:100px;height:100px;"/>';
        div.innerHTML = str;
        img = document.getElementById("id_license_img");
        img.src = "getmemecover/"+data.memeID;
    }
}

document.getElementById('file').onchange = function() {
    if(typeof FileReader != 'undefined'){
        var file = document.getElementById("file").files[0];
        if((file.type).indexOf("image/")==-1){
            alert("请上传图片文件!");
        }
    }else{
        var fileName=document.getElementById("file").value;
        var suffixIndex=fileName.lastIndexOf(".");
        var suffix=fileName.substring(suffixIndex+1).toUpperCase();
        if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){
            alert( "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!");
        }
    }
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function tijiao() {
    var result;
    var url=window.location.search;
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);
    }
    var avatarpicture=null;
    var formdata = new FormData();

    {
        if (document.getElementById("meme_name").value.length != 0
            && document.getElementById("meme_classes").value != 0
            && document.getElementById("meme_author").value.length != 0
            && document.getElementById("meme_src").value.length != 0
            && document.getElementById("meme_intro").value.length != 0) {
            var memeName = document.getElementById("meme_name").value;
            // var memeName = JSON.stringify(a);
            var classis = document.getElementById("meme_classes").value;
            // var classis = JSON.stringify(b);
            var author = document.getElementById("meme_author").value;
            // var author = JSON.stringify(c);
            var src = document.getElementById("meme_src").value;
            // var src = JSON.stringify(d);
            var memeIntro = document.getElementById("meme_intro").value;
            // var memeIntro = JSON.stringify(e);
            formdata.append('meme_name',memeName);
            formdata.append('meme_classes',classis);
            formdata.append('meme_author',author);
            formdata.append('meme_src',src);
            formdata.append('meme_intro',memeIntro);
            var file=document.getElementById('file');
            if (file.files && file.files[0]) {
                avatarpicture = file.files[0];
                formdata.append('file',avatarpicture);
            }
            //用ajax来实现不刷新网页的基础上更新数据
            $.ajax({
                type: "post", //请求方式
                url: "./entitymemeupdate/"+result, //路径
                dataType: "json",
                // contentType: "application/json; charset=utf-8",
                data:formdata,
                processData:false,//用于对data参数进行序列化处理 这里传文件必须false
                contentType:false,
                // data: '{"memeName":' + memeName + ',"classis":' + classis + ',"author":' + author + ',' +
                // '"src":' + src + ',"cover":"2.img","memeIntro":' + memeIntro + ',"upTime":"","downloads":"0"}',
                success: function () {
                    window.location.href = "http://localhost:8081/meme/admin-meme.html"; //修改成功就跳转到login.html
                    alert("修改成功");
                },
                error: function () {
                    alert("listComicType error");
                }
            });
        }
        else if (document.getElementById("meme_name").value.length == 0)
            alert("名称不能为空");
        else if (document.getElementById("meme_classes").value.length == 0)
            alert("分类不能为空");
        else if (document.getElementById("meme_author").value.length == 0)
            alert("作者不能为空");
        else if (document.getElementById("meme_src").value.length == 0)
            alert("路径不能为空");
        else if (document.getElementById("meme_intro").value.length == 0)
            alert("描述不能为空");
        else
            alert("填写格式错误")
    };
}
