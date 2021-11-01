package com.tools.oeliks.ui.items.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.tools.oeliks.R;
import com.tools.oeliks.model.olx.search.OlxSearchData;
import com.tools.oeliks.ui.items.OlxItemFragment;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateSearchItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateSearchItemFragment extends DialogFragment {

    private EditText url;
    private EditText description;

    private CreateSearchItemFragment() {
    } //use newInstance instead

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateSearchItemFragment.
     */
    public static CreateSearchItemFragment newInstance() {
        CreateSearchItemFragment fragment = new CreateSearchItemFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, url);
//        args.putString(ARG_PARAM2, description);
//        fragment.setArguments(args);
        return fragment;
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            url = getArguments().getString(ARG_PARAM1);
//            description = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_search_item, container, false);
    }


    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        url = view.findViewById(R.id.urlText);
        description = view.findViewById(R.id.descriptionText);
        final Button ok = view.findViewById(R.id.okButton);
        final Button cancel = view.findViewById(R.id.cancelButton);

        ok.setOnClickListener((l) -> {
            onActionOkButton();
            this.dismiss();
        });

        cancel.setOnClickListener((l) -> this.dismiss());

        url.requestFocus();
        getDialog()
                .getWindow()
                .setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog()
                .getWindow()
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void onActionOkButton() {
        //TODO add listener with Analyser
        OlxSearchData searchItem = new OlxSearchData(url.getText().toString(), description.getText().toString());
        OlxItemFragment.getOlxAdapter().addItem(searchItem);
        OlxItemFragment.getOlxAdapter().notifyDataSetChanged();
    }
}
