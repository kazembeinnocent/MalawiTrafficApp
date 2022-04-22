package com.example.trafficmi.AdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficmi.Model.AccidentSceneModel;
import com.example.trafficmi.R;
import com.example.trafficmi.Views.ViewVehicleTheftDetails;

import java.io.Serializable;
import java.util.ArrayList;

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.ViewHolder> {
    ArrayList<AccidentSceneModel> accidentSceneModels;
    Context context;

    public AccidentAdapter(Context context, ArrayList<AccidentSceneModel> accidentSceneModels ) {
        this.accidentSceneModels = accidentSceneModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_theft_cases_layout_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccidentSceneModel accidentSceneModel = accidentSceneModels.get(position);

        holder.name.setText(accidentSceneModel.getName().toString());
        holder.regnum.setText(accidentSceneModel.getRegNum().toString());
        holder.color.setText(accidentSceneModel.getColor().toString());
//        holder.otherDetails2.setText(accidentSceneModel.getOtherDetails().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewVehicleTheftDetails.class);
                intent.putExtra("carRegnum", accidentSceneModel.getRegNum().toString());
                intent.putExtra("nameOfCar", accidentSceneModel.getName().toString());
                intent.putExtra("colorOfCar", accidentSceneModel.getColor().toString());
                intent.putExtra("yearOfMake", accidentSceneModel.getYearOfMake().toString());
                intent.putExtra("otherDetails", accidentSceneModel.getOtherDetails().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return accidentSceneModels.size();
    }

    public void filterList(ArrayList<AccidentSceneModel> models){
        accidentSceneModels = models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView regnum,color,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            regnum = itemView.findViewById(R.id.car_color_id);
            color = itemView.findViewById(R.id.car_reg_num_id);
            name = itemView.findViewById(R.id.car_name_id);
//            otherDetails2 = itemView.findViewById(R.id.otherDetails2);
        }
    }
}
