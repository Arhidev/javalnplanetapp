package com.arinteck.planetsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Planet> planetArrayList;
    private  static CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1 AdapterView: a ListView
        listView = findViewById(R.id.listView);

        // 2 Data source
        planetArrayList = new ArrayList<>();

        Planet planet1 = new Planet ( "Earth",  "1 Earth", R.drawable.earth);
        Planet planet2 = new Planet ( "Mercury",  "0 Mercury",R.drawable.mercury);
        Planet planet3 = new Planet ( "Venus", "0 Venus", R.drawable.venus);

        Planet planet4 = new Planet ( "Mars",  "2 Mars", R.drawable.mars);
        Planet planet5 = new Planet ("Jupiter",  "79 Jupiter", R. drawable.jupiter);
        Planet planet6 = new Planet("Saturn",  "83 Saturn", R. drawable.saturn);

        Planet planet7 = new Planet ( "Uranus",  "27 Uranus", R. drawable.uranus);
        Planet planet8 = new Planet ( "Neptune", "14 Neptune", R. drawable.neptune);

        planetArrayList.add(planet1);
        planetArrayList.add(planet2);
        planetArrayList.add(planet3);
        planetArrayList.add(planet4);
        planetArrayList.add(planet5);
        planetArrayList.add(planet6);
        planetArrayList.add(planet7);
        planetArrayList.add(planet8);

        adapter = new CustomListAdapter(getApplicationContext(), planetArrayList);
        listView.setAdapter(adapter);

        //Handling click Event

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Planet Name: " + Objects.requireNonNull(adapter.getItem(position)).getPlanetName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}