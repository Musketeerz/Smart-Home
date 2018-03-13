package com.example.musketeers.realm;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    ImageView reg, loc;
    String name, aadhar, location, econsumer;

    Button home;
    GPSTracker gps;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et1 = findViewById(R.id.nameField);
        et2 = findViewById(R.id.aadharField);
        et3 = findViewById(R.id.locationField);
        et4 = findViewById(R.id.econsumerField);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loc = findViewById(R.id.gps);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GPSTracker(RegisterActivity.this);
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    et3.setText(latitude + "," + longitude);
                    //Toast.makeText(getApplicationContext(), "Your location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });
    }

    public void register(View view) {
        name = et1.getText().toString();
        aadhar = et2.getText().toString();
        econsumer = et4.getText().toString();
        location = et3.getText().toString();

        Intent i = new Intent(this, PairActivity.class);
        i.putExtra(PairActivity.aadhar_name, aadhar);
        i.putExtra(PairActivity.econsumer_name, econsumer);
        startActivity(i);
    }
}
