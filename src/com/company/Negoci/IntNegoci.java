package com.company.Negoci;

import com.company.Domini.Film;

public interface IntNegoci {

    public void llistarPelicules();

    public boolean afegirPelicula(Film pelicula);

    public Film cercarPelicula(Film pelicula);

    public void iniciarCataleg();

}
