package boundservices.santhoshthepro.com.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import boundservices.santhoshthepro.com.boundservicedemo.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private Button btnTimeCheck;
    private TextView txtTime;
    MyService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTimeCheck=(Button)findViewById(R.id.btnTimeCheck);
        txtTime=(TextView)findViewById(R.id.txtTime);

        Intent myIntent = new Intent(MainActivity.this,MyService.class);
        bindService(myIntent,myServiceConnection, Context.BIND_AUTO_CREATE);

        btnTimeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTime = myService.getCurrentTime();
                txtTime.setText(currentTime);
            }
        });
    }

    private ServiceConnection myServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder binder = (MyBinder)service;
            myService=binder.getService();
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };
}
