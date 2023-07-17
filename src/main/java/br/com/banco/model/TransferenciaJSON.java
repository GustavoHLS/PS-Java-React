package br.com.banco.model;

import java.time.OffsetDateTime;

public class TransferenciaJSON {
    private long numeroConta;
    private String nome;
    private OffsetDateTime inicio;
    private OffsetDateTime fim;

    public long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public OffsetDateTime getInicio() {
        return inicio;
    }

    public void setInicio(OffsetDateTime inicio) {
        this.inicio = inicio;
    }

    public OffsetDateTime getFim() {
        return fim;
    }

    public void setFim(OffsetDateTime fim) {
        this.fim = fim;
    }

    public TransferenciaJSON(long id){
        this.numeroConta = id;
    }

    public TransferenciaJSON(String nome){
        this.nome = nome;
    }

    public TransferenciaJSON(OffsetDateTime inicio, OffsetDateTime fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public TransferenciaJSON(long id, String nome, OffsetDateTime inicio, OffsetDateTime fim){
        this.numeroConta = id;
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
    }

}
