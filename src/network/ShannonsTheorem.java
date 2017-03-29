package network;

import java.awt.FlowLayout;
import java.util.Observer;
import javax.swing.JFrame;

/**
 * This class calculates the maximum data rate of a channel using bandwidth and 
 * signal-to-noise ratio.
 * All methods are called from the ShannonsModel class.
 *
 * @author Erik Njolstad
 */
public class ShannonsTheorem implements ShannonsController {

    // Created a variable that uses ShannonsModel as its object type for calling
    // the methods that are accessible within the ShannonsModel class.
    private static ShannonsModel model;
    private static ShannonsTheorem controller = new ShannonsTheorem();

    /**
     * Instantiated the object of ShannonsModel class inside of the default constructor.
     */
    public ShannonsTheorem() {
        super();
        model = new ShannonsModel();
    }
    
    /**
     * Calls the parameterized Constructor from ShannonsModel.
     *
     * @param bandwidth
     * @param signalToNoise
     */
    public ShannonsTheorem(double bandwidth, double signalToNoise) {
        model = new ShannonsModel(bandwidth, signalToNoise);
    }

    /**
     * Gets the model.
     * @return model 
     */
    private ShannonsModel getModel() {
        return model;
    }
    
    /**
     * Calls the getBandwidth method from ShannonsModel.
     *
     * @return sm.getBandwidth()
     */
    public double getBandwidth() {
        return model.getBandwidth();
    }

    /**
     * Calls the getSignalToNoise method from ShannonsModel.
     *
     * @return sm.getSignalToNoise()
     */
    public double getSignalToNoise() {
        return model.getSignalToNoise();
    }

    /**
     * Calls the getMaximumDataRate method from ShannonsModel.
     *
     * @return sm.getMaximumDataRate()
     */
    public double getMaximumDataRate() {
        return model.getMaximumDataRate();
    }

    /**
     * Calls the setBandwidth method from ShannonsModel.
     *
     * @param b
     */
    @Override
    public void setBandwidth(double b) {
        model.setBandwidth(b);
    }

    /**
     * Calls the setSignalToNoise method from ShannonsModel.
     *
     * @param snr
     */
    @Override
    public void setSignalToNoise(double snr) {
        model.setSignalToNoise(snr);
    }
    
    /**
     * Sets the model and passes ShannonsModel as its argument.
     * @param model 
     */
    private void setModel(ShannonsModel model) {
        ShannonsTheorem.model = model;
    }

    /**
     * This method updates upon execution and is called whenever the observer object is changed.
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        model.addObserver(o);
    }
    
    /**
     * This method is where the main GUI draws the frame for taking in each of the labels.
     */
    private void initGUI() {
        controller = new ShannonsTheorem();
        ShannonsPanel panel = new ShannonsPanel(controller);
        
        controller.addObserver(panel);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Shannon's Theorem");
        frame.setBounds(400, 100, 555, 357);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    /**
     * Program execution begins with a GUI asking for the bandwidth and the 
     * signal-to-noise ratio which outputs the maximum data rate.
     * @param args 
     */
    public static void main(String [] args) {
        controller = new ShannonsTheorem();
        controller.initGUI();
    }
}