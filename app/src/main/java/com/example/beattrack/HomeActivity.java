package com.example.beattrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RvAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        adapter = new RvAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.rvList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton buttonAdd = findViewById(R.id.fabAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,Insert.class));
            }
        });

        downloadData();
    }

    private void downloadData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) return;
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(!snapshot.exists()){
//                    Toast.makeText(HomeActivity.this, "No data found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                List<EachData> allData = new ArrayList<>();

                for(DataSnapshot ds : snapshot.getChildren()){
                    try{
                        EachData data = ds.getValue(EachData.class);
                        allData.add(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                adapter.replaceData(allData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}