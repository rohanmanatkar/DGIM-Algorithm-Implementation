import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClass extends Thread {
	
	private String ip;
	private int port;
	private int perc;
	private long timestamp;
	InputCount ic = new InputCount();
	private int x = 1;
	
	/*
	 * Socket class initialized with ip, port, number of buckets of same size, Timestamp, and input count.
	 * */
	SocketClass(String ip, int port, int perc, long timestamp, Object ct) {
		this.ip = ip;
		this.port = port;
		this.perc = perc;
		this.timestamp = timestamp;
		this.ic = (InputCount) ct;
	}
	
	
	/*
	 * Method - Reads input from Socket, then converts the number into binary and sends it to DGIMAlgo class, then prints the number. 
	 * */
	public void run() {
		Socket socket = null;
		
		try {
			
		    socket = new Socket(ip, port);
		    
		    BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    String val;
		    int[] binaryNum = new int[16];
		    DGIMAlgo obj1 = new DGIMAlgo();
		    obj1.create();
		    obj1.setSameSize(perc);
		    
		    while((val = socketInput.readLine()) != null) {
		    	
		    	try {
		    		
		    		int i = 0;
		    		int n = Integer.parseInt(val);
		    		while(i < 16) {
		    			if(n == 0) {
		    				binaryNum[i] = 0;
		    			}
		    			binaryNum[i] = n % 2;
		    			n = n / 2;
		    			i++;
		    		}
		    		if(n > 0) {
		    			System.out.println("value cannot be stored in 16 bit!!");
		    			return;
		    		}

		    		obj1.getNumber(binaryNum, timestamp);
		    		timestamp += 1;
		    		
		    		ic.setInputCount(timestamp - 1);
		    		
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    	
		    	
		    	P2 obj = new P2();
		    	obj.consolePrint(val + "\t", x);

		    }

		    socket.close();
		    
		} catch (IOException e) {
			System.out.println("Enter valid input!");
		} catch(NumberFormatException n) {
			System.out.println("Enter valid input!!");
		}
	}
}
