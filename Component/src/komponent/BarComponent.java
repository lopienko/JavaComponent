//Bar Diagram Component
//Krystain Deresz u-14655
//Adam Pawlik u -14821

package komponent;
 
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 
 * Main class of bar diagram component. 
 * It uses get and set methods to change components properties.
 * Component is a JPanel containing all necessary sub-components to build
 * a bar diagram.
 *
 */

public class BarComponent extends JPanel {
   private PropertyChangeSupport propertyChangeSupport;
   //Properties
   private int barCount ;
   private Color barsColor;
   private Color barDiagramFontColor;
   private int barsMinValue;
   private int barsMaxValue;
   private Border barDiagramBorder;
   private Border barsBorder;
   private boolean isBarValueShown;
   private int[] barValues;
   private String[] barsNames;
   
   private int min=00;
   private int max=100;
   
   private final JPanel mainPanel = new JPanel();

   private JPanel infoPanel;
   private JLabel minText;
   private JLabel maxText;
   private JLabel minLabel;
   private JLabel maxLabel;

   private JPanel[] barPanels;
   private JProgressBar[] progressBars;
   private JLabel[] barsLabels;
   
   
   /**
    * Constructor of component class.
    */
   
   public BarComponent(){
       setLayout(new BorderLayout());
       propertyChangeSupport=new PropertyChangeSupport(this);
       
       infoPanel = new JPanel(new FlowLayout());
       minLabel = new JLabel("Minimum: " + min);
       maxLabel = new JLabel("Maksimum: " + max);
       minText = new JLabel();
       maxText = new JLabel();
       
       mainPanel.setLayout(new FlowLayout());
       add(infoPanel, BorderLayout.NORTH);
       add(mainPanel, BorderLayout.CENTER);
       
       setBarCount(5);
   }
    /**
     * Getter method for Border of bar diagram.
     * @return Border which is set for whole bar diagram. 
     */
    public Border getBarDiagramBorder() {
        return barDiagramBorder;
    }
    /**
    * Setter method to set Border for whole bar diagram.
    * @param barDiagramBorder Border property for whole bar diagram.
    */
    public void setBarDiagramBorder(Border barDiagramBorder) {
        this.barDiagramBorder = barDiagramBorder;
        mainPanel.setBorder(barDiagramBorder);
    }
    /**
     * Getter method for Color of bar diagram font.
     * @return Color which is set for bar diagram font. 
     */
    public Color getBarDiagramFontColor() {
        return barDiagramFontColor;
    }
    /**
    * Setter method to set Color for bar diagram font.
    * @param barDiagramFontColor Color property for bar diagram font.
    */
    public void setBarDiagramFontColor(Color barDiagramFontColor) {
        this.barDiagramFontColor = barDiagramFontColor;
        minLabel.setForeground(barDiagramFontColor);
        maxLabel.setForeground(barDiagramFontColor);
        minText.setForeground(barDiagramFontColor);
        maxText.setForeground(barDiagramFontColor);
        
       for (JLabel barsLabel : barsLabels) {
           barsLabel.setForeground(barDiagramFontColor);
       }
    }
    /**
     * Getter method for a number of bars in bar diagram.
     * @return Integer set for numbet of bar diagram bars.
     */
    public int getBarCount() {
        return barCount;
    }
    /**
     * Setter method to set Integer for number of bars in bar diagram.
     * It also creates all sub-comopnents of bar diagram depending on the number
     * of bars, and adds all necessary listeners for component.
     * @param barsNumber Integer number of bars in bar diagram and all necessary
     * sub-components.
     */
    public void setBarCount(int barsNumber) {
        
        if(this.barCount==barsNumber){
            return;
        }
        else{
            propertyChangeSupport.firePropertyChange("direction", this.barCount, barsNumber);
            this.barCount=barsNumber;
            barPanels = new JPanel[barsNumber];
            progressBars = new JProgressBar[barsNumber];
            barsLabels = new JLabel[barsNumber];
            barsNames = new String[barsNumber+1];
            barValues = new int[barsNumber+1];
        }
        
        mainPanel.removeAll();
       
        for(int i=0; i<barPanels.length; i++)
         {
                        barPanels[i] = new JPanel();
                        barPanels[i].setLayout(new BorderLayout());
                   
                        progressBars[i] = new JProgressBar(0,100);
                        progressBars[i].setOrientation(SwingConstants.VERTICAL);
                        progressBars[i].addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e){
                                Object source = e.getSource();
                                for (JProgressBar progressBar : progressBars) {
                                    if (source == progressBar) {
                                        Color pickedColor = JColorChooser.showDialog(null, "Wybierz kolor słupka", barsColor);
                                        progressBar.setForeground(pickedColor);
                                    }
                                }
                        }
                    });
                        barPanels[i].add(progressBars[i], BorderLayout.CENTER);
                        barsNames[i] = "Label" + (i+1);
                        barsLabels[i] = new JLabel(barsNames[i], SwingConstants.CENTER);
                        barPanels[i].add(barsLabels[i], BorderLayout.SOUTH);

                        mainPanel.add(barPanels[i]);  
         }
         mainPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                for (JPanel barPanel : barPanels) {
                    barPanel.setPreferredSize(new Dimension((mainPanel.getWidth()/barsNumber)-20, (int) ((mainPanel.getHeight()*0.8))));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
                }
         });
         infoPanel.add(minLabel);
         infoPanel.add(maxLabel);
    }
    /**
     * Getter method for Color of all bars in bar diagram.
     * @return Color set for all bars in bar diagram.
     */
    public Color getBarsColor() {
        return barsColor;
    }
    /**
     * Setter method to set Color for all bars in bar diagram.
     * @param barsColor Color property for alla bars in bar diagram.
     */
    public void setBarsColor(Color barsColor) {
        this.barsColor = barsColor;
       for (JProgressBar progressBar : progressBars) {
           progressBar.setForeground(barsColor);
       }
    }
    /**
     * Getter method for minimum value of bars in bar diagram.
     * @return Integer set for minimum value of lower range for bars in bar diagram.
     */
    public int getBarsMinValue() {
        return barsMinValue;
    }
    /**
     * Setter method to set minimum value of lower range for bars in bar diagram.
     * @param barsMinValue Integer number for lower range for bars in bar diragram.
     */
    public void setBarsMinValue(int barsMinValue) {
        this.barsMinValue = barsMinValue;
        minText.setText(String.valueOf(barsMinValue));
        min = Integer.parseInt(minText.getText());
        minLabel.setText("Minimum: " + minText.getText());
        
       for (JProgressBar progressBar : progressBars) {
           progressBar.setMinimum(min);
       }
    }
    /**
     * Getter method for maximum value of bars in bar diagram.
     * @return Integer set for maximum value of upper range for bars in bar diagram.
     */
    public int getBarsMaxValue() {
        return barsMaxValue;
    }
        /**
     * Setter method to set maximum value of upper range for bars in bar diagram.
     * @param barsMaxValue Integer number for upper range for bars in bar diagram.
     */
    public void setBarsMaxValue(int barsMaxValue) {
        this.barsMaxValue = barsMaxValue;
        maxText.setText(String.valueOf(barsMaxValue));
        max = Integer.parseInt(maxText.getText());
        maxLabel.setText("Maksimum: " + maxText.getText());
        
       for (JProgressBar progressBar : progressBars) {
           progressBar.setMaximum(max);
       }
    }
    /**
     * Getter method for array of Integers set as values for bars in bar diagram.
     * @return Array of Integers with values for bars in bar diagram.
     */
    public int[] getBarValues() {
        return barValues;
    }
    /**
     * Setter method to set array of values for bars in bar diagram.
     * @param newBarValues Array of values for bars in bar diagram.
     */
    public void setBarValues(int[] newBarValues) {
        for(int i=0; i<newBarValues.length; i++){
            barValues[i] = newBarValues[i];
            progressBars[i].setValue(barValues[i]);
        }
    }
    /**
     * Getter method for array of Strings set as names of bars in bar diagram.
     * @return Array of String with names of bars in bar diagram.
     */
    public String[] getBarsNames() {
        return barsNames;
    }
    /**
     * Setter method to set array of Strings used as names for bars in bar diagram.
     * @param newBarsNames Array of String used as names for bars in bar diagram.
     */
    public void setBarsNames(String[] newBarsNames) {
        
        for(int i=0; i<newBarsNames.length; i++){
            barsNames[i] = newBarsNames[i];
            barsLabels[i].setText(barsNames[i]);
        }
    }
    /**
     * Getter method for Border of bars in bar diagram.
     * @return Border of bars in bar diagram.
     */
    public Border getBarsBorder() {
        return barsBorder;
    }  
    /**
     * Setter method to set Border of all bars in bar diagram.
     * @param barsBorder Border for all bars in bar diagram.
     */
    public void setBarsBorder(Border barsBorder){
        this.barsBorder = barsBorder;
        for(int i=0; i<progressBars.length; i++)
        {
            progressBars[i].setBorder(barsBorder);
            barPanels[i].add(progressBars[i]);
        }
    }
    /**
     * Getter method for boolean value saying if the value of bar is shown inside the bar.
     * @return Boolean value saying if value of bar is shown.
     */
    public boolean isIsBarValueShown() {
        return isBarValueShown;
    }
    /**
     * Setter method to set if the value of bar is shown inside of it.
     * @param isBarValueShown Boolean saying if the value is shown.
     */
    public void setIsBarValueShown(boolean isBarValueShown) {
        this.isBarValueShown = isBarValueShown;
        
        if(isBarValueShown)
        {
            for (JProgressBar progressBar : progressBars) {
                progressBar.setStringPainted(true);
                progressBar.setString(String.valueOf(progressBar.getValue()));
            }
        }
        else if(!isBarValueShown)
        {
            for (JProgressBar progressBar : progressBars) {
                progressBar.setStringPainted(false);
            }
        }
    }
}