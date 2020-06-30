//Bar Diagram Component
//Programowanie Komponentowe
//Krystian Deresz U-14655
//Adam Pawlik   U-14821
//Informatyka, sem. VI
//Studia stacjonarne
package components;
 
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ComponentListener;

public class BarComponent extends JPanel implements ComponentListener {
   
   //Properties
   private int barsNumber;
   private Color barsColor;
   private Color barDiagramFontColor;
   private int barsMinValue;
   private int barsMaxValue;
   private Border barDiagramBorder;
   private Border barsBorder;
   private boolean isReady;
   private boolean isBarValueShown;
   private int[] barValues;
   private String[] barsNames;
   
   //Support variables
   private int number;
   private int min=0;
   private int max=100;
   
   //Main container
   private final JPanel mainPanel = new JPanel();
   
   //Main container components
   private JPanel infoPanel;
   private JLabel minText;
   private JLabel maxText;
   private JLabel minLabel;
   private JLabel maxLabel;
 
   //Arrays for components
   private JPanel[] barPanels;
   private JProgressBar[] progressBars;
   private JLabel[] barsLabels;
   
   //Component constructor
   public BarComponent(){
       setLayout(new BorderLayout());
       
       infoPanel = new JPanel(new FlowLayout());
       minLabel = new JLabel("Minimum: " + min);
       maxLabel = new JLabel("Maksimum: " + max);
       minText = new JLabel();
       maxText = new JLabel();
       
       
       mainPanel.setLayout(new FlowLayout());
       add(infoPanel, BorderLayout.NORTH);
       add(mainPanel, BorderLayout.CENTER);
   }

    //Getters
    public Border getBarDiagramBorder() {
        return barDiagramBorder;
    }
    public Color getBarDiagramFontColor() {
        return barDiagramFontColor;
    }
    public int getBarsNumber() {
        return barsNumber;
    }
    public Color getBarsColor() {
        return barsColor;
    }
    public int getBarsMinValue() {
        return barsMinValue;
    }
    public int getBarsMaxValue() {
        return barsMaxValue;
    }
    public int[] getBarValues() {
        return barValues;
    }
    public String[] getBarsNames() {
        return barsNames;
    }
    public Border getBarsBorder() {
        return barsBorder;
    }
    public boolean isIsBarValueShown() {
        return isBarValueShown;
    }
    public boolean isIsReady() {
        return isReady;
    }
    
