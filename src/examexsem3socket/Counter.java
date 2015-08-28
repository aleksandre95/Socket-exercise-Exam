/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examexsem3socket;

import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author user
 */
public class Counter  {
    AtomicInteger ar = new AtomicInteger();
    
    public int getValue(){
       return ar.intValue();
    }
    public synchronized void incrementValue(){
        ar.incrementAndGet();
    }
    
    
}
