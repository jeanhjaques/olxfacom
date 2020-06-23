package com.progmobile.olxfacom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.progmobile.olxfacom.R;
import com.santalu.maskedittext.MaskEditText;

import java.util.Locale;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private EditText campoTitulo, campoDescricao;
    private CurrencyEditText campoValor;
    private MaskEditText campoTelefone;
    private Spinner spinnerEstado, spinnerCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);
        inicializarComponentes();
        ArrayAdapter adapterEstado = ArrayAdapter.createFromResource(this,R.array.estados,android.R.layout.simple_spinner_item);
        ArrayAdapter adapterCategoria = ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
        spinnerEstado.setAdapter(adapterEstado);
        spinnerCategoria.setAdapter(adapterCategoria);
    }

    public void salvarAnuncio(View view) {
        String valor = campoTelefone.getText().toString();
        Log.d("salvar","salvarAnuncio" + valor);

    }

    private void inicializarComponentes() {
        campoTitulo = findViewById(R.id.edtTitulo);
        campoDescricao = findViewById(R.id.edtDescricao);
        campoValor = findViewById(R.id.edtValor);
        campoTelefone = findViewById(R.id.edtTelefone);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        // Configurar para pt-BR
        Locale locale = new Locale("pt","BR");
        campoValor.setLocale(locale);
    }
}