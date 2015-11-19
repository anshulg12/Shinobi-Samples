
package com.example.customdataadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CustomDataAdapterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_data_adapter);

        // Set the UpdatingChartFragment running the first time the Activity is
        // created
        if (savedInstanceState == null) {

            UpdatingChartFragment chartFragment =
                    (UpdatingChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            chartFragment.run();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_data_adapter, menu);
        return true;
    }
}
