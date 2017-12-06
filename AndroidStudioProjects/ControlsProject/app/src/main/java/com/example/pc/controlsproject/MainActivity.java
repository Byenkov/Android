package com.example.pc.controlsproject;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private TextView tvDisplayTime;
    private Button btnChangeTime;
    private SeekBar seekBar;

    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSeekBar();
        setCurrentTimeOnView();
        addListenerOnButton();
        setVisibilityToggle();
    }

    public void setVisibilityToggle(){
        ToggleButton toggle = findViewById(R.id.toggle_visibility);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvDisplayTime.setVisibility(View.VISIBLE);
                    findViewById(R.id.lblDate).setVisibility(View.VISIBLE);
                    btnChangeTime.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                } else {
                    tvDisplayTime.setVisibility(View.INVISIBLE);
                    findViewById(R.id.lblDate).setVisibility(View.INVISIBLE);
                    btnChangeTime.setVisibility(View.INVISIBLE);
                    seekBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void setSeekBar(){
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                tvDisplayTime.setTextSize((progressValue*5)+55);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void setCurrentTimeOnView() {

        tvDisplayTime = findViewById(R.id.tvDate);

        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 37);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        tvDisplayTime.setText(new StringBuilder()
                .append(hour).append(":").append(minute));
    }

    public void addListenerOnButton() {

        btnChangeTime = findViewById(R.id.btnChangeDate);

        btnChangeTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int newHour, int newMinute) {
                        hour = newHour;
                        minute = newMinute;
                        tvDisplayTime.setText(new StringBuilder()
                                .append(hour).append(":").append(minute));
                    }
                }, hour, minute, true);
        }
        return null;
    }

}