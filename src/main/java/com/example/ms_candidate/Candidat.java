package com.example.ms_candidate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidat implements Serializable {
    private static final long serialVersionUID = 6;

    @Id()
    @GeneratedValue
    public Integer id;

    public String nom,prenom,email;

    public Integer likes=0;

    public Candidat(String nom) {
        this.nom=nom;
    }

/*
    public Integer getId(){
        return id;

    };
    public String getNom(){
        return nom;
    };
    public void setNom(String nom){
        this.nom=nom;
    };

    public String getPrenom(){
        return prenom;
    };
    public void setPrenom(String prenom){
        this.prenom=prenom;
    };

    public String getEmail(){
        return email;
    };
    public void setEmail(String email){
        this.email=email;
    };
    public Candidat(String nom){
        super();
        this.nom=nom;
    }
    public Candidat(){
        super();
    }

 */
}
