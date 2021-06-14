package com.example.todoapp;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ViewAllUsers extends AppCompatActivity {

    Button btn_goBack;
    Button btn_showAll;
    ListView lv_userList;
    DataBaseOperations dataBaseOperations;

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        btn_goBack = findViewById(R.id.btn_goBack);
        btn_showAll = findViewById(R.id.btn_showAll);
        lv_userList = findViewById(R.id.lv_userList);
        dataBaseOperations = new DataBaseOperations(ViewAllUsers.this);

        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAllUsers.this, MainActivity.class));
            }
        });

        lv_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedUser = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseOperations.deleteOne(clickedUser);
                //ShowCustomerOnListView(dataBaseOperations);
                int clickedUserId = clickedUser.getId();

              //  Intent intent = new Intent(ViewAllUsers.this, EditOrDelete.class);

              //   Bundle b = new Bundle();
              //  b.putString("Id", String.valueOf(clickedUserId));
              //  b.putString("Name", clickedUser.getName());
               // b.putString("Phone", clickedUser.getPhoneNumber());
              //  b.putString("Description", clickedUser.getDescription());
               // intent.putExtras(b);
               // startActivityForResult(intent, 200, b);

                Toast.makeText( ViewAllUsers.this, "Редиректва "+clickedUser.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        btn_showAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DataBaseOperations dbOperations = new DataBaseOperations(ViewAllUsers.this);
                 List<CustomerModel> everyUser = dbOperations.getEveryone();

                // Intent intent = new Intent(MainActivity.this, ViewAllUsers.class);
                // startActivity(intent);

                 ArrayAdapter userArrayAdapter = new ArrayAdapter<CustomerModel>(ViewAllUsers.this, android.R.layout.simple_dropdown_item_1line, dataBaseOperations.getEveryone());
                 lv_userList.setAdapter(userArrayAdapter);
                Toast.makeText( ViewAllUsers.this, "Виж всички", Toast.LENGTH_SHORT).show();
            }
        });
    }
}