package istic.pr.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;


public class ClientTCP {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        //créer une socket client
    	int portDuServeur = 9999; 
    	String adresseDuServeur = "localhost"; 
    	Socket socketVersLeServeur = new Socket(adresseDuServeur, portDuServeur);
    	
    	System.out.println("Connecté à " + socketVersLeServeur.getInetAddress());
    	
        //créer reader et writer associés
    	PrintWriter out = creerPrinter(socketVersLeServeur);
    	BufferedReader in = creerReader(socketVersLeServeur);
    	
        //Tant que le mot «fin» n’est pas lu sur le clavier,
    	String msglu;
        //Lire un message au clavier
    	while ( !(msglu = lireMessageAuClavier()).equals("fin") ) {
    	System.out.println("j'envoie : "+msglu);
    	envoyerMessage(out,msglu);
    	System.out.println("j'ai envoye : "+msglu);
    	
        //envoyer le message au serveur
        //recevoir et afficher la réponse du serveur
    	String fun;
    	while ( (fun = recevoirMessage(in)) != null ) {
        System.out.println("le serveur dit : "+fun);
    	}
    	
    	}
    }

    public static String lireMessageAuClavier() throws IOException {
        //lit un message au clavier en utilisant par exemple un BufferedReader
        //sur System.in
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	return br.readLine();
    }

    public static BufferedReader creerReader(Socket socketVersUnClient)
    throws IOException {
        //identique serveur
    	return new BufferedReader(new InputStreamReader(socketVersUnClient.getInputStream()));
    }

    public static PrintWriter creerPrinter(Socket socketVersUnClient) throws
    IOException {
        //identique serveur
    	return new PrintWriter(new OutputStreamWriter(socketVersUnClient.getOutputStream()));
    }

    public static String recevoirMessage(BufferedReader reader) throws
    IOException {
        //Récupérer une ligne
        //Retourner la ligne lue ou null si aucune ligne à lire.
    	return reader.readLine();
    }

    public static void envoyerMessage(PrintWriter printer, String message)
    throws IOException {
       printer.println(message);
       printer.flush();
    }

}
