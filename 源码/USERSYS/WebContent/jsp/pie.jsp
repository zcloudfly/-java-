<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script src="${pageContext.request.contextPath}/js/highcharts-3d.js"></script>
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/export-data.js"></script>

<div id="piecontainer" style="height: 350px"></div>


<script type="text/javascript">
function pie(param){
	$.ajax({
		url:'${pageContext.request.contextPath}/SelectUsers?type=pie',
		type:'post',
		data:param,
		dataType:'json',
		success:function(ret){
		
		Highcharts.chart('piecontainer', {
		    chart: {
		        type: 'pie',
		        options3d: {
		            enabled: true,
		            alpha: 45,
		            beta: 0
		        }
		    },
		    title: {
		        text: param['text']+'展示'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            depth: 35,
		            dataLabels: {
		                enabled: true,
		                format: '{point.name}'
		            }
		        }
		    },
		    series: [{
		        type: 'pie',
		        name: 'Browser share',
		        data: ret
		    }]
		});
		}
	});
}
</script>

</html>