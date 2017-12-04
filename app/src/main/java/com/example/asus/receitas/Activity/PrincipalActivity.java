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


/**
 * The type Principal activity.
 */
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

        //instancia as variaveis locais e respectivos valores de entrada
        edtEmailLogin   = (EditText)findViewById(R.id.edtEmail);
        edtSenhaLogin   = (EditText)findViewById(R.id.edtSenha);
        btnLogin        = (Button)findViewById(R.id.btnLogin);
        btnCadastro     = (Button)findViewById(R.id.btnCadstro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick (View view) {
                validaCamposLogin();
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

        //instancia o metodo de autenticação do firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        //passa por parametro email e senha convertido para string pro metodo de autenticação
        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(),usuario.getSenha().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            //estado onComplete da tarefa
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //se sucesso
                if(task.isSuccessful()){
                    //mensagem de sucesso
                    Toast.makeText(PrincipalActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    //direciona
                    abrirTelaDrawer();
                //caso sem sucesso
                }else{
                    Toast.makeText(PrincipalActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void  validaCamposLogin(){
        if(!edtEmailLogin.getText().toString().equals("") && !edtSenhaLogin.getText().toString().equals("")){

            usuario = new Usuario();
            usuario.setEmail(edtEmailLogin.getText().toString());
            usuario.setSenha(edtSenhaLogin.getText().toString());

            validarLogin();

        }else{
            Toast.makeText(PrincipalActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirTelaDrawer(){
        Intent intent = new Intent(PrincipalActivity.this, Activity_Drawer.class);
        startActivity(intent);
    }

    private void abrirTelaCadastro(){
        Intent intent = new Intent(PrincipalActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}