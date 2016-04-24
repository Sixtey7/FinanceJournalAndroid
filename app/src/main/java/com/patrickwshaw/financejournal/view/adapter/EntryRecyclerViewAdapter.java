package com.patrickwshaw.financejournal.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.patrickwshaw.financejournal.R;
import com.patrickwshaw.financejournal.model.Entry;
import com.patrickwshaw.financejournal.util.LoggingUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by minty on 4/10/16.
 */
public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.ViewHolder> {

    private static final LoggingUtil logger = new LoggingUtil(EntryRecyclerViewAdapter.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);


    protected List<Entry> entryList = new ArrayList<Entry>();
    protected Context context;

    public EntryRecyclerViewAdapter(Context context, List<Entry> entryList) {
        logger.logEnter("constructor");

        this.entryList = entryList;
        this.context = context;

        logger.logExit();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        logger.logEnter("onCreateViewHolder");

        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_entity, null);

        logger.logExit();
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        logger.logEnter("onBindViewHolder");

        Entry entryToBind = entryList.get(i);

        if (entryToBind.getSource() != null) {
            viewHolder.source.setText(entryToBind.getSource());
        }

        if (entryToBind.getAmount() != null) {
            viewHolder.amount.setText(String.valueOf(entryToBind.getAmount()));
        }

        if (entryToBind.getTotal() != null) {
            viewHolder.total.setText(String.valueOf(entryToBind.getTotal()));
        }

        if (entryToBind.getDate() != null) {
            viewHolder.date.setText(sdf.format(entryToBind.getDate()));
        }

        if (entryToBind.getNotes() != null) {
            viewHolder.notes.setText(entryToBind.getNotes());
        }

        logger.logExit();
    }


    @Override
    public int getItemCount() {
        return entryList.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView source;
        public EditText amount;
        public TextView total;
        public TextView date;
        public TextView notes;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            source = (TextView) itemLayoutView.findViewById(R.id.entityRowSource);
            amount = (EditText) itemLayoutView.findViewById(R.id.entityRowAmount);
            total = (TextView) itemLayoutView.findViewById(R.id.entityRowTotal);
            date = (TextView) itemLayoutView.findViewById(R.id.entityRowDate);
            notes = (TextView) itemLayoutView.findViewById(R.id.entityRowNotes);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); // gets item position
            Entry user = entryList.get(position);
            // We can access the data within the views
            Toast.makeText(context, source.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
