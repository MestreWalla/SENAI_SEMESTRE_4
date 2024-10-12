package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Manutencao {
    private String id;
    private String maquinaId;
    private String data;
    private String tipo;
    private String pecasTrocadas;
    private int tempoDeParada;
    private String tecnico;
    private String observacoes;

    @Override
    public String toString() {
        return "Manutencao{"
                + "id='" + id + '\''
                + ", maquinaId='" + maquinaId + '\''
                + ", data='" + data + '\''
                + ", tipo='" + tipo + '\''
                + ", pecasTrocadas='" + pecasTrocadas + '\''
                + ", tempoDeParada=" + tempoDeParada
                + ", tecnico='" + tecnico + '\''
                + ", observacoes='" + observacoes + '\''
                + '}';
    }
}
