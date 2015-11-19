
package com.example.columnseriesstacking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

/**
 * In this sample, we first check for whether it is the first time the Activity
 * is being created as we only want to set it up the once. We then get a
 * reference to the ShinobiChart from the ChartFragment, which is inflated from
 * XML. This allows us to create several ColumnSeries with some simple data and
 * set their stack id's. We will then add these series to the chart and observe
 * the stacking behavior.
 */
public class ColumnSeriesStackingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stackedcolumnseries);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            // Get a reference to the ShinobiChart from the ChartFragment
            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart
			.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create our DataAdapters and data
            DataAdapter<String, Double> da1 = new SimpleDataAdapter<String, Double>();
            da1.add(new DataPoint<String, Double>("q1", 1.0));
            da1.add(new DataPoint<String, Double>("q2", 3.0));
            da1.add(new DataPoint<String, Double>("q3", 4.5));
            da1.add(new DataPoint<String, Double>("q4", -2.5));

            DataAdapter<String, Double> da2 = new SimpleDataAdapter<String, Double>();
            da2.add(new DataPoint<String, Double>("q1", 1.5));
            da2.add(new DataPoint<String, Double>("q2", 3.6));
            da2.add(new DataPoint<String, Double>("q3", 5.5));
            da2.add(new DataPoint<String, Double>("q4", -3.5));

            DataAdapter<String, Double> da3 = new SimpleDataAdapter<String, Double>();
            da3.add(new DataPoint<String, Double>("q1", 1.57));
            da3.add(new DataPoint<String, Double>("q2", 10.6));
            da3.add(new DataPoint<String, Double>("q3", 45.5));
            da3.add(new DataPoint<String, Double>("q4", -13.5));

            DataAdapter<String, Double> da4 = new SimpleDataAdapter<String, Double>();
            da4.add(new DataPoint<String, Double>("q1", 2.57));
            da4.add(new DataPoint<String, Double>("q2", 7.6));
            da4.add(new DataPoint<String, Double>("q3", 28.5));
            da4.add(new DataPoint<String, Double>("q4", -17.5));

            // Set up the axes and apply them to the chart
            CategoryAxis axisX = new CategoryAxis();
            axisX.setTitle("Revenue Quarter");
            axisX.getStyle().setInterSeriesPadding(1.0f);

            shinobiChart.setXAxis(axisX);

            NumberAxis axisY = new NumberAxis();
            axisY.setTitle("Revenue ($M)");

            shinobiChart.setYAxis(axisY);

            // Set up legend
            shinobiChart.getLegend().setVisibility(View.VISIBLE);
            shinobiChart.getLegend().setTitle("Region");

            // Create our ColumnSeries and give them their data adapters
            ColumnSeries series1 = new ColumnSeries();
            series1.setDataAdapter(da1);
            series1.setTitle("North");

            ColumnSeries series2 = new ColumnSeries();
            series2.setDataAdapter(da2);
            series2.setTitle("South");

            ColumnSeries series3 = new ColumnSeries();
            series3.setDataAdapter(da3);
            series3.setTitle("East");

            ColumnSeries series4 = new ColumnSeries();
            series4.setDataAdapter(da4);
            series4.setTitle("West");

            // Set the stack id of each series and add it to the chart - in order for the series to
            // stack on top of each other they must have the same stackId - the number is irrelevant
            series1.setStackId(1);
            shinobiChart.addSeries(series1);
            series2.setStackId(1);
            shinobiChart.addSeries(series2);
            series3.setStackId(1);
            shinobiChart.addSeries(series3);
            series4.setStackId(1);
            shinobiChart.addSeries(series4);

            // Give the chart a title
            shinobiChart.setTitle("Annual Revenue");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.columnseriesstacking, menu);
        return true;
    }

}
