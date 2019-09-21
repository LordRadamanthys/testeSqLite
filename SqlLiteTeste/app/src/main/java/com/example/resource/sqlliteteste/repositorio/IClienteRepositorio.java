package com.example.resource.sqlliteteste.repositorio;

public interface IClienteRepositorio {

    void listarTodos();

    void atualizarCliente(Integer id,String nome, String cpf, String data);

    void deleteCliente(String cpf);

    void inserirClientes(Integer id,String nome, String cpf, String data);
}
