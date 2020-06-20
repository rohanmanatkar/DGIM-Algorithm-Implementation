import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class P2 {
	
	
	public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		
		try {
			//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String per, connect, ip, port;
			int p = 0, perc;
			long timestamp = 1;
			InputCount inputCount = new InputCount();
			
			
			/*
			 * Calculate percentage
			 * */
			per = bf.readLine();
			perc = Integer.parseInt(per.substring(0, per.length() - 1));
			perc = (int)100 / perc;
			/*
			 * Store ip and port
			 * */
			connect = bf.readLine();
			String[] s = connect.split(":");
			ip = s[0];
			port = s[1];
			try {
				p = Integer.parseInt(port);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * Run Socket thread - It takes numbers as input.
			 * Run Query thread - It takes user query as input.
			 * */
			SocketClass obj1 = new SocketClass(ip, p, perc, timestamp, inputCount);
			Query obj2 = new Query(inputCount);
			
			obj1.start();
			obj2.start();
			
		} catch (NumberFormatException e) {
			System.out.println("Input invalid!");
		} catch (IOException e) {
			System.out.println("Invalid input!!");
		} catch (Exception e) {
			System.out.println("Input invalid");
		}
	}
	
	public synchronized void consolePrint(String print, int type) {
		if(type == 1) {
			System.out.print(print);
		}
		else {
			System.out.println(print);
		}
		
	}
}
