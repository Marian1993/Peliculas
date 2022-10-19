package com.company.Dades;

import com.company.Domini.Film;

public interface IntDades{


    public boolean existeix(Film pelicula);

    public void llistar();

    public boolean actualitzar(Film pelicula);

    public boolean inserir(Film pelicula);

    public boolean eliminar(int id);

    public Film cercar(Film pelicula);
}
