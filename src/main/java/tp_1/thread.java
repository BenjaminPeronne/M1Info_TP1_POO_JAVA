package tp_1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class thread extends Thread {
    // ==================== Attributs ====================
    private Socket clientSocket = null;
    ArrayList<Object> listeSend;
    ArrayList<Object> listeReceive;

    // ==================== Constructeur ====================
    public thread(Socket clientSocket, ArrayList<Object> listeSend, ArrayList<Object> listeReceive) {
        this.clientSocket = clientSocket;
        this.listeSend = listeSend;
        this.listeReceive = listeReceive;
    }

    // ==================== Méthodes ====================
    public void run() {
        try {
            // Création des flux d'entrée et de sortie
            OutputStream os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            InputStream is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // Synchronisation des threads
            synchronized (this) {
                if (listeSend.isEmpty()) {
                    System.out.println("Liste vide");
                    wait();
                } else {
                    System.out.println("Envoi de l'objet");
                    oos.writeObject(listeSend.get(0));
                    listeSend.remove(0);
                    wait();
                }
            }

            if (!this.isInterrupted()) {
                synchronized (this) {
                    Object obj = (Object) ois.readObject();
                    System.out.println("Objet reçu");
                    listeReceive.add(obj);
                }
            }

            // Fermeture des flux et du socket
            oos.close();
            ois.close();
            os.close();
            is.close();
            clientSocket.close();
            System.out.println("Socket fermé");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

}
