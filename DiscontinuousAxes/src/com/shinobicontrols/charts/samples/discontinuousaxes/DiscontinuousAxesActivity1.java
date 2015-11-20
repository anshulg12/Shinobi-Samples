package com.shinobicontrols.charts.samples.discontinuousaxes;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.shinobicontrols.charts.Annotation;
import com.shinobicontrols.charts.AnnotationsManager;
import com.shinobicontrols.charts.Axis;
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

public class DiscontinuousAxesActivity1 extends Activity {

	private final GregorianCalendar calendar = new GregorianCalendar();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discontinuous_axes);

		// Only set the chart up the first time the Activity is created
		if (savedInstanceState == null) {

			// Get the a reference to the ShinobiChart from the ChartFragment
			ChartFragment chartFragment = (ChartFragment) getFragmentManager()
					.findFragmentById(R.id.chart);

			ShinobiChart shinobiChart = chartFragment.getShinobiChart();

			// TODO: replace <license_key_here> with your trial license key
			shinobiChart
					.setLicenseKey("DdAG4JqBNmMf/LUMjAxNTEyMTl0cmFpeWFuaTc1QGdtYWlsLmNvbQ==ctSk/vENy1KoCUsF8AGxXwKzMSXpmlJzB/+7m+EteT6h2BO8KYSPsmdgqGWDDY4avH3Ctz5pYn3LVXCb08GvCX4d3pD2uirxH5Nkxx+X47pUgl975yX6uwEICIjEAArfpCrYA4yZNe4adR1Fmx8/KIG07ngM=AXR/y+mxbZFM+Bz4HYAHkrZ/ekxdI/4Aa6DClSrE4o73czce7pcia/eHXffSfX9gssIRwBWEPX9e+kKts4mY6zZWsReM+aaVF0BL6G9Vj2249wYEThll6JQdqaKda41AwAbZXwcssavcgnaHc3rxWNBjJDOk6Cd78fr/LwdW8q7gmlj4risUXPJV0h7d21jO1gzaaFCPlp5G8l05UUe2qe7rKbarpjoddMoXrpErC9j8Lm5Oj7XKbmciqAKap+71+9DGNE2sBC+sY4V/arvEthfhk52vzLe3kmSOsvg5q+DQG/W9WbgZTmlMdWHY2B2nbgm3yZB7jFCiXH/KfzyE1A==PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

			shinobiChart.setTitle("");

			// Create the X-axis, showing ticks daily with a custom format and
			// clipping the tick at the far right
			NumberAxis xAxis = new NumberAxis();
			xAxis.setTitle("");
			xAxis.setMajorTickFrequency(6.0);
			xAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);

			// Create the Y-axis, clipping the tick at the top
			NumberAxis yAxis = new NumberAxis();
			yAxis.setTitle("");
			yAxis.getStyle().getTickStyle().setLabelsShown(false);

			// Create the series
			LineSeries series = createLineSeries();
			series.setBaseline(154);
			series.getStyle().setLineWidth(0.0f);
			series.getStyle().setFillStyle(FillStyle.FLAT);
			series.getStyle().setAreaColor(Color.rgb(54, 76, 106));
			series.getStyle().setAreaColorBelowBaseline(
					Color.rgb(197, 212, 232));
			series.getStyle().getPointStyle().setPointsShown(true);
			series.getStyle().getPointStyle().setRadius(3);
			series.getStyle().getPointStyle().setInnerRadius(2.0f);
			series.getStyle().getPointStyle()
					.setColor(getResources().getColor(android.R.color.white));
			series.getStyle()
					.getPointStyle()
					.setColorBelowBaseline(
							getResources().getColor(android.R.color.white));

			LineSeries series3 = createLineSeries();
			series3.setBaseline(96);
			series3.getStyle().setLineWidth(0.0f);
			series3.getStyle().setFillStyle(FillStyle.FLAT);
			series3.getStyle().setAreaColor(Color.rgb(184, 168, 143));
			series3.getStyle().setAreaColorBelowBaseline(
					Color.rgb(197, 212, 232));
			series3.getStyle().getPointStyle().setPointsShown(false);

			LineSeries series4 = createLineSeries();
			series4.setBaseline(0);
			series4.getStyle().setLineWidth(0.0f);
			series4.getStyle().setFillStyle(FillStyle.FLAT);
			series4.getStyle().setAreaColor(Color.rgb(244, 245, 245));
			series4.getStyle().getPointStyle().setPointsShown(false);

			// two vertical lines
			LineSeries series1 = createLineSeries1();
			series1.setTitle("HI 96");
			series1.getSelectedStyle().setLineWidth(6);
			series1.getSelectedStyle().setLineColor(Color.rgb(184, 168, 143));

			LineSeries series2 = createLineSeries2();
			series1.setTitle("LO 154");
			series2.getSelectedStyle().setLineWidth(6);
			series2.getSelectedStyle().setLineColor(Color.rgb(54, 76, 106));

			//

			shinobiChart.addSeries(series4, xAxis, yAxis);
			shinobiChart.addSeries(series3, xAxis, yAxis);
			shinobiChart.addSeries(series, xAxis, yAxis);
			shinobiChart.addSeries(series1, xAxis, yAxis);
			shinobiChart.addSeries(series2, xAxis, yAxis);

			addAnnotations(shinobiChart);

		}
	}

	private View createDottedLineView(int color) {
		View view = new View(this);

		// A bug in the Android framework prevents the dotted line being drawn
		// unless this is set!
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		view.setBackgroundColor(color);

		// MATCH_PARENT on the width so the annotation stretches across the plot
		// area
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 2));

		return view;
	}

	private void addAnnotations(ShinobiChart shinobiChart) {
		AnnotationsManager manager = shinobiChart.getAnnotationsManager();
		
		Axis<?, ?> xAxis = shinobiChart.getXAxis();
		Axis<?, ?> YAxis = shinobiChart.getYAxis();

		
		
		Annotation thousandLine = manager.addTextAnnotation("HI\n\n154", 22, 156, xAxis, YAxis);
		thousandLine.getStyle().setBackgroundColor(Color.rgb(197, 212, 232));
		thousandLine.getStyle().setTextColor(Color.rgb(54, 76, 106));
		
		
		Annotation thousandLine1 = manager.addTextAnnotation("LO\n\n96", 22, 97, xAxis, YAxis);
		thousandLine1.getStyle().setBackgroundColor(Color.rgb(197, 212, 232));
		thousandLine1.getStyle().setTextColor(Color.rgb(184, 168, 143));
		
	}

	private LineSeries createLineSeries() {

		LineSeries series = new LineSeries();
		series.getStyle().getPointStyle().setPointsShown(true);

		DataAdapter<Float, Integer> data = new SimpleDataAdapter<Float, Integer>();
		data.add(new DataPoint<Float, Integer>(0f, 0));
		data.add(new DataPoint<Float, Integer>(3f, 80));
		data.add(new DataPoint<Float, Integer>(6f, 170));
		data.add(new DataPoint<Float, Integer>(9f, 140));
		data.add(new DataPoint<Float, Integer>(12f, 105));
		data.add(new DataPoint<Float, Integer>(15f, 70));
		data.add(new DataPoint<Float, Integer>(18f, 90));
		data.add(new DataPoint<Float, Integer>(21f, 30));
		data.add(new DataPoint<Float, Integer>(23.59f, 0));

		series.setDataAdapter(data);

		return series;
	}

	private LineSeries createLineSeries2() {
		LineSeries series = new LineSeries();
		series.getStyle().getPointStyle().setPointsShown(false);

		DataAdapter<Integer, Integer> data = new SimpleDataAdapter<Integer, Integer>();

		data.add(new DataPoint<Integer, Integer>(0, 154));
		data.add(new DataPoint<Integer, Integer>(4, 154));
		data.add(new DataPoint<Integer, Integer>(6, 154));
		data.add(new DataPoint<Integer, Integer>(9, 154));
		data.add(new DataPoint<Integer, Integer>(12, 154));
		data.add(new DataPoint<Integer, Integer>(15, 154));
		data.add(new DataPoint<Integer, Integer>(18, 154));
		data.add(new DataPoint<Integer, Integer>(21, 154));
		data.add(new DataPoint<Integer, Integer>(24, 154));

		series.setDataAdapter(data);

		return series;
	}

	private LineSeries createLineSeries1() {

		LineSeries series = new LineSeries();
		series.getStyle().getPointStyle().setPointsShown(false);

		DataAdapter<Integer, Integer> data = new SimpleDataAdapter<Integer, Integer>();

		data.add(new DataPoint<Integer, Integer>(0, 96));
		data.add(new DataPoint<Integer, Integer>(4, 96));
		data.add(new DataPoint<Integer, Integer>(6, 96));
		data.add(new DataPoint<Integer, Integer>(9, 96));
		data.add(new DataPoint<Integer, Integer>(12, 96));
		data.add(new DataPoint<Integer, Integer>(15, 96));
		data.add(new DataPoint<Integer, Integer>(18, 96));
		data.add(new DataPoint<Integer, Integer>(21, 96));
		data.add(new DataPoint<Integer, Integer>(24, 96));

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
