package network;

import java.util.Observable;

/**
 * This class shows all of the algorithmic calculations for the maximum data rate
 * using the bandwidth and the signal-to-noise ratio.
 * 
 * @author Erik Njolstad
 */
public class ShannonsModel extends Observable{
    
    // Created two variables named bandwidth and signalToNoise where it takes a
    // double as its data type.
    private double bandwidth, signalToNoise;
    private final java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
    
    /**
     * Default Constructor.
     */
    public ShannonsModel() {
        
    }
    
    /**
     * Parameterized Constructor.
     *
     * @param bandwidth
     * @param signalToNoise
     */
    public ShannonsModel(double bandwidth, double signalToNoise) {
        this.bandwidth = bandwidth;
        this.signalToNoise = signalToNoise;
    }
    
    /**
     * Returns the bandwidth.
     *
     * @return bandwidth
     */
    public double getBandwidth() {
        return bandwidth;
    }
    
    /**
     * Returns the signal-to-noise ratio.
     *
     * @return signalToNoise
     */
    public double getSignalToNoise() {
        return signalToNoise;
    }
    
    /**
     * Returns the calculations for bandwidth and signal-to-noise. Otherwise,
     * returns Not a Number.
     * @param hertz
     * @param signalToNoise
     * @return 
     */
    private static double getMaximumDataRate(double hertz, double signalToNoise) {
        if (hertz < 0 || signalToNoise < 0) {
            return Double.NaN;
        } else {
        return (hertz * (Math.log(1 + Math.pow(10, signalToNoise / 10)) / Math.log(2)));
        }
    }
    
    /**
     * Overloaded method which is public and takes no parameters.
     *
     * @return getMaximumDataRate(bandwidth, signalToNoise)
     */
    public double getMaximumDataRate() {
        return getMaximumDataRate(bandwidth, signalToNoise);
    }
    
    /**
     * Sets a number for the bandwidth.
     * @param b 
     */
    public void setBandwidth(Double b) {
        this.bandwidth = b;
        this.setChanged();
        this.notifyObservers();
        if (b < 0) {
            throw new IllegalArgumentException();
        }
        else if (Double.toString(b) == null) {
            throw new NumberFormatException();
        }
        else if (Double.isInfinite(b) || Double.isNaN(b)) {
            throw new ArithmeticException();
        }
    }
    
    /**
     * Sets a number for the signal-to-noise ratio.
     *
     * @param snr
     */
    public void setSignalToNoise(Double snr) {
        this.signalToNoise = snr;
        this.setChanged();
        this.notifyObservers();
        if (snr < 0) {
            throw new IllegalArgumentException();
        }
        else if (Double.toString(snr) == null) {
            throw new NumberFormatException();
        }
        else if (Double.isInfinite(snr) || Double.isNaN(snr)) {
            throw new ArithmeticException();
        }
    }
    
    /**
     * Displays the output for the bandwidth, signal-to-noise ratio and the
     * maximum data rate. This overrides the toString method.
     *
     * @return String
     */
    
    @Override
    public String toString() {
        return df.format(getMaximumDataRate(bandwidth, signalToNoise));
    }
    
    // old toString method
//    @Override
//    public String toString() {
//        return "The Bandwidth is: " + getBandwidth() + "\nThe Signal-to-Noise ratio is: " + getSignalToNoise() + "\nThe maximum data rate is " + getMaximumDataRate();
//    }
}