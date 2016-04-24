package com.patrickwshaw.financejournal.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.patrickwshaw.financejournal.R;
import com.patrickwshaw.financejournal.model.Entry;
import com.patrickwshaw.financejournal.model.dao.DaoFactory;
import com.patrickwshaw.financejournal.util.LoggingUtil;

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

        if (DaoFactory.getInstance(getActivity()).getEntryDAO().getAll().size() == 0) {
            //TODO: remove this test code
            logger.warn("There are no items in the database, adding some to test with");
            Entry fakeEntryOne = new Entry();
            fakeEntryOne.setAmount();
        }

        logger.logExit();
    }
}
