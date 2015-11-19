
package com.shinobicontrols.charts.samples.crosshair;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import com.shinobicontrols.charts.Axis.Position;
import com.shinobicontrols.charts.CartesianSeries;
import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DefaultTooltipView;
import com.shinobicontrols.charts.Legend;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.TickMark.ClippingMode;
import com.shinobicontrols.charts.Tooltip;

public class CrosshairActivity extends Activity implements
        ShinobiChart.OnCrosshairActivationStateChangedListener,
        ShinobiChart.OnTrackingInfoChangedForTooltipListener {

    private static final int CROSSHAIR_INACTIVE_COLOR = Color.argb(255, 240, 240, 240);
    private static final int CROSSHAIR_ACTIVE_COLOR = Color.argb(150, 0, 0, 0);
    private static final int TRENDLINE_COLOR = Color.argb(255, 59, 172, 200);
    private static final String DOWNLOADS = "\nDownloads:\n";
    private static final String COMPANY_REVENUE = "\nRevenue ($M):\n";
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crosshair);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart
			.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Give the chart a title and a legend
            shinobiChart.setTitle("MySpiffyApp - 2013 Figures");
            shinobiChart.getLegend().setPosition(Legend.Position.TOP_LEFT);
            shinobiChart.getLegend().setPlacement(Legend.Placement.INSIDE_PLOT_AREA);
            shinobiChart.getLegend().setVisibility(View.VISIBLE);
            shinobiChart.getLegend().getStyle().setBackgroundColor(Color.TRANSPARENT);
            shinobiChart.getLegend().getStyle().setBorderColor(Color.TRANSPARENT);
            shinobiChart.getLegend().getStyle().setTextAlignment(Gravity.LEFT);
            shinobiChart.getLegend().getStyle().setTextColor(Color.BLACK);

            // Create the axes and series
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = createYAxis();
            NumberAxis reverseYAxis = createReverseYAxis();
            ColumnSeries downloadsUS = createDownloadsSeriesForUS();
            ColumnSeries downloadsRestOfWorld = createDownloadsSeriesForRestOfWorld();
            LineSeries companyRevenue = createCompanyRevenueSeries();

            // Add them to the chart - for the line series we specify the axes because
            // we don't want it to use the default Y axis
            shinobiChart.addXAxis(xAxis);
            shinobiChart.addYAxis(yAxis);
            shinobiChart.addYAxis(reverseYAxis);
            shinobiChart.addSeries(downloadsUS);
            shinobiChart.addSeries(downloadsRestOfWorld);
            shinobiChart.addSeries(companyRevenue, xAxis, reverseYAxis);

            // Style the chart and the crosshair
            shinobiChart.getStyle().setPlotAreaBackgroundColor(
                    CrosshairActivity.CROSSHAIR_INACTIVE_COLOR);
            shinobiChart.getCrosshair().getStyle().setLineColor(Color.WHITE);

            // Remember to redraw the chart to make the changes visible
            shinobiChart.redrawChart();

            // Add this Activity as a listener for any crosshair changes
            shinobiChart.setOnCrosshairActivationStateChangedListener(this);
            shinobiChart.setOnTrackingInfoChangedForTooltipListener(this);
        }
    }

    @Override
    public void onCrosshairActivationStateChanged(ShinobiChart chart) {
        // Set the plot area background color depending on the crosshair's
        // activation state
        if (chart.getCrosshair().isActive()) {
            chart.getStyle().setPlotAreaBackgroundColor(CrosshairActivity.CROSSHAIR_ACTIVE_COLOR);
            chart.getLegend().getStyle().setTextColor(Color.WHITE);
        }
        else {
            chart.getStyle().setPlotAreaBackgroundColor(CrosshairActivity.CROSSHAIR_INACTIVE_COLOR);
            chart.getLegend().getStyle().setTextColor(Color.BLACK);
        }

        // Remember to redraw the chart to make the color change visible
        chart.redrawChart();
    }

    @Override
    public void onTrackingInfoChanged(Tooltip tooltip,
            DataPoint<?, ?> dataPoint,
            DataPoint<?, ?> dataPointPosition,
            DataPoint<?, ?> interpolatedDataPointPosition) {

        if (interpolatedDataPointPosition != null) {
            // Update tooltip position and text for LineSeries (Company Revenue)
            tooltip.setCenter(interpolatedDataPointPosition);
            populateTooltipText(tooltip, interpolatedDataPointPosition, COMPANY_REVENUE);

        } else {
            // Update tooltip position and text for ColumnSeries (downloads)
            tooltip.setCenter(dataPointPosition);
            populateTooltipText(tooltip, dataPoint, DOWNLOADS);
        }
    }

    private void populateTooltipText(Tooltip tooltip, DataPoint<?, ?> dataPoint,
            String descriptiveText) {
        // Set the text to be the data or interpolated values depending upon the
        // series type - we know our axes types so we can safely cast

        // Start by getting hold of the tool tip view which we know is of type
        // DefaultTooltipView
        DefaultTooltipView tooltipView = (DefaultTooltipView) tooltip.getView();
        // Next get the tracked series
        CartesianSeries<?> trackedSeries = tooltip.getTrackedSeries();
        // Now get the x and y axes and cast to the correct type, which we know
        CategoryAxis xAxis = (CategoryAxis) trackedSeries.getXAxis();
        NumberAxis yAxis = (NumberAxis) trackedSeries.getYAxis();
        // Let's build a string for our tooltip text - clear down the builder
        // first
        stringBuilder.setLength(0);
        // x value
        stringBuilder.append(xAxis.getFormattedString((Double) dataPoint.getX()));
        // now the text in the middle of the x and y values
        stringBuilder.append(descriptiveText);
        // now the y value
        stringBuilder.append(yAxis.getFormattedString((Double) dataPoint.getY()));
        // Now we can populate the TextView within the tooltip
        tooltipView.setText(stringBuilder.toString());
    }

    private NumberAxis createYAxis() {
        // Create the Y Axis, clip the top tick and label and give it a title
        NumberAxis yAxis = new NumberAxis(new NumberRange(0.0, 60.0));
        yAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);
        yAxis.setTitle("Downloads (1000s)");
        return yAxis;
    }

    private NumberAxis createReverseYAxis() {
        // Create the reverse Y Axis, clip the top tick and label and give it a title
        NumberAxis yAxis = new NumberAxis(new NumberRange(0.0, 40.0));
        yAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);
        yAxis.setTitle("Company Revenue ($M)");

        // Position this Y axis on the right hand side of the chart
        yAxis.setPosition(Position.REVERSE);
        return yAxis;
    }

    private ColumnSeries createDownloadsSeriesForRestOfWorld() {
        ColumnSeries downloadsForRestOfWorld = new ColumnSeries();

        // Enable the crosshair for this series
        downloadsForRestOfWorld.setCrosshairEnabled(true);

        // Give the series some data and set the stackId to 0 -
        // the stack ids must be identical for the columns to stack
        downloadsForRestOfWorld.setDataAdapter(createDownloadsDataForRestOfWorld());
        downloadsForRestOfWorld.setStackId(0);
        downloadsForRestOfWorld.setTitle("Downloads-Rest of World");
        return downloadsForRestOfWorld;
    }

    private ColumnSeries createDownloadsSeriesForUS() {
        ColumnSeries downloadsForUS = new ColumnSeries();

        // Enable the crosshair for this series
        downloadsForUS.setCrosshairEnabled(true);

        // Give the series some data and set the stackId to 0 -
        // the stack ids must be identical for the columns to stack
        downloadsForUS.setDataAdapter(createDownloadsDataForUS());
        downloadsForUS.setStackId(0);
        downloadsForUS.setTitle("Downloads-US");
        return downloadsForUS;
    }

    private DataAdapter<?, ?> createDownloadsDataForUS() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 4.6));
        data.add(new DataPoint<String, Double>("Feb", 5.1));
        data.add(new DataPoint<String, Double>("Mar", 6.4));
        data.add(new DataPoint<String, Double>("Apr", 12.3));
        data.add(new DataPoint<String, Double>("May", 11.3));
        data.add(new DataPoint<String, Double>("Jun", 8.6));
        data.add(new DataPoint<String, Double>("Jul", 15.7));
        data.add(new DataPoint<String, Double>("Aug", 21.4));
        data.add(new DataPoint<String, Double>("Sep", 19.0));
        data.add(new DataPoint<String, Double>("Oct", 21.7));
        data.add(new DataPoint<String, Double>("Nov", 23.0));
        data.add(new DataPoint<String, Double>("Dec", 23.9));

        return data;
    }

    private DataAdapter<?, ?> createDownloadsDataForRestOfWorld() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 5.9));
        data.add(new DataPoint<String, Double>("Feb", 7.2));
        data.add(new DataPoint<String, Double>("Mar", 8.3));
        data.add(new DataPoint<String, Double>("Apr", 13.3));
        data.add(new DataPoint<String, Double>("May", 12.8));
        data.add(new DataPoint<String, Double>("Jun", 10.6));
        data.add(new DataPoint<String, Double>("Jul", 13.7));
        data.add(new DataPoint<String, Double>("Aug", 20.4));
        data.add(new DataPoint<String, Double>("Sep", 21.3));
        data.add(new DataPoint<String, Double>("Oct", 23.2));
        data.add(new DataPoint<String, Double>("Nov", 25.0));
        data.add(new DataPoint<String, Double>("Dec", 23.9));

        return data;
    }

    private LineSeries createCompanyRevenueSeries() {
        LineSeries companyRevenue = new LineSeries();

        // Enable the crosshair for this series
        companyRevenue.setCrosshairEnabled(true);

        // Give the series some data
        companyRevenue.setDataAdapter(createCompanyRevenueData());

        // Style the line to make it stand out
        companyRevenue.getStyle().setLineColor(CrosshairActivity.TRENDLINE_COLOR);
        companyRevenue.getStyle().setLineWidth(2.0f);
        companyRevenue.setTitle("Company Revenue");

        return companyRevenue;
    }

    private DataAdapter<?, ?> createCompanyRevenueData() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 15.6));
        data.add(new DataPoint<String, Double>("Feb", 16.2));
        data.add(new DataPoint<String, Double>("Mar", 14.8));
        data.add(new DataPoint<String, Double>("Apr", 25.5));
        data.add(new DataPoint<String, Double>("May", 20.1));
        data.add(new DataPoint<String, Double>("Jun", 22.8));
        data.add(new DataPoint<String, Double>("Jul", 20.4));
        data.add(new DataPoint<String, Double>("Aug", 35.0));
        data.add(new DataPoint<String, Double>("Sep", 32.7));
        data.add(new DataPoint<String, Double>("Oct", 33.3));
        data.add(new DataPoint<String, Double>("Nov", 34.9));
        data.add(new DataPoint<String, Double>("Dec", 33.6));

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crosshair, menu);
        return true;
    }

}
