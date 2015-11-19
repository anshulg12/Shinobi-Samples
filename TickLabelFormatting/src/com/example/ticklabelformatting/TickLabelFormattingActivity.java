
package com.example.ticklabelformatting;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.Title;

import java.text.DecimalFormat;

/**
 * In this sample, we first check for whether it is the first time the Activity
 * is being created as we only want to set it up the once. We then get a
 * reference to the ShinobiChart from the ChartFragment, which is inflated from
 * XML. This allows us to create a LineSeries with some simple data and add it
 * to the Chart. Following this, we create and style an X and Y axis. A custom
 * major tick frequency is set on the X axis and the tick mark labels on the Y
 * axis are formatted using a custom formatter.
 */
public class TickLabelFormattingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticklabelformatting);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            // Get a reference to the ShinobiChart from the ChartFragment
            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create our DataAdapter and data
            DataAdapter<Integer, Double> dataAdapter = new SimpleDataAdapter<Integer, Double>();
            dataAdapter.add(new DataPoint<Integer, Double>(0, 0.0));
            dataAdapter.add(new DataPoint<Integer, Double>(1, 1.657));
            dataAdapter.add(new DataPoint<Integer, Double>(2, 2.566));
            dataAdapter.add(new DataPoint<Integer, Double>(3, 3.785));
            dataAdapter.add(new DataPoint<Integer, Double>(4, 4.755));
            dataAdapter.add(new DataPoint<Integer, Double>(5, 5.557));
            dataAdapter.add(new DataPoint<Integer, Double>(6, 6.554));
            dataAdapter.add(new DataPoint<Integer, Double>(7, 7.5489));
            dataAdapter.add(new DataPoint<Integer, Double>(8, 8.406));
            dataAdapter.add(new DataPoint<Integer, Double>(9, 10.564));
            dataAdapter.add(new DataPoint<Integer, Double>(10, 11.123));

            // Create a LineSeries and give it the data adapter
            LineSeries series = new LineSeries();
            series.setDataAdapter(dataAdapter);
            shinobiChart.addSeries(series);

            // Create the axes and set them on the chart
            NumberAxis axisX = createXAxis();
            NumberAxis axisY = createYAxis();

            shinobiChart.setXAxis(axisX);
            shinobiChart.setYAxis(axisY);

            // Give the chart a title
            shinobiChart.setTitle("Crazy Widget Pricing");
        }
    }

    private NumberAxis createXAxis() {

        // Create the X axis with a default number range and title
        NumberAxis axisX = new NumberAxis();
        axisX.setDefaultRange(new NumberRange(0.0, 11.0));
        axisX.setTitle("Number of widgets purchased");

        // Style the axis - blue axis line, grid lines and title to the right
        axisX.getStyle().setLineColor(Color.argb(255, 60, 60, 215));
        axisX.getStyle().getGridlineStyle().setGridlinesShown(true);
        axisX.getStyle().getTitleStyle().setPosition(Title.Position.TOP_OR_RIGHT);

        // Set the custom tick mark frequency for the X axis - only using major ticks
        axisX.setMajorTickFrequency(2.0);

        return axisX;
    }

    private NumberAxis createYAxis() {

        // Create the Y axis with a default number range and title
        NumberAxis axisY = new NumberAxis();
        axisY.setDefaultRange(new NumberRange(0.0, 12.5));
        axisY.setTitle("Total price");

        // Style the axis - blue axis line, grid lines and include minor ticks
        axisY.getStyle().setLineColor(Color.argb(255, 60, 60, 215));
        axisY.getStyle().getGridlineStyle().setGridlinesShown(true);
        axisY.getStyle().getTickStyle().setMinorTicksShown(true);

        // Set the custom tick mark frequency for the Y axis - both minor and major ticks
        axisY.setMajorTickFrequency(1.0);
        axisY.setMinorTickFrequency(0.25);

        // Set the custom tick mark format for the Y axis
        DecimalFormat formatY = new DecimalFormat("$0.00");
        formatY.setDecimalSeparatorAlwaysShown(true);
        axisY.setLabelFormat(formatY);

        return axisY;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ticklabelformatting, menu);
        return true;
    }

}
