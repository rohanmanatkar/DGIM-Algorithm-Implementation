import java.util.ArrayList;
import java.util.List;

public class DGIMAlgo {
	
	static List<List<Bucket>> bucketTable = new ArrayList<List<Bucket>>();
	
	private int sameSize;
	private long timestamp;
	
	/*
	 * Initialize data structure.
	 * List of Bucket lists
	 * */
	public void create() {
		for(int i = 0; i < 16; i++) {
			ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
			bucketTable.add(bucketList);
		}
	}
	/*
	 * Stores value of number of buckets that can have the same size
	 * */
	public void setSameSize(int same_s) {
		sameSize = same_s;
	}
	/*
	 * Method - adds Bucket to a bucket list and then modifies the list
	 * Input - Number in binary format with it's Timestamp
	 * */
	public void getNumber(int[] num, long ts) {
		timestamp = ts;
		List<Bucket> bucketList = new ArrayList<Bucket>();
		for(int i = 0; i < 16; i++) {
			if(num[i] == 1) {
				bucketList = (List<Bucket>) bucketTable.get(i);
				Bucket bucket = new Bucket(timestamp, timestamp, 1);

				bucketList.add(bucket);
				bucketList = needUpdate(bucketList, 1);
				bucketTable.set(i, bucketList);
			}
			
		}

	}
	/*
	 * Method - checks if after adding a bucket to a given list, does it need to modified. If it does, the Bucket list is then modified.
	 * Input - Bucket list and count of bucket of given size
	 * Output - Modified Bucket list
	 * */
	public List<Bucket> needUpdate(List<Bucket> bList, long sizeCount) {
		int ct = 0;
		
		List<CountBucket> countBucket = new ArrayList<CountBucket>();
		for(int i = 0; i < bList.size(); i++) {
			CountBucket obj1 = new CountBucket();
			if(bList.get(i).getSize() == sizeCount) {
				obj1.setIndex(i);
				countBucket.add(obj1);
				ct += 1;
			}
		}
		if(ct > sameSize) {
			long updateSize = bList.get(countBucket.get(1).getIndex()).getSize();
			updateSize = updateSize * 2;
			long updatePrevTime = bList.get(countBucket.get(0).getIndex()).getPrev_time();
			bList.get(countBucket.get(1).getIndex()).setPrev_time(updatePrevTime);
			bList.get(countBucket.get(1).getIndex()).setSize(updateSize);
			bList.remove(bList.get(countBucket.get(0).getIndex()));
			bList = needUpdate(bList, updateSize);
		}
		return bList;
	}
	/*
	 * Method - finds the sum of last n integers as specified in query from user in Query thread.
	 * Input - last n integers whose sum needs to be calculated.
	 * Output - returns the sum of last n integers
	 * */
	public long findSum(long num) {
		long totalSum = 0;
		for(int i = 0; i < bucketTable.size(); i++) {
			List<Bucket> b = bucketTable.get(i);
			long sum = 0;
			boolean flag = true;
			for (int j = b.size() - 1; j >= 0; j--) {
				if(flag) {
					if(num <= b.get(j).getPrev_time()) {
						sum = sum + b.get(j).getSize();
					}else if(num > b.get(j).getPrev_time() && num <= b.get(j).getTime()) {
						sum = sum + ((b.get(j).getSize())/2);
						flag = false;
					}else {
						flag = false;
					}
				}
				
				
			}	
				
			totalSum += (sum * Math.pow(2, i));	

		}
		return totalSum;
	}

}
