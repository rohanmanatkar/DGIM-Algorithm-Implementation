import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query extends Thread {
	
	private int y = 0;
	/*
	 * Query initialized with input count of numbers
	 * */
	InputCount count = new InputCount();
	Query(Object ts) {
		this.count = (InputCount) ts;
	}
	
	
	public void run() {
		String query = "What is the sum for last ([0-9]*) integers[?]";
		
		String q;
		long num;
		
		try {
			//BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			while((q = P2.bf.readLine()) != null ) {
					if(q.equalsIgnoreCase("end") || q.equalsIgnoreCase("end")) {
						return;
					}
					long sum;
				    Pattern pattern = Pattern.compile(query);
				    Matcher match = pattern.matcher(q);
				    if(match.matches()) {
				    	num = Long.parseLong(match.group(1));
				    	if(count.getInputCount() - num < 0) {
				    		System.out.println("Not enough numbers!");
				    		return;
				    	}
				    	String output = "The estimated sum is : ";
				    	P2 obj = new P2();
					    obj.consolePrint(q, y);
					    
					    DGIMAlgo obj1 = new DGIMAlgo();
					    
					    sum = obj1.findSum(count.getInputCount() - num + 1);
					    output += Long.toString(sum);
					    obj.consolePrint(output, y);
				    }
				    
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input!");
		} catch (IOException e) {
			System.out.println("Invalid input!!");
		}
		
	}
}
