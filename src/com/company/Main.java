package com.company;

import com.company.Domini.Film;
import com.company.Negoci.ImpNegoci;

import java.util.Scanner;

public class Main {

    static Film afeguirPelicula(){


        Film pel = new Film();
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Titol: ");
        pel.setTitol(sc1.nextLine());
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Director: ");
        pel.setDirector(sc2.nextLine());
        System.out.print("Any: ");
        pel.setAny(Error.esNecessitaNumero());
        Scanner sc4 = new Scanner(System.in);
        System.out.print("Genere: ");
        pel.setGenere(sc4.nextLine());

        return pel;
    }

    public static void main(String[] args) {


        ImpNegoci negoci = new ImpNegoci();
        int eleccio;

        do {


            System.out.println("-------------------------------------------");
            System.out.println("Posa el número de l'acció que vols fer");
            System.out.println("1.Veure totes les pelicules");
            System.out.println("2.Afegir una pelicula");
            System.out.println("3.Cercar una pelicula");
            System.out.println("4.Restaurar Cataleg");
            System.out.println("0.Sortir");
            System.out.println("-------------------------------------------");

            eleccio = Error.esNecessitaNumero();



            switch (eleccio) {

                case 0:
                    break;
                case 1:
                    negoci.llistarPelicules();
                    break;
                case 2:
                    if(negoci.afegirPelicula(afeguirPelicula())){
                        System.out.println("La pelicula s'ha afegit correctament.");
                    }else {
                        System.out.println("La pelicula ja existeix");
                    }
                    break;
                case 3:
                    System.out.print("Posa la id de la pelicula que vols cercar:");
                    Film pelicula;

                    pelicula = negoci.cercarPelicula(new Film(Error.esNecessitaNumero()));
                    if(pelicula != null) {
                        System.out.println(pelicula);
                    }else {
                        System.out.println("Aquesta pelicula no existeix");
                    }
                    break;
                case 4:
                    negoci.iniciarCataleg();
                    break;
                default:
                    System.out.println("No has posat cap número dels indicats");
            }
        } while (eleccio != 0);

    }

}
