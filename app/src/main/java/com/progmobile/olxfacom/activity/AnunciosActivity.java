package com.progmobile.olxfacom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.progmobile.olxfacom.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AnunciosActivity extends AppCompatActivity {
    FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(autenticacao == null){
//            autenticacao.getCurrentUser()
            menu.setGroupVisible(R.id.group_deslogado, true);

        }else{
            menu.setGroupVisible(R.id.group_logado, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cadastrar:
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
                break;
            case R.id.menu_sair:
                autenticacao.signOut();
                invalidateOptionsMenu();
                break;
            case R.id.menu_anuncios:
                startActivity(new Intent(getApplicationContext(), MeusAnunciosActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}