
package com.example.customdataadapter;

import android.os.Bundle;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;

/**
 * In this sample we are subclassing ChartFragment and overriding onCreate()
 * with the code to set up our chart. Our custom fragment implements Runnable
 * and simple adds data points at 200ms intervals when it is told to start
 * running. As we're using our CustomDataAdapter the chart is only being told to
 * update every 10 data points added.
 * <p>
 * Bear in mind this is quite a contrived example and should not be used for
 * production code!
 */
public class UpdatingChartFragment extends ChartFragment implements Runnable {

    private static final int MAX_DATAPOINTS = 50;
    private static final long DELAY = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the a reference to the ShinobiChart
        ShinobiChart shinobiChart = getShinobiChart();

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

    @Override
    public void onPause() {
        getView().removeCallbacks(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        run();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        // Retrieve the data adapter
        @SuppressWarnings("rawtypes")
        DataAdapter dataAdapter = getShinobiChart().getSeries().get(0).getDataAdapter();
        int count = dataAdapter.size();

        // Add a new point to the data adapter
        dataAdapter.add(new DataPoint<Double, Double>((double) count, count / 2.0));

        // For this demo we're adding 50 data points at 200ms intervals. We get
        // the fragments View so we can call postDelayed on it in order
        // to schedule the run() method to be called again in 200ms.
        if (count < UpdatingChartFragment.MAX_DATAPOINTS) {

            getView().postDelayed(this, UpdatingChartFragment.DELAY);
            count++;
        }
    }

}
