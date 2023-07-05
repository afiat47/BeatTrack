package com.example.beattrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<EachData> allData = new ArrayList<>();
    private final Context mContext;

    public RvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceData(List<EachData> allData){
        this.allData = allData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(allData.get(position).getSystolic());
        holder.tvTime.setText(allData.get(position).getSystolic());
        holder.tvS.setText(allData.get(position).getSystolic());
        holder.tvD.setText(allData.get(position).getSystolic());
        holder.tvH.setText(allData.get(position).getSystolic());
        holder.tvC.setText(allData.get(position).getSystolic());;
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Insert.class);
                intent.putExtra("data",allData.get(holder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String uid = auth.getUid();
                    FirebaseDatabase.getInstance().getReference().child("data").child(uid)
                            .child(allData.get(holder.getAdapterPosition()).getKey()).removeValue();
                }catch (Exception ignored){}
            }
        });
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvDate,tvTime,tvS, tvD, tvH, tvC;
        private final Button buttonEdit,buttonDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.Date);
            tvTime = itemView.findViewById(R.id.Time);
            tvS = itemView.findViewById(R.id.systolic);
            tvD = itemView.findViewById(R.id.dia);
            tvH = itemView.findViewById(R.id.Heart);
            tvC = itemView.findViewById(R.id.comment);
            buttonEdit = itemView.findViewById(R.id.edit);
            buttonDelete = itemView.findViewById(R.id.delete);
        }
    }

}

