package network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class represents the view MVC design pattern where it draws three different
 * types of views. It also inherits from the JPanel class and implements the Observer interface.
 * @author Erik Njolstad
 */
public class ShannonsPanel extends JPanel implements Observer {
    
    private ShannonsController ctrl; // private variable that takes ShannonsController as its data type.
    private final JLabel maxDataRateLBL; // private variable that takes JLabel as its data type.
    private JPanel mainPanel; // private variable that takes JPanel as its data type.
    private JPanel viewTextField; // private variable that takes JPanel as its data type.
    private JPanel viewSlider; // private variable that takes JPanel as its data type.
    private JPanel viewCustom; // private variable that takes JPanel as its data type.
    private JPanel signalToNoisePanel; // private variable that takes JPanel as its data type.
    private JPanel bandwidthPanel; // private variable that takes JPanel as its data type.
    private JPanel secondMain;
    private final JTextField snrText; // private variable that takes JTextField as its data type.
    private final JTextField bwText; // private variable that takes JTextField as its data type.
    private final JSlider bwSlider; // private variable that takes JSlider as its data type.
    private final JSlider snrSlider; // private variable that takes JSlider as its data type.
    private final JSlider rSlider;
    private final JSlider gSlider;
    private final JSlider bSlider;
    private final JProgressBar pbBand; // private variable that takes JProgressBar as its data type.
    private final JProgressBar pbSNR; // private variable that takes JProgressBar as its data type.
    private final JProgressBar pbMDR; // private variable that takes JProgressBar as its data type.
    private JPanel mdrBar;
    private String maxDataRateRes; // private variable that takes String as its data type.
    private final int MIN = 0; // This values can only be set to 0.
    private final int MAX = 3000; // This values can only be set to 3000.
    private final int RGB_MIN = 0;
    private final int RGB_MAX = 255;
    private Graphics2D g2d;
    private final int n = Math.abs(38) * Math.floorDiv(304, 1);

    /**
     * This constructor passes ShannonsController as an argument.
     * It also instantiates various objects and calls the initGUI() method.
     * @param ctrl 
     */
    public ShannonsPanel(ShannonsController ctrl) {
        this.ctrl = ctrl; 
        maxDataRateLBL = new JLabel();
        signalToNoisePanel = new JPanel();
        bandwidthPanel = new JPanel();
        snrText = new JTextField();
        bwText = new JTextField();
        bwSlider = new JSlider(JSlider.HORIZONTAL, MAX, MIN);
        snrSlider = new JSlider(JSlider.HORIZONTAL, MAX, MIN);
        pbBand = new JProgressBar(0, 3000);
        pbSNR = new JProgressBar(0, 3000);
        pbMDR = new JProgressBar(0, (int) 2989735.29);
        rSlider = new JSlider(JSlider.HORIZONTAL, RGB_MAX, RGB_MIN);
        gSlider = new JSlider(JSlider.HORIZONTAL, RGB_MAX, RGB_MIN);
        bSlider = new JSlider(JSlider.HORIZONTAL, RGB_MAX, RGB_MIN);
        initGUI();
    }

    /**
     * Gets the maxDataRateLBL.
     * @return maxDataRateLBL
     */
    public JLabel getMaxDataRateLBL() {
        return maxDataRateLBL;
    }

    /**
     * Sets the maxDataRate and passes mdrlbl as its argument.
     * @param mdrlbl 
     */
    public void setMaxDataRateLBL(JLabel mdrlbl) {
        mdrlbl.setText(maxDataRateRes);
    }

