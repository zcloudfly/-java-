<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<table id="table_id" class="display">
    <thead>
    <tr>
        <th>ID</th>
        <th>性别</th>
        <th>出生年份</th>
        <th>飞行里程</th>
        <th>飞行时间</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
<script>
function uList(param) {
        $('#table_id').DataTable({
        	 language: {
        	     url:"${pageContext.request.contextPath}/jsp/c_zh.txt"
        	 },
        	 "destroy":true,
        	 "info" : true,
             "paging": true,
             "processing":false,
             "searching": false,
             "aLengthMenu":[10,15,20],
        	 serverSide: true,
             columns: [
                      { data: 'id' },
                      { data: 'sex' ,
                    	"render":function(data,type,row){
                    		return row.sex==1?'男':'女';
                    	}  
                      },
                      { data: 'bornYear' },
                      { data: 'tripDistance' },
                      { data: 'tripTime' }
                      
                  ],
             ajax: {
                      url: '${pageContext.request.contextPath}/SelectUsers',
                      type: 'POST',
                      data:param
                  }
        });
    }
</script>
</html>