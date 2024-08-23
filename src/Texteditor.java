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

    JMenu file, edit, format, color;

    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JMenuItem wrap, arial, csms, tmr, font8, font12, font16, font20, font24, font28;
    JMenu font, fontSize;

    JTextArea textArea;

    boolean wrapOn = false;
    Font arialFont, comicSans, timesNewRoman;
    String selectedFont = "Arial";
    int defaultFontSize = 16;

    // COLOR MENU
    JMenuItem color1, color2, color3;

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
        format = new JMenu("Format");
        color = new JMenu("Color");

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

        // Initialize format menu items
        wrap = new JMenuItem("Word Wrap: Off");
        wrap.addActionListener(this);
        wrap.setActionCommand("Word Wrap");
        format.add(wrap);

        font = new JMenu("Font");
        format.add(font);

        // Add Font Style to Font
        arial = new JMenuItem("Arial");
        arial.addActionListener(this);
        arial.setActionCommand("Arial");
        font.add(arial);

        csms = new JMenuItem("Comic Sans MS");
        csms.addActionListener(this);
        csms.setActionCommand("Comic Sans MS");
        font.add(csms);

        tmr = new JMenuItem("Times New Roman");
        tmr.addActionListener(this);
        tmr.setActionCommand("Times New Roman");
        font.add(tmr);

        fontSize = new JMenu("Font Size");
        format.add(fontSize);

        // Adding font size
        font8 = new JMenuItem("8");
        font8.addActionListener(this);
        font8.setActionCommand("size8");
        fontSize.add(font8);

        font12 = new JMenuItem("12");
        font12.addActionListener(this);
        font12.setActionCommand("size12");
        fontSize.add(font12);

        font16 = new JMenuItem("16");
        font16.addActionListener(this);
        font16.setActionCommand("size16");
        fontSize.add(font16);

        font20 = new JMenuItem("20");
        font20.addActionListener(this);
        font20.setActionCommand("size20");
        fontSize.add(font20);

        font24 = new JMenuItem("24");
        font24.addActionListener(this);
        font24.setActionCommand("size24");
        fontSize.add(font24);

        font28 = new JMenuItem("28");
        font28.addActionListener(this);
        font28.setActionCommand("size28");
        fontSize.add(font28);

        // Initialize Color Menu items
        color1 = new JMenuItem("Black");
        color1.addActionListener(this);
        color.add(color1);

        color2 = new JMenuItem("Green");
        color2.addActionListener(this);
        color.add(color2);

        color3 = new JMenuItem("Blue");
        color3.addActionListener(this);
        color.add(color3);



        // Add menu items to menubar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(color);

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

        if(actionEvent.getSource() == wrap){
            if(!wrapOn){
                wrapOn = true;
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                wrap.setText("Word Wrap: On");
            } else if(wrapOn){
                wrapOn = false;
                textArea.setLineWrap(false);
                textArea.setWrapStyleWord(false);
                wrap.setText("Word Wrap: Off");
            }
        }

        // Font Style
        if(actionEvent.getSource() == arial) setFont("Arial");
        if(actionEvent.getSource() == csms) setFont("Comic Sans MS");
        if(actionEvent.getSource() == tmr) setFont("Times New Roman");


        if(actionEvent.getSource() == font8) createFont(8);
        if(actionEvent.getSource() == font12) createFont(12);
        if(actionEvent.getSource() == font16) createFont(16);
        if(actionEvent.getSource() == font20) createFont(20);
        if(actionEvent.getSource() == font24) createFont(24);
        if(actionEvent.getSource() == font28) createFont(28);

        if(actionEvent.getSource() == color1) textArea.setForeground(Color.black);
        if(actionEvent.getSource() == color2) textArea.setForeground(Color.green);
        if(actionEvent.getSource() == color3) textArea.setForeground(Color.blue);
    }

    public void createFont(int fontSize){
        arialFont = new Font("Arial", Font.PLAIN, fontSize);
        comicSans = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);

        setFont(selectedFont);
    }

    public void setFont(String font){
        selectedFont = font;

        switch(selectedFont){
            case "Arial":
                textArea.setFont(arialFont);
                break;

            case "Comis Sans MS":
                textArea.setFont(comicSans);
                break;

            case "Times New Roman":
                textArea.setFont(timesNewRoman);
                break;
        }
    }


    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
    }
}