package com.company.Negoci;

import com.company.Dades.ImpPelicula;
import com.company.Domini.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImpNegoci implements IntNegoci{

    ImpPelicula dades = new ImpPelicula();

    public void llistarPelicules(){
        dades.llistar();
    }

    public boolean afegirPelicula(Film pelicula){
       return dades.inserir(pelicula);
    }

    public Film cercarPelicula(Film pelicula){

        return dades.cercar(pelicula);
    }

    public void iniciarCataleg(){

        try {
            Statement statement = dades.establirConnexio().createStatement();
            ResultSet resultado = statement.executeQuery("select * from pelicula");

            while (resultado.next()) {

               dades.eliminar(resultado.getInt("id"));
            }
            Statement statRestart = dades.establirConnexio().createStatement();
            statRestart.execute("alter table pelicula AUTO_INCREMENT = 1");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            dades.tancarConnexio();
        }
    }
}
