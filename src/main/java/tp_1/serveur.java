/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:36
 * @modify date 2022-10-13 16:45:36
 * @desc [TP1 - Serveur]
 */

/*
 * A faire :
   * - Mettre en place une boucle while pour que le serveur reste en écoute et qui ne se coupe pas
   * - Réaliser une méthode connexion() qui permet de se connecter au serveur afin de d'issocié l'envoie de message et la connexion
 */

package tp_1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    public static void main(String[] args) {
        serveur serveur1 = new serveur("Serveur1", "192.168.1.9", 8003, "HTTP");

        serveur1.afficher();
        serveur1.ecouter();
    }

    // ==================== Attributs ====================
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

    // ==================== GETTERS & SETTERS ====================
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseIP() {
        return this.adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocole() {
        return this.protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    // ==================== Méthodes ====================
    public void afficher() { // Affiche les informations du serveur
        System.out.println("Nom : " + this.nom);
        System.out.println("Adresse IP : " + this.adresseIP);
        System.out.println("Port : " + this.port);
        System.out.println("Protocole : " + this.protocole);
    }

    public void ecouter() { // Méthode pour écouter les clients
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(this.port);
                System.out.println("Serveur en écoute sur le port " + this.port);

                Socket socket = serverSocket.accept();
                System.out.println("Connexion établie avec le client " + socket.getInetAddress().getHostAddress());

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                byte[] buffer = new byte[1024];
                int nbOctets = inputStream.read(buffer);

                String message = new String(buffer, 0, nbOctets);
                System.out.println("Message reçu : " + message);

                String messageReponse = "Bonjour, je suis le serveur " + this.nom;
                outputStream.write(messageReponse.getBytes());

                socket.close();
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}