package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Toolkit; 

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first java graphical interface");

    private SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel();
        final JTextArea textArea = new JTextArea();
        panel.setLayout(new BorderLayout());
        final JButton save = new JButton("Save");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeToFile(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) screen.getWidth();
        final int height =  (int) screen.getHeight();
        frame.setSize(width/2, height/2);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... a) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }

}
