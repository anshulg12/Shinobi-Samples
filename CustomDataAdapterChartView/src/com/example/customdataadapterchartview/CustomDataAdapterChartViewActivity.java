
package com.example.customdataadapterchartview;

import com.shinobicontrols.charts.ChartView;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

public class CustomDataAdapterChartViewActivity extends Activity implements Runnable {

    private static final int MAX_DATAPOINTS = 50;
    private static final long DELAY = 200;
    private ChartView chartView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_data_adapter);

        // If the activity is newly created
        if (chartView == null) {

            chartView = (ChartView) this.findViewById(R.id.chart);

            ChartView oldChartView = null;
            @SuppressWarnings("deprecation")
            Object o = getLastNonConfigurationInstance();
            if (o != null && o instanceof ChartView) {
                oldChartView = (ChartView) o;
            }

            // If this is the activity's first existence
            if (oldChartView == null) {
                // Get the a reference to the ShinobiChart
                ShinobiChart shinobiChart = chartView.getShinobiChart();

                // TODO: replace <license_key_here> with your trial license key
                shinobiChart
				.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

                // Set the title
                shinobiChart.setTitle("Custom Data Adapter");

                // Create X and Y axes and add to the chart
                NumberAxis xAxis = new NumberAxis();
                shinobiChart.setXAxis(xAxis);
                xAxis.getStyle().setInterSeriesPadding(0.2f);

                NumberAxis yAxis = new NumberAxis();
                shinobiChart.setYAxis(yAxis);

                // Create our custom DataAdapter
                @SuppressWarnings("rawtypes")
                DataAdapter dataAdapter = new CustomDataAdapter<Double, Double>();

                // Create a ColumnSeries and give it the data adapter
                ColumnSeries series = new ColumnSeries();
                series.setDataAdapter(dataAdapter);
                shinobiChart.addSeries(series);
            }
            // If the activity had a previous existence
            else {
                // Remove the new ChartView from its parent
                ViewGroup parent = ((ViewGroup) chartView.getParent());
                parent.removeView(chartView);
                // Add the old Chartview to the parent, and replace the local
                // reference
                parent.addView(oldChartView);
                chartView = oldChartView;
            }

            run();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove the old ChartView from its parent
        ViewGroup parent = (ViewGroup) chartView.getParent();
        parent.removeView(chartView);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        // Tell Android to hold on to the ChartView
        return chartView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_data_adapter, menu);
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (chartView != null) {
            chartView.removeCallbacks(this);
            // Ensure the GL views get to hear about the pause/resume events
            chartView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (chartView != null) {
            // Ensure the GL views get to hear about the pause/resume events
            chartView.onResume();
            run();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {

        // Retrieve the data adapter
        @SuppressWarnings("rawtypes")
        DataAdapter dataAdapter = chartView.getShinobiChart().getSeries().get(0).getDataAdapter();

        // Add a new point to the data adapter
        int count = dataAdapter.size();
        dataAdapter.add(new DataPoint<Double, Double>((double) count, count / 2.0));

        // For this demo we're adding 50 data points at 200ms intervals. We get
        // the chartView so we can call postDelayed on it in order
        // to schedule the run() method to be called again in 200ms.
        if (count < MAX_DATAPOINTS) {

            chartView.postDelayed(this, DELAY);
            count++;
        }
    }

}
