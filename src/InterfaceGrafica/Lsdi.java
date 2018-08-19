package InterfaceGrafica;

import javax.swing.*;

public class Lsdi {
    private JLabel controleDeAmbienteDoLabel;
    private JLabel Vtemperatura;
    private JLabel Vumidade;
    private JLabel Vfumaca;
    private JLabel pVtemperatura;
    private JLabel pVumidade;
    private JLabel pVfumaca;
    private JPanel Controle;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lsdi");
        frame.setContentPane(new Lsdi().Controle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
