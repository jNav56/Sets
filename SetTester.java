import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*

*****DR. JEKYL AND MR HYDE*****

Size (kb): 160
Total Words: 28,662
Distinct Words: 6,742

CS314 SortedSet Time: 0.1623213 seconds
CS314 UnsortedSet Time: 0.2483096 seconds
Java HashSet Time: 0.0255984 seconds
Java TreeSet Time: 0.0329827 seconds

*****PARADISE LOST*****

Size (kb): 483 ----- 3.02x
Total Words: 83,140 ----- 2.90x
Distinct Words: 17,958 ----- 2.66x

CS314 SortedSet Time: 0.7966622 seconds ----- 3.91x
CS314 UnsortedSet Time: 1.9920247 seconds ----- 6.02x
Java HashSet Time: 0.0709295 seconds ----- 2.77x
Java TreeSet Time: 0.0919071 seconds ----- 2.79x

*****MIDDLEMARCH*****

Size (kb): 1,805 ----- 3.74x
Total Words: 319,344 ----- 3.84x
Distinct Words: 32,275 ----- 1.80x

CS314 SortedSet Time: 2.0951661 seconds ----- 2.63x
CS314 UnsortedSet Time: 7.7285505 seconds ----- 3.88x
Java HashSet Time: 0.2269805 seconds ----- 3.20x
Java TreeSet Time: 0.320827 seconds ----- 3.50x

*****DON QUIXOTE*****

Size (kb): 2,293 ----- 1.27x
Total Words: 428,919 ----- 1.34x
Distinct Words: 32,820 ----- 1.02x

CS314 SortedSet Time: 2.1136674 seconds ----- 1.01x
CS314 UnsortedSet Time: 8.2594361 seconds ----- 1.07x
Java HashSet Time: 0.3308079 seconds ----- 1.46x
Java TreeSet Time: 0.3723393 seconds ----- 1.16x

*****QUESTIONS*****
What do you think the order (Big O) of the two processText methods are for each kind of Set? 
Assume N = total number of words in a file and M = number of distinct words in the file.
 M = the size of the set when finished.
	
	SortedSet: O(M)
	UnsortedSet: O(M^2)
	HashSet: O(N)
	TreeSet: O(N)
	
What are the orders (Big O) of your add methods? What do you think the Big O of the HashSet
and TreeSet add methods are?
	
	SortedSet: add is O(N)
	UnsortedSet: add is O(N) 
	HashSet: add is O(N)
	TreeSet: add is O(N)
	
What are the differences between HashSet and TreeSet when printing out the contents of the Set?
	
	HashSet is not sorted while TreeSet is sorted 


CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

In order to implement intersection, union, or difference, we would need to
reference one of the other methods if we do not want to directly reference
SortedSet or UnsortedSet. Implementing all 3 methods, we would have no choice
but to reference SortedSet or UnsortedSet or risk continuously calling each set
from the other three.

*/


public class SetTester {
	
    public static void main(String[] args){    	
    	//builtInTests();
        
    	myTests();
       
        // Uncomment this section when ready to 
        // run your experiments
        //                try {
        //                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //                }
        //                catch(Exception e) {
        //                    System.out.println("Unable to change look and feel");
        //                }
        //        		Scanner sc = new Scanner(System.in);
        //        		String response = "";
        //        		do {
        //        			largeTest();
        //        			System.out.print("Another file? Enter y to do another file: ");
        //        			response = sc.next();
        //        		} while( response != null && response.length() > 0 
        //                      && response.substring(0,1).equalsIgnoreCase("y") );
    }
    
