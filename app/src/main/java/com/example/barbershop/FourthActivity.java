package com.example.barbershop;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.barbershop.MainActivity.myDB;

public class FourthActivity extends AppCompatActivity {

    private String[] monthNames = {"ינואר","פברואר","מרץ","אפריל","מאי","יוני","יולי","אוגוסט","ספטמבר","אוקטובר","נובמבר","דצמבר"};
    private ArrayList<Client> monthsClients;
    private SimpleDateFormat dateFormat;
    TextView priceTV;
    TextView netoTV;
    TextView header;
    ListView monthlyClientsLV;
    private int monthsValue;
    private double neto;
    Calendar today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        header = (TextView) findViewById(R.id.textView14);
        priceTV = (TextView)findViewById(R.id.monthPaymentTextView);
        netoTV = (TextView) findViewById(R.id.monthPaymentNetoTextView);
        monthlyClientsLV = (ListView) findViewById(R.id.monthlyClientsListView);
        monthsClients = new ArrayList<Client>();
        Cursor res = myDB.getAllData();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        monthsValue = 0;
        neto = 0;
        today = Calendar.getInstance();
        Log.d("TODAY", String.valueOf(today.get(Calendar.MONTH)));
        header.setText(monthNames[today.get(Calendar.MONTH)] + " " + today.get(Calendar.YEAR));
        while (res.moveToNext()){
            Calendar dateFromDB = Calendar.getInstance();
            try {
                dateFromDB.setTime(dateFormat.parse(res.getString(4)));
                if((dateFromDB.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                        (dateFromDB.get(Calendar.YEAR) == today.get(Calendar.YEAR))){
                    Client client = new Client();
                    client.setIndex(res.getInt(0));
                    client.setName(res.getString(1));
                    client.setPhone(res.getString(2));
                    client.setDogName(res.getString(3));
                    client.setDate(dateFromDB.get(Calendar.DAY_OF_MONTH), dateFromDB.get(Calendar.MONTH),
                            dateFromDB.get(Calendar.YEAR),dateFromDB.get(Calendar.HOUR), dateFromDB.get(Calendar.MINUTE));
                    client.setPayment(res.getInt(5));
                    client.setDogStatus(res.getString(6));
                    monthsClients.add(client);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(monthsClients.size() == 0) {
            showMessage("אזהרה", "לא קיימים לקוחות בחודש זה");
            priceTV.setText("0");
            netoTV.setText("0");
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1, monthsClients);
            monthlyClientsLV.setAdapter(adapter);
        }
        else {
            for (Client c:monthsClients){
                    monthsValue += c.getPayment();
            }
            priceTV.setText(String.valueOf(monthsValue));
            neto = (monthsValue*0.6)*0.83;
            netoTV.setText(String.format("%.2f", neto));
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1 ,monthsClients);
            monthlyClientsLV.setAdapter(adapter);
        }
    }

    public void backBtnClick(View view){
        monthsValue = 0;
        neto = 0;
        if(today.get(Calendar.MONTH) == 0){
            today.set(Calendar.YEAR, today.get(Calendar.YEAR) - 1);
            today.set(Calendar.MONTH, 11);
        }
        else {
            today.set(Calendar.MONTH, today.get(Calendar.MONTH) - 1);
        }
        header.setText(monthNames[today.get(Calendar.MONTH)] + " " + today.get(Calendar.YEAR));
        monthsClients.clear();
        Cursor res = myDB.getAllData();
        while (res.moveToNext()){
            Calendar dateFromDB = Calendar.getInstance();
            try {
                dateFromDB.setTime(dateFormat.parse(res.getString(4)));
                if((dateFromDB.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                        (dateFromDB.get(Calendar.YEAR) == today.get(Calendar.YEAR))){
                    Client client = new Client();
                    client.setIndex(res.getInt(0));
                    client.setName(res.getString(1));
                    client.setPhone(res.getString(2));
                    client.setDogName(res.getString(3));
                    client.setDate(dateFromDB.get(Calendar.DAY_OF_MONTH), dateFromDB.get(Calendar.MONTH),
                            dateFromDB.get(Calendar.YEAR),dateFromDB.get(Calendar.HOUR), dateFromDB.get(Calendar.MINUTE));
                    client.setPayment(res.getInt(5));
                    client.setDogStatus(res.getString(6));
                    monthsClients.add(client);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(monthsClients.size() == 0) {
            showMessage("אזהרה", "לא קיימים לקוחות בחודש זה");
            priceTV.setText("0");
            netoTV.setText("0");
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1, monthsClients);
            monthlyClientsLV.setAdapter(adapter);
        }
        else {
            for (Client c:monthsClients){
                    monthsValue += c.getPayment();
            }
            priceTV.setText(String.valueOf(monthsValue));
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1 ,monthsClients);
            neto = (monthsValue*0.6)*0.83;
            netoTV.setText(String.format("%.2f",neto));
            monthlyClientsLV.setAdapter(adapter);
        }
    }

    public void nextBtnClick(View view){
        monthsValue = 0;
        neto = 0;
        if(today.get(Calendar.MONTH) == 11){
            today.set(Calendar.YEAR, today.get(Calendar.YEAR)+1);
            today.set(Calendar.MONTH, 0);
        }
        else {
            today.set(Calendar.MONTH, today.get(Calendar.MONTH) + 1);
        }
        header.setText(monthNames[today.get(Calendar.MONTH)] + " " + today.get(Calendar.YEAR));
        monthsClients.clear();
        Cursor res = myDB.getAllData();
        while (res.moveToNext()){
            Calendar dateFromDB = Calendar.getInstance();
            try {
                dateFromDB.setTime(dateFormat.parse(res.getString(4)));
                if((dateFromDB.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                        (dateFromDB.get(Calendar.YEAR) == today.get(Calendar.YEAR))){
                    Client client = new Client();
                    client.setIndex(res.getInt(0));
                    client.setName(res.getString(1));
                    client.setPhone(res.getString(2));
                    client.setDogName(res.getString(3));
                    client.setDate(dateFromDB.get(Calendar.DAY_OF_MONTH), dateFromDB.get(Calendar.MONTH),
                            dateFromDB.get(Calendar.YEAR),dateFromDB.get(Calendar.HOUR), dateFromDB.get(Calendar.MINUTE));
                    client.setPayment(res.getInt(5));
                    client.setDogStatus(res.getString(6));
                    monthsClients.add(client);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(monthsClients.size() == 0) {
            showMessage("אזהרה", "לא קיימים לקוחות בחודש זה");
            priceTV.setText("0");
            netoTV.setText("0");
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1, monthsClients);
            monthlyClientsLV.setAdapter(adapter);
        }
        else {
            for (Client c:monthsClients){
                monthsValue += c.getPayment();
            }
            priceTV.setText(String.valueOf(monthsValue));
            ClientAdapter adapter = new ClientAdapter(this, R.layout.list_row_1 ,monthsClients);
            neto = (monthsValue*0.6)*0.83;
            netoTV.setText(String.format("%.2f",neto));
            monthlyClientsLV.setAdapter(adapter);
        }
    }

    public void showMessage(String title, String msg)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
