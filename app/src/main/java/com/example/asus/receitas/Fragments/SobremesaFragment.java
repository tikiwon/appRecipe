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

import com.example.asus.receitas.Activity.ActivityReceitaChocolate;
import com.example.asus.receitas.Activity.ActivityReceitaFrutas;
import com.example.asus.receitas.Activity.ActivityReceitaPudim;
import com.example.asus.receitas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobremesaFragment extends Fragment {


    public SobremesaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobremesa, container, false);

        String[] menuSobremesa = {"Pudim de Leite",
                "Salada de Frutas",
                "Bolo de chocolate"};

        ListView listView = (ListView) view.findViewById(R.id.menuSobremesa);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuSobremesa
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(getActivity(), ActivityReceitaPudim.class);
                    startActivity(intent);
                } else if(i == 1){
                    Intent intent = new Intent(getActivity(), ActivityReceitaFrutas.class);
                    startActivity(intent);

                } else if(i == 2){
                    Intent intent = new Intent(getActivity(), ActivityReceitaChocolate.class);
                    startActivity(intent);

                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}