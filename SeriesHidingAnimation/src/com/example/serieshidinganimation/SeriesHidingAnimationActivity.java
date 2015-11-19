
package com.example.serieshidinganimation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.LineSeriesStyle;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.Series;
import com.shinobicontrols.charts.SeriesAnimation;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

public class SeriesHidingAnimationActivity extends Activity implements
        ShinobiChart.OnGestureListener, ShinobiChart.OnSeriesAnimationListener {

    LineSeries series1, series2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            series1 = new LineSeries();
            series1.setDataAdapter(dataAdapter1);

            // Enable animations on this series and add it to the chart
            series1.enableAnimation(true);
            shinobiChart.addSeries(series1);

            // Set the entry and exit animations to fade in and out
            series1.setEntryAnimation(SeriesAnimation.createFadeAnimation());
            series1.setExitAnimation(SeriesAnimation.createFadeAnimation());

            // Create a lines series and populate it with the cosine data
            series2 = new LineSeries();
            series2.setDataAdapter(dataAdapter2);

            // Set the initial state to hidden, enable animations on this series and add it to the chart
            series2.setHidden(true);
            series2.enableAnimation(true);
            shinobiChart.addSeries(series2);

            // Set the entry and exit animations to fade in and out
            series2.setEntryAnimation(SeriesAnimation.createFadeAnimation());
            series2.setExitAnimation(SeriesAnimation.createFadeAnimation());

            // Set the listeners to this activity which implements their methods further down
            shinobiChart.setOnGestureListener(this);
            shinobiChart.setOnSeriesAnimationListener(this);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onDoubleTapDown(ShinobiChart arg0, PointF arg1) {
    }

    @Override
    public void onDoubleTapUp(ShinobiChart arg0, PointF arg1) {
    }

    @Override
    public void onLongTouchDown(ShinobiChart arg0, PointF arg1) {
    }

    @Override
    public void onLongTouchUp(ShinobiChart arg0, PointF arg1) {
    }

    @Override
    public void onPinch(ShinobiChart arg0, PointF arg1, PointF arg2, PointF arg3) {
    }

    @Override
    public void onPinchEnd(ShinobiChart arg0, PointF arg1, boolean arg2, PointF arg3) {
    }

    @Override
    public void onSecondTouchDown(ShinobiChart arg0, PointF arg1, PointF arg2) {
    }

    @Override
    public void onSecondTouchUp(ShinobiChart arg0, PointF arg1, PointF arg2) {
    }

    @Override
    public void onSingleTouchDown(ShinobiChart arg0, PointF arg1) {
        if ((series1.isAnimating() == false) && (series2.isAnimating() == false)) {
            // If the series aren't animating then make the hidden series visible and vice versa
            if (series1.isHidden()) {
                series1.setHidden(false);
                series2.setHidden(true);
            }
            else {
                series1.setHidden(true);
                series2.setHidden(false);
            }
        }
    }

    @Override
    public void onSingleTouchUp(ShinobiChart arg0, PointF arg1) {
    }

    @Override
    public void onSwipe(ShinobiChart arg0, PointF arg1, PointF arg2) {
    }

    @Override
    public void onSwipeEnd(ShinobiChart arg0, PointF arg1, boolean arg2, PointF arg3) {
    }

    @Override
    public void onSeriesAnimationFinished(Series<?> series) {
        // When the series animation is finished show a Toast message saying which series is hidden
        if (series == series1 && series.isHidden()) {
            Toast.makeText(this, "Series 1 is now hidden", Toast.LENGTH_SHORT).show();
        }
        else if (series == series2 && series.isHidden()) {
            Toast.makeText(this, "Series 2 is now hidden", Toast.LENGTH_SHORT).show();
        }
    }

}
