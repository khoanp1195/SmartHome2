package com.example.firebasetest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import pl.pawelkleczkowski.customgauge.CustomGauge;


public class hienthi3 extends AppCompatActivity {
    /*

        DatabaseReference myRef = database.getReference("devices");
        DatabaseReference FirebaseLight1 = myRef.child("light1");
     */


    private ThingSpeakChannel tsChannel;
    private ThingSpeakLineChart tsChart;
    private LineChartView chartView;

    private PieView pieView_hum, pieView_fire;
    private CustomGauge customGauge;
    private TextView textView,text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        tsChannel = new ThingSpeakChannel(1524224);

        //  imageView1 = (ImageView) findViewById(R.id.img1);
        customGauge = findViewById(R.id.gauge2);
        textView = findViewById(R.id.text1);
        pieView_hum = findViewById(R.id.pieView_hum);
        pieView_fire = findViewById(R.id.pieView_fire);
        text1 = findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hienthi3.this, admin.class);
                startActivity(intent);
            }
        });

        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {// Notify last update time of the Channel feed through a Toast message
                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
                //  Toast.makeText(chartt.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();
            }
        });

        tsChannel.loadChannelFeed();

        Firebase.setAndroidContext(Objects.requireNonNull(pieView_hum.getContext()));
        Firebase mRef3 = new Firebase("https://esp8266project-8ea11-default-rtdb.firebaseio.com/RealtimeData/Humidity");
        Firebase mRef4 = new Firebase("https://esp8266project-8ea11-default-rtdb.firebaseio.com/RealtimeData/Humidity");
        final Firebase mRef1 = new Firebase("https://esp8266project-8ea11-default-rtdb.firebaseio.com/RealtimeData/Temperature");

        pieView_fire.setPercentageBackgroundColor(getResources().getColor(R.color.bluehigh));
        pieView_fire.setMainBackgroundColor(getResources().getColor(R.color.white));



        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textView.setText(value + "°C");
                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
                customGauge.setValue(i * 10);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);

                float i = Float.parseFloat(value);
                pieView_hum.setPercentage(i);
                pieView_hum.setPieInnerPadding(30);
                pieView_hum.setPercentageTextSize(20);
                PieAngleAnimation animation = new PieAngleAnimation(pieView_hum);
                animation.setDuration(2000); //This is the duration of the animation in millis
                pieView_hum.startAnimation(animation);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                float i = Float.parseFloat(value);
                pieView_fire.setPieInnerPadding(30);
                pieView_fire.setPercentageTextSize(20);
                pieView_fire.setPercentage(i);
                PieAngleAnimation animation = new PieAngleAnimation(pieView_fire);
                animation.setDuration(2000); //This is the duration of the animation in millis
                pieView_fire.startAnimation(animation);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

/*

/*-----------------------------------CHART----------------------------------------------*/
/*             // Connect to ThinkSpeak Channel
        tsChannel = new ThingSpeakChannel( 1518153);
        // Set listener for Channel feed update events
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                // Show Channel ID and name on the Action Bar
                getSupportActionBar().setTitle(channelName);
                getSupportActionBar().setSubtitle("Channel " + channelId);
                // Notify last update time of the Channel feed through a Toast message
                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
                Toast.makeText(hienthi3.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();
            }
        });
        // Fetch the specific Channel feed
        tsChannel.loadChannelFeed();

        // Create a Calendar object dated 5 minutes ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);

        // Configure LineChartView
        chartView = findViewById(R.id.chart);
        chartView.setZoomEnabled(false);
        chartView.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart = new ThingSpeakLineChart(9, 1);
        // Get 200 entries at maximum
        tsChart.setNumberOfEntries(200);
        // Set value axis labels on 10-unit interval
        tsChart.setValueAxisLabelInterval(10);
        // Set date axis labels on 5-minute interval
        tsChart.setDateAxisLabelInterval(1);
        // Show the line as a cubic spline
        tsChart.useSpline(true);
        // Set the line color
        tsChart.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChart.setChartStartDate(calendar.getTime());

        // Set listener for chart data update
        tsChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously
        tsChart.loadChartData();
 */       // Create a Calendar object dated 5 minutes ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);
        chartView = findViewById(R.id.chart);
        chartView.setZoomEnabled(true);
        chartView.setValueSelectionEnabled(true);
        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart = new ThingSpeakLineChart(1524224, 1);
        // Get entries at maximum
        tsChart.setNumberOfEntries(50);
        // Set value axis labels on 10-unit interval
        tsChart.setValueAxisLabelInterval(2);
        // Set date axis labels on 5-minute interval
        tsChart.setDateAxisLabelInterval(1);
        // Show the line as a cubic spline
        tsChart.useSpline(true);
        // Set the line color
        tsChart.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChart.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView.setCurrentViewport(initialViewport);
           /* LineChartData data = new LineChartData();
            float data1=data.getBaseValue();
            TextView tvName = (TextView)findViewById(R.id.textView);
            tvName.setText((int) data1);*/

            }
        });
        // Load chart data asynchronously
        tsChart.loadChartData();
    }


    }


