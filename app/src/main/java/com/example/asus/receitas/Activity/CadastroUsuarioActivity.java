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

        //instancia as variáveis locais com o tipo correspondente e recebe o valo pelo id correspondente
        apelido = (EditText) findViewById(R.id.edtCadApelido);
        email = (EditText) findViewById(R.id.edtCadEmail);
        senha1 = (EditText) findViewById(R.id.edtCadSenha1);
        senha2 = (EditText) findViewById(R.id.edtCadSenha2);
        btnCadastrar = (Button) findViewById(R.id.btnCadstro);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        //botao cancelar direciona para a tela inicial
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaLogin();
            }
        });

        //botao cadastrar valida os dados e envia para o firebase
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //valida se todos os campos estão preenchidos
                if(!email.getText().toString().equals("")
                        && !senha1.getText().toString().equals("")
                        && !senha2.getText().toString().equals("")
                        && !apelido.getText().toString().equals("")) {

                    //se tudo estiver preenchido
                    if (senha1.getText().toString().equals( senha2.getText().toString() )) {
                        if (senha1.getText().toString().length() >=6
                                && senha2.getText().toString().length() >= 6) {

                            //instancia o objeto usuario
                            usuario = new Usuario();

                            //seta os valores no objeto
                            usuario.setEmail( email.getText().toString() );
                            usuario.setSenha( senha1.getText().toString() );
                            usuario.setApelido( apelido.getText().toString() );

                            cadastrarUsuario();

                        //senha menor de 6 caracteres
                        } else {
                            Toast.makeText( CadastroUsuarioActivity.this, "A senha precisa ter pelo menos 6 caracteres", Toast.LENGTH_LONG ).show();
                        }
                    //senhas divergentes
                    } else {
                        Toast.makeText( CadastroUsuarioActivity.this, "Senhas divergentes", Toast.LENGTH_LONG ).show();
                    }
                //alguns capos vazios
                }else{
                    Toast.makeText( CadastroUsuarioActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG ).show();
                }
            }
        });
     }

    //prepara a tarefa de atutenticação de usuário
    private void cadastrarUsuario() {

        //instancia os métodos de autenticação do firebase préviamente configurado no DAO
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        //passa por parâmetro os dados Email e Senha do objeto usuario para o método autenticação
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        //quando passado os dados, os sistema fica aguardando uma resposta,
        //pra passar os dados recebidos para a proxima etapa
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            //recebeu os dados do firebase
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //se sucesso
                if (task.isSuccessful()) {
                    //proxima etapa
                    insereUsuario(usuario);
                //se falha
                } else {
                    //instancia uma variavel de excecao
                    String erroExcecao = "";

                    //tratamento de erro
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

                    //imprime o erro
                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean insereUsuario(Usuario usuario) {

        try {
            //cria referencia para o nó usuário
            reference = ConfiguracaoFirebase.getFirebase().child("usuarios");
            //pega o objeto usuario e envia para o nó no firebase
            reference.push().setValue(usuario);
            //imprime sucesso
            Toast.makeText(CadastroUsuarioActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            return true;

        //caso de erro
        } catch (Exception e) {
            Toast.makeText(CadastroUsuarioActivity.this, "Erro ao gravar o usuário!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    //direcionador para a tela de login
    private void abrirTelaLogin(){
        Intent intent = new Intent(CadastroUsuarioActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

}