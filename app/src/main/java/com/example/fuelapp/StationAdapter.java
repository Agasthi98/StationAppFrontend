package com.example.fuelapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.fuelapp.domain.Station;

import java.util.List;

public class StationAdapter extends ArrayAdapter<Station> {
    private Context context;

    public StationAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Station> objects){
        super(context, resource, textViewResourceId, objects);
    }

    private List<Station> stations;

    public StationAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Station> objects){
        super(context, resource, objects);
        this.context = context;
        this.stations = objects;
    }

}