    private static void myTests() {
    	
    	int i = 1;
    	String actual = "";
    	String expected = "";
    	
		UnsortedSet<String> unsorted1 = new UnsortedSet<String>();
		SortedSet<String> sorted1 = new SortedSet<String>();
		
		UnsortedSet<String> unsorted2 = new UnsortedSet<String>();
		SortedSet<String> sorted2 = new SortedSet<String>();
		
		UnsortedSet<String> unsorted3 = new UnsortedSet<String>();
		SortedSet<String> sorted3 = new SortedSet<String>();
		
		// Testing add method
    	System.out.println("*****Testing Add Method (UnsortedSet)*****");
    	System.out.println();
    	
    	unsorted1.add("G");
    	unsorted1.add("T");
    	unsorted1.add("O");
    	unsorted1.add("E");
    	unsorted1.add("B");
    	
    	System.out.println("Adding \"G\", \"T\", \"O\", \"E\", and \"B\" in that order");
        actual = unsorted1.toString();
        expected = "(G, T, O, E, B)";
        status(actual, expected, i++);
        
        System.out.println("*****Testing Add Method (SortedSet)*****");
    	System.out.println();
    	
    	sorted1.add("G");
    	sorted1.add("T");
    	sorted1.add("O");
    	sorted1.add("E");
    	sorted1.add("B");
    	sorted1.add("T");
    	
    	System.out.println("Adding \"G\", \"T\", \"O\", \"E\", \"B\", and \"T\" in that order");
        actual = sorted1.toString();
        expected = "(B, E, G, O, T)";
        status(actual, expected, i++);
    	
        // Testing addAll method
        System.out.println("*****Testing addAll Method (AbstractSet through UnsortedSet)*****");
    	System.out.println();
    	
    	unsorted2.add("Y");
    	unsorted2.add("O");
    	unsorted2.add("A");
    	unsorted2.add("Z");
    	unsorted1.addAll(unsorted2);
    	
    	System.out.println("Adding members from set (Y, O, A, Z)");
        actual = unsorted1.toString();
        expected = "(G, T, O, E, B, Y, A, Z)";
        status(actual, expected, i++);
        
        System.out.println("*****Testing addAll Method (SortedSet)*****");
    	System.out.println();
    	
    	sorted2.add("Y");
    	sorted2.add("O");
    	sorted2.add("A");
    	sorted2.add("Z");
    	sorted1.addAll(sorted2);
    	
    	System.out.println("Adding members from set (A, O, Y, Z)");
        actual = sorted1.toString();
        expected = "(A, B, E, G, O, T, Y, Z)";
        status(actual, expected, i++);
        
        // Testing clear method
        System.out.println("*****Testing clear Method (Applies for both classes)*****");
    	System.out.println();
    	
    	unsorted2.clear();
    	System.out.println("Cleared set (Y, O, A, Z)");
    	actual = unsorted2.toString();
        expected = "()";
        status(actual, expected, i++);
        
        // Testing contains method
        System.out.println("*****Testing contains Method (AbstractSet through UnsortedSet)*****");
        System.out.println();
        
        System.out.println("Searching for \"K\" in set (G, T, O, E, B, Y, A, Z)");
        actual = "" + unsorted1.contains("K");
        expected = "false";
        status(actual, expected, i++);
        
        System.out.println("*****Testing contains Method (SortedSet)*****");
        System.out.println();
        
        System.out.println("Searching for \"T\" in set (A, B, E, G, O, T, Y, Z)");
        actual = "" + sorted1.contains("T");
        expected = "true";
        status(actual, expected, i++);
        
        // Testing containsAll method
        System.out.println("*****Testing containsAll Method (AbstractSet through UnsortedSet)*****");
        System.out.println();
        
        unsorted2.add("B");
        unsorted2.add("E");
        unsorted2.add("G");
        unsorted1.remove("Y");
        unsorted1.remove("Z");
        
        System.out.println("Searching for set " + unsorted2 + " in set " + unsorted1);
        actual = "" + unsorted1.containsAll(unsorted2);
        expected = "true";
        status(actual, expected, i++);
        
        System.out.println("*****Testing containsAll Method (SortedSet)*****");
        System.out.println();
        
        sorted2.add("V");
        
        System.out.println("Searching for set " + sorted2 + " in set " + sorted1);
        actual = "" + sorted1.containsAll(sorted2);
        expected = "false";
        status(actual, expected, i++);
        
        // Testing differences method
        System.out.println("*****Testing difference Method (UnsortedSet)*****");
        System.out.println();
        
        unsorted3 = (UnsortedSet<String>) unsorted1.difference(unsorted2);
        
        System.out.println("Getting difference between set " + unsorted1 + " and set " + unsorted2);
        actual = unsorted3.toString();
        expected = "(T, O, A)";
        status(actual, expected, i++);
        
        System.out.println("*****Testing difference Method (SortedSet)*****");
        System.out.println();
        
        sorted3 = (SortedSet<String>) sorted1.difference(sorted2);
        
        System.out.println("Getting difference between set " + sorted1 + " and set " + sorted2);
        actual = sorted3.toString();
        expected = "(B, E, G, T)";
        status(actual, expected, i++);
        
        // Testing equals method
        System.out.println("*****Testing equals Method (AbstractSet through UnsortedSet)*****");
        System.out.println();
        
        unsorted1.remove("A");
        unsorted1.remove("T");
        unsorted2.add("O");
        
        System.out.println("Checking if set " + unsorted1 + " and set " + unsorted2 + " are equal");
        actual = "" + unsorted1.equals(unsorted2);
        expected = "true";
        status(actual, expected, i++);
        
        System.out.println("*****Testing equals Method (SortedSet)*****");
        System.out.println();
        
        sorted1.clear();
        sorted2.clear();
        sorted1.add("A");
        sorted1.add("B");
        sorted1.add("C");
        sorted1.add("D");
        sorted1.add("E");
        sorted2.add("B");
        sorted2.add("D");
        sorted2.add("C");
        
        System.out.println("Checking if set " + sorted1 + " and set " + sorted2 + " are equal");
        actual = "" + sorted1.equals(sorted2);
        expected = "false";
        status(actual, expected, i++);
        
        // Testing intersection method
        System.out.println("*****Testing instersection Method (UnsortedSet)*****");
        System.out.println();
        
        unsorted3 = (UnsortedSet<String>) unsorted1.intersection(unsorted2);
        unsorted2.add("L");
        unsorted2.add("M");
        
        System.out.println("Getting intersection between set " + unsorted1 + " and set " + unsorted2);
        actual = unsorted3.toString();
        expected = "(G, O, E, B)";
        status(actual, expected, i++);
        
        System.out.println("*****Testing instersection Method (SortedSet)*****");
        System.out.println();
        
        sorted3 = (SortedSet<String>) sorted1.intersection(sorted2);
        sorted2.add("F");
        
        System.out.println("Getting intersection between set " + sorted1 + " and set " + sorted2);
        actual = sorted3.toString();
        expected = "(B, C, D)";
        status(actual, expected, i++);
        
        // Testing iterator method
        System.out.println("*****Testing iterator Method (UnsortedSet)*****");
        System.out.println();
        
        actual = "";
        Iterator<String> it1 = unsorted1.iterator();
        while(it1.hasNext()) {
        	actual += it1.next() + " ";
        }
        System.out.println("Printing set " + unsorted1 + " with iterator");
        expected = "G O E B ";
        status(actual, expected, i++);
        
        System.out.println("*****Testing iterator Method (SortedSet)*****");
        System.out.println();
        
        actual = "";
        it1 = sorted1.iterator();
        while(it1.hasNext()) {
        	actual += it1.next() + " ";
        }
        System.out.println("Printing set " + sorted1 + " with iterator");
        expected = "A B C D E ";
        status(actual, expected, i++);
        
        // Testing remove method
        System.out.println("*****Testing remove Method (Applies for both classes)*****");
        System.out.println();
        
        sorted1.remove("C");
        
        System.out.println("Removing \"C\" from set (A, B, C, D, E)");
        actual = sorted1.toString();
        expected = "(A, B, D, E)";
        status(actual, expected, i++);
        
        // Testing size method
        System.out.println("*****Testing size Method (Applies for both classes)*****");
        System.out.println();
        
        System.out.println("Getting size unsorted set " + unsorted3 + " and sorted set " + sorted3);
        actual = unsorted3.size() + " and " + sorted3.size();
        expected = "4 and 3";
        status(actual, expected, i++);
        
        // Testing union method
        System.out.println("*****Testing union Method (AbstractSet through UnsortedSet)*****");
        System.out.println();
        
        unsorted1.remove("G");
        unsorted1.add("A");
        unsorted1.add("H");
        unsorted3 = (UnsortedSet<String>) unsorted1.union(unsorted2);
        
        System.out.println("Getting union between set " + unsorted1 + " and set " + unsorted2);
        actual = unsorted3.toString();
        expected = "(A, H, B, E, G, O, L, M)";
        status(actual, expected, i++);
        
        System.out.println("*****Testing union Method (SortedSet)*****");
        System.out.println();
        
        sorted3 = (SortedSet<String>) sorted1.union(sorted2);
        
        System.out.println("Getting union between set " + sorted1 + " and set " + sorted2);
        actual = sorted3.toString();
        expected = "(A, B, C, D, E, F)";
        status(actual, expected, i++);
	}

