package com.example.asus.receitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;



public class MainActivity extends AppCompatActivity {

    private EditText ic_email;
    private EditText ic_senha;
    private Button ic_acessar;
    private Button ic_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ic_email = (EditText)findViewById(R.id.ic_email);
        EditText ic_senha = (EditText)findViewById(R.id.ic_senha);
        Button ic_acessar = (Button)findViewById(R.id.ic_acessar);
        Button ic_registrar = (Button)findViewById(R.id.ic_registrar);

        ic_registrar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent it = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(it);
            }

        });

        ic_acessar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent it = new Intent(MainActivity.this, DrawerActivity.class);
                startActivity(it);
            }

        });
    }
}
