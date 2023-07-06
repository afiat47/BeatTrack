package com.example.beattrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
    private List<EachData> allData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        TextView tvSystolicValue = findViewById(R.id.tvSystolicValue);
        TextView tvHeartRateValue = findViewById(R.id.tvHeartRateValue);
        TextView tvDiastolicValue = findViewById(R.id.tvDiastolicValue);
        TextView tvDateValue = findViewById(R.id.tvDateValue);
        TextView tvTimeValue = findViewById(R.id.tvTimeValue);
        TextView tvCommentValue = findViewById(R.id.tvCommentValue);

        // Retrieve the data from the intent
        Intent intent = getIntent();
        String systolic = intent.getStringExtra("systolic");
        String diastolic = intent.getStringExtra("diastolic");
        String heart = intent.getStringExtra("heartRate");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String comment = intent.getStringExtra("comment");

        // Set the data to the respective TextViews
        tvSystolicValue.setText(systolic+" mmHg");
        if (Integer.parseInt(systolic) <90 || Integer.parseInt(systolic)>140  ) {
            tvSystolicValue.setTextColor(getResources().getColor(R.color.red));
        }
        tvDiastolicValue.setText(diastolic+" mmHg");
        if (Integer.parseInt(diastolic) <60 || Integer.parseInt(diastolic)>90  ) {
            tvDiastolicValue.setTextColor(getResources().getColor(R.color.red));
        }
        tvHeartRateValue.setText(heart+" bpm");
        tvDateValue.setText(date);
        tvTimeValue.setText(time);
        tvCommentValue.setText(comment);

    }
}