package com.example.trafficmi.AdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficmi.Model.ModelClass;
import com.example.trafficmi.R;
import com.example.trafficmi.Views.VehicleTheftData;

import java.util.ArrayList;

public class VehicleTheftAdapter extends RecyclerView.Adapter<VehicleTheftAdapter.ViewHolder> {
    ArrayList<ModelClass>  arrayList;
    Context appContext;

    public VehicleTheftAdapter(Context appContext, ArrayList<ModelClass> arrayList) {
        this.arrayList = arrayList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(appContext).inflate(R.layout.display_theft_cases_layout_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass vehicleTheftReport = arrayList.get(position);
        holder.car_color.setText(vehicleTheftReport.getCarNameTheft().toString());
        holder.car_reg.setText(vehicleTheftReport.getCarOwnerTheft().toString());
        holder.carname.setText(vehicleTheftReport.getSexTheft().toString());

        //Handling events to holder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appContext, VehicleTheftData.class);
                intent.putExtra("carNameTheft", vehicleTheftReport.getCarNameTheft().toString());
                intent.putExtra("carOwnerTheft", vehicleTheftReport.getCarOwnerTheft().toString());
                intent.putExtra("sexOfOwner", vehicleTheftReport.getSexTheft().toString());
                intent.putExtra(" carRegNumTheft", vehicleTheftReport.getCarRegNumTheft().toString());
                intent.putExtra("carYearOfMakeTheft", vehicleTheftReport.getCarYearOfMakeTheft().toString());
                intent.putExtra("colorOfCarTheft", vehicleTheftReport.getColorOfCarTheft().toString());
                intent.putExtra("locationTheft", vehicleTheftReport.getLocationTheft().toString());
                intent.putExtra(" detailsOfTheft", vehicleTheftReport.getDetailsOfTheft().toString());
                appContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filteredList(ArrayList<ModelClass> models){
        arrayList= models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView carname;
        TextView car_reg;
        TextView car_color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carname = itemView.findViewById(R.id.car_name_id);
            car_reg = itemView.findViewById(R.id.car_reg_num_id);
            car_color = itemView.findViewById(R.id.car_color_id);
        }
    }
}