    //Setters
    public void setBarDiagramBorder(Border barDiagramBorder) {
        this.barDiagramBorder = barDiagramBorder;
    }
    public void setBarDiagramFontColor(Color barDiagramFontColor) {
        this.barDiagramFontColor = barDiagramFontColor;
        minLabel.setForeground(barDiagramFontColor);
        maxLabel.setForeground(barDiagramFontColor);
        minText.setForeground(barDiagramFontColor);
        maxText.setForeground(barDiagramFontColor);
        
        for(int i=0; i<number;i++)
        {
            barsLabels[i].setForeground(barDiagramFontColor);
        }
    }
    public void setBarsNumber(int barsNumber) {
        this.barsNumber = barsNumber;
        //Removes old bars, when typed new bar number
        mainPanel.removeAll();
        number=barsNumber;
        //Arrays initialize according to value set in barsNumber property
        barPanels = new JPanel[barsNumber];
        progressBars = new JProgressBar[barsNumber];
        barsLabels = new JLabel[barsNumber];
       
         for(int i=0; i<barPanels.length; i++)
         {
                        //Sub-panels for labels,textfields and bars
                        barPanels[i] = new JPanel();
                        barPanels[i].setLayout(new BorderLayout());
                   
                        //Bars
                        progressBars[i] = new JProgressBar(0,100);
                        progressBars[i].setOrientation(SwingConstants.VERTICAL);
                        progressBars[i].addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e){
                                Object source = e.getSource();
                                for(int i = 0; i<barsNumber; i++){
                                if(source==progressBars[i]){
                                Color pickedColor = JColorChooser.showDialog(null, "Wybierz kolor słupka", barsColor);
                                progressBars[i].setForeground(pickedColor);
                                }
                            }
                        }
                    });
                        
                        //Adding all components to sub-panels
                        barPanels[i].add(progressBars[i], BorderLayout.CENTER);
                        barsLabels[i] = new JLabel("Label: "+(i+1), SwingConstants.CENTER);
                        barPanels[i].add(barsLabels[i], BorderLayout.SOUTH);
                        
                       
                        //Adding sub-panels to main container
                        mainPanel.add(barPanels[i]);
                        
                        //LISTENER DO SKALOWANIA CAŁEGO MAINPANELU (KOMPONENTU W SUMIE)
                        mainPanel.addComponentListener(this);
         }
         infoPanel.add(minLabel);
         infoPanel.add(maxLabel);
    }
    public void setBarsColor(Color barsColor) {
        this.barsColor = barsColor;
    }
    public void setBarsMinValue(int barsMinValue) {
        this.barsMinValue = barsMinValue;
        minText.setText(String.valueOf(barsMinValue));
        min = Integer.parseInt(minText.getText());
        minLabel.setText("Minimum: " + minText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMinimum(min);
        }
    }
    public void setBarsMaxValue(int barsMaxValue) {
        this.barsMaxValue = barsMaxValue;
        maxText.setText(String.valueOf(barsMaxValue));
        max = Integer.parseInt(maxText.getText());
        maxLabel.setText("Maksimum: " + maxText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMaximum(max);
        }
    }
    public void setBarValues(int[] barValues) {
        this.barValues = barValues;
    }
    public void setBarsNames(String[] barsNames) {
        this.barsNames = barsNames;
    }
    public void setBarsBorder(Border barsBorder){
        this.barsBorder = barsBorder;
    }
    public void setIsBarValueShown(boolean isBarValueShown) {
        this.isBarValueShown = isBarValueShown;
        
        if(isBarValueShown)
        {
            for(int i=0; i<number; i++)
            {
            progressBars[i].setStringPainted(true); 
            }
            
        }
        else if(!isBarValueShown)
        {
            for(int i=0; i<number; i++)
            progressBars[i].setStringPainted(false);
        }
    }
    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
        for(int i=0; i<number; i++)
        {
            progressBars[i].setValue(barValues[i]);
            barsLabels[i].setText(barsNames[i]);
            barsLabels[i].setForeground(barDiagramFontColor);
            progressBars[i].setForeground(barsColor);
            progressBars[i].setBorder(barsBorder);
            progressBars[i].setMinimum(min);
            progressBars[i].setMaximum(max);
            mainPanel.setBorder(barDiagramBorder);
            
            if(isBarValueShown)
            progressBars[i].setString(String.valueOf(progressBars[i].getValue()));
        }
    }

    //ADAM TUTAJ RESIZE
    @Override
    public void componentResized(ComponentEvent e) {
        for(int i=0; i<barsNumber; i++){
            //TUTAJ TRZEBA WYMYŚLIĆ LICZBY KTÓRE BĘDĄ DOBRZE DZIAŁAĆ PRZY SKALOWANIU
            barPanels[i].setPreferredSize(new Dimension((mainPanel.getWidth()/barsNumber)-20, (int) ((mainPanel.getHeight()*0.8))));
            
            
            //PRÓBA Z KOLORAMI
//            if(barPanels[i].getWidth()>300){
//                barsLabels[i].setFont(barsLabels[i].getFont().deriveFont(30.0f));
//                barsLabels[i].setForeground(Color.cyan);
//            } 
            
            //TE DWIE LINIE WAZNE 
            mainPanel.revalidate();
            mainPanel.repaint();
            }
    }

    
    //TE TRZY NIEWAŻNE CICHO
    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
}
