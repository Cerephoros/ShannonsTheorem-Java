package network;

import java.util.Observer;

/**
 * The ShannonsController class is an interface that has a collection of abstract
 * methods made available to the controller.
 * @author Erik Njolstad
 */
public interface ShannonsController {
    
    /**
     * The addObserver method adds the observer to the set of observers for this object.
     * @param o
     */
    public void addObserver(Observer o);
    
    /**
     * Sets the bandwidth
     * @param bandwidth 
     */
    public void setBandwidth(double bandwidth);
    
    /**
     * Sets the signal-to-noise
     * @param signalToNoise 
     */
    public void setSignalToNoise(double signalToNoise);
}
