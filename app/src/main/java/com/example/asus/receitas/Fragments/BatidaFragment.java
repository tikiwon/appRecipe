package com.example.asus.receitas.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.receitas.Activity.ActivityReceitaCaipirinha;
import com.example.asus.receitas.Activity.ActivityReceitaCoquetel;
import com.example.asus.receitas.Activity.ActivityReceitaEspanhola;
import com.example.asus.receitas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatidaFragment extends Fragment {


    public BatidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batida, container, false);

        String[] menuBatidas = {"Caipirinha de Morango com Saque",
                "Espanhola",
                "Coquetel Brasil"};

        ListView listView = (ListView) view.findViewById(R.id.menuBatidas);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuBatidas
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(getActivity(), ActivityReceitaCaipirinha.class);
                    startActivity(intent);

                } else if(i == 1){
                    Intent intent = new Intent(getActivity(), ActivityReceitaEspanhola.class);
                    startActivity(intent);

                } else if(i == 2){
                    Intent intent = new Intent(getActivity(), ActivityReceitaCoquetel.class);
                    startActivity(intent);

                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
