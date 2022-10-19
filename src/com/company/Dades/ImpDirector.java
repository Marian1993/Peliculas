package com.company.Dades;

import com.company.Domini.Film;

import java.sql.*;
import java.util.ArrayList;

public class ImpDirector implements IntDades{

    public ImpDirector(){}

    //Dades per accedir a la bbdd
    private final String url = "jdbc:mysql://localhost:3306/peliculas";
    private final String user = "root";
    private final String passw = "mamm4444";

    //Declaracion per fer la interacció amb la bbdd
    private Connection conexio;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ArrayList<Film> llista = null;

    //nom de la taula
    private final String nomTaula = "pelicula";



    public Connection establirConnexio(){

        try {
            conexio = DriverManager.getConnection(url, user, passw);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conexio;
    }

    public void tancarConnexio(){

        try {
            statement.close();
            conexio.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Comprovar si existeix una pelicula
    public boolean existeix(Film pelicula){

        try {

            statement = establirConnexio().createStatement();
            ResultSet resultado = statement.executeQuery("select * from " + nomTaula + " where id = " + pelicula.getId());

            if (resultado.next()) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            tancarConnexio();
        }
        return false;
    }

    //Ensenya totes les pelicules que hi ha a la taula pelicules de la bbdd
    public void llistar(){
        try {
            statement = establirConnexio().createStatement();
            ResultSet resultado = statement.executeQuery("select * from "+ nomTaula);

            Film pelicules;
            llista = new ArrayList<Film>();

            //accedim al resultat
            while (resultado.next()) {

                //Aqui afegeix a un Array s'es pelicules que hi ha de sa taula pelicules de sa bbdd
                pelicules = new Film(resultado.getInt("id"),resultado.getString("titol"), resultado.getString("director"), resultado.getInt("any"),resultado.getString("genere"));
                llista.add(pelicules);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            tancarConnexio();
        }
        for (Film p : llista) {

            System.out.println(p);
        }
    }

    //actualitzar una pelicula a la bbdd
    public boolean actualitzar(Film pelicula){

        try {

            prepStatement = establirConnexio().prepareStatement("update " + nomTaula + " set titol = ?, director = ?, any = ?, genere = ? where id = ?");

            prepStatement.setString(1, pelicula.getTitol());
            prepStatement.setString(2, pelicula.getDirector());
            prepStatement.setInt(3,pelicula.getAny());
            prepStatement.setString(4, pelicula.getGenere());
            prepStatement.setInt(5,pelicula.getId());

            if (prepStatement.executeUpdate() == 1) {
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            tancarConnexio();
        }
        return false;
    }

    //Afegir una pelicula a la bbdd
    public boolean inserir(Film pelicula){

        try {

            prepStatement = establirConnexio().prepareStatement("insert into " + nomTaula + " values (?,?,?,?,?) ");

            prepStatement.setInt(1,pelicula.getId());
            prepStatement.setString(2, pelicula.getTitol());
            prepStatement.setString(3, pelicula.getDirector());
            prepStatement.setInt(4,pelicula.getAny());
            prepStatement.setString(5, pelicula.getGenere());

            if (prepStatement.executeUpdate() == 1) {
                return true;
            }

        }catch (SQLIntegrityConstraintViolationException a){
            System.out.println("Aquesta pelicula ja existeix");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //Eliminar una pelicula de la bbdd
    public boolean eliminar(int id){

        try {

            prepStatement = establirConnexio().prepareStatement("delete from " + nomTaula + " where id = ? ");

            prepStatement.setInt(1,id);

            if (prepStatement.executeUpdate() == 1) {
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            tancarConnexio();
        }
        return false;
    }

    //Cercar si hi ha sa pelicula a sa bbdd
    public Film cercar(Film pelicula){

        try {

            statement = establirConnexio().createStatement();
            ResultSet resultado = statement.executeQuery("select * from " + nomTaula + " where id = " + pelicula.getId());

            if (resultado.next()) {
                return new Film(resultado.getInt("id"),resultado.getString("titol"), resultado.getString("director"), resultado.getInt("any"),resultado.getString("genere"));

            }
        }catch (SQLException ignore){

        }
        finally {
            tancarConnexio();
        }
        return null;
    }

}
