package com.example.musketeers.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PairActivity extends AppCompatActivity {

    TextView pairId;
    ImageView pair;

    public static final String aadhar_name ="aadhar", econsumer_name = "econsumer" ;
    String pair_id;
    String aadhar,passcode_pass,KEY;
    DatabaseReference databaseReference;
    ArrayList<String> name=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        pairId = findViewById(R.id.pair_id);
        //pair = findViewById(R.id.pair);

        aadhar = getIntent().getStringExtra(aadhar_name);
        String econsumer = getIntent().getStringExtra(econsumer_name);

      KEY= getIntent().getStringExtra("KEY");

        //pair_id = aadhar + econsumer;


        pairId.setText(KEY.substring(0,2)+"-"+KEY.substring(2,4)+"-"+KEY.substring(4,6)+"-"+KEY.substring(6,8)+"-"+KEY.substring(8,10));


                databaseReference= FirebaseDatabase.getInstance().getReference(KEY.substring(0,2)+KEY.substring(2,4)+KEY.substring(4,6)+KEY.substring(6,8)+KEY.substring(8,10)).child("USER DETAILS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()) {
                    String usrs = child.getValue(String.class);

                    name.add(usrs);
                   // Toast.makeText(PairActivity.this,""+usrs,Toast.LENGTH_LONG).show();




                }
                //Toast.makeText(PairActivity.this,""+name.get(3),Toast.LENGTH_LONG).show();

                if(name.get(3).equals("true"))
                {
                    pair();
                }
                else
                {
                    name.clear();
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

     void pair() {
        Intent i = new Intent(this, DashboardActivity.class);
        i.putExtra("KEY",KEY);
        startActivity(i);
        //Toast.makeText(getApplicationContext(),""+KEY,Toast.LENGTH_LONG).show();
    }
}
