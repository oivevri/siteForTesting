<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // 차트에 들어갈 데이터
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        
        var chartData = [];
        // AJAX DATA 조회 (db 데이터 가져와서 아작스데이터 조회)
		$.ajax({
			url : "ajax/empStat.do",
			dataType : "json", // 넘어오는 데이터 타입
			async : false, // 동기방식
			success : function(result){ // 결과 처리할 콜백함수
				for(i=0; i<result.length; i++){
					chartData.push([result[i].department_id, result[i].cnt]); // push : 값 담는 자바스크립트 메소드..
				}
			}
		});
        
        data.addRows(chartData);

        // Set chart options
        var options = {'title':'부서별 인원수',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>