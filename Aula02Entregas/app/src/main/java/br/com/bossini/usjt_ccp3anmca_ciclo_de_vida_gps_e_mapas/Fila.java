package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import java.io.Serializable;
import java.util.List;
public class Fila implements Serializable {
    private String nome;
    private List <Localizacao> localizacao;

    public Fila(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Localizacao> getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(List<Localizacao> localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Fila{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
