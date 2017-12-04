package com.example.asus.receitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asus.receitas.Fragments.BatidaFragment;
import com.example.asus.receitas.Fragments.ComidaFragment;
import com.example.asus.receitas.Fragments.ForumFragment;
import com.example.asus.receitas.Fragments.SendFragment;
import com.example.asus.receitas.Fragments.SobremesaFragment;
import com.example.asus.receitas.R;

public class Activity_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu; isso adiciona os itens na barra de ação
        getMenuInflater().inflate(R.menu.activity__drawer_upper, menu);
        return true;
    }

    //Menu tres pontinhos superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // recebe o id do item clicado
        int id = item.getItemId();

        //verifica o item clicado
        if (id == R.id.action_settings) {
            //mostra toast
            Toast.makeText(Activity_Drawer.this, "Deslogado com sucesso", Toast.LENGTH_LONG).show();
            //redireciona para a tela principal
            abrirTelaPrincipalActivity();
            return true;
        }
        //retorna o item
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // recebe o id do item clicado
        int id = item.getItemId();

        //verifica qual item clicado
        if (id == R.id.nav_comida) {
            //instancia o objeto fragment COmidaFragment;
            ComidaFragment comidaFragment = new ComidaFragment();
            //cria objeto manager do tipo FragmentManager com o método getSupportFragmentManager
            FragmentManager manager = getSupportFragmentManager();
            //inicia o processo de transição da fragment,
            manager.beginTransaction()
                    //substituindo o mainLayout pelo comidaFragment
                    .replace(R.id.mainLayout, comidaFragment)
                    //dispara a alteração
                    .commit();

        } else if (id == R.id.nav_sobremesa) {
            SobremesaFragment sobremesaFragment = new SobremesaFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.mainLayout, sobremesaFragment)
                    .commit();

        } else if (id == R.id.nav_batidas) {
            BatidaFragment batidaFragment = new BatidaFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.mainLayout, batidaFragment)
                    .commit();

        } else if (id == R.id.nav_forum) {
            ForumFragment forumFragment = new ForumFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.mainLayout, forumFragment)
                    .commit();

        } else if (id == R.id.nav_send) {
            SendFragment SendFragment = new SendFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.mainLayout, SendFragment)
                    .commit();

        }

        //encontra o id do drawer para o objeto drawer do tipo DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //fecha o drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //redirecionador de tela
    private void abrirTelaPrincipalActivity(){
        Intent intent = new Intent (Activity_Drawer.this, PrincipalActivity.class);
        startActivity(intent);
    }
}
