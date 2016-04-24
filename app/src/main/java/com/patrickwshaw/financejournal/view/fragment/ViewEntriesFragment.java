package com.patrickwshaw.financejournal.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwshaw.financejournal.R;
import com.patrickwshaw.financejournal.model.Entry;
import com.patrickwshaw.financejournal.model.dao.DaoFactory;
import com.patrickwshaw.financejournal.util.LoggingUtil;

import java.util.Calendar;

public class ViewEntriesFragment extends Fragment {
    private static final LoggingUtil logger = new LoggingUtil(ViewEntriesFragment.class);

    private static RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logger.logEnter("onCreate");

        getActivity().setTitle(R.string.view_entries_fragment_title);

        //turn on the options menu
        setHasOptionsMenu(true);

        //TODO: remove this test code
        if (DaoFactory.getInstance(getActivity()).getEntryDAO().getAll().size() == 0) {
            logger.warn("There are no items in the database, adding some to test with");
            for (int x = 0; x < 10; x++) {
                //add some fake entries
                Entry entryToAdd = new Entry();
                entryToAdd.setAmount((123f + x));

                Calendar calendarToAdd = Calendar.getInstance();
                calendarToAdd.add(Calendar.DAY_OF_MONTH, (-1 * x));
                entryToAdd.setDate(calendarToAdd);

                if (x % 3 == 0) {
                    entryToAdd.setEstimate(true);
                }
                else {
                    entryToAdd.setEstimate(false);
                }

                entryToAdd.setId(x);
                entryToAdd.setNotes("Note: " + x);
                entryToAdd.setSource("Item " + x);

                DaoFactory.getInstance(getActivity()).getEntryDAO().save(entryToAdd);
            }
        }
        else {
            logger.debug("Database has entries, good to go!");
        }
        //TODO: END OF TEST CODE

        logger.logExit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        //inflate the view
        View v = inflater.inflate(R.layout.fragment_view_entries, parent, false);

        //setup the recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.activityViewEntryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener();

        logger.logExit();
    }
}
