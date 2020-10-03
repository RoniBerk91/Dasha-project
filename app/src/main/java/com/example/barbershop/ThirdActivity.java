package com.example.barbershop;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.barbershop.MainActivity.myDB;

public class ThirdActivity extends AppCompatActivity {

    EditText searchText;
    ListView lv;
    ArrayList<Client> foundClients;
    private ClientAdapter clientAdapter;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        lv = (ListView) findViewById(R.id.clientsListView);
        searchText = (EditText) findViewById(R.id.searchEditText);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        foundClients = new ArrayList<Client>();
    }

    public void searchClicked(View view){
        foundClients.clear();
        String num = searchText.getText().toString();
        Cursor res = myDB.findByPhone(num);
        if(res.getCount() > 0){
            while (res.moveToNext()){
                Client client = new Client();
                try {
                    Calendar dateFromDB = Calendar.getInstance();
                    dateFromDB.setTime(dateFormat.parse(res.getString(4)));
                    client.setIndex(res.getInt(0));
                    client.setName(res.getString(1));
                    client.setPhone(res.getString(2));
                    client.setDogName(res.getString(3));
                    client.setDate(dateFromDB.get(Calendar.DAY_OF_MONTH),
                            dateFromDB.get(Calendar.MONTH), dateFromDB.get(Calendar.YEAR),
                            dateFromDB.get(Calendar.HOUR), dateFromDB.get(Calendar.MINUTE));
                    client.setPayment(res.getInt(5));
                    client.setDogStatus(res.getString(6));
                    foundClients.add(client);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            lv.setVisibility(View.VISIBLE);
            clientAdapter = new ClientAdapter(this, R.layout.list_row_1 ,foundClients);
            lv.setAdapter(clientAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "לא קיים לקוח בעל מספר זה", Toast.LENGTH_LONG).show();
        }
    }
}







