package com.example.musketeers.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PairActivity extends AppCompatActivity {

    TextView pairId;
    ImageView pair;

    public static final String aadhar_name ="aadhar", econsumer_name = "econsumer";
    String pair_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        pairId = findViewById(R.id.pair_id);
        pair = findViewById(R.id.pair);

        String aadhar = getIntent().getStringExtra(aadhar_name);
        String econsumer = getIntent().getStringExtra(econsumer_name);

        pair_id = aadhar + econsumer;
        pairId.setText(pair_id);
    }

    public void pair(View view) {
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }
}