    /**
     * Sets the controller and passes ShannonsController as its argument.
     * @param ctrl 
     */
    public void setController(ShannonsController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * This method is where the GUI gets drawn from using multiple JPanels.
     */
    private void initGUI() {
        // This is the main panel for adding multiple sub-panels to.
        mainPanel = new JPanel(new GridLayout(3, 1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = getWidth();
                int y = getHeight();
                g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(new Point(0,0), new Color(80,180,25),
                                               new Point(x,y), new Color(0,100,60), false));
                g2d.fillRect(0, 0, x, y);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));        
        // This sub-panel contains the textfields for the maximum data rate, bandwidth, and signal-to-noise ratio.
        viewTextField = new JPanel(new GridLayout(4, 1));
        viewTextField.add(new JLabel("Maximum Data Rate via Shannons Theorem: "));
        viewTextField.add(maxDataRateLBL);
        viewTextField.add(createBandwidthPanel());
        viewTextField.add(bwText);
        viewTextField.add(createSignalToNoisePanel());
        viewTextField.add(snrText);
        viewTextField.setBorder(BorderFactory.createTitledBorder("TextField View"));
        bwText.setToolTipText("Enter values inside of the bandwidth text.");
        snrText.setToolTipText("Enter values inside of the signal-to-noise ratio text.");
        maxDataRateLBL.setToolTipText("This is the value for the maximum data rate.");

        // This sub-panel contains the sliders for the bandwidth, and signal-to-noise ratio.
        viewSlider = new JPanel(new GridLayout(2, 0));
        viewSlider.add(createBandwidthPanel(), BorderLayout.WEST);
        viewSlider.add(bwSlider);
        viewSlider.add(createSignalToNoisePanel(), BorderLayout.WEST);
        viewSlider.add(snrSlider);
        viewSlider.setBorder(BorderFactory.createTitledBorder("Slider View"));
        bwSlider.setToolTipText("Use the sliders to increase or decrease the bandwidth.");
        snrSlider.setToolTipText("Use the sliders to increase or decrease the signal-to-noise ratio.");
        
        // This sub-panel layout contains a progress bar for the bandwidth, signal-to-noise ratio and maximum data rate.
        viewCustom = new JPanel(new GridLayout(3, 1));
        viewCustom.add(new JLabel("Bandwidth (in Hertz): "));
        viewCustom.add(pbBand);
        viewCustom.add(new JLabel("Signal-To-Noise (in DB): "));
        viewCustom.add(pbSNR);
        viewCustom.add(new JLabel("Maximum Data Rate: "));
        viewCustom.add(pbMDR);
        viewCustom.setBorder(BorderFactory.createTitledBorder("Progress Bar View"));
        pbBand.setToolTipText("This bar represents the amount of bandwidth.");
        pbSNR.setToolTipText("This bar represents the signal-to-noise ratio.");
        pbMDR.setToolTipText("This bar represents the maximum data rate.");

        bwSlider.setMajorTickSpacing(500);
        bwSlider.setMinorTickSpacing(30);
        bwSlider.setPaintTicks(true);
        bwSlider.setPaintTrack(true);
        bwSlider.setPaintLabels(true);

        snrSlider.setMajorTickSpacing(500);
        snrSlider.setMinorTickSpacing(30);
        snrSlider.setPaintTicks(true);
        snrSlider.setPaintTrack(true);
        snrSlider.setPaintLabels(true);
        
        pbBand.setForeground(Color.red);
        pbBand.setStringPainted(true);
        
        pbSNR.setForeground(Color.green);
        pbSNR.setStringPainted(true);
        
        pbMDR.setForeground(Color.blue);
        pbMDR.setStringPainted(true);

        // The mainPanel adds all of the created sub-panels
        mainPanel.add(viewTextField);
        mainPanel.add(viewSlider);
        mainPanel.add(viewCustom);

        secondMain = new JPanel(new GridLayout(5,1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = getWidth();
                int y = getHeight();
                g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(new Point(0,0), new Color(120,25,255),
                                               new Point(x,y), new Color(200,100,60), false));
                g2d.fillRect(0, 0, x, y);
            }
        };
        secondMain.setLayout(new BoxLayout(secondMain, BoxLayout.PAGE_AXIS));
        secondMain.setBorder(BorderFactory.createTitledBorder("Channel Capacity"));
        JLabel mdrLabel = new JLabel("Maximum Data Rate Output");
        
