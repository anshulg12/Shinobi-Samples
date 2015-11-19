
package com.example.candlestickchart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shinobicontrols.charts.CandlestickSeries;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.Data;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.MultiValueDataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;

public class CandlestickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only set the chart up the first time the Activity is created
        if (savedInstanceState == null) {

            // Get the a reference to the ShinobiChart from the ChartFragment
            ChartFragment chartFragment =
                    (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            ShinobiChart shinobiChart = chartFragment.getShinobiChart();

            // TODO: replace <license_key_here> with your trial license key
            shinobiChart
			.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            // Create the X axis and enable panning and zooming
            DateTimeAxis xAxis = new DateTimeAxis();
            xAxis.setTitle("Date");
            xAxis.enableGesturePanning(true);
            xAxis.enableGestureZooming(true);
            shinobiChart.addXAxis(xAxis);

            // Create the Y axis and enable panning and zooming
            NumberAxis yAxis = new NumberAxis();
            yAxis.setTitle("Price (USD)");
            yAxis.enableGesturePanning(true);
            yAxis.enableGestureZooming(true);
            shinobiChart.addYAxis(yAxis);

            // Create the candlestick series
            CandlestickSeries series = new CandlestickSeries();
            DataAdapter<Date, Double> dataAdapter = new SimpleDataAdapter<Date, Double>();

            // Load and add the stock price data
            dataAdapter.addAll(loadStockPriceData("AppleStockPrices.json"));
            series.setDataAdapter(dataAdapter);

            // Add the series to the chart
            shinobiChart.addSeries(series);

        }

    }

    private List<Data<Date, Double>> loadStockPriceData(String assetName)
    {
        List<Data<Date, Double>> dataPoints = new ArrayList<Data<Date, Double>>();
        AssetManager assetManager = this.getAssets();
        InputStream stream = null;

        // Formatter for converting the dates in the .json file to Date objects
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        try {
            // Open the .json file - it's held in the assets folder
            stream = assetManager.open(assetName);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            // Build the string from the reader
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            // Create an array of json object from the string
            JSONArray jsonArray = new JSONArray(responseStrBuilder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                // Loop through the json array and create a multi value data point for each object
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MultiValueDataPoint<Date, Double> dataPoint = new MultiValueDataPoint<Date, Double>(
                        formatter.parse(jsonObject.getString("date")),
                        jsonObject.getDouble("low"),
                        jsonObject.getDouble("high"),
                        jsonObject.getDouble("open"),
                        jsonObject.getDouble("close"));
                // Add the data point to the list of data points
                dataPoints.add(dataPoint);
            }
        } catch (IOException exc) {
            Log.e("CandlestickChart", "Unable to load asset");
        } catch (JSONException exc) {
            Log.e("CandlestickChart", "Unable to parse JSON data");
        } catch (ParseException exc) {
            Log.e("CandlestickChart", "Unable to parse JSON data");
        } finally {
            // Put our stream cleanup code in a finally block to ensure that it executes
            if (stream != null) {
                try {
                    // Close the stream in order to free up any system resources associated with it
                    stream.close();
                } catch (IOException exc) {
                    Log.e("CandlestickChart", "Unable to close input stream.");
                }
            }
        }

        // Return the stock price data
        return dataPoints;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
