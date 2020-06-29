//Bar Diagram Test Application
//Programowanie Komponentowe
//Krystian Deresz U-14655
//Adam Pawlik   U-14821
//Informatyka, sem. VI
//Studia stacjonarne

package bartestapp;


public class TestApplication extends javax.swing.JFrame {

    public TestApplication() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barComponent2 = new components.BarComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikacja testująca");
        setBackground(new java.awt.Color(204, 204, 204));
        setLocation(new java.awt.Point(500, 300));

        barComponent2.setBarDiagramBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sprzedaż rowerów", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 0, 0))); // NOI18N
        barComponent2.setBarDiagramFontColor(new java.awt.Color(153, 0, 0));
        barComponent2.setBarValues(new int[] {22, 50, 4, 78, 31, 1, 98});
        barComponent2.setBarsBorder(javax.swing.BorderFactory.createEtchedBorder());
        barComponent2.setBarsColor(new java.awt.Color(255, 153, 153));
        barComponent2.setBarsMaxValue(100);
        barComponent2.setBarsNames(new String[] {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"});
        barComponent2.setBarsNumber(7);
        barComponent2.setIsBarValueShown(true);
        barComponent2.setIsReady(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestApplication().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.BarComponent barComponent2;
    // End of variables declaration//GEN-END:variables
}
