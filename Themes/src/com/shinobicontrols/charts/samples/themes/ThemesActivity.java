
package com.shinobicontrols.charts.samples.themes;

import android.app.Activity;
import android.os.Bundle;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.PieSeries;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

public class ThemesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        if (savedInstanceState == null)
        {
            // Get a reference to the ChartFragment from the layout.
            ChartFragment chartFragment = (ChartFragment) getFragmentManager().findFragmentById(
                    R.id.chart);

            // Get a reference to the chart from the Fragment.
            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // Set the title of the chart
            shinobiChart.setTitle("My Styled Chart");

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create a simple set of data to display.
            SimpleDataAdapter<String, Double> dataAdapter = new SimpleDataAdapter<String, Double>();
            dataAdapter.add(new DataPoint<String, Double>("One", 1.0));
            dataAdapter.add(new DataPoint<String, Double>("Two", 2.0));
            dataAdapter.add(new DataPoint<String, Double>("Three", 3.0));
            dataAdapter.add(new DataPoint<String, Double>("Four", 4.0));
            dataAdapter.add(new DataPoint<String, Double>("Five", 5.0));
            dataAdapter.add(new DataPoint<String, Double>("Six", 6.0));

            // Add a series to display the data.
            PieSeries series = new PieSeries();
            series.setDataAdapter(dataAdapter);
            shinobiChart.addSeries(series);

            // Set the Theme of the chart to use our custom theme defined in
            // res/values/styles.xml
            shinobiChart.applyTheme(R.style.Theme_Default_Dark_MyCustomTheme, true);

            // Redraw the chart to ensure all the changes are applied.
            shinobiChart.redrawChart();
        }
    }
}
