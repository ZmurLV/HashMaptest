package test.pack;

import auxillary.Randoms;
import java.util.logging.Level;
import java.util.logging.Logger;
import myhashmap.MyEntry;
import myhashmap.MyHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyHashMap {

    private static final Logger LOG = Logger.getLogger(TestMyHashMap.class.getName() );
    
    public TestMyHashMap() {
    }

    @Test
    public void testPutGet() {
        MyHashMap hm = new MyHashMap();
        hm.put( "entry1","aaa" );
        hm.put( "entry2","bbb" );
        hm.put( "entry3","ccc" );
        hm.put( 50,"cfc" );
        
        MyEntry entry = (MyEntry) hm.get("entry1");
        assertEquals( "aaa", entry.value );
        entry = (MyEntry) hm.get("entry2");
        assertEquals( "bbb", entry.value );
        entry = (MyEntry) hm.get("entry3");
        assertEquals( "ccc", entry.value );
        entry = (MyEntry) hm.get(50);
        assertEquals( "cfc", entry.value );
        entry = (MyEntry) hm.get("entry50");
        assertEquals( null, entry );        
    }
    @Test
    public void testRemove() {
        MyHashMap hm = new MyHashMap();
        hm.put( "entry1","aaa" );
        hm.put( "entry2","bbb" );
        hm.put( "entry3","ccc" );
        
        hm.remove( "entry2" );
        hm.remove( "entry50" );
        Object entry1 = hm.get("entry2");
        assertEquals( null, entry1 );
        MyEntry entry = (MyEntry) hm.get("entry1");
        assertEquals( "aaa", entry.value );
    }
    @Test
    public void testSize() {
        MyHashMap hm = new MyHashMap();
        hm.put( "entry1","aaa" );
        hm.put( "entry2","bbb" );
        hm.put( "entry3","ccc" );
        assertEquals( 3, hm.size() );
    }
    @Test
    public void testManyVal() {
        MyHashMap hm = new MyHashMap();
        for(int i = 0; i<200; i++){
            String randStr = "";
            for( int j = 0; j<4; j++ ){
                randStr += Randoms.getRandChar( 48, 122 );
            }
            hm.put( "entry_"+i, randStr );
        }
        hm.remove( "entry_2" );
        hm.remove( "entry_50" );
        assertEquals( 198, hm.size() );
        hm.remove( "entry_33" );
        hm.remove( "entry_1" );
        assertEquals( 196, hm.size() );
        
        hm.put( "entry_1","aaa" );
        MyEntry entry = (MyEntry) hm.get("entry_1");
        assertEquals( "aaa", entry.value );
        hm.put( "entry_70","abc" );
        entry = (MyEntry) hm.get("entry_70");
        assertEquals( "abc", entry.value );
        
        hm.put( "entry_70",5000 );
        entry = (MyEntry) hm.get("entry_70");
        assertEquals( 5000, entry.value );
        
        assertEquals( 197, hm.size() );
    }
}
