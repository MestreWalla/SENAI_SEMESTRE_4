package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Manutencao {
    private int id;
    private int maquinaId; // Alterado para int
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
                + ", maquinaId='" + maquinaId + '\''  // Isso agora será um int
                + ", data='" + data + '\''
                + ", tipo='" + tipo + '\''
                + ", pecasTrocadas='" + pecasTrocadas + '\''
                + ", tempoDeParada=" + tempoDeParada
                + ", tecnico='" + tecnico + '\''
                + ", observacoes='" + observacoes + '\''
                + '}';
    }
}
