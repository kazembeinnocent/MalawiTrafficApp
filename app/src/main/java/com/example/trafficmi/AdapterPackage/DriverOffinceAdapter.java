package com.example.trafficmi.AdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficmi.DriverOffenceData;
import com.example.trafficmi.Model.AccidentSceneModel;
import com.example.trafficmi.Model.DriversOffenceModel;
import com.example.trafficmi.R;
import com.example.trafficmi.Views.ViewVehicleTheftDetails;

import java.util.ArrayList;

public class DriverOffinceAdapter extends RecyclerView.Adapter<DriverOffinceAdapter.ViewHolder> {
    ArrayList<DriversOffenceModel> data;
    Context context;

    public DriverOffinceAdapter(Context context, ArrayList<DriversOffenceModel> data ) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public DriverOffinceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_theft_cases_layout_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverOffinceAdapter.ViewHolder holder, int position) {
        DriversOffenceModel driversOffenceModel= data.get(position);
        holder.disPayName.setText(driversOffenceModel.getDisPayName().toString());
        holder.lisenceNumber.setText(driversOffenceModel.getLisenceNumber().toString());

        //getting driver offence data to cardView

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DriverOffenceData.class);
                intent.putExtra("driverName", driversOffenceModel.getDisPayName().toString());
                intent.putExtra("driverSex", driversOffenceModel.getSelectedSex().toString());
                intent.putExtra("driverLicenseNumber", driversOffenceModel.getLisenceNumber().toString());
                intent.putExtra("offenceDescription", driversOffenceModel.getDriverOffenceDescription().toString());
                intent.putExtra("driverLatitude", driversOffenceModel.getLat().toString());
                intent.putExtra("driverLongitude", driversOffenceModel.getLongt().toString());
                intent.putExtra("latitude", driversOffenceModel.getLat().toString());
                intent.putExtra("longitude", driversOffenceModel.getLongt().toString());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void filterList(ArrayList<DriversOffenceModel> models){
        data = models;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView disPayName;
        TextView driverOffenceLocation;
        TextView lisenceNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            disPayName = itemView.findViewById(R.id.car_name_id);
            driverOffenceLocation = itemView.findViewById(R.id.car_reg_num_id);
            lisenceNumber = itemView.findViewById(R.id.car_color_id);
        }
    }
}
