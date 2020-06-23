package com.progmobile.olxfacom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.progmobile.olxfacom.R;
import com.progmobile.olxfacom.helper.Permissoes;
import com.santalu.maskedittext.MaskEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CadastrarAnuncioActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText campoTitulo, campoDescricao;
    private CurrencyEditText campoValor;
    private MaskEditText campoTelefone;
    private ImageView image1, image2, image3;
    private Spinner spinnerEstado, spinnerCategoria;

    private String[] permissoes = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private List<String> listaFotosRecuperadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        // Validar permissões
        Permissoes.validarPermissoes(permissoes, this, 1);

        inicializarComponentes();
        carregarDadosSpinner();

    }

    public void salvarAnuncio(View view) {
        String valor = campoTelefone.getText().toString();
        Log.d("salvar","salvarAnuncio" + valor);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageCadastro1 :
                escolherImagem(1);
                break;
            case R.id.imageCadastro2 :
                escolherImagem(2);
                break;
            case R.id.imageCadastro3 :
                escolherImagem(3);
                break;
        }
    }

    public void escolherImagem(int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            //Recuperar imagem
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            //Configura imagem no ImageView
            if(requestCode == 1) {
                image1.setImageURI(imagemSelecionada);
            }
            else if(requestCode == 2) {
                image2.setImageURI(imagemSelecionada);
            }
            else if (requestCode == 3) {
                image3.setImageURI(imagemSelecionada);
            }

            listaFotosRecuperadas.add(caminhoImagem);
        }
    }

    private void carregarDadosSpinner() {

        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapterEstado = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, estados);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapterEstado);

        String[] categorias = getResources().getStringArray(R.array.categorias);
        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categorias);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

//        ArrayAdapter adapterEstado = ArrayAdapter.createFromResource(this,R.array.estados,android.R.layout.simple_spinner_item);
//        ArrayAdapter adapterCategoria = ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
//        spinnerEstado.setAdapter(adapterEstado);
//        spinnerCategoria.setAdapter(adapterCategoria);
    }

    private void inicializarComponentes() {
        campoTitulo = findViewById(R.id.edtTitulo);
        campoDescricao = findViewById(R.id.edtDescricao);
        campoValor = findViewById(R.id.edtValor);
        campoTelefone = findViewById(R.id.edtTelefone);
        image1 = findViewById(R.id.imageCadastro1);
        image2 = findViewById(R.id.imageCadastro2);
        image3 = findViewById(R.id.imageCadastro3);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        // Configurar para pt-BR
        Locale locale = new Locale("pt","BR");
        campoValor.setLocale(locale);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int permissaoResultado : grantResults) {
            if(permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}