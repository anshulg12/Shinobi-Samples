
package com.example.legend;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.shinobicontrols.charts.BarSeries;
import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.Legend;
import com.shinobicontrols.charts.LegendStyle;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

public class LegendActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            // and set it up with a title, background color, axes and a
            // set of BarSeries
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            shinobiChart.setTitle("Units sold in 2012");
            int backgroundColor = Color.argb(255, 219, 211, 229);
            shinobiChart.getStyle().setBackgroundColor(backgroundColor);

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            NumberAxis xAxis = new NumberAxis();
            shinobiChart.addXAxis(xAxis);

            CategoryAxis yAxis = new CategoryAxis();
            shinobiChart.addYAxis(yAxis);

            BarSeries smartphoneSales = createSmartphoneSalesSeries();
            shinobiChart.addSeries(smartphoneSales);

            BarSeries tabletSales = createTabletSalesSeries();
            shinobiChart.addSeries(tabletSales);

            BarSeries laptopSales = createLaptopSalesSeries();
            shinobiChart.addSeries(laptopSales);

            BarSeries desktopSales = createDesktopSalesSeries();
            shinobiChart.addSeries(desktopSales);

            // Get the legend from the chart
            Legend legend = shinobiChart.getLegend();

            // Make the legend visible
            legend.setVisibility(View.VISIBLE);

            // Give it a title
            legend.setTitle("Device Type");

            // Set the placement and position of the legend
            legend.setPlacement(Legend.Placement.INSIDE_PLOT_AREA);

            // Set the maximum number of series to show in each row
            legend.setMaxSeriesPerRow(2);

            // Change the legend's style
            LegendStyle legendStyle = legend.getStyle();
            legendStyle.setBackgroundColor(Color.argb(150, 255, 255, 255));
            legendStyle.setBorderWidth(6.0f);
            legendStyle.setBorderColor(Color.argb(255, 153, 153, 153));
            legendStyle.setCornerRadius(10.0f);
            legendStyle.setTextSize(12.0f);
            legendStyle.setSymbolAlignment(Legend.SymbolAlignment.RIGHT);
            legendStyle.setPadding(14.0f);

            // Don't forget to redraw the chart in order to show these changes
            shinobiChart.redrawChart();
        }
    }

    private BarSeries createSmartphoneSalesSeries() {
        BarSeries smartphoneSales = new BarSeries();

        // Give the series a title to be displayed in the legend
        smartphoneSales.setTitle("Smartphones");

        // Create some data...
        DataAdapter<Integer, String> mobileSalesData = new SimpleDataAdapter<Integer, String>();

        mobileSalesData.add(new DataPoint<Integer, String>(151, "Q4"));
        mobileSalesData.add(new DataPoint<Integer, String>(140, "Q3"));
        mobileSalesData.add(new DataPoint<Integer, String>(122, "Q2"));
        mobileSalesData.add(new DataPoint<Integer, String>(93, "Q1"));

        // ... and give it to the bar series
        smartphoneSales.setDataAdapter(mobileSalesData);

        return smartphoneSales;
    }

    private BarSeries createTabletSalesSeries() {
        BarSeries tabletSales = new BarSeries();

        // Give the series a title to be displayed in the legend
        tabletSales.setTitle("Tablets");

        // Create some data...
        DataAdapter<Integer, String> tabletSalesData = new SimpleDataAdapter<Integer, String>();

        tabletSalesData.add(new DataPoint<Integer, String>(135, "Q4"));
        tabletSalesData.add(new DataPoint<Integer, String>(91, "Q3"));
        tabletSalesData.add(new DataPoint<Integer, String>(68, "Q2"));
        tabletSalesData.add(new DataPoint<Integer, String>(35, "Q1"));

        // ... and give it to the bar series
        tabletSales.setDataAdapter(tabletSalesData);

        return tabletSales;
    }

    private BarSeries createLaptopSalesSeries() {
        BarSeries laptopSales = new BarSeries();

        // Give the series a title to be displayed in the legend
        laptopSales.setTitle("Laptops");

        // Create some data...
        DataAdapter<Integer, String> laptopSalesData = new SimpleDataAdapter<Integer, String>();

        laptopSalesData.add(new DataPoint<Integer, String>(69, "Q4"));
        laptopSalesData.add(new DataPoint<Integer, String>(74, "Q3"));
        laptopSalesData.add(new DataPoint<Integer, String>(72, "Q2"));
        laptopSalesData.add(new DataPoint<Integer, String>(63, "Q1"));

        // ... and give it to the bar series
        laptopSales.setDataAdapter(laptopSalesData);

        return laptopSales;
    }

    private BarSeries createDesktopSalesSeries() {
        BarSeries desktopSales = new BarSeries();

        // Give the series a title to be displayed in the legend
        desktopSales.setTitle("Desktops");

        // Create some data...
        DataAdapter<Integer, String> desktopSalesData = new SimpleDataAdapter<Integer, String>();

        desktopSalesData.add(new DataPoint<Integer, String>(70, "Q4"));
        desktopSalesData.add(new DataPoint<Integer, String>(72, "Q3"));
        desktopSalesData.add(new DataPoint<Integer, String>(83, "Q2"));
        desktopSalesData.add(new DataPoint<Integer, String>(102, "Q1"));

        // ... and give it to the bar series
        desktopSales.setDataAdapter(desktopSalesData);

        return desktopSales;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.legend, menu);
        return true;
    }

}
