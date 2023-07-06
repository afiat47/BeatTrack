package com.example.beattrack;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Insert extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter, timeFormatter;
    EditText systolic,diastolic,heart,date,time,comment;
    Button savebutton;
    private EachData passedData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        savebutton=findViewById(R.id.button2);
        systolic=findViewById(R.id.systolic);
        diastolic=findViewById(R.id.dia);
        heart=findViewById(R.id.Heart);
        date=findViewById(R.id.Date2);
        time=findViewById(R.id.Time);
        comment=findViewById(R.id.comment);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);

        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        Object obj = getIntent().getSerializableExtra("data");
        if(obj instanceof EachData){
            passedData = (EachData)obj;
            date.setText(passedData.getDate());
            time.setText(passedData.getTime());
            systolic.setText(passedData.getSystolic());
            diastolic.setText(passedData.getDiastolic());
            heart.setText(passedData.getHeartRate());
            comment.setText(passedData.getComment());
        }

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }


    private void showDatePicker() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        datePickerDialog = new DatePickerDialog(Insert.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Set selected date to date EditText
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                String selectedDateString = dateFormatter.format(selectedDate.getTime());
                date.setText(selectedDateString);
            }
        }, year, month, dayOfMonth);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create TimePickerDialog
        timePickerDialog = new TimePickerDialog(Insert.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Set selected time to time EditText
                Calendar selectedTime = Calendar.getInstance();
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                selectedTime.set(Calendar.MINUTE, minute);
                String selectedTimeString = timeFormatter.format(selectedTime.getTime());
                time.setText(selectedTimeString);
            }
        }, hourOfDay, minute, true);

        // Show TimePickerDialog
        timePickerDialog.show();
    }


    private void saveData(){
        String dt = String.valueOf(this.date.getText()).trim();

        String tm = String.valueOf(time.getText()).trim();
        String sys = String.valueOf(systolic.getText()).trim();

        String dys = String.valueOf(diastolic.getText()).trim();
        String hrt = String.valueOf(heart.getText()).trim();
        String cmt = String.valueOf(comment.getText()).trim();

        if(dt.isEmpty() || tm.isEmpty() || sys.isEmpty() || dys.isEmpty() || hrt.isEmpty() || cmt.isEmpty()){
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int sysValue = Integer.parseInt(sys);
        int dysValue = Integer.parseInt(dys);
        int hrtValue = Integer.parseInt(hrt);

        if (sysValue < 0 || dysValue < 0 || hrtValue < 0) {
            Toast.makeText(this, "Values cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();

        if(uid == null){
            Toast.makeText(Insert.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        EachData data = new EachData(dt,tm,sys,dys,hrt,cmt);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data").child(uid);

        String key = null;
        if(passedData == null){
            key = ref.push().getKey();
        }
        else{
            key = passedData.getKey();
        }

        data.setKey(key);

        ref.child(key).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Insert.this, "Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Insert.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
