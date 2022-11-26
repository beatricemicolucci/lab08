package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller;

    public SimpleGUI(final Controller controller) {
        
        this.controller = controller;
        final JPanel canvas = new JPanel();
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JPanel southPanel = new JPanel();
        canvas.setLayout(new BorderLayout());
        canvas.add(textField, BorderLayout.NORTH);
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");
        canvas.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(print);
        southPanel.add(history);
        textArea.setEditable(false);
        textField.setBackground(Color.lightGray);
        frame.setContentPane(canvas);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextString(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                final List<String> historyStrings = SimpleGUI.this.controller.getHistoryOfPrintedStrings();
                for (final String print : historyStrings) {
                    text.append(print);
                    text.append('\n');
                }
                if(!historyStrings.isEmpty()) {
                    text.deleteCharAt(text.length()-1);
                }
                textArea.setText(text.toString());
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw/2, sh/2);
        frame.setLocationByPlatform(true);

    }

    private void display() {
        frame.setVisible(true);
    }


    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