        mdrBar = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = pbMDR.getValue() / n;
                int y = getHeight();
                g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(new Point(0,0), new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()),
                                               new Point(x,y), new Color(rSlider.getValue() / Math.abs(3), gSlider.getValue() / Math.abs(3), bSlider.getValue() / Math.abs(3)), false));
                g2d.fillRect(0, 0, x, y);
                mdrBar.updateUI();
            }
        };
        mdrBar.setBorder(BorderFactory.createLoweredBevelBorder());
        mdrBar.setToolTipText("This bar is dependent of the bandwidth and signal-to-noise ratio.");
        
        secondMain.add(mdrLabel);
        secondMain.add(new JLabel(" "));
        secondMain.add(mdrBar);
        secondMain.add(new JLabel(" "));
        
        JPanel rPanel = new JPanel(null);
        rPanel.add(rSlider);
        rPanel.setBorder(BorderFactory.createTitledBorder("Red"));
        rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
        rPanel.setOpaque(false);
        
        JPanel gPanel = new JPanel(null);
        gPanel.add(gSlider);
        gPanel.setBorder(BorderFactory.createTitledBorder("Green"));
        gPanel.setLayout(new BoxLayout(gPanel, BoxLayout.Y_AXIS));
        gPanel.setOpaque(false);
        
        JPanel bPanel = new JPanel(null);
        bPanel.add(bSlider);
        bPanel.setBorder(BorderFactory.createTitledBorder("Blue"));
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));
        bPanel.setOpaque(false);
        
        secondMain.add(rPanel);
        secondMain.add(gPanel);
        secondMain.add(bPanel);
        
        rSlider.setOpaque(false);
        rSlider.setMajorTickSpacing(255);
        rSlider.setMinorTickSpacing(15);
        rSlider.setPaintLabels(true);
        rSlider.setPaintTicks(true);
        rSlider.setPaintTrack(true);
        rSlider.setValue(50);
        rSlider.setToolTipText("Increases/decreases the red colour parameters.");
        
        gSlider.setOpaque(false);
        gSlider.setMajorTickSpacing(255);
        gSlider.setMinorTickSpacing(15);
        gSlider.setPaintLabels(true);
        gSlider.setPaintTicks(true);
        gSlider.setPaintTrack(true);
        gSlider.setValue(20);
        gSlider.setToolTipText("Increases/decreases the green colour parameters.");
        
        bSlider.setOpaque(false);
        bSlider.setMajorTickSpacing(255);
        bSlider.setMinorTickSpacing(15);
        bSlider.setPaintLabels(true);
        bSlider.setPaintTicks(true);
        bSlider.setPaintTrack(true);
        bSlider.setValue(200);
        bSlider.setToolTipText("Increases/decreases the blue colour parameters.");
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame particlesFrame = new JFrame("MDR: Shannons Theorem");
        particlesFrame.setBounds(965,250,350,325);
        particlesFrame.setLayout(new FlowLayout());
        particlesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        particlesFrame.add(secondMain);
        particlesFrame.setVisible(true);
        
        // This adds the mainPanel and shows on display
        add(mainPanel);
        setBorder(BorderFactory.createLineBorder(Color.pink, 3, true));
    }

    /**
     * This method is private, passes JPanel as its data type and creates the bandwidth panel
     * in conjunction with each of the sub-panels.
     * 
     * @return bandwidthPanel
     */
    private JPanel createBandwidthPanel() {
        bandwidthPanel = new JPanel(new GridLayout(1,1));
        bandwidthPanel.add(new JLabel("Bandwidth (in Hertz): "));

        /* This receives data for bandwidth from keyboard inputs. 
        * Only numbers can be used in this case otherwise
        * exceptions will be thrown.
        */
        bwText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent key) {
                try {
                    ctrl.setBandwidth(Double.parseDouble(bwText.getText()));
                    int showBWSliderAndBar = Integer.parseInt(bwText.getText());
                    bwSlider.setValue(showBWSliderAndBar);
                    if (Double.parseDouble(bwText.getText()) > 3000) {
                        bwText.setText(String.valueOf(0));
                        pbBand.setValue(0);
                        bwSlider.setValue(0);
                        maxDataRateRes = String.valueOf(0);
                        JOptionPane.showMessageDialog(ShannonsPanel.this, "The bandwidth does not return a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        bwText.setText("");
                    }
                } catch (IllegalArgumentException | ArithmeticException e) {
                    if (bwText.getText().isEmpty()) {
                        bwText.setText(String.valueOf(0));
                        pbBand.setValue(0);
                        bwSlider.setValue(0);
                        maxDataRateRes = String.valueOf(0);
                        return;
                    }
                    JOptionPane.showMessageDialog(ShannonsPanel.this, "You must enter a proper value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    bwText.setText("");
                }
            }
        });

            /* This listens for the bandwidth slider and makes changes. */
            bwSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent l) {
                pbMDR.setValue(pbBand.getValue() * pbSNR.getValue() / 300 * 100);
                ctrl.setBandwidth(bwSlider.getValue());
                bwText.setText(String.valueOf(bwSlider.getValue()));
                int valueBW = bwSlider.getValue();
                pbBand.setValue(valueBW);
            }
        });
        return bandwidthPanel;
    }

    /**
     * This method is private, passes JPanel as its data type and creates the signal-to-noise panel
     * in conjunction with each of the sub-panels.
     * 
     * @return signalToNoisePanel
     */
    private JPanel createSignalToNoisePanel() {
        signalToNoisePanel = new JPanel(new GridLayout(1,3));
        signalToNoisePanel.add(new JLabel("Signal-To-Noise (in DB): "));

        /* This receives data for signal-to-noise from keyboard inputs. 
        * Only numbers can be used in this case otherwise
        * exceptions will be thrown.
        */
        snrText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent key) {
                try {
                    ctrl.setSignalToNoise(Double.parseDouble(snrText.getText()));
                    int showSNRSliderAndBar = Integer.parseInt(snrText.getText());
                    snrSlider.setValue(showSNRSliderAndBar);
                    if (Double.parseDouble(snrText.getText()) > 3000) {
                        snrText.setText(String.valueOf(0));
                        pbSNR.setValue(0);
                        snrSlider.setValue(0);
                        maxDataRateLBL.setText(String.valueOf(0));
                        JOptionPane.showMessageDialog(ShannonsPanel.this, "The signal-to-noise does not return a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        snrText.setText("");
                    }
                } catch (IllegalArgumentException | ArithmeticException e) {
                    if (snrText.getText().isEmpty()) {
                        snrText.setText(String.valueOf(0));
                        pbSNR.setValue(0);
                        snrSlider.setValue(0);
                        maxDataRateLBL.setText(String.valueOf(0));
                        return;
                    }
                    JOptionPane.showMessageDialog(ShannonsPanel.this, "You must enter a proper value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    snrText.setText("");
                }
            }
        });

        /* This listens for the signal-to-noise slider and makes changes. */
        snrSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent l) {
                ctrl.setSignalToNoise(snrSlider.getValue());
                pbMDR.setValue(pbBand.getValue() * pbSNR.getValue() / 300 * 100);
                snrText.setText(String.valueOf(snrSlider.getValue()));
                int valueSNR = snrSlider.getValue();
                pbSNR.setValue(valueSNR);
            }
        });
        return signalToNoisePanel;
    }
    
    /**
     * This method updates upon execution and is called whenever the observer object is changed.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
            maxDataRateRes = o.toString();
            setMaxDataRateLBL(getMaxDataRateLBL());
    }
}