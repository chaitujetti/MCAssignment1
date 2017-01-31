package com.example.chaitanya.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] hlabels= new String[10];
        for (int i = 0; i < 10; i++) {
            hlabels[i]=String.valueOf(i * 18);
        }

        int k = 0;

        String[] vlabels= new String[10];
        for (int i = 0; i < 10; i++){
            vlabels[i]=String.valueOf((i * 10) + k);
        }

        final float[] values= new float[10];
        final GraphView g = new GraphView(MainActivity.this,values,"Main Graph",hlabels,vlabels,GraphView.LINE);
        LinearLayout graph= (LinearLayout)findViewById(R.id.graphll);
        graph.addView(g);

        Thread movingGraph = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Button buttonRun= (Button)findViewById(R.id.buttonRun);
        buttonRun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int k = 0;
                while(k < 10) {
                    for (int i = 0; i < 10; i++) {
                          values[i] = (float) Math.random();
                        values[i] = (float) Math.ceil(Math.random() * 180);
                    }
                    g.invalidate();
                    g.setValues(values);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
            }
        });

        Button buttonStop= (Button)findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread.currentThread().interrupt();
                for (int i = 0; i < 10; i++) {
                    values[i] = 0;
                }
                g.invalidate();
                g.setValues(values);
            }
        });


    }
}
