package com.example.barbershop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.barbershop.MainActivity.myDB;

public class ClientAdapter extends ArrayAdapter {

    private final Context myContext;
    private final List<Client> clientList;
    private final int mResource;
    private Button delBtn;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public ClientAdapter(Context context, int resource , ArrayList<Client> list)
    {
        super(context,resource,list);
        myContext = context;
        clientList = list;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(myContext).inflate(R.layout.list_row_1,parent,false);
        final Client client = clientList.get(position);
        TextView name = (TextView) listItem.findViewById(R.id.clientNameTextView);
        name.setText(client.getName());
        TextView phone = (TextView) listItem.findViewById(R.id.clientPhoneTextView);
        phone.setText(client.getPhoneNumber());
        TextView dog = (TextView) listItem.findViewById(R.id.dogNameTextView);
        dog.setText(client.getDogName());
        TextView today = (TextView) listItem.findViewById(R.id.todayTextView);
        today.setText(dateFormat.format(client.getDate().getTime()));
        TextView price = (TextView) listItem.findViewById(R.id.priceTextView);
        price.setText(String.valueOf(client.getPayment()));
        TextView status = (TextView) listItem.findViewById(R.id.dogStatusTextView);
        status.setText(client.getDogStatus());
        delBtn = (Button) listItem.findViewById(R.id.deleteBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteClient(client);
                clientList.remove(client);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "הלקוח הוסר מרשימת הלקוחות", Toast.LENGTH_SHORT).show();
            }
        });
        return listItem;
    }
}
