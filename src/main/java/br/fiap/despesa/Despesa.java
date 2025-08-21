package br.fiap.despesa;

import br.fiap.modelo.Categoria;

import java.time.LocalDate;

public class Despesa {

    /// VARIAVEIS --------------------
    private Double valor;
    private LocalDate data;
    private Long id;
    private String descricao;
    private Categoria categoria;

    /// GET SET ------------------------
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}/////////////////////////
