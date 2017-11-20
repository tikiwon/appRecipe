package com.example.asus.receitas.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import com.example.asus.receitas.Classes.Usuario;
import com.example.asus.receitas.DAO.ConfiguracaoFirebase;
import com.example.asus.receitas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText edtEmailLogin;
    private EditText edtSenhaLogin;
    private Button btnLogin;
    private Button btnCadastro;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        edtEmailLogin   = (EditText)findViewById(R.id.edtEmail);
        edtSenhaLogin   = (EditText)findViewById(R.id.edtSenha);
        btnLogin        = (Button)findViewById(R.id.btnLogin);
        btnCadastro     = (Button)findViewById(R.id.btnCadstro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override

            public void onClick (View view) {
                if(!edtEmailLogin.getText().toString().equals("") && !edtSenhaLogin.getText().toString().equals("")){

                    usuario = new Usuario();
                    usuario.setEmail(edtEmailLogin.getText().toString());
                    usuario.setSenha(edtSenhaLogin.getText().toString());

                    validarLogin();

                }else{
                    Toast.makeText(PrincipalActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastro();
            }
        });
    }

    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(),usuario.getSenha().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    abrirTelaForum();

                    Toast.makeText(PrincipalActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PrincipalActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void abrirTelaForum(){
        Intent intent = new Intent(PrincipalActivity.this, ForumActivity.class);
        startActivity(intent);
    }

    private void abrirTelaCadastro(){
        Intent intent = new Intent(PrincipalActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
