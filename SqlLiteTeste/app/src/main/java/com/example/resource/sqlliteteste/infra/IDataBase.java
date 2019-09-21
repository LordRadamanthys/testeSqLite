package com.example.resource.sqlliteteste.infra;

import android.database.Cursor;

public interface IDataBase {


     Cursor listarTodos();

     void atualizarCliente(Integer id, String nome, String cpf, String data);

    void deleteCliente(String cpf);

    void inserirClientes(Integer id, String nome, String cpf, String data);
}
