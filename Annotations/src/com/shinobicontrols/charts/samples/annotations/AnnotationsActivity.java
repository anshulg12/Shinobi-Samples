package com.shinobicontrols.charts.samples.annotations;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.shinobicontrols.charts.Annotation;
import com.shinobicontrols.charts.AnnotationsManager;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.Data;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateRange;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class AnnotationsActivity extends ActionBarActivity {

	// The name of the tag used when logging errors
	private static final String TAG = "AnnotationsActivity";

	// A set of Dates relating to a specific Android version release
	private final Date KITKAT;
	private final Date JELLY_BEAN_18;
	private final Date JELLY_BEAN_17;
	private final Date JELLY_BEAN_16;
	private final Date ICE_CREAM_SANDWICH_15;
	private final Date ICE_CREAM_SANDWICH_14;
	private final Date GINGERBREAD_10;
	private final Date GINGERBREAD_9;

	// Used to create the Date objects
	private final GregorianCalendar calendar = new GregorianCalendar();

	// A map of Strings (the release name) to a data point for that release date
	private final Map<String, Data<Date, Double>> releaseInfo = new HashMap<String, Data<Date, Double>>();

	public AnnotationsActivity() {
		// Creates Dates for each release - missing out Honeycomb for brevity

		// Clear the time fields of the calendar - assume midnight on each date
		calendar.clear();

		calendar.set(2013, Calendar.OCTOBER, 31);
		KITKAT = calendar.getTime();

		calendar.set(2013, Calendar.JULY, 24);
		JELLY_BEAN_18 = calendar.getTime();

		calendar.set(2012, Calendar.NOVEMBER, 13);
		JELLY_BEAN_17 = calendar.getTime();

		calendar.set(2012, Calendar.JULY, 9);
		JELLY_BEAN_16 = calendar.getTime();

		calendar.set(2011, Calendar.DECEMBER, 16);
		ICE_CREAM_SANDWICH_15 = calendar.getTime();

		calendar.set(2011, Calendar.OCTOBER, 19);
		ICE_CREAM_SANDWICH_14 = calendar.getTime();

		calendar.set(2011, Calendar.FEBRUARY, 9);
		GINGERBREAD_10 = calendar.getTime();

		calendar.set(2010, Calendar.DECEMBER, 6);
		GINGERBREAD_9 = calendar.getTime();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annotations);

		// Only set the chart up the first time the Activity is created
		if (savedInstanceState == null) {

			// Get the a reference to the ShinobiChart from the ChartFragment
			ChartFragment chartFragment = (ChartFragment) getFragmentManager()
					.findFragmentById(R.id.chart);

			ShinobiChart shinobiChart = chartFragment.getShinobiChart();

			// TODO: replace <license_key_here> with your trial license key
			shinobiChart
					.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

			shinobiChart.setTitle("Closing Stock Price for Google Inc. (GOOG)");

			// Create a default DateRange
			calendar.set(2010, Calendar.JANUARY, 1);
			Date min = calendar.getTime();
			calendar.set(2014, Calendar.JANUARY, 1);
			Date max = calendar.getTime();
			DateRange xAxisDefaultRange = new DateRange(min, max);

			// Create the X axis with pre-defined default range and enable
			// panning and zooming
			DateTimeAxis xAxis = new DateTimeAxis(xAxisDefaultRange);
			xAxis.setTitle("Date");
			xAxis.enableGesturePanning(true);
			xAxis.enableGestureZooming(true);
			shinobiChart.setXAxis(xAxis);

			// Create the Y axis and enable panning and zooming
			NumberAxis yAxis = new NumberAxis(new NumberRange(0.0, 1200.0));
			yAxis.setTitle("Price (USD)");
			yAxis.enableGesturePanning(true);
			yAxis.enableGestureZooming(true);
			shinobiChart.setYAxis(yAxis);

			// Create the line series
			LineSeries series = new LineSeries();
			DataAdapter<Date, Double> dataAdapter = new SimpleDataAdapter<Date, Double>();

			// Load and add the stock price data
			dataAdapter.addAll(loadStockPriceData(R.raw.goog));
			series.setDataAdapter(dataAdapter);

			// Add the series to the chart
			shinobiChart.addSeries(series);

			// Add some annotations
			addReleaseTextAnnotations(shinobiChart);
			addThousandDollarLineAnnotation(shinobiChart);
			addAndroidLogoAnnotation(shinobiChart);
		}
	}

	// Add a text annotation for each Android release
	private void addReleaseTextAnnotations(ShinobiChart shinobiChart) {
		AnnotationsManager annotationsManager = shinobiChart
				.getAnnotationsManager();

		// Go through each entry in the map...
		for (Entry<String, Data<Date, Double>> entry : releaseInfo.entrySet()) {

			String releaseName = entry.getKey();
			Date releaseDate = entry.getValue().getX();
			Double closingPrice = entry.getValue().getY();

			// ... and add a text annotation
			annotationsManager.addTextAnnotation(releaseName, releaseDate,
					closingPrice, shinobiChart.getXAxis(),
					shinobiChart.getYAxis());
		}
	}

	private void addThousandDollarLineAnnotation(ShinobiChart shinobiChart) {
		AnnotationsManager annotationsManager = shinobiChart
				.getAnnotationsManager();

		// Add a thin, faint green line behind the data to highlight the $1000
		// mark
		Annotation thousandLine = annotationsManager
				.addHorizontalLineAnnotation(1000, 2.0f,
						shinobiChart.getXAxis(), shinobiChart.getYAxis());

		thousandLine.setPosition(Annotation.Position.BEHIND_DATA);

		thousandLine.getStyle().setBackgroundColor(Color.argb(20, 0, 255, 0));

		// Remember to redraw the chart to apply the style changes
		shinobiChart.redrawChart();
	}

	private void addAndroidLogoAnnotation(ShinobiChart shinobiChart) {
		AnnotationsManager annotationsManager = shinobiChart
				.getAnnotationsManager();

		// Create an image view to hold the robot image file
		ImageView androidLogo = new ImageView(this);
		androidLogo.setImageResource(R.drawable.android_robot);
		androidLogo.setScaleType(ImageView.ScaleType.FIT_XY);

		// Set the width and height of its layout params to 0 so the annotation
		// knows to use the ranges we're going to set
		androidLogo.setLayoutParams(new LayoutParams(0, 0));

		// Create an annotation that uses our ImageView - we're going to specify
		// ranges so X and Y values don't make sense (so set them to null)
		Annotation logoAnnotation = annotationsManager.addViewAnnotation(
				androidLogo, null, null, shinobiChart.getXAxis(),
				shinobiChart.getYAxis());
		logoAnnotation.setPosition(Annotation.Position.BEHIND_DATA);

		// Use the calendar to create min and max dates for our range - we
		// want the image to span 2011-07-01 to 2012-07-01 on the X axis
		calendar.set(2011, Calendar.JULY, 1);
		Date min = calendar.getTime();
		calendar.set(2012, Calendar.JULY, 1);
		Date max = calendar.getTime();

		// Set an X range on the annotation
		logoAnnotation.setXRange(new DateRange(min, max));

		// Set a Y range on the annotation - we want it to span 300 to 900
		logoAnnotation.setYRange(new NumberRange(300.0, 900.0));
	}

	private List<Data<Date, Double>> loadStockPriceData(
			int stockPricesResourceId) {

		List<Data<Date, Double>> dataPoints = new ArrayList<Data<Date, Double>>();

		// Formatter for converting the dates in the .csv file to Date objects
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.US);

		// Open the .csv file - it's held in the res/raw folder
		InputStream stream = getResources().openRawResource(
				stockPricesResourceId);

		try {
			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(stream, "UTF-8"));

			// Don't process the first line - these are the headers
			String line = streamReader.readLine();

			while ((line = streamReader.readLine()) != null) {
				String[] values = line.split(",");

				// We want the date and the closing price - 1st and 5th values
				Date date = formatter.parse(values[0]);
				double closePrice = Double.parseDouble(values[4]);

				// Create a data point
				Data<Date, Double> dataPoint = new DataPoint<Date, Double>(
						date, closePrice);
				dataPoints.add(dataPoint);

				// Check if the date of this point relates to a specific Android
				// release - if it does, save it for later
				saveAndroidReleaseInfo(dataPoint);
			}

		} catch (NotFoundException e) {
			Log.e(AnnotationsActivity.TAG, "Cannot find stock price data.");
		} catch (IOException e) {
			Log.e(AnnotationsActivity.TAG, "Unable to load stock price data.");
		} catch (ParseException e) {
			Log.e(AnnotationsActivity.TAG, "Unable to parse stock price data.");
		} finally {
			// Put our stream cleanup code in a finally block to ensure that it
			// executes
			if (stream != null) {
				try {
					// Close the stream in order to free up any system resources
					// associated with it
					stream.close();
				} catch (IOException e) {
					Log.e(AnnotationsActivity.TAG,
							"Unable to close input stream.");
				}
			}
		}

		// Return the stock price data
		return dataPoints;
	}

	private void saveAndroidReleaseInfo(Data<Date, Double> dataPoint) {
		if (dataPoint.getX().equals(KITKAT)) {
			releaseInfo.put("KitKat (19)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(JELLY_BEAN_18)) {
			releaseInfo.put("Jelly Bean (18)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(JELLY_BEAN_17)) {
			releaseInfo.put("Jelly Bean (17)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(JELLY_BEAN_16)) {
			releaseInfo.put("Jelly Bean (16)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(ICE_CREAM_SANDWICH_15)) {
			releaseInfo.put("Ice Cream Sandwich (15)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(ICE_CREAM_SANDWICH_14)) {
			releaseInfo.put("Ice Cream Sandwich (14)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(GINGERBREAD_10)) {
			releaseInfo.put("Gingerbread (10)", dataPoint);
			return;
		}
		if (dataPoint.getX().equals(GINGERBREAD_9)) {
			releaseInfo.put("Gingerbread (9)", dataPoint);
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.annotations, menu);
		return true;
	}
}
