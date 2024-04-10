package com.example.backendingsw.Model;


import jakarta.persistence.*;

@Entity
@Table(name="socialAcquirente")
@IdClass(SocialAcquirenteID.class)
public class SocialAcquirente {

    @Id
    @Column(name="nome")
    private String nome;
    @Id
    @Column(name="link")
    private String link;



    @ManyToOne
    @JoinColumn(name = "indirizzo_email", referencedColumnName = "indirizzo_email")
    private Acquirente acquirente;


    public SocialAcquirente(){

    }

    public SocialAcquirente(String n,String l,Acquirente a){
        nome=n;
        link=l;
        acquirente=a;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
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
