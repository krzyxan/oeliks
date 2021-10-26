package com.tools.oeliks.ui.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tools.oeliks.R;
import com.tools.oeliks.model.olx.search.OlxSearchData;

import java.util.List;

import lombok.ToString;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OlxSearchData}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OlxRecyclerViewAdapter extends RecyclerView.Adapter<OlxRecyclerViewAdapter.ViewHolder> {

    private final List<OlxSearchData> mValues;

    public OlxRecyclerViewAdapter(List<OlxSearchData> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String buttonText = holder.mItem.getAvailableNewItems() + " NEW ITEMS";
        holder.itemSearchDescription.setText(holder.mItem.getDescription());
        holder.newItemsButton.setText(buttonText);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @ToString
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView itemSearchDescription;
        public final Button newItemsButton;
        public OlxSearchData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            itemSearchDescription = (TextView) view.findViewById(R.id.itemSearchDescription);
            newItemsButton = (Button) view.findViewById(R.id.newItemsButton);
            //TODO add edit action
            //TODO add delete action
        }
    }
}