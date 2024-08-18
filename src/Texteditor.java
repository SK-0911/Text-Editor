import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Texteditor implements ActionListener {
    // Declaring properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    Texteditor (){
        // Initialize the frame
        frame = new JFrame();

        // Initialize menubar
        menuBar = new JMenuBar();

        // Initialize Text Area
        textArea = new JTextArea();


        // Initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        // Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // Add action listener to edit menu items
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        // Add menu items to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // Set MenuBar to frame
        frame.setJMenuBar(menuBar);
        // Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // Add the text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // Create Scroll Pane
        JScrollPane scrollpane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollpane);
        // Add panel to frame
        frame.add(panel);
        // Set Dimensions of Frame
        frame.setBounds(100,100,500,500);
        frame.setTitle("Text Editor (By Shubham Kumar)");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }if(actionEvent.getSource() == copy){
            textArea.copy();
        }if(actionEvent.getSource() == paste){
            textArea.paste();
        }if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }if(actionEvent.getSource() == close){
            System.exit(0);
        }if(actionEvent.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // if the user clicks on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting the selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filePath = file.getPath();
                try{
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output= "";
                    // Read contents of the file
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    // Set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // Initialize File Picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Set choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if user click on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Create a new file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile){
            Texteditor newTextEditor = new Texteditor();
        }
    }
    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
    }
}