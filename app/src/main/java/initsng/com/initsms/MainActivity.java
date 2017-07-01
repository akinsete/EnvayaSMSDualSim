package initsng.com.initsms;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.SimInfo;

public class MainActivity extends AppCompatActivity {

    final int RC_PERMISSION_REQUEST = 1607;

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button)findViewById(R.id.btn_send);


        setOnClicklistener();

        requestPermissions();
    }

    private void setOnClicklistener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage("08026764936", null, "sms message", null, null);

                SimUtils.sendSMS(MainActivity.this,1,"08026764926",null,"This works fine",null,null);

                Toast.makeText(getApplicationContext(), "SMS Sent!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_PHONE_STATE
        }, RC_PERMISSION_REQUEST);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int grantedCount = 0;

    }



    public static List<SimInfo> getSIMInfo(Context context) {
        List<SimInfo> simInfoList = new ArrayList<>();
        Uri URI_TELEPHONY = Uri.parse("content://telephony/siminfo/");
        Cursor c = context.getContentResolver().query(URI_TELEPHONY, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex("_id"));
                int slot = c.getInt(c.getColumnIndex("slot"));
                String display_name = c.getString(c.getColumnIndex("display_name"));
                String icc_id = c.getString(c.getColumnIndex("icc_id"));
                SimInfo simInfo = new SimInfo(id, display_name, icc_id, slot);
                Log.d("apipas_sim_info", simInfo.toString());
                simInfoList.add(simInfo);
            } while (c.moveToNext());
        }
        c.close();

        return simInfoList;
    }

}
