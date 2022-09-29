package com.example.pgtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtmonthyear,edtthings,edtprice,edttotal;
    Button btnaddadata,btnupdatedata,btndeletedata,btnviewdata;
    DataBaseHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtmonthyear=findViewById(R.id.edtMonthYear);
        edtthings=findViewById(R.id.edtThings);
        edtprice=findViewById(R.id.edtPrice);
        edttotal=findViewById(R.id.edtTotal);
        btnaddadata=findViewById(R.id.btnadddata);
        btnupdatedata=findViewById(R.id.btnupdatedata);
        btndeletedata=findViewById(R.id.btndeletedata);
        btnviewdata=findViewById(R.id.btnviewdata);
        DB=new DataBaseHandler(this);
        btnaddadata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthyeartext = edtmonthyear.getText().toString();
                String things = edtthings.getText().toString();
                String price= edtprice.getText().toString();
                String total = edttotal.getText().toString();
                Boolean checkinsertdata = DB.insertPGTrackerdetails(monthyeartext,things,price,total);
                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this,"New Entry is done",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New Entry is not  done",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnupdatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthyeartext = edtmonthyear.getText().toString();
                String things = edtthings.getText().toString();
                String price= edtprice.getText().toString();
                String total = edttotal.getText().toString();
                Boolean checkupdatedata = DB.insertPGTrackerdetails(monthyeartext,things,price,total);
                if(checkupdatedata==true)
                {
                    Toast.makeText(MainActivity.this,"Entry is Updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Entry is not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndeletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthyeartext = edtmonthyear.getText().toString();
                Boolean checkdeletedata = DB.deletePGTrackerdetails(monthyeartext);
                if(checkdeletedata==true)
                {
                    Toast.makeText(MainActivity.this,"Entry is Deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Entry is not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnviewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getPGTrackerdetails();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name:"+res.getString(0)+ "\n");
                    buffer.append("Things:"+res.getString(1)+ "\n");
                    buffer.append("Price:"+res.getString(2)+ "\n");
                    buffer.append("Total:"+res.getString(3)+ "\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}