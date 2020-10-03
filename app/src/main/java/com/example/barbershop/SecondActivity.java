package com.example.barbershop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.barbershop.MainActivity.myDB;

public class SecondActivity extends AppCompatActivity {

    TextView tvDate;
    EditText clientName;
    EditText clientPhone;
    EditText dogName;
    EditText price;
    EditText dogState;
    SimpleDateFormat dateFormat;
    RelativeLayout smallRelative;
    Button cancelBtn;
    Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvDate = (TextView) findViewById(R.id.textView3);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvDate.setText(dateFormat.format(Calendar.getInstance().getTime()));
        clientName = (EditText) findViewById(R.id.editText);
        clientPhone = (EditText) findViewById(R.id.editText1);
        dogName = (EditText) findViewById(R.id.editText2);
        price = (EditText) findViewById(R.id.editText3);
        dogState = (EditText) findViewById(R.id.editText4);
        smallRelative = (RelativeLayout)findViewById(R.id.smallRelative);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
    }

    public void addBtnClick(View view){
        if(clientName.getText().toString().equals("")){
            showMessage("אזהרה", "לא הוזן שם לקוח");
            return;
        }
        if(clientPhone.getText().toString().equals("")){
            showMessage("אזהרה", "לא הוזן מספר טלפון");
            return;
        }
        if(dogName.getText().toString().equals("")){
            showMessage("אזהרה", "לא הוזן שם כלב");
            return;
        }
        if(price.getText().toString().equals("")){
            showMessage("אזהרה", "לא הוזן מחיר");
            return;
        }
        TextView name = (TextView) findViewById(R.id.nameRelative);
        TextView phone = (TextView) findViewById(R.id.phoneRelative);
        TextView dog = (TextView) findViewById(R.id.dogRelative);
        TextView price2 = (TextView) findViewById(R.id.priceRelative);
        TextView status = (TextView) findViewById(R.id.statusRelative);
        name.setText(clientName.getText().toString());
        phone.setText(clientPhone.getText().toString());
        dog.setText(dogName.getText().toString());
        price2.setText(price.getText().toString());
        status.setText(dogState.getText().toString());
        smallRelative.setVisibility(View.VISIBLE);
        okBtn.setClickable(true);
        cancelBtn.setClickable(true);
    }

    public void okBtnClick(View view){
        Client client = new Client();
        client.setName(clientName.getText().toString());
        client.setPhone(clientPhone.getText().toString());
        client.setDogName(dogName.getText().toString());
        client.setPayment(Integer.parseInt(price.getText().toString()));
        client.setDogStatus(dogState.getText().toString());
        myDB.insertNewClient(client);
        showMessage("הידד", "הלקוח נוסף בהצלחה למאגר הנתונים");
        clientName.setText("");
        clientPhone.setText("");
        dogName.setText("");
        price.setText("");
        dogState.setText("");
        smallRelative.setVisibility(View.INVISIBLE);
        okBtn.setClickable(false);
        cancelBtn.setClickable(false);
    }

    public void cancelBtnClick(View view){
        showMessage("הידד", "הלקוח לא הוכנס למאגר הנתונים");
        clientName.setText("");
        clientPhone.setText("");
        dogName.setText("");
        price.setText("");
        dogState.setText("");
        smallRelative.setVisibility(View.INVISIBLE);
        okBtn.setClickable(false);
        cancelBtn.setClickable(false);
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










