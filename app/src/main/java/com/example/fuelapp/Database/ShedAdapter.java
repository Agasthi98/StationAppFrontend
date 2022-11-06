package com.example.fuelapp.Database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelapp.R;
import com.example.fuelapp.ShedView;
import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.domain.ShedListModel;
import com.example.fuelapp.domain.ShedViewModal;

import java.util.List;

public class ShedAdapter extends ArrayAdapter<FuelModel> {
    private Context context;

    public static String SHED_PHONE;

    public ShedAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<FuelModel> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ShedAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<FuelModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.fuels = objects;
    }

    /*
    fuel model list view
     */
    private List<FuelModel> fuels;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shed_list_view, parent, false);

        TextView txtshedNameUser = rowView.findViewById(R.id.txtShedNAme1);
        TextView txtLocationUser = rowView.findViewById(R.id.txtLocationUser);

        System.out.println(fuels.get(position).getStationName());
        txtshedNameUser.setText((String.format("Shed Name: %s", fuels.get(position).getStationName())));
        txtLocationUser.setText(String.format("Shed Location: %s", fuels.get(position).getStationLocation()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShedView.class);
                SHED_PHONE =  String.valueOf(fuels.get(position).getStationNumber());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}