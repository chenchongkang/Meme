
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

function add() {
    var avatarpicture=null;
    var file=document.getElementById('file');
    if (file.files && file.files[0]) {
        avatarpicture = file.files[0];
    }
    var formdata = new FormData();
    formdata.append('file',avatarpicture);
    if(avatarpicture==null)
        alert("请上传表情包封面");
    if(document.getElementById("meme_name").value.length!=0
        && document.getElementById("meme_classes").value!=0
        && document.getElementById("meme_author").value.length!=0
        && document.getElementById("meme_src").value.length!=0
        && document.getElementById("meme_intro").value.length!=0)
    {
        var memeName=document.getElementById("meme_name").value;
        var classis=document.getElementById("meme_classes").value;
        var author=document.getElementById("meme_author").value;
        var src=document.getElementById("meme_src").value;
        var memeIntro=document.getElementById("meme_intro").value;
        formdata.append('meme_name',memeName);
        formdata.append('meme_classes',classis);
        formdata.append('meme_author',author);
        formdata.append('meme_src',src);
        formdata.append('meme_intro',memeIntro);
        //用ajax来实现不刷新网页的基础上更新数据
        $.ajax({
            type:"post", //请求方式
            url:"./addmeme", //路径
            dataType:"json",
            // contentType: "application/json; charset=utf-8",
            data:formdata,
            processData:false,//用于对data参数进行序列化处理 这里传文件必须false
            contentType:false,
            // data:'{"memeName":'+memeName+',"classis":'+classis+',"author":'+author+',' +
            // '"src":'+src+',"cover":"2.img","memeIntro":'+memeIntro+',"upTime":"","downloads":"0"}',
            success:function(redata){
                if(redata.toString()==0)
                {
                    alert("路径已经存在");
                }
                else {
                    window.location.href = "http://localhost:8081/meme/pictureadd.html?src=" + src; //添加成功就跳转到login.html
                }
            },
            error:function() {
                alert("listComicType error");
            }
        });
    }
    else if(document.getElementById("meme_name").value.length==0)
        alert("名称不能为空");
    else if(document.getElementById("meme_classes").value.length==0)
        alert("分类不能为空");
    else if(document.getElementById("meme_author").value.length==0)
        alert("作者不能为空");
    else if(document.getElementById("meme_src").value.length==0)
        alert("路径不能为空");
    else if(document.getElementById("meme_intro").value.length==0)
        alert("描述不能为空");
    else
        alert("填写格式错误")
};
