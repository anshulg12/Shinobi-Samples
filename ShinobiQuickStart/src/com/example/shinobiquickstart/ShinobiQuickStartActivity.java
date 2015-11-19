
package com.example.shinobiquickstart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.LineSeriesStyle;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

public class ShinobiQuickStartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shinobi_quick_start);

        // Only setup the chart the first time the activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();
            shinobiChart.setTitle("Trigonometric Functions");

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create the X axis
            NumberAxis xAxis = new NumberAxis();
            shinobiChart.setXAxis(xAxis);

            // Create the Y axis with a default range
            NumberAxis yAxis = new NumberAxis();
            shinobiChart.setYAxis(yAxis);
            yAxis.setDefaultRange(new NumberRange(-1.05, 1.05));

            // Populate the data adapters with data points of the sine and cosine waves
            SimpleDataAdapter<Double, Double> dataAdapter1 = new SimpleDataAdapter<Double, Double>();
            SimpleDataAdapter<Double, Double> dataAdapter2 = new SimpleDataAdapter<Double, Double>();
            for (int i = 0; i < 100; i++) {
                double radians = i * Math.PI / 25.0;
                dataAdapter1.add(new DataPoint<Double, Double>(radians, Math.sin(radians)));
                dataAdapter2.add(new DataPoint<Double, Double>(radians, Math.cos(radians)));
            }

            // Create a lines series and populate it with the sine data
            LineSeries series1 = new LineSeries();
            series1.setDataAdapter(dataAdapter1);
            shinobiChart.addSeries(series1);

            // Create a lines series and populate it with the cosine data
            LineSeries series2 = new LineSeries();
            series2.setDataAdapter(dataAdapter2);
            shinobiChart.addSeries(series2);

            // Here, we're setting colors using the Color class, supplying it
            // with values for alpha, red, green and blue. Alternatively, you
            // could take a more Android-like approach and define these in a
            // resources file. See
            // http://developer.android.com/guide/topics/resources/index.html
            // for more information.

            LineSeriesStyle style1 = series1.getStyle();
            style1.setFillStyle(FillStyle.GRADIENT);
            style1.setAreaColor(Color.argb(179, 94, 51, 95));
            style1.setAreaColorGradient(Color.argb(255, 94, 51, 95));

            LineSeriesStyle style2 = series2.getStyle();
            style2.setFillStyle(FillStyle.GRADIENT);
            style2.setAreaColor(Color.argb(179, 26, 96, 164));
            style2.setAreaColorGradient(Color.argb(255, 26, 96, 164));

            // Redraw the chart to apply the style changes
            shinobiChart.redrawChart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shinobi_quick_start, menu);
        return true;
    }
}
