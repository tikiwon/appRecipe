package com.example.asus.receitas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RegistrarActivity extends AppCompatActivity {

    private EditText ic_reg_apelido;
    private EditText ic_reg_celular;
    private EditText ic_reg_email;
    private EditText ic_reg_senha;
    private Button ic_reg_registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        EditText ic_reg_apelido = (EditText)findViewById(R.id.ic_reg_apelido);
        EditText ic_reg_celular = (EditText)findViewById(R.id.ic_reg_celular);
        EditText ic_reg_email = (EditText)findViewById(R.id.ic_reg_email);
        EditText ic_reg_senha = (EditText)findViewById(R.id.ic_reg_senha);
        Button ic_reg_registrar = (Button)findViewById(R.id.ic_reg_registrar);



        ic_reg_registrar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent it = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(it);

            }
        });


    }

}
