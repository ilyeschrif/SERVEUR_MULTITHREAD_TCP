package Client;



import Service.Calculatrice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private static  int numClient;
    private static Socket sk;


    public Client(int numClient,Socket sk)
    {
        this.numClient=numClient;
        this.sk=sk;
    }

    public static void main(String [] args)
    {

        try {

            Socket sk = new Socket("localhost", 1234);

            // flux de sortie pour envoyer des objets au serveur
            ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());

            // saisie de l'utilisateur
            Scanner sc = new Scanner(System.in);
            System.out.println("donner ent1: ");
            int oper1 = sc.nextInt();
            sc.nextLine();
            System.out.println("donner oper: ");
            String oper = sc.nextLine();

            System.out.println("donner ent2:");
            int oper2 = sc.nextInt();

            // objet Calculatrice avec les valeurs saisies
            Calculatrice cal = new Calculatrice(oper1, oper, oper2);

            // Envoie de l'objet Calculatrice au serveur
            out.writeObject(cal);

            // flux d'entrée pour recevoir des objets du serveur
            ObjectInputStream ios = new ObjectInputStream(sk.getInputStream());

            //l'affiche de résultat renvoyé par le serveur
            System.out.println("\nresultat : " + ((Calculatrice) ios.readObject()).getRes());

            out.close();
            sk.close();
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        }
    }



}