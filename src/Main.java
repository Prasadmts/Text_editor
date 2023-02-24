import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.text.*;
import java.io.*;

class notepad extends JFrame implements  ActionListener{
    JTextArea t;
    JFrame f;

    //constructor
    notepad(){
        //initialising the frame and the textarea
        f = new JFrame("notepad");

        t = new JTextArea();

        //initialising menubar and all individual menus
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("file");

        JMenuItem f1 = new JMenuItem("new");
        JMenuItem f2 = new JMenuItem("open");
        JMenuItem f3 = new JMenuItem("save");
        JMenuItem f4 = new JMenuItem("print");

        //adding actionerlistener to individual menuitems
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding menu items to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        JMenu edit = new JMenu("edit");

        JMenuItem f5 = new JMenuItem("cut");
        JMenuItem f6 = new JMenuItem("copy");
        JMenuItem f7 = new JMenuItem("paste");
        // adding the action listener to individual menu items;
        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);
        // adding menu items to the file menu;
        edit.add(f5);
        edit.add(f6);
        edit.add(f7);
        JMenuItem close = new JMenuItem("close");
        // ADDING THE FILE AND EDIT AND CLOSE IN THE MENU
        menu.add(file);
        menu.add(edit);
        menu.add(close);
        // connecting the menu and adding the size of frame if the application
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1000,1000);
        f.show();
    }

    public void actionPerformed(ActionEvent e){
        // getting in the user cliked function in the form of a string
        String s = e.getActionCommand();

        //processing the string s
        switch(s){
            case "new":
                t.setText("");
                break;
            case "open":
                //creating object of jfilechooser class and initialising it to a location in the computer
                JFileChooser j = new JFileChooser("D:");
                //initialising the openDailogbox
                int r = j.showOpenDialog(null);

                //if the user selects a file
                if(r == JFileChooser.APPROVE_OPTION){
                    //extracting the absolute path of the selected file
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1,s2;
                    try{
                        //place pointer at the starting of the file
                        FileReader fr = new FileReader(fi);

                        //use bufferedreader to read line by line
                        BufferedReader br = new BufferedReader(fr);

                        //storing the first line in S1
                        s1 = br.readLine();

                        //appending subsequent lines till end of file is reached
                        while((s2 = br.readLine())!=null){
                            s1 = s1 + "\n" +s2 ;
                        }
                        t.setText(s1);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"Application Cancelled");
                }
                break;
            case "save":
                //creating object of jFile chooser class and initialising it to a location in the computer
                JFileChooser ji = new JFileChooser("D:");
                //initialising the open dialog box
                int ri = ji.showOpenDialog(null);

                //if the user selects a file
                if(ri == JFileChooser.APPROVE_OPTION){
                    //extracting the absolute path of the selected file
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());

                    try{
                        //place pointer at the starting of the file
                        FileWriter fr = new FileWriter(fi);

                        //use bufferedreader to read line by line
                        BufferedWriter br = new BufferedWriter(fr);
                        br.write(t.getText());
                        br.flush();
                        br.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"Application Cancelled");
                }
                break;
            case "print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "cut":
                t.cut();
                break;
            case "copy":
                t.copy();
                break;
            case "paste":
                t.paste();
                break;
            case "close":
                f.setVisible(false);
                break;
        }
    }

    public static void main(String[] args){
        notepad note = new notepad();
    }
}