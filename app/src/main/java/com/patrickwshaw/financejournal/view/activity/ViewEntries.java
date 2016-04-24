package com.patrickwshaw.financejournal.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.patrickwshaw.financejournal.R;
import com.patrickwshaw.financejournal.util.LoggingUtil;
import com.patrickwshaw.financejournal.view.fragment.ViewEntriesFragment;

public class ViewEntries extends AppCompatActivity {

    private static final LoggingUtil logger = new LoggingUtil(ViewEntries.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        setContentView(R.layout.activity_view_entries);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.viewEntriesFragmentContainer);

        if (fragment == null) {
            logger.d("Fragment was null!  Creating it");
            fragment = new ViewEntriesFragment();
            fm.beginTransaction()
                    .add(R.id.viewEntriesFragmentContainer, fragment)
                    .commit();
        }

        logger.logExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logger.logEnter("onCreateOptionsMenu");

        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.view_entries_menu, menu);

        logger.logExit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedItem) {
        //Handle action bar clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml

        logger.logEnter("onOptionsItemSelected");

        boolean matchFound = false;
        if (selectedItem.getItemId() == R.id.viewEntriesMenuSettings) {
            //TODO: Need to implement a settings menu
            matchFound = true;
        }

        logger.logExit();
        if (!matchFound) {
            return super.onOptionsItemSelected(selectedItem);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        logger.logEnter("onBackPressed");

        logger.d("there are: " + getFragmentManager().getBackStackEntryCount() + " entries in the back stack");
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            logger.d("Popping the back stack...");
            getFragmentManager().popBackStack();
        }

        logger.logExit();
    }
}
