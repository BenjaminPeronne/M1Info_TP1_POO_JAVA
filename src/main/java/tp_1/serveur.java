/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:36
 * @modify date 2022-10-13 16:45:36
 * @desc [TP1 - Serveur]
 */

package tp_1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serveur {
    // ==================== Attributs ====================
    private static int serverPort = 8010;

    private static ArrayList<Object> listeSend = new ArrayList<Object>();
    private static ArrayList<Object> listeReceive = new ArrayList<Object>();

    private String nom;
    private String adresseIP;
    private int port;
    private String protocole;

    // ==================== Constructeur ====================
    public serveur(String nom, String adresseIP, int port, String protocole) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.port = port;
        this.protocole = protocole;
    }

    // ==================== Méthodes ====================
    public void afficher() { // Affiche les informations du serveur
        System.out.println("Nom : " + this.nom);
        System.out.println("Adresse IP : " + this.adresseIP);
        System.out.println("Port : " + this.port);
        System.out.println("Protocole : " + this.protocole);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {
            Point p = new Point(i, i);
            Segment s = new Segment(p, p);

            listeSend.add(p);
            listeSend.add(s);
        }

        ServerSocket ecoute;

        try {
            ecoute = new ServerSocket(serverPort);
            System.out.println("Lancement du serveur sur le port " + serverPort);

            while (true) {
                System.out.println("Serveur en attente de connexion");
                Socket client = ecoute.accept();
                System.out.println("Connexion établie avec " + client.getInetAddress());

                // Envoi
                OutputStream os = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(listeSend);
                oos.flush();

                // Réception
                InputStream is = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                listeReceive = (ArrayList<Object>) ois.readObject();

                System.out.println("Liste reçue : " + listeReceive);

                ois.close();
                oos.close();
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}