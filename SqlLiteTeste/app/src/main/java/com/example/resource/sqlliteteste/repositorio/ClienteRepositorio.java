package com.example.resource.sqlliteteste.repositorio;

import com.example.resource.sqlliteteste.model.App;

public class ClienteRepositorio implements IClienteRepositorio {
    @Override
    public void listarTodos() {
        App.getDb().listarTodos();
    }

    @Override
    public void atualizarCliente(Integer id, String nome, String cpf, String data) {
        App.getDb().atualizarCliente(id, nome, cpf, data);
    }

    @Override
    public void deleteCliente(String cpf) {
        App.getDb().deleteCliente(cpf);
    }

    @Override
    public void inserirClientes(Integer id, String nome, String cpf, String data) {
        App.getDb().inserirClientes(id, nome, cpf, data);
    }
}
