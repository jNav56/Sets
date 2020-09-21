import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	
	// Variable to act as the internal storage container for this set
	private ArrayList<E> myCon;

	/* Create an empty UnsortedSet */
	public UnsortedSet() {
		myCon = new ArrayList <E>();
	}
	
	/* Create an empty UnsortedSet 
	 * O(N^2) */
	public UnsortedSet(ISet<E> other) {
		if(other == null) {
    		throw new IllegalArgumentException("other is null");
    	}
		myCon = new ArrayList <E>();
		// Traverse through other and add each member to myCon
		for(Iterator<E> it = other.iterator(); it.hasNext();) {
			myCon.add(it.next());
		}
	}
	
	/* Return true if this set changed as a result of adding item, false otherwise.
	 * O(N)
     * <br> item != null */
    public boolean add(E item) {
    	// Check precondition, item must not be null
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	
    	// If item is already in set, then return false
    	if(contains(item)) {
    		return false;
    	}
    	// Item not in set, add it and return true
    	myCon.add(item);
    	return true;
    }
    
    /* Return an ISet of elements that are in this Set but not in otherSet.
     * O(N^2)
     * <br>pre: otherSet != null */
    public ISet<E> difference(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	UnsortedSet<E> res = new UnsortedSet<E>();
    	
    	// Traverse through calling set
    	for(Iterator<E> it = iterator(); it.hasNext();) {
			E cur = it.next();
			// If cur is not in the otherSet, then add to res
			if(!otherSet.contains(cur)) {
				res.myCon.add(cur);
			}
		}
    	return res;
    }
	
    /* Return a set that is the intersection of this set and otherSet.
     * O(N^2)
     * pre: otherSet != null */
    public ISet<E> intersection(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	UnsortedSet<E> res = new UnsortedSet<E>();
    	
    	// Traverse through calling set
    	for(Iterator<E> it = iterator(); it.hasNext();) {
			E cur = it.next();
			// If cur is in the otherSet, then add to res
			if(otherSet.contains(cur))
				res.myCon.add(cur);
		}   	
    	return res;
    }
    
    /* Return an Iterator object for the elements of this set. */
    public Iterator<E> iterator() {
    	return myCon.iterator();
    }
    
	/* Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set */
    public int size() {
    	return myCon.size();
    }
}
