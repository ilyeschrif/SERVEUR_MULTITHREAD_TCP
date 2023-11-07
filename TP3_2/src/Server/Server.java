package Server;

import Service.Calculatrice;
import Client.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static int PORT = 1234;

    public static void main(String[] args) throws IOException {

        ServerSocket ssk = null;
        try {

            // Crée un serveur socket écoutant sur le port spécifié (1234)
            ssk = new ServerSocket(PORT);

            int numClient = 0;
            while (true) {

                numClient++;
                Socket sk = ssk.accept(); // Accepte la connexion d'un client
                Thread t1 = new Client(numClient, sk);
                t1.start();

                ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());

                // Lire l'objet Calculatrice envoyé par le client
                Calculatrice cal = (Calculatrice) ois.readObject();

                // Récupèrer les valeurs de l'objet Calculatrice
                int ent1 = cal.getOper1();
                int ent2 = cal.getOper2();
                String oper = cal.getOper();

                int res = 0;
                switch (oper) {
                    case "+":
                        res = ent1 + ent2;
                        break;
                    case "-":
                        res = ent1 - ent2;
                        break;
                    case "*":
                        res = ent1 * ent2;
                        break;
                    case "/":
                        res = ent1 / ent2;
                        break;

                }

                // Met à jour le résultat dans l'objet Calculatrice
                cal.setRes(res);
                
                // flux de sortie pour envoyer l'objet Calculatrice avec le résultat au client
                ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
                oos.writeObject(cal);


            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            // Vérifie si serverSocket n'est pas null et s'il n'a pas été déjà fermé.
            if (ssk != null && ssk.isClosed()) {
                ssk.close();
            }

        }

    }


}
