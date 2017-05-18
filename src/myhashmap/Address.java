package myhashmap;

public class Address {
    private final int hash;
    private final int index;

    public Address( int hash, int index ) {
        this.hash = hash;
        this.index = index;
    }

    public int getHash() {
        return hash;
    }

    public int getIndex() {
        return index;
    }
    
}
