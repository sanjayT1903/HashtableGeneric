// --== CS400 Project One File Header ==--
// Name: Sanjay Thasma
// CSL Username: thasma
// Email: thasma@wisc.edu
// Lecture #: Lec 004 3:30-4:20 MWF
// Notes to Grader: Used a GetArray method to help with tester; sentinel value is defined in the KeyValue class constructor

import java.util.NoSuchElementException;
import java.util.Objects;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{

	
	//KeyValue is an generic Object that groups KeyType and ValueType for HashableMap class
	 class KeyValue<k, v> {
		protected k key ; //keyType stand in
		protected v value;// ValueType Stand in
		
		/**
		 * overloaded constructor for specified KeyValues
		 * @param KeyType k for specified key
		 * @param ValueType v for specified value
		 */
	 public KeyValue(k KeyType, v ValueType){
		 key = KeyType; 
		 value = ValueType;
	 }
	 
	 //default constructor for keyValue
	 public KeyValue(){
		 key = null;
		 value = null;
	 }
	 
	 /**
	  * Constructor for Sentiniel values////
	  * 
	  * @param v ValueType be set
	  */
	 public KeyValue(v ValueType) {
		 //for sentinel value
		 key=(k)"A";
		 value = ValueType;
	 }
	 
	 /**
	  * gets key of KeyValue
	  * @return k value of KeyValue
	  */
	 public k getKey() {
		 return key;
	 }
	 
	 /**
	  * gets value of KeyValue
	  * @return v value of Keyvalue
	  */
	 public v getValue() {
		 return value;
	 }

	

	}
	////////////////////////////
	
	private int capacity; //capacity of store
	protected KeyValue [] store; //array for KeyValue
	private int size; //counts value in the store
	
	@SuppressWarnings("unchecked")
	/**
	 * HashtableMap constructer with a set capacity
	 * @param capacity, the size of store
	 */
	public HashtableMap(int capacity) {
		this.capacity=capacity;
		size=0; 
		store =  new KeyValue[capacity] ;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * default hashtable constructor
	 * capacity is set to 8
	 */
	public HashtableMap() {
		capacity = 8;// with default capacity = 8
		size=0;
		store = new KeyValue[capacity] ;
	}
	
	/**
	 * gets the size fo the array Store
	 * @return size;
	 */
	 public int getSize() {
		 return size;
	 }
	
	 /**
	  * gets the capacity of array store
	  * @return capacity;
	  */
	 public int getCapacity() {
		 return capacity;
	 }
	 
	/**my own method for testing at one point
	 *@return store 
	 **/
	public KeyValue[] getArray(){
		return store;
	}
	 
	 
	 
	// 
	/**
	 * check whether a key maps to a value within this collection
	 * @param key for key of KeyValue
	 * @return true if key is contained, false if not
	 */
	    public boolean containsKey(KeyType key) {
	    	
	    	for(int i = 0; i<capacity;i++) //looks through value looking for key
	    	{
	    		if(store[i]!=null) {
	    		if(key== (KeyType)store[i].getKey()){
	    			//key was found
	    			return true;
	    		}
	    		}
	    	}
	    	//key was not found
	    	return false;
	    }
	 
	    
	    
	    /**
	     * retrieve the specific value that a key maps to
	     * @param key to get what key to return
	     * @throws NoSuchElementException when key is not stored in this collection
	     * @return value of same key
	     */
	    public ValueType get(KeyType key) throws NoSuchElementException {
	    //	int i = Math.abs(key.hashCode())%capacity;
	    
	     	int keyStore = Math.abs(key.hashCode())%capacity; //where the key should be
	     	
	     	//for loop works like linear probing and searches each index +1 if the key is not immiedely found
	     	//this is better than a normal for loop for speed if the key is close
	    	for(int k = Math.abs(key.hashCode())%capacity; k<capacity;k++) {
	    		
	    		
	    		if(store[k]!=null) {
	    			if(store[k].getKey()==key) {
	    				return (ValueType) store[k].getValue();
	    			}
	    		}
	    	}
	    	
	    	//if the keyStore value goes all the way to end of array 
	    	//then this for loop is to check values from 0 to keyStore value
	    	
	    	for(int t = 0; t<keyStore;t++)
	    	{
	    		if(store[t]!=null) {
	    			if(store[t].getKey()==key) {
	    				return (ValueType) store[t].getValue();
	    			}
	    		}
	    	}
	    	//}
	    	//at this point the entire array has been searched so the item cannot exist
	    	throw new NoSuchElementException("The item does not exist");
	    	
	    
	    }
	 
	
	    /**
	     *  add a new key-value pair/mapping to this collection
	     * @param key for the key of the value to be placed
	     * @param value for value to be added at placement of key
	     * @throws IllegalArgumentException when key is null or duplicate of one already stored
	     */
	    public void put(KeyType key, ValueType value) throws IllegalArgumentException{
	    	@SuppressWarnings("rawtypes")
			KeyValue sentiniel = new KeyValue("S");
	    	@SuppressWarnings("rawtypes")
			KeyValue use = new KeyValue(key,value); //makes a keyvalue to add
	    	if (key ==null) {
	    		//throws error
	    		throw new IllegalArgumentException("Key is null");
	    	}
	    	
	    	if(containsKey(key)) {
	    		//error case 
	    		throw new IllegalArgumentException("Key is taken");
	    	}
	    	
	    	//{ the absolute value of the key's hashCode() } modulus the HashtableMap's current capacity.
	    	int i = Math.abs(key.hashCode())%capacity; //calcualte the key placement
	    	
	    	//if the spot is empty or a sentinel value
	    	if(store[i]==null||store[i].getKey()=="A")  {
	    		
	    		store[i] = use;
	    		size++;
	    	}
	    	else{
	    		//Linear probing
	    		while(store[i]!=null)
	    		{
	    			if(i==(capacity-1)) {
	    				//makes the capacity go to zero after capacity of array is reached
	    				i=0;
	    			}
	    			else {
	    				i++;
	    			}
	    		
	    		}
	    		//set the new value at the null placement
	    		store[i] = use;
	    		size++;
	    	}
	    	
	    	
	    	
	    	//these two variable for calculations 
	   double sizeCheck = (double) size;
	   double capacityCheck = (double) capacity;
	   
	   //if its 0.7 or above a resize and rehash is necessary
	   if(sizeCheck/capacityCheck>=0.7) { //exceeding load
		   @SuppressWarnings("unchecked")
		KeyValue<KeyType, ValueType>[] oldStored =store.clone(); //make a duplicate of the array
		   clear(); //clear the main array store
		   
		   int oldCapacity = capacity;
		   //keep oldCapacity to for future calculations
		   
		   capacity =capacity *2; //store will need a doubled length
		 //  System.out.println(capacity);
		   
		   store =  new KeyValue[capacity] ; //new store array with null values
		
		   
		   
		   //rehash
		   for(int g = 0; g<oldCapacity;g++) {
			   if(oldStored[g]!=null) {
				if((oldStored[g].getValue()!="S"))
				{
					//ONLY add values that are not null or sentinel values from the oldStored array
					//Only real values are rehashed
				   put(oldStored[g].getKey(),oldStored[g].getValue());
				}
				
			   }
		   }
		  
	    }
	   }
	    
	    
	    
	    
	
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    /**
	     *  remove the mapping for a given key from this collection
	     * @param key for key to be removed
	     * @return Value of key that was removed
	     * @throws NoSuchElementException  when key is not stored in this collection
	     */
		public ValueType remove(KeyType key) throws NoSuchElementException
	    {
	    	if(containsKey(key)==false) {
	    		throw new NoSuchElementException("does not contain key for remove");
	    	}
	    	
	    	else {
	    		
		    	for(int i = 0; i<capacity;i++) {
		    		if(store[i]!=null) {
		    			if(key== (KeyType)store[i].getKey()){
		    				ValueType a = (ValueType)store[i].getValue();
		    				KeyValue sentiniel = new KeyValue("S");
		    				store[i] = sentiniel;
		    				size--;
		    				return a;
		    		}
		    		
		    		}
		    	}
	    	}
	    	return null;
	    }

	    
	    // remove all key-value pairs from this collection
	    public void clear() {
	    	store=  new KeyValue[capacity];
	    	size = 0;
	    }
}
