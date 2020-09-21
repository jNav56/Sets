import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	// Variable to act as the internal storage container for this set
    private ArrayList<E> myCon;

    /* create an empty SortedSetm*/
    public SortedSet() {
    	myCon = new ArrayList<E>();
    }

    /* create a SortedSet out of an unsorted set.
     * O(NlogN) when otherSet is UnsortedSet, otherwise it's O(N)
     * @param other != null */
    public SortedSet(ISet<E> other) {
    	// Check precondition, other must not be null
    	if(other == null) {
    		throw new IllegalArgumentException("other is null");
    	}
    	myCon = new ArrayList<E>();
    	
    	if(other instanceof SortedSet<?>) {
    		// Traverse through each member of set
    		for(E x: other) {
    			add(x);
    		}
    	} else {
    		// Traverse through each member of other set 
	    	for(Iterator<E> it = other.iterator(); it.hasNext();) {
	    		binaryInsert(it.next()); // Insert each member separately, O(logN)
	    	}
    	}
    }
       
    /* Helper method for constructor to insert value in set
     * Uses format of code for binary search from class slides */
    private void binaryInsert(E val) {
    	int start = 0;
    	int last = size();	
    	// Continue to look for a spot until start == last
    	while(start < last) {
    		// Get middle point between start and last
    		int mid = (start + last) / 2;
    		
    		// Change last if val is in bottom half, otherwise change start
    		if(val.compareTo(myCon.get(mid)) < 0)
    			last = mid;
    		else
    			start = mid + 1;
    	}
    	// Finally found spot
    	myCon.add(start, val);
    }
    
    /* Return true if this set changed as a result of adding item, false otherwise.
     * O(N) when adding at random or descending order, otherwise it's O(1)
     * <br> item != null */
    public boolean add(E item) {
    	// Check precondition, item must not be null
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	// If the set is empty or item > max() immediately add and return true
    	// This will make the method O(1) when we add in order
    	if(size() == 0 || item.compareTo(max()) > 0 ) {
    		myCon.add(item);
    		return true;
    	// If the item is in the set, return false
    	} else if(contains(item)) {
    		return false;
    	}
    	// At this point we know we can add the item
    	boolean boo = true;
    	int index = 0;
    	// Traverse through set
    	while(boo && index < size()) {
    		// If item is less than the current spot, then add item at current spot
    		if(item.compareTo(myCon.get(index)) < 0) {
    			myCon.add(index, item);
    			boo = false;
    		}
    		index++;
    	}
    	return true;
    }
    
    /* Return true if this set changed as a result of adding otherSet, false otherwise
     * O(N) when otherSet is SortedSet, otherwise it's O(N^2)
     * @param otherSet != null */
    public boolean addAll(ISet<E> otherSet) {
	   // Check precondition, otherSet must not be null
	  if(otherSet == null) {
		  throw new IllegalArgumentException("otherSet is null");
	  }
	  int currentSize = size(); // Get size before code is run
	  
	  // Do merge based sort if otherSet is a SortedSet, otherwise call parent addAll method
	  if(otherSet instanceof SortedSet<?>) {
		  myCon = merge((SortedSet<E>) otherSet);
	  } else {
		  super.addAll(otherSet);
	  }
	  
	  // Return true if size() is different from the size before the code
	  return currentSize != size();
   }
   
   /* Helper method for constructor to insert value in set
    * Uses format of code for merge sort from class slides */
   private ArrayList<E> merge(SortedSet<E> otherSet) {
		ArrayList<E> mer = new ArrayList<E>();
		
		int index1 = 0;
		int index2 = 0;
		// Traverse through both sets until we reach the end of the smaller set
		while(index1 < size() && index2 < otherSet.size()) {
			E thisE = myCon.get(index1); // Get current member of this set
			E otherE = otherSet.myCon.get(index2); // Get current member of otherSet

			// Variable to hold smaller value of both members
			E res = thisE.compareTo(otherE) < 0? thisE: otherE;
			// If the member from this set is smaller, increase the index to access the next member from this set
			index1 += thisE.compareTo(otherE) < 0? 1: 0;
			// If the member from otherSet is smaller, increase the index to access the next member from otherSet
			index2 += thisE.compareTo(otherE) < 0? 0: 1;
			
			E merLast = !mer.isEmpty()? mer.get(mer.size() - 1): null;
			// If mer is empty or res is not already in, add to ArrayList
			if(mer.size() == 0 || res.compareTo(merLast) > 0) {
				mer.add(res);
			}
		}
		
		// Prepare reference and index for larger set
		SortedSet<E> rest = index1 < size()? this: otherSet;
		int index = index1 < size()? index1: index2;
		// Traverse through rest of larger set
		while(index < rest.size()) {
			E cur = rest.myCon.get(index); // Current member of larger set
			
			E merLast = !mer.isEmpty()? mer.get(mer.size() - 1): null;
			// If mer is empty or cur is not already in, add to ArrayList
			if(mer.size() == 0 || cur.compareTo(merLast) > 0) {
				mer.add(cur);
			}
			index++;
		}
		return mer;
   }
   
   /* Return true if this set contains the specified item, false otherwise.
    * O(logN)
    * <br>pre: item != null */
   public boolean contains(E item) {
	   // Check precondition, item is not null
	   if(item == null) {
		   throw new IllegalArgumentException("Item is null");
	   }
	   return binarySearch(item, 0, size());
   }
   
   /* Helper method for contains to find member in set
    * Uses format of code for binary search from class slides */
   private boolean binarySearch(E item, int first, int last) {
	   // If first and last are not the same, enter loop
	   if(first < last) {
		   // Get middle point between first and last
		   int mid = (first + last) / 2;
		   // Compare item to member in middle point
		   int compare = item.compareTo(myCon.get(mid));
    		
		   // Return true if the middle point is the same as item
		   // Otherwise if item is less than middle, then search in bottom half
		   // Otherwise search in upper half
		   if(compare == 0) {
			   return true;   
		   } else if(compare < 0) {
			   return binarySearch(item, first, mid);
		   }
		   return binarySearch(item, mid + 1, last);
    	}
    	return false;
	}

    /* Return true if all of the elements of otherSet are in this set.
     * O(N) when otherSet is SortedSet, otherwise it's O(N^2)
     * <br> pre: otherSet != null */
    public boolean containsAll(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    		
    	// If calling set is empty and otherSet isn't immediately return false;
    	if(size() == 0 && otherSet.size() != 0)
    		return false;
    	
    	// If otherSet is UnsortedSet, call parent containsAll method	
    	if(otherSet instanceof UnsortedSet<?>)
    		return super.containsAll(otherSet);
		
    	Iterator<E> itOther = otherSet.iterator();
    	int index = 0;
    	
    	// Traversing through otherSet
    	while(itOther.hasNext()) {
    		E eOther = itOther.next(); // Current spot of otherSet
    		E eThis = myCon.get(index); // Current spot of the calling set
    		
    		// Traverse through calling until eThis is no longer less than eOther
    		while(eThis.compareTo(eOther) < 0) {
    			index++;
    			eThis = myCon.get(index);
    		}
    		// Once we know that the eThis is not less than eOther, then return false 
    		// If they are not equal, then we know that eOther is not in this set or show up ahead
    		if(!eThis.equals(eOther))
    			return false;
    		index++;
    	}
    	return true;
    }
        
    /* Return an ISet of elements that are in this Set but not in otherSet.
     * O(N) when otherSet is SortedSet, otherwise it's O(NlogN)
     * <br>pre: otherSet != null */
    public ISet<E> difference(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	// Change otherSet to SortedSet if it's an UnsortedSet, otherwise cast
    	if (otherSet instanceof UnsortedSet<?>)
            otherSet = new SortedSet<E>(otherSet);
        else
            otherSet = (SortedSet<E>) otherSet;
    	
    	SortedSet<E> res = new SortedSet<E>();
    	
    	// Create an iterator for the calling set and otherSet
    	Iterator<E> it1 = iterator();
    	Iterator<E> it2 = otherSet.iterator();
    	
    	E temp = null; // E value to store member of otherSet once we leave its iterator
    	
    	// Traverse through calling set
    	while(it1.hasNext()) {
    		E e1 = it1.next(); // Current member of calling set
    		// Variable to determine if we traverse through otherSet
    		boolean boo = temp == null || temp.compareTo(e1) < 0;
    		
    		// Traverse through otherSet until the e2 is no longer less than e1
    		while(boo && it2.hasNext()){
    			E e2 = it2.next(); // Current member of otherSet
    			// If e2 becomes greater than or equal to e1, make temp equal e2 and exit loop
    			if(e2.compareTo(e1) >= 0) {
    				boo = false;
    				temp = e2;
    			}
    		}
    		// If temp is null or check if temp is equal to e1
    		if(temp == null || !e1.equals(temp))
    			res.myCon.add(e1);
    	}
    	return res;
    }

    /* Return true if other is a Set and has the same elements as this set
     * O(N) when otherSet is SortedSet, otherwise it's O(N^2) */
    public boolean equals(Object other) {
    	// Immediately return false if other is not a type of ISet object
    	if(!(other instanceof ISet<?>)) {
    		return false;
    	
    	// If other is an UnsortedSet object, call parent equals method
    	} else if(other instanceof UnsortedSet<?>) {
    		return super.equals(other);
    	}
    	
    	// Create an iterator for the calling set and otherSet
    	Iterator<E> it1 = iterator();
		Iterator<E> it2 = ((ISet<E>) other).iterator();
    	
		// Traverse through both sets
    	while(it1.hasNext() && it2.hasNext())
    		// Return false the first instance that both members are different
    		if(!it1.next().equals(it2.next()))
    			return false;
    	
    	// Never found a difference
    	return true;
    }

    /* Return a set that is the intersection of this set and otherSet.
     * O(N) when otherSet is SortedSet, otherwise it's O(NlogN)
     * pre: otherSet != null */
    public ISet<E> intersection(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	// Change otherSet to SortedSet if it's an UnsortedSet, otherwise cast
    	if (otherSet instanceof UnsortedSet<?>)
            otherSet = new SortedSet<E>(otherSet);
        else
            otherSet = (SortedSet<E>) otherSet;
    	
    	SortedSet<E> res = new SortedSet<E>();
    	
    	// Create an iterator for the calling set and otherSet
    	Iterator<E> it1 = iterator();
    	Iterator<E> it2 = otherSet.iterator();
    	
    	E temp = null; // E value to store member of otherSet once we leave its iterator
    	
    	// Traverse through calling set
    	while(it1.hasNext()) {
    		E e1 = it1.next(); // Current member of calling set
    		// Variable to determine if we traverse through otherSet
    		boolean boo = temp == null || temp.compareTo(e1) < 0;
    		
    		// Traverse through otherSet until the e2 is no longer less than e1
    		while(boo && it2.hasNext()) {
    			E e2 = it2.next(); // Current member of otherSet
    			// If e2 becomes greater than or equal to e1, make temp equal e2 and exit loop
    			if(e2.compareTo(e1) >= 0) {
    				boo = false;
    				temp = e2;
    			}
    		}
    		// If temp is not null and temp is equal to e1, add to res
    		if(temp != null && e1.equals(temp))
    			res.myCon.add(e1);
    	}
    	return res;
    }
    
    /* Return the smallest element in this SortedSet.
     * <br> pre: size() != 0 */
    public E min() {
    	if(size() == 0) {
    		throw new IllegalStateException("Size is 0");
    	}    	
    	return myCon.get(0);
    }

    /* Return the smallest element in this SortedSet.
     * <br> pre: size() != 0 */
    public E max() {
    	if(size() == 0) {
    		throw new IllegalStateException("Size is 0");
    	}    	 	
    	return myCon.get(size() - 1);
    }

    /* Return an Iterator object for the elements of this set. */
    public Iterator<E> iterator() {
    	return myCon.iterator();
    }

    /* Return the number of elements of this set.
     * pre: none */
    public int size() {
    	return myCon.size();
    }

    /* Return a set that is the union of this set and otherSet.
     * O(N) when otherSet is SortedSet, otherwise it's O(NlogN)
     * <br>pre: otherSet != null */
    public ISet<E> union(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	// Change otherSet to SortedSet if it's an UnsortedSet
    	if (otherSet instanceof UnsortedSet<?>)
            otherSet = new SortedSet<E>(otherSet);
    	
    	SortedSet<E> res = new SortedSet<E>();
    	
    	res.myCon = merge((SortedSet<E>) otherSet);
    	return res;
    }
}
