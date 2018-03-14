package com.example.musketeers.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ArrayList<Word> appliances = new ArrayList<>();
        appliances.add(new Word("Air Conditioner", false, R.drawable.ac));
        appliances.add(new Word("Blender", false, R.drawable.blender));
        appliances.add(new Word("Computer", false, R.drawable.computer));
        appliances.add(new Word("Iron Box", false, R.drawable.iron));
        appliances.add(new Word("Microwave Oven", false, R.drawable.microwave));
        appliances.add(new Word("Modem", false, R.drawable.modem));
        appliances.add(new Word("Refrigerator", false, R.drawable.refrigerator));
        appliances.add(new Word("Television", false, R.drawable.tv));
        appliances.add(new Word("Washing Machine", false, R.drawable.wash));

        WordAdapter adapter = new WordAdapter(this, appliances);

        ListView applianceListView = findViewById(R.id.list);
        applianceListView.setAdapter(adapter);
    }
}
