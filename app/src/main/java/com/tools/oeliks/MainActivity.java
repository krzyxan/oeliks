package com.tools.oeliks;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tools.oeliks.model.http.HttpRequester;
import com.tools.oeliks.model.olx.analyser.OlxResponseAnalyser;
import com.tools.oeliks.model.olx.search.OlxSearchData;
import com.tools.oeliks.model.olx.search.item.OlxItem;
import com.tools.oeliks.ui.items.OlxItemFragment;
import com.tools.oeliks.ui.items.create.CreateSearchItemFragment;

import java.io.IOException;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //setting dark mode

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            new Thread(this::openAddItemDialog).start();
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void openAddItemDialog() {
        final CreateSearchItemFragment dialogFragment = CreateSearchItemFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), "CreateSearchItemDialogFragment");
    }

    private void addOlxItem() {
        HashSet<OlxItem> items = new HashSet<>();
        items.add(new OlxItem("ps5"));
        OlxSearchData searchItem = new OlxSearchData("url-s", "first ps5");
        searchItem.compareAndResetItems(items);

        OlxItemFragment.getOlxAdapter().addItem(searchItem);
        runOnUiThread(() -> OlxItemFragment.getOlxAdapter().notifyDataSetChanged());
    }

    private void doBasicOlxRequest() {
        final String httpBody;
        try {
            httpBody = HttpRequester.requestUrl("https://www.olx.pl/ruda-slaska/q-Xbox-series-s/?search%5Bfilter_float_price%3Afrom%5D=500&search%5Bfilter_float_price%3Ato%5D=950&search%5Bdist%5D=50&reason=observed_search");
            OlxResponseAnalyser.parseItems(httpBody)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}