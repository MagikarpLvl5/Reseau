package istic.pr.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

public class ServeurTCP {

    public static void main(String[] args) throws IOException {
		int portEcoute = 9999;
		ServerSocket socketServeur = new ServerSocket(portEcoute);
		try {
	    	while (true) {
	    		System.out.println("Attends les clients");
	    		Socket socketVersUnClient = socketServeur.accept();
	    		System.out.println("Le client " + socketVersUnClient.getInetAddress() + " est connect�");
	    		traiterSocketCliente(socketVersUnClient);
	    		socketVersUnClient.close();
			}
		}
		catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			socketServeur.close();
		}
    }

    public static void traiterSocketCliente(Socket socketVersUnClient) throws IOException {
        //Cr�er printer et reader
    	BufferedReader lecture = creerReader(socketVersUnClient);
    	PrintWriter ecrire = creerPrinter(socketVersUnClient);
    	
    	String fun;
  
    	while ( (fun = recevoirMessage(lecture)) != null ) {
    	System.out.println("j'ai recu : "+fun);
    	envoyerMessage(ecrire,fun);
    	System.out.println("je renvoie : "+fun);
    	}
        //Envoyer message au client via envoyerMessage
        //Si plus de ligne � lire fermer socket cliente
    }

    public static BufferedReader creerReader(Socket socketVersUnClient)
    throws IOException {
        //cr�� un BufferedReader associ� � la Socket
    	return new BufferedReader(new InputStreamReader(socketVersUnClient.getInputStream()));
    }

    public static PrintWriter creerPrinter(Socket socketVersUnClient) throws
    IOException {
        //cr�� un PrintWriter associ� � la Socket
    	return new PrintWriter(new OutputStreamWriter(socketVersUnClient.getOutputStream()));
    }

    public static String recevoirMessage(BufferedReader reader) throws
    IOException {
        //R�cup�rer une ligne
        //Retourner la ligne lue ou null si aucune ligne � lire.
    	return reader.readLine();
    }

    public static void envoyerMessage(PrintWriter printer, String message)
    throws IOException {
       printer.print(message);
       printer.flush();
    }

}