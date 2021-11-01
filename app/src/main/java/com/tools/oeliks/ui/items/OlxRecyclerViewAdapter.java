package com.tools.oeliks.ui.items;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tools.oeliks.R;
import com.tools.oeliks.model.olx.search.OlxSearchData;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OlxSearchData}.
 */
public class OlxRecyclerViewAdapter extends RecyclerView.Adapter<OlxRecyclerViewAdapter.ViewHolder> {

    private final List<OlxSearchData> searchItems = new ArrayList<>();

    public void addItem(OlxSearchData searchItem) {
        searchItems.add(searchItem);
    }

    public void removeSearchItem(OlxSearchData searchItem) {
        searchItems.remove(searchItem);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setMItem(searchItems.get(position));
        holder.getItemSearchDescription().setText(holder.mItem.getDescription());

        final String buttonText = holder.mItem.getAvailableNewItems() + " NEW ITEMS";
        holder.getNewItemsButton().setText(buttonText);
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    @Getter
    @Setter
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView itemSearchDescription;
        private final Button newItemsButton;
        private final ImageButton editItem;
        private final ImageButton deleteItem;

        private OlxSearchData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            itemSearchDescription = view.findViewById(R.id.itemSearchDescription);
            newItemsButton = view.findViewById(R.id.newItemsButton);
            editItem = view.findViewById(R.id.editButton);
            deleteItem = view.findViewById(R.id.deleteButton);

            editItem.setOnClickListener((l) -> onActionEditItem());
            deleteItem.setOnClickListener((l) -> onActionDeleteItem());
        }

        private void onActionEditItem() {
            //TODO add edit action dialog
        }

        private void onActionDeleteItem() {
            new AlertDialog.Builder(mView.getContext()).setTitle("Confirm delete")
                    .setMessage("Would you like to delete " + mItem.getDescription() + "?")
                    .setPositiveButton("OK", (dialog, id) -> {
                        OlxItemFragment.getOlxAdapter().removeSearchItem(mItem);
                        OlxItemFragment.getOlxAdapter().notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel())
                    .create()
                    .show();
        }
    }
}