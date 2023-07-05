package com.example.beattrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView tvSystolicValue = findViewById(R.id.tvSystolicValue);
        TextView tvHeartRateValue = findViewById(R.id.tvHeartRateValue);
        TextView tvDiastolicValue = findViewById(R.id.tvDiastolicValue);
        TextView tvDateValue = findViewById(R.id.tvDateValue);
        TextView tvTimeValue = findViewById(R.id.tvTimeValue);
        TextView tvCommentValue = findViewById(R.id.tvCommentValue);

        // Retrieve the data from the intent
        EachData data = (EachData) getIntent().getSerializableExtra("data");

        // Set the data to the respective TextViews
        tvSystolicValue.setText(data.getSystolic());
        tvHeartRateValue.setText(data.getHeartRate());
        tvDiastolicValue.setText(data.getDiastolic());
        tvDateValue.setText(data.getDate());
        tvTimeValue.setText(data.getTime());
        tvCommentValue.setText(data.getComment());
    }
}