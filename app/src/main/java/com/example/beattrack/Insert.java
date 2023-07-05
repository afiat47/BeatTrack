package com.example.beattrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Insert extends AppCompatActivity {
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
        date=findViewById(R.id.Date);
        time=findViewById(R.id.Time);
        comment=findViewById(R.id.comment);

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
