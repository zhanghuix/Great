
	function FileUploadUtil(){}
	
	FileUploadUtil.initUpload=function(fileId){
		
		var s =  '<form action="'+getSiteRootUrl()+'work/public/upload/upload.jsp" id="'+fileId+'UploadForm"  encType="multipart/form-data"  method="post" target="'+fileId+'HiddenFrame" >'
			  + '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">'
			  + '	<tr>'
			  + '		<td >'
			  + '			<input name="'+fileId+'" type="file" id="'+fileId+'" style="width:100%"/>'
			  + '		</td>'
			  + '		<td width="2"></td>'
			  + '		<td id="bnt_id" width="65">'
			  + '			<input type="button"  value="上传文件" onclick="FileUploadUtil.submitUpload(\''+fileId+'\')"/>'
			  + '		</td>'
			  + '	</tr>'
			  + '</table>'
			  + '</form>';
		document.write(s);	
	};

	//上面那个方法会导致easyui的layout消失
	//targetId为生成上传组件的位置的父元素id
	FileUploadUtil.initUpload2=function(fileId,targetId,basePath){
		alert(basePath);
		var s =  '<form action='+basePath+'background/upload/uploadFile.html" id="'+fileId+'UploadForm"  encType="multipart/form-data"  method="post" target="'+fileId+'HiddenFrame" >'
			  + '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">'
			  + '	<tr>'
			  + '		<td >'
			  + '			<input name="'+fileId+'" type="file" id="'+fileId+'" style="width:100%"/>'
			  + '			<input  type="hidden" id="swjgHidden"/>'
			  + '		</td>'
			  + '		<td width="2"></td>'
			  + '		<td id="bnt_id" width="95" align="right">'
			  + '			<input type="button"  value="上传文件" onclick="FileUploadUtil.submitUpload(\''+fileId+'\')"/>'
			  + '		</td>'
			  + '	</tr>'
			  + '</table>'
			  + '</form>';
		document.getElementById(targetId).innerHTML=s;	
	};

	FileUploadUtil.submitUpload=function(fileId){
		
		var filepath = document.getElementById(fileId).value;
		if(filepath==""){
			alert("请选择文件进行上传。");
			return;
		}else{
				new ajaxFileUpload({
					id:fileId,
					callback:function(res){
						var data = res;//FileUploadUtil.formatResponseText(this.responseText);
                       /* if(data.uploadFlag=="N"){
                            alert("上传失败，请上传非EXE和BAT文件！");
                            return;
                        }
						data["id"]=fileId;*/
					}
				}); 
		}
	};
	
	FileUploadUtil.formatResponseText=function(s){
		var data={};
		var a = s.split(";");
		for(var i=0;i<a.length;i++){
			var keys = a[i].split("=");
			data[keys[0]]=keys[1];
		}
		return data;
	}

	//设置上传文件所属系统的名称
	FileUploadUtil.getSystemName=function(){		 
		return "common";
	}

	var ajaxFileUpload = function(opts){
	    return new ajaxFileUpload.prototype.init(opts);
	};
	
	ajaxFileUpload.prototype = {
	    init:function(opts){
			var set = this.extend({
			    id:'fileId',
			    callback:function(){}
			},opts || {});
			var _this = this;
			var form = document.getElementById(set.id+"UploadForm");
			var frame = this.createIframe(set.id);
			setTimeout(function(){
				if(FileUploadUtil.getSystemName() &&　form.action.indexOf("?xt=")<0){
					form.action=form.action+"?xt="+FileUploadUtil.getSystemName();
				}				
			    form.submit();
			    if(frame.attachEvent){
				frame.attachEvent('onload',function(){_this.uploadCallback(set.id,set.callback);});
			    }else{
				frame.onload = function(){_this.uploadCallback(set.id,set.callback);}
			    }
			},100);
	    },

	    /*
		创建iframe，ie7和6比较蛋疼，得像下面那样创建，否则会跳转
	    */
	    createIframe:function(id){
			var frameId = id+"HiddenFrame";
			var  iFrame;
			
			var IE = /msie ((\d+\.)+\d+)/i.test(navigator.userAgent) ? (document.documentMode ||  RegExp['\x241']) : false;
			
			if(IE && IE < 8){
			    iFrame = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
			    
			}else{
			    iFrame = document.createElement('iframe');
			    this.attr(iFrame,{
				'id':frameId,
				'name':frameId
			    });
			};
			iFrame.style.cssText = 'display:none;';
			return document.body.appendChild(iFrame);
	    },

	    /*
		获取iframe内容，执行回调函数，并移除生成的iframe和form
	    */
	    uploadCallback:function(id,callback){
			var form = document.getElementById(id+"UploadForm");
			var frame = document.getElementById(id+"HiddenFrame");
			var data = {};
			var db = document.body;
			try{
			    if(frame.contentWindow){
			    	data.responseText = frame.contentWindow.document.body ? frame.contentWindow.document.body.innerHTML : null;
			    	data.responseXML = frame.contentWindow.document.XMLDocument ? frame.contentWindow.document.XMLDocument : frame.contentWindow.document;
			    }else{
			    	data.responseText = frame.contentDocument.document.body ? frame.contentDocument.document.body.innerHTML : null;
			    	data.responseXML = frame.contentDocument.document.XMLDocument ? frame.contentDocument.document.XMLDocument : frame.contentDocument.document;
			    }
			}catch(e){};
			callback && callback.call(data);
			setTimeout(function(){
			    db.removeChild(frame);
			    var obj = document.getElementById(id);
			    obj.outerHTML=obj.outerHTML;
			},100);
	    },
	    attr:function(el,attrs){
			for(var prop in attrs) el.setAttribute(prop,attrs[prop]);
			return el;
	    },
	    extend:function(target,source){
			for(var prop in source) target[prop] = source[prop];
			return target;
	    }
	};

	ajaxFileUpload.prototype.init.prototype = ajaxFileUpload.prototype;
	
	
	
	
	
	//月末
	function getLastDay(rq){
		var dd = new Date(rq);
	  var y = dd.getFullYear();
	  var m = (dd.getMonth()+2 ) < 10 ? "0" + (dd.getMonth()+2 ) : (dd.getMonth()+2 );
		 return GetDateStr(y+"/"+m+"/"+"01",-1);
		  
		  
		  
		
	}
	//月初
	function getFirstDay(rq){
		var dd = new Date(rq);
	  var y = dd.getFullYear();
	  var m = (dd.getMonth()+1 ) < 10 ? "0" + (dd.getMonth()+1 ) : (dd.getMonth()+1 );
		 return y+"-"+m+"-"+"01";
		
	}

	//上月月初
	function getPreviousDay(){
		var dd = new Date();
	  var y = dd.getFullYear();
	  var m = (dd.getMonth()+1 ) < 10 ? "0" + (dd.getMonth()+1 ) : (dd.getMonth()+1 );
		return (GetDateStr(y+"-"+m+"-"+'01',-1));
		
		
	}

	function GetDateStr(rq , AddDayCount) {

	   var dd = new Date(Date.parse(rq));
	// 加一天
	    dd.setDate(dd.getDate() +AddDayCount);
	    var y = dd.getFullYear();
	    var m = (dd.getMonth()+1 ) < 10 ? "0" + (dd.getMonth()+1 ) : (dd.getMonth()+1 );
	    var d = (dd.getDate() ) < 10 ? "0" + (dd.getDate() ) : (dd.getDate() );
	  
	    return y+"-"+m+"-"+d;
	}

	
	
	
	
	
	