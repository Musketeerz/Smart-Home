package com.example.musketeers.realm;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private ToggleButton eco, eHeater, eIron, eOutLight, eBedLight, eMotor, eBedFan, eWash;
    private Switch heater, iron, outLight, bedLight, motor, bedFan, wash;
    private FloatingActionButton speak;
    private TextToSpeech tts;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String command, reply;

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

        if (eco.isChecked()) {
            eHeater.setVisibility(View.VISIBLE);
            eIron.setVisibility(View.VISIBLE);
            eOutLight.setVisibility(View.VISIBLE);
            eBedLight.setVisibility(View.VISIBLE);
            eMotor.setVisibility(View.VISIBLE);
            eBedFan.setVisibility(View.VISIBLE);
            eWash.setVisibility(View.VISIBLE);
        } else {
            eHeater.setVisibility(View.INVISIBLE);
            eIron.setVisibility(View.INVISIBLE);
            eOutLight.setVisibility(View.INVISIBLE);
            eBedLight.setVisibility(View.INVISIBLE);
            eMotor.setVisibility(View.INVISIBLE);
            eBedFan.setVisibility(View.INVISIBLE);
            eWash.setVisibility(View.INVISIBLE);
        }

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void ecoMode(View view) {
        if (eco.isChecked()) {
            eHeater.setVisibility(View.VISIBLE);
            eIron.setVisibility(View.VISIBLE);
            eOutLight.setVisibility(View.VISIBLE);
            eBedLight.setVisibility(View.VISIBLE);
            eMotor.setVisibility(View.VISIBLE);
            eBedFan.setVisibility(View.VISIBLE);
            eWash.setVisibility(View.VISIBLE);
        } else {
            eHeater.setVisibility(View.INVISIBLE);
            eIron.setVisibility(View.INVISIBLE);
            eOutLight.setVisibility(View.INVISIBLE);
            eBedLight.setVisibility(View.INVISIBLE);
            eMotor.setVisibility(View.INVISIBLE);
            eBedFan.setVisibility(View.INVISIBLE);
            eWash.setVisibility(View.INVISIBLE);
        }
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
            if (cmd.contains("mode on")) {
                eco.setChecked(true);
                ecoMode(eco);
                reply = "eco mode turned on";
            } else if (cmd.contains("mode off")) {
                eco.setChecked(false);
                ecoMode(eco);
                reply = "eco mode turned off";
            } else if (eco.isChecked()) {
                if (cmd.contains("water heater")) {
                    if (cmd.contains("on")) {
                        eHeater.setChecked(true);
                        reply = "eco mode of water heater turned on";
                    } else if (cmd.contains("off")) {
                        eHeater.setChecked(false);
                        reply = "eco mode of water heater turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("iron box")) {
                    if (cmd.contains("on")) {
                        eIron.setChecked(true);
                        reply = "eco mode of iron box turned on";
                    } else if (cmd.contains("off")) {
                        eIron.setChecked(false);
                        reply = "eco mode of iron box turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("outside light")) {
                    if (cmd.contains("on")) {
                        eOutLight.setChecked(true);
                        reply = "eco mode of outside light turned on";
                    } else if (cmd.contains("off")) {
                        eOutLight.setChecked(false);
                        reply = "eco mode of outside light turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("bedroom light")) {
                    if (cmd.contains("on")) {
                        eBedLight.setChecked(true);
                        reply = "eco mode of bedroom light turned on";
                    } else if (cmd.contains("off")) {
                        eBedLight.setChecked(false);
                        reply = "eco mode of bedroom light turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("water motor")) {
                    if (cmd.contains("on")) {
                        eMotor.setChecked(true);
                        reply = "eco mode of water motor turned on";
                    } else if (cmd.contains("off")) {
                        eMotor.setChecked(false);
                        reply = "eco mode of water motor turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("bedroom fan")) {
                    if (cmd.contains("on")) {
                        eBedFan.setChecked(true);
                        reply = "eco mode of bedroom fan turned on";
                    } else if (cmd.contains("off")) {
                        eBedFan.setChecked(false);
                        reply = "eco mode of bedroom fan turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else if (cmd.contains("washing machine")) {
                    if (cmd.contains("on")) {
                        eWash.setChecked(true);
                        reply = "eco mode of washing machine turned on";
                    } else if (cmd.contains("off")) {
                        eWash.setChecked(false);
                        reply = "eco mode of washing machine turned off";
                    } else {
                        reply = "Pardon! Speak Again.";
                    }
                } else {
                    reply = "Pardon! Speak Again.";
                }
            }
            else {
                reply = "Pardon! Speak Again.";
            }
        } else if (!eco.isChecked() || eco.isChecked()) {
            if (cmd.contains("water heater")) {
                if (cmd.contains("on")) {
                    heater.setChecked(true);
                    reply = "water heater turned on";
                } else if (cmd.contains("off")) {
                    heater.setChecked(false);
                    reply = "water heater turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("iron box")) {
                if (cmd.contains("on")) {
                    iron.setChecked(true);
                    reply = "iron box turned on";
                } else if (cmd.contains("off")) {
                    iron.setChecked(false);
                    reply = "iron box turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("outside light")) {
                if (cmd.contains("on")) {
                    outLight.setChecked(true);
                    reply = "outside light turned on";
                } else if (cmd.contains("off")) {
                    outLight.setChecked(false);
                    reply = "outside light turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("bedroom light")) {
                if (cmd.contains("on")) {
                    bedLight.setChecked(true);
                    reply = "bedroom light turned on";
                } else if (cmd.contains("off")) {
                    bedLight.setChecked(false);
                    reply = "bedroom light turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("water motor")) {
                if (cmd.contains("on")) {
                    motor.setChecked(true);
                    reply = "water motor turned on";
                } else if (cmd.contains("off")) {
                    motor.setChecked(false);
                    reply = "water motor turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("bedroom fan")) {
                if (cmd.contains("on")) {
                    bedFan.setChecked(true);
                    reply = "bedroom fan turned on";
                } else if (cmd.contains("off")) {
                    bedFan.setChecked(false);
                    reply = "bedroom fan turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
            } else if (cmd.contains("washing machine")) {
                if (cmd.contains("on")) {
                    wash.setChecked(true);
                    reply = "washing machine turned on";
                } else if (cmd.contains("off")) {
                    wash.setChecked(false);
                    reply = "washing machine turned off";
                } else {
                    reply = "Pardon! Speak Again.";
                }
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
