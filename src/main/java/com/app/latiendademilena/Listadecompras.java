package com.app.latiendademilena;

public class Listadecompras {

    static  String [][] lista= {{"",""},{"",""},{"",""}};
    static int entrada=0;


    public static String[][] getLista() {
        return lista;
    }

    public static void setLista(String[][] lista) {
        Listadecompras.lista = lista;
    }

    public static void agregar(String producto,String unidades){

        lista[entrada][0]=producto;
        lista[entrada][1]=unidades;
        entrada++;
    }
    public static void quitar(int select){

        String g1; //nombre
        String g2; //unidades
        lista[select][0]="";
        lista[select][1]="";

        if(select==0){

            lista[0][0]=lista[1][0];
            lista[0][1]=lista[1][1];

            lista[1][0]=lista[2][0];
            lista[1][1]=lista[2][1];

            lista[2][0]="";
            lista[2][1]="";
            entrada--;

        }else if(select==1){

            lista[1][0]=lista[2][0];
            lista[1][1]=lista[2][1];

            lista[2][0]="";
            lista[2][1]="";
            entrada--;

        }else {

            entrada--;
        }
    }

    public static void reinicia(){
        lista= new String [3][2];
    }

    public static int getEntrada() {
        return entrada;
    }

    public static void setEntrada(int entrada) {
        Listadecompras.entrada = entrada;
    }
}
