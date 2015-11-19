
package com.example.pieseries;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.PieSeries;
import com.shinobicontrols.charts.PieSeriesStyle;
import com.shinobicontrols.charts.PieDonutSeries.RadialEffect;
import com.shinobicontrols.charts.Series.SelectionMode;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

/**
 * In this sample, we first check for whether it is the first time the Activity
 * is being created as we only want to set it up the once. We then get a
 * reference to the ShinobiChart from the ChartFragment, which is inflated from
 * XML. This allows us to create a PieSeries with some simple data and add it to
 * the Chart. Following this, the series is styled to use green, yellow and pink
 * slices. We then finally go on to apply a style to selected slices.
 */
public class PieSeriesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_series);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            // Get the a reference to the ShinobiChart from the ChartFragment
            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create our DataAdapter and data
            DataAdapter<String, Double> dataAdapter = new SimpleDataAdapter<String, Double>();
            dataAdapter.add(new DataPoint<String, Double>("one", 1.0));
            dataAdapter.add(new DataPoint<String, Double>("two", 2.0));
            dataAdapter.add(new DataPoint<String, Double>("three", 3.0));

            // Create a PieSeries and give it the data adapter
            PieSeries series = new PieSeries();
            series.setDataAdapter(dataAdapter);

            // Set the selection mode to only select a single segment and to rotate to the top
            series.setSelectionMode(SelectionMode.POINT_SINGLE);
            series.setSelectedPosition(0.0f);

            // Add the series to the chart
            shinobiChart.addSeries(series);

            // Apply styling to the Pie Series
            PieSeriesStyle style = series.getStyle();
            style.setFlavorColors(new int[] {
                    Color.argb(255, 103, 169, 66), // green
                    Color.argb(255, 248, 184, 60), // yellow
                    Color.argb(255, 233, 74, 114)  // pink
            });
            style.setRadialEffect(RadialEffect.BEVELLED_LIGHT);
            style.setCrustShown(false);
            style.setLabelTextSize(16.0f);

            // apply style to selected slices
            PieSeriesStyle selectedSeriesStyle = series.getSelectedStyle();
            selectedSeriesStyle.setFlavorColors(new int[] {
                    Color.argb(255, 103, 169, 66), // green
                    Color.argb(255, 248, 184, 60), // yellow
                    Color.argb(255, 233, 74, 114)  // pink
            });
            selectedSeriesStyle.setCrustThickness(10f);
            selectedSeriesStyle.setCrustColors(new int[] {
                    Color.argb(255, 0, 0, 0), // black
            });
            series.setSelectedStyle(selectedSeriesStyle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_data_adapter, menu);
        return true;
    }

}
