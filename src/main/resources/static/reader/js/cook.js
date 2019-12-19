var options = {path: '/', expires: 10};			//定义路径，过期时间为10天
        //<![CDATA[
		//背景颜色
        $(function(){
			
            var $li =$("#skin li");
            $li.click(function(){
                switchSkin( this.id );
            });
            var cookie_skin = $.cookie( "backColor");
            if (cookie_skin) {
                switchSkin( cookie_skin );
            }

        });
        function switchSkin(skinsName){

                 $("#"+skinsName).addClass("selected")                 //当前<li>元素选中
                              .siblings().removeClass("selected");  //去掉其它同辈<li>元素的选中
                 $("body").attr("class",skinsName); //设置不同皮肤
				 
                $.cookie( "backColor" ,  skinsName ,options);
        }
		//字体大小
		$(function(){
			
            var $li =$("#size li");
            $li.click(function(){
                switchSize( this.id );
            });
            var cookie_size = $.cookie( "fontsize");
            if (cookie_size) {
                switchSize( cookie_size );
            }

        });
        function switchSize(sizesName){

                 $("#"+sizesName).addClass("selected")                 //当前<li>元素选中
                              .siblings().removeClass("selected");  //去掉其它同辈<li>元素的选中
                 $("#dx").attr("class",sizesName); //设置不同皮肤
				 
                $.cookie( "fontsize" ,  sizesName ,options);
        }
		//字体
		$(function(){
            var $li =$("#family li");
            $li.click(function(){
                switchFamily( this.id );
            });
            var cookie_family = $.cookie( "fontfamily");
            if (cookie_family) {
                switchFamily( cookie_family );
            }

        });
        function switchFamily(familysName){

                 $("#"+familysName).addClass("selected")                 //当前<li>元素选中
                              .siblings().removeClass("selected");  //去掉其它同辈<li>元素的选中
                 $("#zt").attr("class",familysName); //设置不同皮肤
				 
                $.cookie( "fontfamily" ,  familysName , options);
        }
//恢复默认方法
function defaultCL(){
	$("#skin_1").addClass("selected")                 //当前背景元素选中
                              .siblings().removeClass("selected"); 
	$("#size_0").addClass("selected")                 //当前大小元素选中
                              .siblings().removeClass("selected");
	$("#family_0").addClass("selected")                 //当前字体元素选中
                              .siblings().removeClass("selected");									
	$("body").attr('class','skin_1');			//清空背景颜色

	$.cookie('backColor','skin_1', options);	

	$("#dx").attr('class','size_0');			//清空大小

	$.cookie("fontsize", 'size_0', options);	

	
	$("#zt").attr('class','family_0');		//清空字体

	$.cookie("fontfamily", null, options);	
	

}  //]]>


