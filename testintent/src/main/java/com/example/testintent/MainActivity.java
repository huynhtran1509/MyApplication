package com.example.testintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("e");
                Bundle bundle=new Bundle();
                Bundle bundle2=new Bundle();

                bundle.putString("1","aaaa");
//                intent.putExtra("q"+String.valueOf(1), bundle);



                intent.putExtra("w"+String.valueOf(1), bundle);
                bundle.putString("2","bbbb");
                sendBroadcast(intent);
            }
        });
    }
}
