package istic.pr.socket.address;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AfficheInterfaces {

	public static void main ( String[] args ) {
		try {
		Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
		while (niList.hasMoreElements()) {
			NetworkInterface niCurrent = niList.nextElement();
			System.out.println(niCurrent.getName()+":"+niCurrent.getDisplayName());
			Enumeration<InetAddress> inAdress = niCurrent.getInetAddresses();
			
			while(inAdress.hasMoreElements()){
				InetAddress IACur = inAdress.nextElement();
				System.out.println("->"+IACur);
			}
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
