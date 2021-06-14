package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //reference to buttons
    Button btn_add, btn_viewAll;
    EditText et_name, et_phone, et_description;
    Switch sw_activeCustomer;
    //ListView lv_userList;

    DataBaseOperations dataBaseOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_seeAll);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_description = findViewById(R.id.et_description);
        sw_activeCustomer = findViewById(R.id.sw_active);
       // lv_userList = findViewById(R.id.lv_userList);

       dataBaseOperations = new DataBaseOperations(MainActivity.this);
        //listeners

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CustomerModel customerModel;
                //Toast.makeText( MainActivity.this, "Добавяне", Toast.LENGTH_SHORT).show();

                try{
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), et_phone.getText().toString(), et_description.getText().toString(), sw_activeCustomer.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Възникна грешка", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "Грешка","404","Грешка", false);
                }

                DataBaseOperations dataBaseOperations = new DataBaseOperations(MainActivity.this);
                boolean success= dataBaseOperations.addUser(customerModel);
                Toast.makeText(MainActivity.this, "Success= "+success, Toast.LENGTH_SHORT).show();
            }

        });

        btn_viewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               // DataBaseOperations dbOperations = new DataBaseOperations(MainActivity.this);
               // List<CustomerModel> everyUser = dbOperations.getEveryone();

                Intent intent = new Intent(MainActivity.this, ViewAllUsers.class);
                startActivity(intent);

               // ArrayAdapter userArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, dataBaseOperations.getEveryone());
               // lv_userList.setAdapter(userArrayAdapter);
                //Toast.makeText( MainActivity.this, "Виж всички", Toast.LENGTH_SHORT).show();
            }
        });



    }

}