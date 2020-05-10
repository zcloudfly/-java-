<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/highcharts-more.js"></script>

<div id="linecontainer" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function line(param){
$.ajax({
	url:'${pageContext.request.contextPath}/SelectUsers?type=line',
	type:'post',
	data:param,
	dataType:'json',
	success:function(ret){
		var averages=ret;
      Highcharts.chart('linecontainer', {

          title: {
              text: param['text']+'展示'
          },

          /* xAxis: {
              type: 'year'
          }, */

          yAxis: {
              title: {
                  text: null
              }
          },

          tooltip: {
              crosshairs: true,
              shared: true,
              valueSuffix: '人'
          },

          legend: {
          },

          series: [{
              name: '年',
              data: averages,
              zIndex: 1,
              marker: {
                  fillColor: 'white',
                  lineWidth: 2,
                  lineColor: Highcharts.getOptions().colors[0]
              }
          }]
      });
	}
});
}
</script>
		
</html>