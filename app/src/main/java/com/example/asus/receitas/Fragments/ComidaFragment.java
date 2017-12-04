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

import com.example.asus.receitas.Activity.ActivityReceitaMadeira;
import com.example.asus.receitas.Activity.ActivityReceitaParmegiana;
import com.example.asus.receitas.Activity.ActivityReceitaPicadinho;
import com.example.asus.receitas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComidaFragment extends Fragment {


    public ComidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comida, container, false);

        String[] menuComidas = {"Filé de Frango à Parmegiana",
                                "Picadinho de Carne com Legumes",
                                "Filé Mignon ao Molho Madeira"};

        ListView listView = (ListView) view.findViewById(R.id.menuComidas);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuComidas
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(getActivity(), ActivityReceitaParmegiana.class);
                    startActivity(intent);
                } else if(i == 1){
                    Intent intent = new Intent(getActivity(), ActivityReceitaPicadinho.class);
                    startActivity(intent);

                } else if(i == 2){
                    Intent intent = new Intent(getActivity(), ActivityReceitaMadeira.class);
                    startActivity(intent);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
