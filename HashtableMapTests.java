// --== CS400 Project One File Header ==--
// Name: Sanjay Thasma
// CSL Username: thasma
// Email: thasma@wisc.edu
// Lecture #: Lec 004 3:30-4:20 MWF
// Notes to Grader: Used a GetArray method to help with tester

import java.util.NoSuchElementException;

//class made to test the HashaleMap.java class
public class HashtableMapTests {
	
	/**this test will check constructor and the get method
	 * 
	 * @return true if the tests works and false if it does not
	 */
	public static boolean test1() {
		//the two main constructors
		HashtableMap a= new HashtableMap();
		HashtableMap b= new HashtableMap(12);
		
		//defalt capacity is to be 8
		if(a.getCapacity()!=8) {
			return false;
		}
		
		//set capacity of b is 12
		if(b.getCapacity()!=12) {
			return false;
		}
		
		//a should be empty
		if(a.getSize()!=0) {
			return false;
		}
		
		//b should also be empty
		if(b.getSize()!=0) {
			return false;
		}
	
		//other get method will be used enough in other tests to verify is usage
		return true;
		}
	
	/**
	 * test 2 tries to work on the put method, resizing and rehashing
	 * @return true if the methods of Hashtable map are true and the put value works, false if not
	 */
	public static boolean test2() { 
		HashtableMap a= new HashtableMap(10);
		
		a.put(1, 1);
		a.put(2, 2);
		a.put(3, 3);
		a.put(4, 4);
		a.put(5, 5);
		a.put(6, 6);
		//this is the size before resizing is needed
		if(a.getArray().length!=10) {
			return false;
		}
		
		
		//supposed to rehash and get a new capacity
		a.put(7, 7);
		a.put(8, 8);
		
		//System.out.println(a.getSize());
		if(a.getSize()!=8)
		{
			return false;
		}
		
		//resize capacity 
		if(a.getArray().length!=20) {
			return false;
		}
		
		
		return true;
		}
	
	/**
	 * testing remove to make sure it works properly
	 * @return if remove method works, false if not
	 */
	public static boolean test3() {
		HashtableMap a = new HashtableMap(10);
		a.put(1, 1);
		a.put(2, 2);
		a.put(3, 3);
		a.put(4, 4);
		a.put(5, 5);
		a.put(6, 6);
		//supposed to rehash and get a new capacity
		a.put(7, 7);
		a.put(8, 8);
		
		//size should be 8
		if(a.getSize()!=8)
		{
			
			return false;
		}
		
	    a.remove(8); //size should drop one and key 8 location should be a sentinel value
		
		//A (Key) and (S) (Value) is the sentinel Value for the class
		if(a.getArray()[8].getValue()!="S") {
			return false;
		}
		
		
		if(a.getSize()!=7)
		{
		//size should have dropped
			return false;
		}
		
		a.remove(7);
		
		if(a.getSize()!=6)
		{//size should have dropped again
			return false;
		}
		
		a.remove(6);
		
		if(a.getSize()!=5)
		{
			//size should have dropped again
			return false;
		}
		
		
		//small subtest to make sure put will replace a sentinel value with an addition
		if(a.getArray()[6].getValue()!="S") {
			return false;
		}
		a.put(6, 6); //sentinel value should be gone
		if(!a.getArray()[6].getValue().toString().equals("6")) {
			return false;
		}
		
		//System.out.println(a.getSize());
		return true; /* test code here */ 
		}
	
	
	
	/**
	 * testing collisions for the hashtable implementation
	 * @return true if the implementation works with expected result, false if not
	 */
	public static boolean test4() //collision testing
	{
		HashtableMap a = new HashtableMap(10);
		a.put(1, 1); //this has the key of 1
		a.put(11, 2); //key of one
		a.put(21, 3); //key of one
		a.put(22, 4); //key of 2
		
		if(a.getSize()!=4) { //should till be 4 due to linear probing
			return false;
		}
		
		//expected result values; null, 1,2,3,4, null...(rest are null)
	if(!a.getArray()[4].getValue().toString().equals("4")) {
		//this should be a.put(22, 4); //key of 2
		//because index for its key was taken 
		return false;
	}
	if(!a.getArray()[3].getValue().toString().equals("3")) {
		//this should be a.put(21, 3); //key of one
		//because index for its key was taken 
		return false;
	}
	if(!a.getArray()[2].getValue().toString().equals("2")) {
		//this should be a.put(11, 2); //key of one
		//because index for its key was taken 
		return false;
	}
		
		return true;/* test code here */ 
		
		
		}
	
	/**
	 * error testing and catches for the Hashtable.map class methods
	 * @return true if the proper error are thrown, false if not
	 */
	public static boolean test5()//error testing 
	{ 
		HashtableMap a = new HashtableMap(10); //tests to make sure the error is thrown properly for a bad get call
		a.put(1, 1);
		
		try {
			a.get(2);
			return false;
		}
		catch(NoSuchElementException e) {
			
		}
		
		try {
		a.put(1, 1);
		return false;
		}
		catch(IllegalArgumentException e) {
			
		}
		
		try {
			a.put(null, 1);
			return false;
			}
			catch(IllegalArgumentException e) {
				
			}
		
		try {
			a.remove(2);
			return false;
			}
			catch(NoSuchElementException e) {
				
			}
		
		
		return true;/* test code here */ 
	}
	
	/**
	 * additional test to work alongside test2 
	 * testing to see the sentinel value is removed when a new array is created
	 * @return if the sentinel values are removed, false if not
	 */
	public static boolean test6() 
	{ 
		//A (Key) and (S) (Value) is the sentinel Value for the class
		HashtableMap a = new HashtableMap(10);
		a.put(1, 1);
		a.put(2, 2);
		a.put(3, 3);
		a.put(4, 4);
		a.put(5, 5);
		a.put(6, 6);
		
				//should be 6
		if(a.getSize()!=6)
		{
			
			return false;
		}
		
	    a.remove(6);
		
	    
		//A (Key) and (S) (Value) is the sentinel Value for the class
		if(a.getArray()[6].getValue()!="S") {
		//should be a sentinel value
			return false;
		}
		a.put(7, 7);
		a.put(8, 8);
		//new array should have been created so the sentinal values should be gone
		
		//System.out.println(a.getCapacity());
		
		//looking to see if any sentinel values exist
		for(int i = 0; i<a.getCapacity();i++) {
			if(a.getArray()[i]!=null) {
				if(a.getArray()[i].getValue()=="S") {
					return false;
				}
			}
			
		}
		return true;
	}

    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 passed: " + test1());
        System.out.println("Test 2 passed: " + test2());
        System.out.println("Test 3 passed: " + test3());
        System.out.println("Test 4 passed: " + test4());
        System.out.println("Test 5 passed: " + test5());
        System.out.println("Test 6 passed: " + test6());
    }
}