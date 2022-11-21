package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second java graphical interface");
    
    private SimpleGUIWithFileChooser(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    controller.writeToFile(textArea.getText());
                } catch(IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        final JTextField path = new JTextField(controller.getPath());
        path.setEditable(false);
        final JButton browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser("Choose where to save");
                fileChooser.setSelectedFile(controller.getCurrentFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = fileChooser.getSelectedFile();
                        controller.setCurrFile(newDest);
                        path.setText(newDest.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION: 
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(path, BorderLayout.CENTER);
        upperPanel.add(browse, BorderLayout.LINE_END);
        panel.add(upperPanel, BorderLayout.NORTH);
        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) screen.getWidth();
        final int height =  (int) screen.getHeight();
        frame.setSize(width/4, height/4);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... a) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
