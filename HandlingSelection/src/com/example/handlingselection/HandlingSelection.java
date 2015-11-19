
package com.example.handlingselection;

import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.Legend.Placement;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.PieSeries;
import com.shinobicontrols.charts.Series;
import com.shinobicontrols.charts.Series.SelectionMode;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class HandlingSelection extends Activity implements ShinobiChart.OnSeriesSelectionListener {

    private ShinobiChart columnChart, pieChart;
    private SimpleDataAdapter<String, Double> sales_2012_data = new SimpleDataAdapter<String, Double>();
    private SimpleDataAdapter<String, Double> sales_2013_data = new SimpleDataAdapter<String, Double>();
    // TODO: replace <license_key_here> with your trial license key
    private final String licenseKey = "DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {
            createSalesData();
            createColumnChart();
            createPieChart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void createColumnChart() {
        // Get the a reference to the ShinobiChart from the ChartFragment
        ChartFragment chartFragment =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.column_chart);

        columnChart = chartFragment.getShinobiChart();

        // and give it a title
        columnChart.setTitle("Grocery Sales Figures");

        columnChart.setLicenseKey(licenseKey);

        // Add a pair of axes and remove the padding between series data points
        // of the same value on the X axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.getStyle().setInterSeriesPadding(0);
        columnChart.setXAxis(xAxis);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setTitle("Sales (1000s)");
        columnChart.setYAxis(yAxis);

        // Add a pair of series
        columnChart.addSeries(createColumnSeries("2012", sales_2012_data));
        columnChart.addSeries(createColumnSeries("2013", sales_2013_data));

        // Programmatically select one of the series
        columnChart.getSeries().get(0).setSelected(true);

        // Show the legend
        columnChart.getLegend().setVisibility(View.VISIBLE);
        columnChart.getLegend().setPlacement(Placement.INSIDE_PLOT_AREA);

        // Listen for series selection events
        columnChart.setSeriesSelectionSingle(true);
        columnChart.setOnSeriesSelectionListener(this);
    }

    ColumnSeries createColumnSeries(String title, DataAdapter<String, Double> dataAdapter) {
        // Create the column series
        ColumnSeries series = new ColumnSeries();
        series.setTitle(title);

        // Set the data
        series.setDataAdapter(dataAdapter);

        // Set the selection mode to select the whole series, remove the gradient and colour it red
        series.setSelectionMode(SelectionMode.SERIES);
        series.getSelectedStyle().setFillStyle(FillStyle.FLAT);
        series.getSelectedStyle().setAreaColor(Color.RED);
        return series;
    }

    private void createPieChart() {
        // Get the a reference to the ShinobiChart from the ChartFragment
        ChartFragment chartFragment =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.pie_chart);

        pieChart = chartFragment.getShinobiChart();

        pieChart.setLicenseKey(licenseKey);

        // Create the pie series
        PieSeries series = new PieSeries();
        series.setTitle("2012");

        // Set the data to the 2012 data for initialisation - this will change on chart selection
        series.setDataAdapter(sales_2012_data);

        //Add the series and make the legend visible
        pieChart.addSeries(series);
        pieChart.getLegend().setVisibility(View.VISIBLE);

    }

    private void createSalesData() {
        // Populate the data
        sales_2012_data.add(new DataPoint<String, Double>("Broccoli", 5.65));
        sales_2012_data.add(new DataPoint<String, Double>("Carrots", 12.6));
        sales_2012_data.add(new DataPoint<String, Double>("Mushrooms", 8.4));

        sales_2013_data.add(new DataPoint<String, Double>("Broccoli", 4.35));
        sales_2013_data.add(new DataPoint<String, Double>("Carrots", 13.2));
        sales_2013_data.add(new DataPoint<String, Double>("Mushrooms", 4.6));
        sales_2013_data.add(new DataPoint<String, Double>("Okra", 0.6));
    }

    @Override
    public void onPointSelectionStateChanged(Series<?> arg0, int arg1) {
    }

    @Override
    public void onSeriesSelectionStateChanged(Series<?> series) {
        if (series.isSelected()) {
            // Set the pie chart title to the selected series title
            pieChart.setTitle(series.getTitle());
            // Set the data of the pie series to the same as the selected series data
            pieChart.getSeries().get(0).setDataAdapter(series.getDataAdapter());
        }
    }

}
