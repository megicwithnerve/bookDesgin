var _num = 10;
function LastRead(){
	this.bookList="bookList"
	}
LastRead.prototype={	
	set:function(bid,tid,title,texttitle,zuozhe,sortid,sortname){
		if(!(bid&&tid&&title&&texttitle&&zuozhe&&sortid&&sortname))return;
		var v=bid+'#'+tid+'#'+title+'#'+texttitle+'#'+zuozhe+'#'+sortid+'#'+sortname;
		this.setItem(bid,v);
		this.setBook(bid)		
		},
	
get:function(k){
		return this.getItem(k)?this.getItem(k).split("#"):"";						
		},
	
	remove:function(k){
		this.removeItem(k);
		this.removeBook(k)			
		},
	
	setBook:function(v){
		var reg=new RegExp("(^|#)"+v); 
		var books =	this.getItem(this.bookList);
		if(books==""){
			books=v
			}
		 else{
			 if(books.search(reg)==-1){
				 books+="#"+v				 
				 }
			 else{
				  books.replace(reg,"#"+v)
				 }	 
			 }	
			this.setItem(this.bookList,books)
		
		},
	
	getBook:function(){
		var v=this.getItem(this.bookList)?this.getItem(this.bookList).split("#"):Array();
		var books=Array();
		if(v.length){
			
			for(var i=0;i<v.length;i++){
				var tem=this.getItem(v[i]).split('#');	
				if(i>v.length-(_num+1)){
					if (tem.length>3)	books.push(tem);
				}
				else{
					lastread.remove(tem[0]);
				}
			}		
		}
		return books		
	},
	
	removeBook:function(v){		
	    var reg=new RegExp("(^|#)"+v); 
		var books =	this.getItem(this.bookList);
		if(!books){
			books=""
			}
		 else{
			 if(books.search(reg)!=-1){	
			      books=books.replace(reg,"")
				 }	 
			 
			 }	
			this.setItem(this.bookList,books)		
		
		},
	
	setItem:function(k,v){
		if(!!window.localStorage){		
			localStorage.setItem(k,v);		
		}
		else{
			var expireDate=new Date();
			  var EXPIR_MONTH=30*24*3600*1000;			
			  expireDate.setTime(expireDate.getTime()+12*EXPIR_MONTH)
			  document.cookie=k+"="+encodeURIComponent(v)+";expires="+expireDate.toGMTString()+"; path=/";		
			}			
		},
		
	getItem:function(k){
		var value=""
		var result=""				
		if(!!window.localStorage){
			result=window.localStorage.getItem(k);
			 value=result||"";	
		}
		else{
			var reg=new RegExp("(^| )"+k+"=([^;]*)(;|\x24)");
			var result=reg.exec(document.cookie);
			if(result){
				value=decodeURIComponent(result[2])||""}				
		}
		return value
		
		},
	
	removeItem:function(k){		
		if(!!window.localStorage){
		 window.localStorage.removeItem(k);		
		}
		else{
			var expireDate=new Date();
			expireDate.setTime(expireDate.getTime()-1000)	
			document.cookie=k+"= "+";expires="+expireDate.toGMTString()							
		}
		},	
	removeAll:function(){
		if(!!window.localStorage){
		 window.localStorage.clear();		
		}
		else{
		var v=this.getItem(this.bookList)?this.getItem(this.bookList).split("#"):Array();
		var books=Array();
		if(v.length){
			for( i in v ){
				var tem=this.removeItem(v[k])				
				}		
			}
			this.removeItem(this.bookList)				
		}
		}	
	}
function showbook(){var showbook=document.getElementById('zuijin');var bookhtml='';var books=lastread.getBook();if(books.length){for(var i=books.length-1;i>-1;i--){var _17mb_id1000=parseInt(books[i][0]/1000);
bookhtml+='<li>';
bookhtml+='<span class="lb"><a href="/class/'+books[i][5]+'_1.html" target="_blank">'+books[i][6]+'</a></span>';
bookhtml+='<span style="width:154px"><a href="/html/'+books[i][0]+'.html" target="_blank">'+books[i][2]+'</a></span>';
bookhtml+='<span style="width:154px"><a href="/author/'+books[i][4]+'" target="_blank">'+books[i][4]+'</a></span>';
bookhtml+='<span class="sq"><a href="/html/'+books[i][0]+'/'+books[i][1]+'.html" target="_blank">'+books[i][3]+'</a></span>';
bookhtml+='<span style="width:56px"><a href="javascript:removebook(\''+books[i][0]+'\')">移除</a></span>';
bookhtml+='<span style="width:90px"><b><a href="javascript:Ajax.Request(\'/modules/article/addbookcase.php?bid='+books[i][0]+'\',{onComplete:function(){alert(this.response.replace(/<br[^<>]*>/g,\'\\n\'));}});">加入书架</a></b></span>';
bookhtml+='</li>';}}
showbook.innerHTML=bookhtml;}
function removebook(k){lastread.remove(k);showbook()}
function yuedu(){showbook();}
window.lastread=new LastRead();

