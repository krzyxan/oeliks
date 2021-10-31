package com.tools.oeliks.ui.items.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.tools.oeliks.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateSearchItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateSearchItemFragment extends DialogFragment {

    //TODO make this dialog fragment working or change to primitive way of building this shit

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
    // TODO: Rename and change types and number of parameters
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
        url = (EditText) view.findViewById(R.id.urlText);
        description = (EditText) view.findViewById(R.id.descriptionText);
        // Fetch arguments from bundle and set title
        getDialog().setTitle("Add new search");
        getDialog().setContentView(this.getView());
        // Show soft keyboard automatically and request focus to field
        url.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}