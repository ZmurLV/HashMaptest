package myhashmap;

public class MyEntry {
   public final Object key;
   public Object value;   
   
   public MyEntry(Object key, Object value){
   this.key = key;
   this.value = value;   
   }
   public void setValue( Object value ) {
       this.value = value;
   }
   @Override
   public String toString(){       
       return "Key = " + key.toString() + "; Value = " + value.toString();
   }
 }
