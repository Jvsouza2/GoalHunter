package jvsouza.com.juntando.model;

public class Conquistas {


    private String nomeConquista;
    private String descricaoConquista;
    private int nivelConquista;
    private Long idConquista;




    public Long getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(Long idConquista) {
        this.idConquista = idConquista;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }

    public String getDescricaoConquista() {
        return descricaoConquista;
    }

    public void setDescricaoConquista(String descricaoConquista) {
        this.descricaoConquista = descricaoConquista;
    }

    public int getNivelConquista() {
        return nivelConquista;
    }

    public void setNivelConquista(int nivelConquista) {
        this.nivelConquista = nivelConquista;
    }
}
