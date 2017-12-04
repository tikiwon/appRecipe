package com.example.asus.receitas.Fragments;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.receitas.Classes.Mural;
import com.example.asus.receitas.DAO.ConfiguracaoFirebase;
import com.example.asus.receitas.R;
import com.google.firebase.database.DatabaseReference;


public class SendFragment extends android.support.v4.app.Fragment {

    private Mural mural;
    private DatabaseReference reference;

    public SendFragment() {
        // Required empty public constructor

    }

    EditText editTextMensagem;
    Button btnEnviarMensagem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_send, container,false);

        editTextMensagem = (EditText)view.findViewById(R.id.editTextMensagem);
        btnEnviarMensagem = (Button)view.findViewById(R.id.btnEnviarMensagem);

        btnEnviarMensagem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //verifica se há algo digitado no campo de mensagem
                if(!editTextMensagem.getText().toString().equals("")){

                    //se tiver coisa escrita, é intanciado o Objeto Mural
                    mural = new Mural();

                    //e setado o valor
                    mural.setTexto( editTextMensagem.getText().toString() );

                    //envio para o firebase
                    try {
                        reference = ConfiguracaoFirebase.getFirebase().child("receitasSolicitada");
                        reference.push().setValue(mural);
                        Toast.makeText(SendFragment.this.getActivity(), "Mensagem enviado com sucesso!", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(SendFragment.this.getActivity(), "Erro ao gravar o solicitação!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                // caso campo de mensagem vazio
                }else {
                    Toast.makeText(SendFragment.this.getActivity(), "Campo de mensagem vazio", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
