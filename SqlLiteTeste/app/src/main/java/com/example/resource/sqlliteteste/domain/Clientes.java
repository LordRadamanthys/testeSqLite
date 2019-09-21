package com.example.resource.sqlliteteste.domain;

import com.example.resource.sqlliteteste.repositorio.IClienteRepositorio;

public class Clientes {

    private int idCliente;
    private String nome;
    private String cpf;
    private String dataNasc;
    private IClienteRepositorio iClienteRepositorio;


    public Clientes(IClienteRepositorio iClienteRepositorio, int idCliente, String nome, String cpf, String dataNasc) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.iClienteRepositorio = iClienteRepositorio;
    }

    public Clientes() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void inserir() {

        iClienteRepositorio.inserirClientes(idCliente, nome, cpf, dataNasc);
    }

    public void deletar() {

        iClienteRepositorio.deleteCliente(cpf);
    }

    public void atualizar() {

        iClienteRepositorio.atualizarCliente(idCliente, nome, cpf, dataNasc);
    }

}