	private static void status(String act, String exp, int test) {
    	String res = exp.equals(act)? "Passed": "Failed";
    	System.out.println("Actual: " + act);
        System.out.println("Expected: " + exp);
        System.out.println("Test " + test + ": " + res);
        System.out.println();
    }
    
    public static void builtInTests() {
    	ISet<String> s1 = new UnsortedSet<String>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        //test 1
        if( s1.contains("A") )
            System.out.println("Passed test 1: add and contains methods SortedSet");
        else
            System.out.println("Failed test 1: add and contains methods SortedSet");

        //test 2
        s1.remove("A");
        if( !s1.contains("A") )
            System.out.println("Passed test 2: remove method UnsortedSet");
        else
            System.out.println("Failed test 2: remove method UnsortedSet");

        //test 3
        if( s1.size() == 2 )
            System.out.println("Passed test 3: size method UnsortedSet");
        else
            System.out.println("Failed test 3: size method UnsortedSet");

        ISet<String> s2 = new UnsortedSet<String>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        //test 4
        if( s2.containsAll(s1) )
            System.out.println("Passed test 4: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 4: containsAll method UnsortedSet");

        //test 5
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 5: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 5: containsAll method UnsortedSet");

        //test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<String>();
        expected.add("A");
        if( s3.equals(expected))
            System.out.println("Passed test 6: difference and equals methods UnsortedSet");
        else
            System.out.println("Failed test 6: difference and equals methods UnsortedSet");

        //test 7
        s3 = s2.union(s1);
        expected.add("B");
        expected.add("C");
        if( s3.equals(expected))
            System.out.println("Passed test 7: union and equals methods UnsortedSet");
        else
            System.out.println("Failed test 7: union and equals methods UnsortedSet");

        //test 8
        s3 = s2.intersection(s1);
        expected.remove("A");
        if( s3.equals(expected))
            System.out.println("Passed test 8: intersection and equals methods UnsortedSet");
        else
            System.out.println("Failed test 8: intersection and equals methods UnsortedSet");

        //sorted sets
        s1 = new SortedSet<String>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        //test 9
        if( s1.contains("A") )
            System.out.println("Passed test 9: add and contains methods SortedSet");
        else
            System.out.println("Failed test 9: add and contains methods SortedSet");

        //test 10
        s1.remove("A");
        if( !s1.contains("A") )
            System.out.println("Passed test 10: remove and contains methods SortedSet");
        else
            System.out.println("Failed test 10: remove and contains methods SortedSet");

        //test 11
        if( s1.size() == 2 )
            System.out.println("Passed test 11: size method SortedSet");
        else
            System.out.println("Failed test 11: size method SortedSet");

        s2 = new SortedSet<String>();
        s2.add("C");
        s2.add("A");
        s2.add("B");
        
        //test 12
        if( s2.containsAll(s1) )
            System.out.println("Passed test 12: containsAll method SortedSet");
        else
            System.out.println("Failed test 12: containsAll method SortedSet");

        //test 13
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 13: containsAll method SortedSet");
        else
            System.out.println("Failed test 13: containsAll method SortedSet");

        //test 14
        s3 = s2.difference(s1);
        expected = new SortedSet<String>();
        expected.add("A");
        if( s3.equals(expected))
            System.out.println("Passed test 14: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 14: difference and equals methods SortedSet");

        //test 14.1
        s3 = s1.difference(s2);
        expected = new SortedSet<String>();
        if( s3.equals(expected))
            System.out.println("Passed test 14.1: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 14.1: difference and equals methods SortedSet");

        //test 15
        s3 = s1.union(s2);
        expected = new SortedSet<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        if( s3.equals(expected))
            System.out.println("Passed test 15: union and equals methods SortedSet");
        else
            System.out.println("Failed test 15: union and equals methods SortedSet");
        
        //test 16
        s3 = s1.intersection(s2);
        expected.remove("A");
        if( s3.equals(expected))
            System.out.println("Passed test 16: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 16: intersection and equals methods SortedSet");

        // test 17
        s1.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while( good && it1.hasNext() )
            good = it1.next().equals(it2.next());
        if( good )
            System.out.println("Passed test 17: iterator and add methods SortedSet");
        else
            System.out.println("Failed test 17: iterator and add methods SortedSet");

        // test 18
        s1 = new UnsortedSet<String>();
        UnsortedSet<Integer> si1 = new UnsortedSet<Integer>();
        if(si1.equals(s1))
            System.out.println("Passed test 18: equals methods UnsortedSet");
        else
            System.out.println("Failed test 18: equals methods UnsortedSet");

        // test 19
        s1.add("is");
        s1.add("a");
        si1.add(12);
        si1.add(13);
        si1.add(12);
        if(!si1.equals(s1))
            System.out.println("Passed test 19: equals methods UnsortedSet");
        else
            System.out.println("Failed test 19: equals methods UnsortedSet");  

        // test 20
        ArrayList<Integer> ar = new ArrayList<Integer>();
        ar.add(12);
        ar.add(13);
        if(!si1.equals(ar))
            System.out.println("Passed test 20: equals methods UnsortedSet");
        else
            System.out.println("Failed test 20: equals methods UnsortedSet"); 

        // test 21
        Object obj1 = s1;
        s2 = new UnsortedSet<String>();
        s2.add("a");
        s2.add("is");
        Object obj2 = s2;
        if(obj1.equals(obj2)) 
            System.out.println("Passed test 21: equals methods UnsortedSet");
        else
            System.out.println("Failed test 21: equals methods UnsortedSet"); 

        // test 22
        s1 = new SortedSet<String>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        ISet<Integer> ss2 = new SortedSet<Integer>();
        ss2.add(12);
        ss2.add(15);
        ss2.add(12);
        ss2.add(15);
        if(!s1.equals(ss2)) 
            System.out.println("Passed test 22: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 22: equals methods SortedSet - different types");

        // test 23
        if(!ss2.equals(s1)) 
            System.out.println("Passed test 23: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 23: equals methods SortedSet - different types");
    }
    
    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}
