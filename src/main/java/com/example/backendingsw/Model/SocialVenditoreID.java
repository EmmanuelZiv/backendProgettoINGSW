package com.example.backendingsw.Model;

import java.io.Serializable;
import java.util.Objects;

public class SocialVenditoreID implements Serializable {
    private String nome;
    private String link;


    public SocialVenditoreID(){

    }

    public SocialVenditoreID(String n,String l){
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
        if (!(o instanceof SocialVenditoreID)) return false;
        SocialVenditoreID other = (SocialVenditoreID) o;
        return Objects.equals(nome, other.nome) && Objects.equals(link, other.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, link);
    }


}