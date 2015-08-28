/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examexsem3socket;

public class CounterUser extends Thread {
   Counter counter;
    private int spectators;
    public CounterUser(Integer spect,Counter count) {
        this.spectators = spect;
        this.counter = count;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < spectators; i++) {
            counter.incrementValue();
            
        }
        
    }
    
}