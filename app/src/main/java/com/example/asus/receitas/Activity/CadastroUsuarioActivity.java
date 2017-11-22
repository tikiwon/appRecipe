package com.example.asus.receitas.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.receitas.Classes.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.example.asus.receitas.DAO.ConfiguracaoFirebase;
import com.example.asus.receitas.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private EditText apelido;
    private EditText email;
    private EditText senha1;
    private EditText senha2;

    private Button btnCadastrar;
    private Button btnCancelar;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

            apelido = (EditText) findViewById(R.id.edtCadApelido);
            email = (EditText) findViewById(R.id.edtCadEmail);
            senha1 = (EditText) findViewById(R.id.edtCadSenha1);
            senha2 = (EditText) findViewById(R.id.edtCadSenha2);
            btnCadastrar = (Button) findViewById(R.id.btnCadstro);
            btnCancelar = (Button) findViewById(R.id.btnCancelar);

            //botao cancelar
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirTelaLogin();
                }
            });

            //botao cadastrar
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (senha1.getText().toString().equals(senha2.getText().toString())) {
                        usuario = new Usuario();

                        usuario.setEmail(email.getText().toString());
                        usuario.setSenha(senha1.getText().toString());
                        //usuario.setApelido(apelido.getText().toString());

                        //chamada de método para cadastro de usuários
                        cadastrarUsuario();

                    } else {
                        Toast.makeText(CadastroUsuarioActivity.this, "As senhas não se correspondem!", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    insereUsuario(usuario);


                } else {

                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail digitado é invalido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está cadastro!";
                    }catch (FirebaseAuthActionCodeException e) {
                        erroExcecao = "Authcodeerror"+e;
                    } catch (Exception e) {
                        erroExcecao = "Erro ao efetuar o cadastro!"+e;
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean insereUsuario(Usuario usuario) {

        try {

            reference = ConfiguracaoFirebase.getFirebase().child("usuarios");
            reference.push().setValue(usuario);
            Toast.makeText(CadastroUsuarioActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            return true;

        } catch (Exception e) {
            Toast.makeText(CadastroUsuarioActivity.this, "Erro ao gravar o usuário!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    private void abrirTelaLogin(){
        Intent intent = new Intent(CadastroUsuarioActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

}
