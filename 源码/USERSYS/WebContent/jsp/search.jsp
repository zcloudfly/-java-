<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="easyui-panel" style="width:100%;padding:30px 60px;position:relative;">
    <form id="sform" >
        <table>
            <tr>
                <td>
	                 <label >查询类别:</label>
	                 <select id="stype" class="easyui-combobox" name="selectType" style="width:120px;">   
					   <option value="BORNYEAR">按出生年份</option>  
					   <option value="TRIPDISTANCE">按旅行里程</option>  
					   <option value="TRIPTIME">按旅行时间</option>   
					</select> 
                </td>
                <td>
		            <label >起始--结束:</label>
		            <input class="easyui-textbox"  type="text" name="begin" style="width:120px;" /> --
		            <input class="easyui-textbox"  type="text" name="end" style="width:120px;" />
                </td>
                <td>
                    <label >展示间隔:</label>
                    <input class="easyui-textbox"  type="text" name="span" value="${requestScope.span }" style="width:120px;" />
                </td>
                <td>
			        <a id="sbtn"  class="easyui-linkbutton" >查询</a>
                </td>
            </tr>
        </table>
        
    </form>
 
</div>

<div id="tt" class="easyui-tabs" style="width:100%;height:100%;">   
    <div title="列表展示" style="display:none;width:100%;height:100%;">   
        <%@include file="userList.jsp" %>  
    </div>   
    <div title="饼状图" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">   
        <%@include file="pie.jsp" %>     
    </div>   
    <div title="柱状图" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">   
           <%@include file="column.jsp" %> 
    </div> 
    <div title="折线图" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">   
         <%@include file="line.jsp" %>  
    </div>   
 </div> 
 <script type="text/javascript">
  
  $(document).ready(function () {
	    var selectType=$('#stype').combo('getValue');
	    var text=$('#stype').combo('getText');
	    var param={'selectType':selectType,'text':text};
	    uList(param);
	    pie(param);
	    column(param);
	    line(param);
	    });
  $("#sbtn").click(function () {
	    var selectType=$('#stype').combo('getValue');
	    var text=$('#stype').combo('getText');
	    var span=$('input[name="span"]').val();
	    var begin=$('input[name="begin"]').val();
	    var end=$('input[name="end"]').val();
	    var param={'selectType':selectType,'begin':begin,'end':end,'span':span,'text':text};
	    uList(param);
	    pie(param);
	    column(param);
	    line(param);
	    
  })
  </script>
</html>