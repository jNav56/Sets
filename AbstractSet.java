import java.util.Iterator;

public abstract class AbstractSet<E> implements ISet<E> {

	
	/* Return true if this set changed as a result of adding otherSet, false otherwise. 
	 * O(N^2)
     * <br>pre: item != null */
    public boolean addAll(ISet<E> otherSet) {
    	// Check precondition, otherSet must not be null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	int oldSize = size(); // Variable to store size before code runs
    	
    	// Traverse through otherSet and add members not in calling set
    	for(Iterator<E> it = otherSet.iterator(); it.hasNext(); ) {
    		E cur = it.next();
    		add(cur);
   	   }
    	return oldSize != size();
    }
    
    /* Make this set empty.
     * O(N)
     * <br>pre: none */
    public void clear() {
    	// Traverse through calling set and remove each member
    	for(Iterator<E> it = iterator(); it.hasNext(); ) {
    		it.next();
    		it.remove();	
    	}
    }
    
    /* Return true if this set contains the specified item, false otherwise. 
     * O(N)
     * <br>pre: item != null */
    public boolean contains(E item) {
    	// Check precondition, item must not be null
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}   	
    	// Traverse through calling set
    	for(Iterator<E> it = iterator(); it.hasNext(); ) {
    		// Return true once we find item
  		   if(item.equals(it.next()))
  			   return true;
  	   }
        return false;
    }
    
    /* Determine if all of the elements of otherSet are in this set.
     * O(N^2)
     * <br> pre: otherSet != null */
    public boolean containsAll(ISet<E> otherSet) {
    	// Check precondition, otherSet must not be null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}  	
    	// Traverse through otherSet
    	for(E val: otherSet) {
    		// Return false if val is not found at all in calling set
    		if(!contains(val))
    			return false;
    	}
    	return true;
    }
    
    /* Return true if other is a Set and has the same elements as this set despite order
     * O(N^2) */ 
    public boolean equals(Object other) {
    	// Immediately return false if other is not a type of ISet
    	if(!(other instanceof ISet<?>)) {
    		return false;
    	}
    	return containsAll((ISet<E>)other) && ((ISet<E>)other).containsAll(this);
    }
    
    /* Return true if this set changed as a result of this operation, false otherwise 
     * O(N)
     * pre: item != null */
    public boolean remove(E item) {
    	// Check precondition, item must not be null
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	// Traverse through calling set
    	for(Iterator<E> it = this.iterator(); it.hasNext();) {
    		// If item is found, call remove method in iterator
    		if(item.equals(it.next())) {
    			it.remove();
                return true;
            }
    	}
    	return false; // Never found item
    }
    
    /* Return a set that is the union of this set and otherSet.
     * O(N^2)
     * <br>pre: otherSet != null */
    public ISet<E> union(ISet<E> otherSet) {
    	// Check precondition, otherSet is not null
    	if(otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null");
    	}
    	// res will initially have members of the difference, this.set - otherSet
    	ISet<E> res = difference(otherSet);
    	
    	// Traverse through otherSet and each member to res
    	for(Iterator<E> it = otherSet.iterator(); it.hasNext();)
    		res.add(it.next());
    	return res;
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}
