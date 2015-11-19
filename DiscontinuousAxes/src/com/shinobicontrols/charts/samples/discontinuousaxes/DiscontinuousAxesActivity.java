
package com.shinobicontrols.charts.samples.discontinuousaxes;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateFrequency;
import com.shinobicontrols.charts.DateFrequency.Denomination;
import com.shinobicontrols.charts.DateRange;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.RepeatedTimePeriod;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.TickMark.ClippingMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DiscontinuousAxesActivity extends Activity {

    private final GregorianCalendar calendar = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discontinuous_axes);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();
            
            // TODO: replace <license_key_here> with your trial license key
            shinobiChart
			.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            shinobiChart.setTitle(getResources().getString(R.string.chart_title));

            // Create the X-axis, showing ticks daily with a custom format and
            // clipping the tick at the far right
            DateTimeAxis xAxis = new DateTimeAxis();
            xAxis.setTitle(getResources().getString(R.string.x_axis_title));
            xAxis.setMajorTickFrequency(new DateFrequency(1, Denomination.DAYS));
            xAxis.setLabelFormat(new SimpleDateFormat("E\ndd", Locale.US));
            xAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);

            // Create the Y-axis, clipping the tick at the top
            NumberAxis yAxis = new NumberAxis();
            yAxis.setTitle(getResources().getString(R.string.y_axis_title));
            yAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);

            // Create the series
            LineSeries series = createLineSeries();
            series.getStyle().setAreaColor(Color.GREEN);
            series.getStyle().setFillStyle(FillStyle.FLAT);
            
            
            LineSeries series3 = createLineSeries3();
            series3.getStyle().setAreaColor(Color.WHITE);
            series3.getStyle().setFillStyle(FillStyle.FLAT);
            
            LineSeries series4 = createLineSeries4();
            series4.getStyle().setAreaColor(Color.MAGENTA);
            series4.getStyle().setFillStyle(FillStyle.FLAT);
            
            LineSeries series1 = createLineSeries1();
            LineSeries series2 = createLineSeries2();
            
            
            DateTimeAxis xAxis1 = new DateTimeAxis();
            xAxis1.setTitle(getResources().getString(R.string.x_axis_title));
            xAxis1.setMajorTickFrequency(new DateFrequency(10, Denomination.DAYS));
            
            
            shinobiChart.addSeries(series, xAxis, yAxis);
            shinobiChart.addSeries(series1, xAxis, yAxis);
            shinobiChart.addSeries(series2, xAxis, yAxis);
            shinobiChart.addSeries(series4, xAxis, yAxis);
            shinobiChart.addSeries(series3, xAxis, yAxis);

            // Skip Good Friday and Easter Monday public holidays
            calendar.clear();
            calendar.set(2015, Calendar.APRIL, 3, 0, 0, 0);
            Date min = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date max = calendar.getTime();
            DateRange goodFriday = new DateRange(min, max);

            calendar.clear();
            calendar.set(2015, Calendar.APRIL, 6, 0, 0, 0);
            min = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            max = calendar.getTime();
            DateRange easterMonday = new DateRange(min, max);

            xAxis.addSkipRange(goodFriday);
            xAxis.addSkipRange(easterMonday);

            // Skip all weekends
            calendar.clear();
            calendar.set(2015, Calendar.MARCH, 28, 0, 0, 0);
            Date start = calendar.getTime();
            DateFrequency length = new DateFrequency(2, Denomination.DAYS);
            DateFrequency frequency = new DateFrequency(1, Denomination.WEEKS);
            RepeatedTimePeriod weekends = new RepeatedTimePeriod(start, length, frequency);

            xAxis.addRepeatedSkipRange(weekends);
            
        }
    }

    private LineSeries createLineSeries() {
        calendar.clear();
        calendar.set(2015, Calendar.APRIL, 1, 0, 0, 0);

        LineSeries series = new LineSeries();
        series.getStyle().getPointStyle().setPointsShown(true);

        DataAdapter<Date, Integer> data = new SimpleDataAdapter<Date, Integer>();

        for (int i = 1; i <= 30; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), i));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        series.setDataAdapter(data);

        return series;
    }
    
        
    private LineSeries createLineSeries3() {
        calendar.clear();
        calendar.set(2015, Calendar.APRIL, 1, 0, 0, 0);

        LineSeries series = new LineSeries();
        series.getStyle().getPointStyle().setPointsShown(false);

        DataAdapter<Date, Integer> data = new SimpleDataAdapter<Date, Integer>();
        
        for (int i = 0; i <= 10; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), i));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        
        for (int i = 11; i <= 30; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), 11));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        series.setDataAdapter(data);

        return series;
    }
    
    private LineSeries createLineSeries4() {
        calendar.clear();
        calendar.set(2015, Calendar.APRIL, 1, 0, 0, 0);

        LineSeries series = new LineSeries();
        series.getStyle().getPointStyle().setPointsShown(false);
        DataAdapter<Date, Integer> data = new SimpleDataAdapter<Date, Integer>();

        for (int i = 0; i <= 20; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), i));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        
        for (int i = 21; i <= 30; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), 21));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        series.setDataAdapter(data);

        return series;
    }

    private LineSeries createLineSeries1() {
        calendar.clear();
        calendar.set(2015, Calendar.APRIL, 1, 0, 0, 0);

        LineSeries series = new LineSeries();
        series.getStyle().getPointStyle().setPointsShown(false);

        DataAdapter<Date, Integer> data = new SimpleDataAdapter<Date, Integer>();

        for (int i = 1; i <= 30; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), 11));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        series.setDataAdapter(data);

        return series;
    }


    private LineSeries createLineSeries2() {
        calendar.clear();
        calendar.set(2015, Calendar.APRIL, 1, 0, 0, 0);

        LineSeries series = new LineSeries();
        series.getStyle().getPointStyle().setPointsShown(false);

        DataAdapter<Date, Integer> data = new SimpleDataAdapter<Date, Integer>();

        for (int i = 1; i <= 30; i++) {
            data.add(new DataPoint<Date, Integer>(calendar.getTime(), 21));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        series.setDataAdapter(data);

        return series;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.discontinuous_axes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
