/**
 * Created by ronnie on 15-11-8.
 */
function diaglog(title, url, width, height) {
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: 0.8,
        area: [width + 'px', height + 'px'],
        content: url
    });
}

function closeDaglog() {
    var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}

function reload() {
    parent.location.reload();
}