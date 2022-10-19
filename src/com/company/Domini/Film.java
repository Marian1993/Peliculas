package com.company.Domini;

public class Film{

    private int id;
    private String titol;
    private String director;
    private int any;
    private String genere;

    public Film() {
    }

    public Film(int id) {
        this.id = id;
    }

    public Film(String tittle, String director, int anyo, String genere) {
        this.titol = tittle;
        this.director = director;
        this.any = anyo;
        this.genere = genere;
    }

    public Film(int id, String tittle, String director, int anyo, String genere) {
        this.id = id;
        this.titol = tittle;
        this.director = director;
        this.any = anyo;
        this.genere = genere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", tittle='" + titol + '\'' +
                ", director='" + director + '\'' +
                ", any=" + any +
                ", genere='" + genere + '\'' +
                '}';
    }
}
