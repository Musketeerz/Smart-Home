package com.example.musketeers.realm;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Voice Register";
    EditText et1, et2, et4;
    LinearLayout et3;
    ImageView loc, reg;
    FloatingActionButton fab;
    String name, aadhar, econsumer, location = null;
    String ano, eno, an = "", en = "";
    private String command, reply;
    int field = 1;

    GPSTracker gps;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextToSpeech tts;

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
        fab = findViewById(R.id.speak);
        reg = findViewById(R.id.reg);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loc = findViewById(R.id.gps);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void location(View view) {
        gps = new GPSTracker(RegisterActivity.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            location = latitude + "," + longitude;
            loc.setImageResource(R.drawable.tick1);
        } else {
            gps.showSettingsAlert();
        }
    }

    public void register(View view) {
        name = et1.getText().toString();
        aadhar = et2.getText().toString();
        econsumer = et4.getText().toString();

        if ((!name.isEmpty()) && (!aadhar.isEmpty()) && (aadhar.length() > 11) && (!econsumer.isEmpty()) && (econsumer.length() > 9) && (location != null)) {
            Intent i = new Intent(this, PairActivity.class);
            i.putExtra(PairActivity.aadhar_name, aadhar);
            i.putExtra(PairActivity.econsumer_name, econsumer);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void talk(View view) {

        switch (field) {
            case 1:
                tts.speak("Please tell your name", TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int progress = 0; progress<100; progress++) {
                            try {
                                Thread.sleep(15);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        promptSpeechInput();
                    }
                }).start();
                break;
            case 2:
                tts.speak("Please tell your 12 digit aadhar number", TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int progress = 0; progress<100; progress++) {
                            try {
                                Thread.sleep(25);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        promptSpeechInput();
                    }
                }).start();
                break;
            case 3:
                tts.speak("Please tell your 10 digit electricity consumer number", TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int progress = 0; progress<100; progress++) {
                            try {
                                Thread.sleep(35);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        promptSpeechInput();
                    }
                }).start();
                break;
            case 4:
                ano = et2.getText().toString();
                eno = et4.getText().toString();
                for (int i=0; i<ano.length(); i++)
                    an = an + ano.charAt(i) + " ";
                for (int i=0; i<eno.length(); i++)
                    en = en + eno.charAt(i) + " ";
                tts.speak("Please confirm your input \n" + "\nName: \n" + et1.getText().toString() + "\n Aadhar number: \n" + an + "\n Electricity Consumer number: \n" + en + "\n Would you like to register?", TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int progress = 0; progress<100; progress++) {
                            try {
                                Thread.sleep(180);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        promptSpeechInput();
                    }
                }).start();
                break;
        }
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
        switch (field) {
            case 1:
                et1.setText(cmd);
                break;
            case 2:
                if (cmd.length() > 11) {
                    cmd = cmd.replace(" ", "");
                    et2.setText(cmd);
                }
                else
                    talk(fab);
                break;
            case 3:
                if (cmd.length() > 9) {
                    cmd = cmd.replace(" ","");
                    et4.setText(cmd);
                }
                else
                    talk(fab);
                location(et3);
                break;
            case 4:
                if (cmd.equals("yes"))
                    register(reg);
                break;
        }
        Toast.makeText(getApplicationContext(), cmd, Toast.LENGTH_SHORT).show();
        field ++;
        if (field < 5)
            talk(fab);
        else {
            field = 1;
        }
    }
}
