package com.example.musketeers.realm;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private ToggleButton eco, eHeater, eIron, eOutLight, eBedLight, eMotor, eBedFan, eWash;
    private Switch heater, iron, outLight, bedLight, motor, bedFan, wash;
    private FloatingActionButton speak;
    private TextToSpeech tts;
    ArrayList<String> device_status=new ArrayList<String >();

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String command, reply;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        eco = findViewById(R.id.eco);
        eHeater = findViewById(R.id.eheater);
        eIron = findViewById(R.id.eiron);
        eOutLight = findViewById(R.id.eoutlight);
        eBedLight = findViewById(R.id.ebedlight);
        eMotor = findViewById(R.id.emotor);
        eBedFan = findViewById(R.id.ebedfan);
        eWash = findViewById(R.id.ewash);

        heater = findViewById(R.id.heater);
        iron = findViewById(R.id.iron);
        outLight = findViewById(R.id.outlight);
        bedLight = findViewById(R.id.bedlight);
        motor = findViewById(R.id.motor);
        bedFan = findViewById(R.id.bedfan);
        wash = findViewById(R.id.wash);

        speak = findViewById(R.id.speak);

        //ecoMode(eco);

        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reply(eco);
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });


        databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren() )
                {
                    String usrs=child.getValue(String.class);
                    device_status.clear();
                    device_status.add(usrs);
//                     Toast.makeText(dummy_firebase_check.this,""+usrs,Toast.LENGTH_SHORT).show();
//                    status=device_status.get(0);

//                    toast_message();
                    String[] check=usrs.split("_");

                    if (check[0].equals("waterheater"))
                    {
                        if (check[1].equals("true"))
                        {
                            heater.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            heater.setChecked(false);
                        }
                    }
                    else if (check[0].equals("ironbox"))
                    {
                        if (check[1].equals("true"))
                        {

                            iron.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            iron.setChecked(false);
                        }
                    }
                    else if (check[0].equals("bedroomlight"))
                    {
                        if (check[1].equals("true"))
                        {
                            bedLight.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            bedLight.setChecked(false);
                        }
                    }
                    else if (check[0].equals("bedroomfan"))
                    {
                        if (check[1].equals("true"))
                        {
                            bedFan.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            bedFan.setChecked(false);
                        }
                    }
                    else if (check[0].equals("washingmachine"))
                    {
                        if (check[1].equals("true"))
                        {
                           wash .setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            wash.setChecked(false);
                        }
                    }
                    else if (check[0].equals("watermotor"))
                    {
                        if (check[1].equals("true"))
                        {
                            motor.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            motor.setChecked(false);
                        }
                    }
                    else if (check[0].equals("outsidelight"))
                    {
                        if (check[1].equals("true"))
                        {
                            outLight.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            outLight.setChecked(false);
                        }
                    }





                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren() )
                {
                    String usrs=child.getValue(String.class);
                    device_status.clear();
                    device_status.add(usrs);

                    String[] check=usrs.split("_");

                    if (check[0].equals("waterheater"))
                    {
                        if (check[1].equals("true"))
                        {
                            eHeater.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eHeater.setChecked(false);
                        }
                    }
                    else if (check[0].equals("ironbox"))
                    {
                        if (check[1].equals("true"))
                        {
                            eIron.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eIron.setChecked(false);
                        }
                    }
                    else if (check[0].equals("bedroomlight"))
                    {
                        if (check[1].equals("true"))
                        {
                            eBedLight.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eBedLight.setChecked(false);
                        }
                    }
                    else if (check[0].equals("bedroomfan"))
                    {
                        if (check[1].equals("true"))
                        {
                            eBedFan.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eBedFan.setChecked(false);
                        }
                    }
                    else if (check[0].equals("washingmachine"))
                    {
                        if (check[1].equals("true"))
                        {
                            eWash .setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eWash.setChecked(false);
                        }
                    }
                    else if (check[0].equals("watermotor"))
                    {
                        if (check[1].equals("true"))
                        {
                            eMotor.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eMotor.setChecked(false);
                        }
                    }
                    else if (check[0].equals("outsidelight"))
                    {
                        if (check[1].equals("true"))
                        {
                            eOutLight.setChecked(true);
                        }
                        else if (check[1].equals("false"))
                        {
                            eOutLight.setChecked(false);
                        }
                    }





                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    public void ecoMode(View view) {
        if (eco.isChecked()) {
            eHeater.setChecked(true);
            eIron.setChecked(true);
            eOutLight.setChecked(true);
            eBedLight.setChecked(true);
            eMotor.setChecked(true);
            eBedFan.setChecked(true);
            eWash.setChecked(true);

            databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
            databaseReference.child("WATER HEATER").setValue("waterheater_true");
            databaseReference.child("IRON BOX").setValue("ironbox_true");
            databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_true");
            databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_true");
            databaseReference.child("WATER MOTOR").setValue("watermotor_true");
            databaseReference.child("BEDROOM FAN").setValue("bedroomfan_true");
            databaseReference.child("WASHING MACHINE").setValue("washingmachine_true");





        } else {
            eHeater.setChecked(false);
            eIron.setChecked(false);
            eOutLight.setChecked(false);
            eBedLight.setChecked(false);
            eMotor.setChecked(false);
            eBedFan.setChecked(false);
            eWash.setChecked(false);

            databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
            databaseReference.child("WATER HEATER").setValue("waterheater_false");
            databaseReference.child("IRON BOX").setValue("ironbox_false");
            databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_false");
            databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_false");
            databaseReference.child("WATER MOTOR").setValue("watermotor_false");
            databaseReference.child("BEDROOM FAN").setValue("bedroomfan_false");
            databaseReference.child("WASHING MACHINE").setValue("washingmachine_false");
        }
    }

    public void reply(View view) {
        if (view.getId() == R.id.heater) {
            if (heater.isChecked()) {
                command = "turn on water heater";


            } else {
                command = "turn off water heater";
            }
        } else if (view.getId() == R.id.eheater) {
            if (eHeater.isChecked()) {
                command = "turn on eco water heater";
            } else {
                command = "turn off eco water heater";
            }
        } else if (view.getId() == R.id.iron) {
            if (iron.isChecked()) {
                command = "turn on iron box";
            } else {
                command = "turn off iron box";
            }
        } else if (view.getId() == R.id.eiron) {
            if (eIron.isChecked()) {
                command = "turn on eco iron box";
            } else {
                command = "turn off eco iron box";
            }
        } else if (view.getId() == R.id.outlight) {
            if (outLight.isChecked()) {
                command = "turn on outside light";
            } else {
                command = "turn off outside light";
            }
        } else if (view.getId() == R.id.eoutlight) {
            if (eOutLight.isChecked()) {
                command = "turn on eco outside light";
            } else {
                command = "turn off eco outside light";
            }
        } else if (view.getId() == R.id.bedlight) {
            if (bedLight.isChecked()) {
                command = "turn on bedroom light";
            } else {
                command = "turn off bedroom light";
            }
        } else if (view.getId() == R.id.ebedlight) {
            if (eBedLight.isChecked()) {
                command = "turn on eco bedroom light";
            } else {
                command = "turn off eco bedroom light";
            }
        } else if (view.getId() == R.id.motor) {
            if (motor.isChecked()) {
                command = "turn on water motor";
            } else {
                command = "turn off water motor";
            }
        } else if (view.getId() == R.id.emotor) {
            if (eMotor.isChecked()) {
                command = "turn on eco water motor";
            } else {
                command = "turn off eco water motor";
            }
        } else if (view.getId() == R.id.bedfan) {
            if (bedFan.isChecked()) {
                command = "turn on bedroom fan";
            } else {
                command = "turn off bedroom fan";
            }
        } else if (view.getId() == R.id.ebedfan) {
            if (eBedFan.isChecked()) {
                command = "turn on eco bedroom fan";
            } else {
                command = "turn off eco bedroom fan";
            }
        } else if (view.getId() == R.id.wash) {
            if (wash.isChecked()) {
                command = "turn on washing machine";
            } else {
                command = "turn off washing machine";
            }
        } else if (view.getId() == R.id.ewash) {
            if (eWash.isChecked()) {
                command = "turn on eco washing machine";
            } else {
                command = "turn off eco washing machine";
            }
        } else if (view.getId() == R.id.eco) {
            if (eco.isChecked()) {
                command = "eco mode on";
            } else {
                command = "eco mode off";
            }
        }
        makeToast(command);
    }

    public void talk(View view) {
        promptSpeechInput();
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    command = result.get(0).toLowerCase();
                    makeToast(command);
                }
                break;
            }

        }
    }

    private void makeToast(String cmd) {
        if (cmd.contains("eco")) {
             if (cmd.contains("water heater")) {
                if (cmd.contains("on")) {
                    eHeater.setChecked(true);
                    reply = "eco mode of water heater turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WATER HEATER").setValue("waterheater_true");

                } else if (cmd.contains("off")) {
                    eHeater.setChecked(false);
                    reply = "eco mode of water heater turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WATER HEATER").setValue("waterheater_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("iron box")) {
                if (cmd.contains("turn on")) {
                    eIron.setChecked(true);
                    reply = "eco mode of iron box turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("IRON BOX").setValue("ironbox_true");

                } else if (cmd.contains("off")) {
                    eIron.setChecked(false);
                    reply = "eco mode of iron box turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("IRON BOX").setValue("ironbox_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("outside light")) {
                if (cmd.contains("on")) {
                    eOutLight.setChecked(true);
                    reply = "eco mode of outside light turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_true");

                } else if (cmd.contains("off")) {
                    eOutLight.setChecked(false);
                    reply = "eco mode of outside light turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("bedroom light")) {
                if (cmd.contains("on")) {
                    eBedLight.setChecked(true);
                    reply = "eco mode of bedroom light turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_true");

                } else if (cmd.contains("off")) {
                    eBedLight.setChecked(false);
                    reply = "eco mode of bedroom light turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("water motor")) {
                if (cmd.contains("on")) {
                    eMotor.setChecked(true);
                    reply = "eco mode of water motor turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WATER MOTOR").setValue("watermotor_true");

                } else if (cmd.contains("off")) {
                    eMotor.setChecked(false);
                    reply = "eco mode of water motor turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WATER MOTOR").setValue("watermotor_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("bedroom fan")) {
                if (cmd.contains("on")) {
                    eBedFan.setChecked(true);
                    reply = "eco mode of bedroom fan turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("BEDROOM FAN").setValue("bedroomfan_true");

                } else if (cmd.contains("off")) {
                    eBedFan.setChecked(false);
                    reply = "eco mode of bedroom fan turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("BEDROOM FAN").setValue("bedroomfan_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("washing machine")) {
                if (cmd.contains("on")) {
                    eWash.setChecked(true);
                    reply = "eco mode of washing machine turned on";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WASHING MACHINE").setValue("washingmachine_true");

                } else if (cmd.contains("off")) {
                    eWash.setChecked(false);
                    reply = "eco mode of washing machine turned off";

                    databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");
                    databaseReference.child("WASHING MACHINE").setValue("washingmachine_false");

                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("on")) {
                eco.setChecked(true);
                ecoMode(eco);
                reply = "eco mode turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");


            } else if (cmd.contains("off")) {
                eco.setChecked(false);
                ecoMode(eco);
                reply = "eco mode turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("ECOMODE STATUS");


            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("water heater")) {
            if (cmd.contains("on")) {
                heater.setChecked(true);
                reply = "water heater turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WATER HEATER").setValue("waterheater_true");


            } else if (cmd.contains("off")) {
                heater.setChecked(false);
                reply = "water heater turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WATER HEATER").setValue("waterheater_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("iron box")) {
            if (cmd.contains("turn on")) {
                iron.setChecked(true);
                reply = "iron box turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("IRON BOX").setValue("ironbox_true");


            } else if (cmd.contains("off")) {
                iron.setChecked(false);
                reply = "iron box turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("IRON BOX").setValue("ironbox_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("outside light")) {
            if (cmd.contains("on")) {
                outLight.setChecked(true);
                reply = "outside light turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_true");

            } else if (cmd.contains("off")) {
                outLight.setChecked(false);
                reply = "outside light turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("OUTSIDE LIGHT").setValue("outsidelight_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("bedroom light")) {
            if (cmd.contains("on")) {
                bedLight.setChecked(true);
                reply = "bedroom light turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_true");


            } else if (cmd.contains("off")) {
                bedLight.setChecked(false);
                reply = "bedroom light turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("BEDROOM LIGHT").setValue("bedroomlight_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("water motor")) {
            if (cmd.contains("on")) {
                motor.setChecked(true);
                reply = "water motor turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WATER MOTOR").setValue("watermotor_true");

            } else if (cmd.contains("off")) {
                motor.setChecked(false);
                reply = "water motor turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WATER MOTOR").setValue("watermotor_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("bedroom fan")) {
            if (cmd.contains("on")) {
                bedFan.setChecked(true);
                reply = "bedroom fan turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("BEDROOM FAN").setValue("bedroomfan_true");

            } else if (cmd.contains("off")) {
                bedFan.setChecked(false);
                reply = "bedroom fan turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("BEDROOM FAN").setValue("bedroomfan_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else if (cmd.contains("washing machine")) {
            if (cmd.contains("on")) {
                wash.setChecked(true);
                reply = "washing machine turned on";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WASHING MACHINE").setValue("washingmachine_true");

            } else if (cmd.contains("off")) {
                wash.setChecked(false);
                reply = "washing machine turned off";

                databaseReference= FirebaseDatabase.getInstance().getReference("DEVICE STATUS");
                databaseReference.child("WASHING MACHINE").setValue("washingmachine_false");

            } else {
                reply = "Pardon! Speak Again.";
            }
        } else {
            reply = "Pardon! Speak Again.";
        }

        Toast.makeText(getApplicationContext(), reply, Toast.LENGTH_SHORT).show();
        tts.speak(reply, TextToSpeech.QUEUE_FLUSH, null);
    }
}
