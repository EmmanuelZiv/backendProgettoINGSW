package com.example.backendingsw.Model;

import java.io.Serializable;
import java.util.Objects;

public class SocialAcquirenteID implements Serializable {
    private String nome;
    private String link;


    public SocialAcquirenteID(){

    }

    public SocialAcquirenteID(String n,String l){
        nome=n;
        link=l;
    }

    public String getLink() {
        return link;
    }

    public String getNome() {
        return nome;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Costruttori, metodi getter, setter, equals e hashCode...
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialAcquirenteID)) return false;
        SocialAcquirenteID other = (SocialAcquirenteID) o;
        return Objects.equals(nome, other.nome) && Objects.equals(link, other.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, link);
    }


}

