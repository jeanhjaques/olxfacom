package com.progmobile.olxfacom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.progmobile.olxfacom.R;

import java.util.Locale;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private EditText campoTitulo, campoDescricao;
    private CurrencyEditText campoValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);
        inicializarComponentes();
    }

    public void salvarAnuncio(View view) {
        String valor = campoValor.getText().toString();
        Log.d("salvar","salvarAnuncio" + valor);
    }

    private void inicializarComponentes() {
        campoTitulo = findViewById(R.id.edtTitulo);
        campoDescricao = findViewById(R.id.edtDescricao);
        campoValor = findViewById(R.id.edtValor);

        // Configurar para pt-BR
        Locale locale = new Locale("pt","BR");
        campoValor.setLocale(locale);
    }
}