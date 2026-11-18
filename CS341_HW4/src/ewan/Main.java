package ewan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Main {
    // SPECIFY THE APPLICATION ELEMENTS: UI AND OBJECTS
    private static JButton inputBtn;
    private static JButton outputBtn;
    private static JButton computeBtn;
    private static JFrame jframeWindow;
    private static JPanel panel;
    private static File fileToRead;
    private static File fileToSave;
    public static void main(String[] args) {
        //TASK 1: CREATE THE GUI APPLICATION WINDOW
        constructAppWindow();
        //TASK 2: REGISTER LISTENER EVENTS FOR THE WINDOW
        addListenerEvents();
    }
    private static void constructAppWindow() {
        //TASK 1: CREATE THE JFRAME WINDOW WITH AN EXIT ON CLOSE
        jframeWindow = new JFrame();
        jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TASK 2: CONSTRUCT A PANEL CONTAINER TO STORE UI ELEMENTS
        panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
        panel.setPreferredSize(new Dimension(500, 150));
        panel.setBackground(Color.DARK_GRAY);
        //TASK 3: BUILD UI ELEMENTS AND ADD THEM TO THE PANEL
        inputBtn = new JButton("Specify Input Text File");
        outputBtn = new JButton("Specify Output Text File");
        computeBtn = new JButton("Perform Work");
        panel.add(inputBtn);
        panel.add(outputBtn);
        panel.add(computeBtn);
        //TASK 4: ADD THE COMPLETED PANEL TO THE APPLICATION WINDOW
        jframeWindow.add(panel);
        // TASK 5: MAKE THE APPLICATION WINDOW VISIBLE TO THE USER
        jframeWindow.pack();
        jframeWindow.setVisible(true);
    }
    //FUNCTION: REGISTER LISTENER EVENT WITH FUNCTIONALITY TO TEST
    private static void addListenerEvents() {
        inputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestInputFile();
            }
        });
        outputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestSaveFile();
            }
        });
        computeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeSomething();
            }
        });
    }
    public static void requestSaveFile() {
        //NATIVE MACOS SAVE DIALOG (JFileChooser greys out files on macOS)
        java.awt.FileDialog fd = new java.awt.FileDialog(jframeWindow, "Specify a file to save", java.awt.FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileToSave = new File(fd.getDirectory(), fd.getFile());
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }
    public static void requestInputFile() {
        java.awt.FileDialog fd = new java.awt.FileDialog(jframeWindow, "Choose an input file", java.awt.FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileToRead = new File(fd.getDirectory(), fd.getFile());
            System.out.println(fileToRead.getAbsolutePath());
        }
    }
    //FUNCTION: DEFAULT FUNCTIONALITY FOR TESTING
    public static void computeSomething() {
        System.out.println("now computing");
    }
}
