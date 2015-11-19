
package com.example.panandzoom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.example.panandzoom.model.PanAndZoomApplication;
import com.example.panandzoom.model.WeatherReport;
import com.shinobicontrols.charts.Axis;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateRange;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanAndZoomActivity extends Activity implements
        ShinobiChart.OnAxisMotionStateChangeListener {

    private final static String title = "Max and Min Temperature (Monthly Mean), Durham Weather Station    ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_and_zoom);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Set up and add the X axis
            DateTimeAxis xAxis = new DateTimeAxis();
            setupXAxis(xAxis);

            // Create and set the default range to be displayed for this axis
            DateRange xDefaultRange = new DateRange(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime(),
                    new GregorianCalendar(2009, Calendar.DECEMBER, 31).getTime());
            xAxis.setDefaultRange(xDefaultRange);

            shinobiChart.addXAxis(xAxis);

            // Add a Y Axis - no need to set it up as we want this to stay fixed
            // and by default gestures are disabled
            NumberAxis yAxis = new NumberAxis();
            shinobiChart.addYAxis(yAxis);

            // Give the chart a title
            setTitle(shinobiChart, xDefaultRange);

            // Put the model data into data adapters for each series
            DataAdapter<Date, Double> minTemperatureDataAdapter = new SimpleDataAdapter<Date, Double>();
            DataAdapter<Date, Double> maxTemperatureDataAdapter = new SimpleDataAdapter<Date, Double>();
            populateDataAdapters(minTemperatureDataAdapter, maxTemperatureDataAdapter);

            // Max Temperature line - give it data and color it red
            LineSeries maxTemperatureLine = new LineSeries();
            maxTemperatureLine.setDataAdapter(maxTemperatureDataAdapter);
            maxTemperatureLine.getStyle().setLineColor(Color.RED);
            shinobiChart.addSeries(maxTemperatureLine);

            // Min Temperature line - give it data and color it blue
            LineSeries minTemperatureLine = new LineSeries();
            minTemperatureLine.setDataAdapter(minTemperatureDataAdapter);
            minTemperatureLine.getStyle().setLineColor(Color.BLUE);
            shinobiChart.addSeries(minTemperatureLine);

            // Make this activity the listener for axis events
            shinobiChart.setOnAxisMotionStateChangeListener(this);

        }
    }

    // Enable pan and zoom
    private void setupXAxis(DateTimeAxis xAxis) {

        xAxis.enableGesturePanning(true);
        xAxis.enableGestureZooming(true);
        xAxis.enableMomentumPanning(true);
        xAxis.enableMomentumZooming(true);

    }

    private void populateDataAdapters(
            DataAdapter<Date, Double> minTemperatureDataAdapter,
            DataAdapter<Date, Double> maxTemperatureDataAdapter) {

        // For convenience our 'model' is stored in our Application object
        PanAndZoomApplication application = (PanAndZoomApplication) getApplication();

        // Loop through the 'model' adding the data
        for (WeatherReport report : application.getWeatherModel()) {

            minTemperatureDataAdapter.add(new DataPoint<Date, Double>(
                    report.getDate(), report.getMinTemperature()));

            maxTemperatureDataAdapter.add(new DataPoint<Date, Double>(
                    report.getDate(), report.getMaxTemperature()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pan_and_zoom, menu);
        return true;
    }

    @Override
    public void onAxisMotionStateChange(Axis<?, ?> axis) {
        // We're only interested in the X axis here
        if (axis == axis.getChart().getXAxis()) {
            // If the axis has stopped moving set the title to include the displayed data range
            // else set the default title
            if (axis.getMotionState() == Axis.MotionState.STOPPED) {
                setTitle(axis.getChart(), (DateRange) axis.getCurrentDisplayedRange());
            } else {
                axis.getChart().setTitle(title);
            }
        }
    }

    // Format the title to include the current displayed date range
    private void setTitle(ShinobiChart chart, DateRange range) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String titleString = String.format("%s    %s to %s",
                title,
                dateFormat.format(range.getMinimum()),
                dateFormat.format(range.getMaximum()));
        chart.setTitle(titleString);
    }

}
