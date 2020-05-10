<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script src="${pageContext.request.contextPath}/js/data.js"></script>
<script src="${pageContext.request.contextPath}/js/drilldown.js"></script>
<div id="colcontainer" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function column(param){
	$.ajax({
		url:'${pageContext.request.contextPath}/SelectUsers?type=column',
		type:'post',
		data:param,
		dataType:'json',
		success:function(ret){
		Highcharts.chart('colcontainer', {
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: param['text']+'展示'
		    },
		    subtitle: {
		        text: 'Click the columns to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
		    },
		    xAxis: {
		        type: 'category'
		    },
		    yAxis: {
		        title: {
		            text: 'Total percent market share'
		        }
		
		    },
		    legend: {
		        enabled: false
		    },
		    plotOptions: {
		        series: {
		            borderWidth: 0,
		            dataLabels: {
		                enabled: true,
		                format: '{point.y:.1f}%'
		            }
		        }
		    },
		
		    tooltip: {
		        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		    },
		
		    series: [
		        {
		            name: "Browsers",
		            colorByPoint: true,
		            data: ret
		        }
		    ]
		});
		}
	});
}
</script>
</html>