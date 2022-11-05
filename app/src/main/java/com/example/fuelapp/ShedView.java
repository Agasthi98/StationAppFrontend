package com.example.fuelapp;

import static com.example.fuelapp.Database.ShedAdapter.SHED_PHONE;
import static com.example.fuelapp.LoginActivity.phoneNUMBER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.domain.QueueModel;
import com.example.fuelapp.service.RetrofitClient;
import com.example.fuelapp.service.StationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShedView extends AppCompatActivity {
    private static final String TAG = "ShedPatrol";
    TextView shedName,shedPhoneNo,fuelQueue,shedQueueSize;
    Button addQueue,fuelingComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_view);

        shedName = (TextView) findViewById(R.id.txtShedName);
        shedPhoneNo = (TextView) findViewById(R.id.txtShedPhoneNo);
        fuelQueue = (TextView) findViewById(R.id.txtShedQueue);
        addQueue = (Button) findViewById(R.id.btnAddQueue);
        shedQueueSize = (TextView) findViewById(R.id.txtShedQueueSize);
        fuelingComplete = (Button) findViewById(R.id.btnFuelComplete);


        System.out.println(SHED_PHONE);
        displayQueue();
        displayPetrol();


        fuelingComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        addQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pQueue = shedPhoneNo.getText().toString();

                addQueue(pQueue);
            }
        });
    }

    public void displayQueue(){
        StationService methods1 = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<QueueModel> call1= methods1.getQueue(SHED_PHONE);

          /*
               If the process success, then receive onResponse:code::200
               Assign retrieved values to the Text Views
            */
        call1.enqueue(new Callback<QueueModel>() {
            @Override
            public void onResponse(Call<QueueModel> call, Response<QueueModel> response) {
                Log.e(TAG, "onResponse:code Time:"+ response.code());
                Log.e(TAG, "onResponse:Body Time:"+ response.body());
                QueueModel queue = response.body();

                //If retrieved queue size is null then assign queue values as 0
                if(queue ==null){
                    shedQueueSize.setText("0");
                }
                else{
                    //If retrieved queue size is null then assign queue values as 0
                    if(queue.getQueue() ==null){
                        shedQueueSize.setText("0");
                    }
                    //Assign retrieved queue size to the textview
                    else{
                        System.out.println("agasthi:"+queue.getQueue());
                        shedQueueSize.setText(queue.getQueue());
                        //  int in2 = new Integer(queue.getQueue());
//                        int in = Integer.valueOf(queue.getQueue());
                        int estimatedTimeInt = Integer.parseInt(queue.getQueue().trim());
                        int time= estimatedTimeInt*5;
                        String Est = String.valueOf(time) +" "+"min";
//                        shedQueueSize.setText(queue.getQueue());
                    }
                }
            }
            /*
                If the process Unsuccessful, then receive onResponse:code::400
                Assign queue value as 0
            */
            @Override
            public void onFailure(Call<QueueModel> call, Throwable t) {
                Log.e(TAG, "onFailure Time:"+ t.getMessage());
                shedQueueSize.setText("0");
            }
        });
    }

    public void displayPetrol(){

        TextView pQueue =  findViewById(R.id.txtShedName);
        TextView SLocation = findViewById(R.id.txtShedPhoneNo);
        TextView pdisplayValue =  findViewById(R.id.txtShedQueue);

        System.out.println(SHED_PHONE);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getPetrol(SHED_PHONE);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    FuelModel Fmodel = response.body();
                    pQueue.setText((String.format("Shed Name: %s", Fmodel.getStationName())));
                    SLocation.setText((String.format(Fmodel.getStationNumber())));
                    pdisplayValue.setText((String.format("Queue length: %s", Fmodel.getQueueLength())));
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void addQueue(String shed){
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<QueueModel> call = stationService.addToQueue("Petrol",shedQueueSize.getText().toString(),shed);
        call.enqueue(new Callback<QueueModel>() {
            @Override
            public void onResponse(Call<QueueModel> call, Response<QueueModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ShedView.this, "Queue Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<QueueModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}