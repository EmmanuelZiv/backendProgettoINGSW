package com.example.backendingsw.Model;


import jakarta.persistence.*;

@Entity
@Table(name="socialVenditore")
public class SocialVenditore {

    @Id
    @Column(name="nome")
    private String nome;
    @Id
    @Column(name="link")
    private String link;



    @ManyToOne
    @JoinColumn(name = "indirizzo_email", referencedColumnName = "indirizzo_email")
    private Venditore venditore;


    public SocialVenditore(){

    }

    public SocialVenditore(String n,String l,Venditore v){
        nome=n;
        link=l;
        venditore=v;
    }

    public Venditore getVenditore() {
        return venditore;
    }

    public void setVenditore(Venditore venditore) {
        this.venditore = venditore;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

}
