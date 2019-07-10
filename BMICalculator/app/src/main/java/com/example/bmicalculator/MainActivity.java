package com.example.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] heights = {"145","146","147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167","168","169", "170","171","172","173","174","175","176","177","178","179", "180","181","182","183","184","185"};
    float height = 0;
    float weight = 0;
    float bmi;

    AlertDialog.Builder builder;

    TextView currentWeight;

    Spinner spinner;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentWeight = (TextView) findViewById(R.id.textView3);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);


        seekBar = (SeekBar) findViewById(R.id.seekBar);

        builder = new AlertDialog.Builder(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,heights);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                weight = progress;
                currentWeight.setText(String.valueOf(weight));
                //  Toast.makeText(getApplicationContext(),"Weight:"+String.valueOf(weight),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        height = Integer.parseInt(heights[pos]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select Height from Spinner", Toast.LENGTH_SHORT).show();
    }

    public void calculate(View view){

        float th = height;
        th = th/100;
        bmi = weight/(th*th);
        Toast.makeText(this,"Your BMI: "+bmi,Toast.LENGTH_SHORT).show();

        System.out.println("YOUR HEIGHT:"+String.valueOf(height));
        System.out.println("YOUR WEIGHT:"+String.valueOf(weight));
        System.out.println("YOUR BMI:"+String.valueOf(bmi));

        if (bmi < 16) {
            builder.setMessage("You are too lean") .setTitle("REPORT");
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (bmi > 25){
            builder.setMessage("You are too obese") .setTitle("REPORT");
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
