package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditOrDelete extends AppCompatActivity {

    Button btn_edit, btn_delete;
    EditText edit_name, edit_phone, edit_description;
    DataBaseOperations dataBaseOperations;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete);

        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_description = findViewById(R.id.edit_description);
        dataBaseOperations = new DataBaseOperations(EditOrDelete.this);
        //listeners
        Bundle b = getIntent().getExtras();
        if(b!=null){
            Id = b.getString("Id");
            edit_name.setText(b.getString("Name"));
            edit_phone.setText(b.getString("Phone"));
            edit_description.setText(b.getString("Description"));
        }

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dataBaseOperations.deleteOne(clickedUser);
            }
        });

    }
}