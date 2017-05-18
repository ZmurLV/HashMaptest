package myhashmap;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap implements CustomMap {

    //private ArrayList<LinkedList> buckets;
    private final ArrayList<MyEntry>[] buckets;
    public static final int CAPACITY = 100;

    /**
     * Design and Implement Simple Hashmap according to the following
     * interface:... Number of buckets is to be fixed to 100.<br>
     * Hash collisions can be either handled (preferred) or ignored.<br>
     * Hash function ought to be as simple as possible.<br>
     * Putting another value with same key should replace the old value.
     *
     */
    public MyHashMap() {
        buckets = new ArrayList[CAPACITY];
        java.util.Arrays.fill( buckets, null );//new LinkedList());
    }

    @Override
    public void put( Object key, Object value ) {
        Address address = getAddress( key );
        if ( address == null ) {
            MyEntry entry = new MyEntry( key, value );
            int hash = hash( entry.key );
            if ( buckets[hash] == null ) {
                buckets[hash] = new ArrayList();
            }
            buckets[hash].add( entry );
        }
        else//replace value if found key
        {
            MyEntry entry = get( address );            
            entry.setValue( value );            
        }
    }

    @Override
    public Object get( Object key ) {
        Address address = getAddress( key );
        if ( address != null ) {
            return buckets[address.getHash()].get( address.getIndex() );
        }
        return null;
    }
    
    private MyEntry get(Address address){
        return buckets[address.getHash()].get( address.getIndex() );
    }

    @Override
    public Object remove( Object key ) {
        Object result = null;
        //find key, remove, link previous to next        
        Address address = getAddress( key );
        if ( address != null ) {
            result = buckets[address.getHash()].get( address.getIndex() );
            buckets[address.getHash()].remove( address.getIndex() );
        }
        return result;
    }

    private Address getAddress( Object key ) {
        int hash = hash( key );
        //System.out.println( "Hash = " + hash );
        int counter = 0;
        if ( buckets[hash] != null ) {
            ArrayList<MyEntry> list = buckets[hash];
            for ( MyEntry entry : list ) {
                //System.out.println( "Counter = " + counter );
                if ( entry.key.equals( key ) ) {
                    return new Address( hash, counter );
                }
                counter++;
            }
            list = null;
        }
        return null;
    }

    @Override
    public int size() {
        //loop through all buckets, count each non-null and return sum
        int result = 0;
        for ( ArrayList<MyEntry> list : buckets ) {
            if ( list != null ) {
                for ( MyEntry entry : list ) {
                    if ( entry != null ) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public int hash( Object key ) {
        return Math.abs( key.hashCode() ) % CAPACITY;
    }
}
