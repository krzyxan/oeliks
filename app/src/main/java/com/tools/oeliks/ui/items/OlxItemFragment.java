package com.tools.oeliks.ui.items;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tools.oeliks.R;
import com.tools.oeliks.model.olx.search.OlxSearchData;
import com.tools.oeliks.model.olx.service.OlxRequestingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A fragment representing a list of Items.
 */
@NoArgsConstructor
public class OlxItemFragment extends Fragment {


    @Getter
    private static OlxRecyclerViewAdapter olxAdapter = new OlxRecyclerViewAdapter(); //TODO try to not using static

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = new Intent(requireContext(), OlxRequestingService.class);
        OlxRequestingService.enqueueWork(this.requireContext(), OlxRequestingService.class, OlxRequestingService.JOB_ID, intent); //TODO improve



        try (final FileInputStream fis = requireContext().openFileInput("olxItems");
             final ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {

            olxAdapter.getSearchItems().clear();
            if (objectInputStream.available() > 0) {
                final JSONArray jsonArray = new JSONArray(objectInputStream.readUTF());
                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final OlxSearchData item = new OlxSearchData(jsonObject);
                    olxAdapter.addItem(item);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(olxAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();

        try (final FileOutputStream fos = requireContext().openFileOutput("olxItems", Context.MODE_PRIVATE);
             final ObjectOutputStream objectStream = new ObjectOutputStream(fos)) {

            final JSONArray jsonArray = new JSONArray();
            olxAdapter.getSearchItems().forEach(item -> {
                try {
                    jsonArray.put(item.toJSONObject());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            objectStream.writeUTF(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();


    }
}