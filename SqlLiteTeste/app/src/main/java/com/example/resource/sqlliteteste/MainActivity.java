package com.example.resource.sqlliteteste;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.resource.sqlliteteste.domain.Clientes;
import com.example.resource.sqlliteteste.infra.DataBase;
import com.example.resource.sqlliteteste.model.App;
import com.example.resource.sqlliteteste.repositorio.ClienteRepositorio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBase db;
    ArrayList<Clientes> clientes = new ArrayList<>();
    Integer idAtual = 0;
    Integer totalClientes = 0;
    EditText editTextID;
    EditText editTextNome;
    EditText editTextCpf;
    EditText editTextData;
    Button btnProximo;
    Button btnAtualizar;
    Button btnNovo;
    Button btnApagar;
    Button btnInserir;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DataBase(this, "base", null, 1);
        editTextCpf = findViewById(R.id.editCPF);
        editTextID = findViewById(R.id.editID);
        editTextNome = findViewById(R.id.editNome);
        editTextData = findViewById(R.id.editData);
        btnProximo = findViewById(R.id.btnProximo);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnNovo = findViewById(R.id.btnNovo);
        btnApagar = findViewById(R.id.btnApagar);
        btnInserir = findViewById(R.id.btnInserir);

        startDataBase();
        popilar2();

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProximo(v);
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar(v);
            }
        });

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserir(v);
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagar(v);
            }
        });

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novo(v);
            }
        });


    }

    private void startDataBase() {
        this.db.inserirClientes(2, "gabs", "7777777", "19/12/1990");
        this.db.inserirClientes(1, "marcos", "46456464646", "17/12/1990");
        this.db.inserirClientes(3, "gerson", "5500055", "18/12/1990");
    }


    public void popilar2() {
        Cursor c = App.getDb().listarTodos();
        if (c.getCount() > 0) {
            c.moveToFirst();
            totalClientes = c.getCount();
            idAtual = 0;

            clientes.clear();

            do {
                Clientes cli = new Clientes();
                cli.setIdCliente(c.getInt(c.getColumnIndex("ID")));
                cli.setNome(c.getString(c.getColumnIndex("NOME")));
                cli.setCpf(c.getString(c.getColumnIndex("CPF")));
                cli.setDataNasc(c.getString(c.getColumnIndex("DATA_NASC")));

                clientes.add(cli);
            } while (c.moveToNext());
        }
    }

    public void showProximo(View v) {
        Clientes cli = clientes.get(idAtual);
        editTextID.setText(String.valueOf(cli.getIdCliente()));
        editTextNome.setText(cli.getNome());
        editTextCpf.setText(cli.getCpf());
        editTextData.setText(cli.getDataNasc());

        idAtual++;

        if (idAtual.equals(totalClientes)) idAtual = 0;
    }

    public void atualizar(View view) {
        //this.db.atualizarCliente(Integer.valueOf(editTextID.getText().toString()), editTextNome.getText().toString(), editTextCpf.getText().toString(), editTextData.getText().toString());
        Clientes cliente = new Clientes(new ClienteRepositorio(), Integer.valueOf(editTextID.getText()
                .toString()), editTextNome.getText()
                .toString(), editTextCpf.getText().toString(), editTextData.getText().toString());
        cliente.atualizar();
        popilar2();
    }


    public void apagar(View view) {
        //this.db.deleteCliente(editTextCpf.getText().toString());
        Clientes cliente = new Clientes(new ClienteRepositorio(), Integer.valueOf(editTextID.getText().toString()), editTextNome.getText()
                .toString(), editTextCpf.getText().toString(), editTextData.getText().toString());
        cliente.deletar();

        popilar2();
    }

    @SuppressLint("SetTextI18n")
    public void novo(View view) {
        editTextID.setText(Integer.valueOf(totalClientes + 1).toString());
        editTextData.setText("");
        editTextCpf.setText("");
        editTextNome.setText("");
    }

    public void inserir(View view) {
        Clientes cliente = new Clientes(new ClienteRepositorio(), Integer.valueOf(editTextID.getText().toString()), editTextNome.getText()
                .toString(), editTextCpf.getText().toString(), editTextData.getText().toString());

        cliente.inserir();

        popilar2();
    }
}

