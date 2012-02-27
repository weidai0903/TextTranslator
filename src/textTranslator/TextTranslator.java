package textTranslator;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
/**
 * The main class, to show GUI, add translator, listen the action and perform the action.
 * including addTranslator(TranslatorInterface translator), and class TranslateListener implements ActionListener.
 * @author Wei Dai
 *
 */
public class TextTranslator extends JFrame{
	JTextArea upperText;
	JTextArea lowerText;
	JFrame frame;
	JLabel label;
	JMenu translateMenu;
	JButton translateButton;
	TranslatorInterface trans;
	/**
	 * main function 
	 * @param args
	 */

	public static void main(String[] args) {
		TextTranslator tt=new TextTranslator();
		tt.showGUI();
	}
	
	/**
	 * set up GUI
	 */
	private void showGUI() {
		
        frame = new JFrame("Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Box box=new Box(BoxLayout.Y_AXIS);
        frame.add(box);
        label=new JLabel("<html>Welcome!</html>");
        label.setHorizontalTextPosition(JLabel.LEFT);
        JPanel labelPanel=new JPanel();
        labelPanel.add(label);
        box.add(labelPanel);
        upperText =new JTextArea(12,50);
        JScrollPane upperPanel=new JScrollPane(upperText);
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setBorder(BorderFactory.createTitledBorder("Text to be translated"));
        box.add(upperPanel);
        lowerText =new JTextArea(12,50);
        JScrollPane lowerPanel=new JScrollPane(lowerText);
        lowerPanel.setBackground(Color.WHITE);
        lowerPanel.setBorder(BorderFactory.createTitledBorder("Translated text"));
        box.add(lowerPanel);
        translateButton=new JButton("Translate");
        JPanel buttonPanel=new JPanel();
        buttonPanel.add(translateButton);
        box.add(buttonPanel);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load...");
        JMenuItem saveAs=new JMenuItem("Save As...");
        JMenuItem quit=new JMenuItem("Quit");
        menuBar.add(fileMenu);
        fileMenu.add(load);
        fileMenu.add(saveAs);
        fileMenu.add(quit);
        translateMenu=new JMenu("Translate");
        menuBar.add(translateMenu);
        
        frame.setJMenuBar(menuBar);
        
        frame.pack();
        frame.setVisible(true);
        translateButton.addActionListener(new ButtonListener());
        load.addActionListener(new LoadListener());
        class QuitListener implements ActionListener{
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        }
        quit.addActionListener(new QuitListener());

        saveAs.addActionListener(new SaveAsListener());
        
        addTranslator(new IdentityTranslator());
        addTranslator(new DetabTranslator());
        addTranslator(new EntabTranslator());
        addTranslator(new SingleSpaceTranslator());
        addTranslator(new DoubleSpaceTranslator());
        addTranslator(new WrapTextTranslator());
        addTranslator(new FlowTextTranslator());
        addTranslator(new FixIndentationTranslator());
      
    }
	
	/**
	 * do everything necessary to install the various translators
	 * @param translator
	 */
	private void addTranslator(TranslatorInterface translator) {
		String tName=translator.getName();
		JMenuItem transItem=new JMenuItem(tName);
		translateMenu.add(transItem);
		transItem.addActionListener(new TranslateListener(translator));
	}
	
	/**
	 * listen the action, perform what translator should do.
	 * @author Wei Dai
	 *
	 */
	class TranslateListener implements ActionListener{
		TranslatorInterface inter;
		/**
		 * Gets the name of the selected translator.Gets the description of the selected translator.Translate the text and put in lower text
		 */
		public void actionPerformed(ActionEvent evt) {
			trans=inter;
			frame.setTitle(trans.getName());
			label.setText(trans.getDescription());
			lowerText.setText(trans.translate(upperText.getText()));
			translateButton.setText(trans.getName());
		}
		/**
		 * Constructor. get the translator.
		 * @param translator
		 */
		public TranslateListener(TranslatorInterface translator) {
			inter=translator;
		}
	}
	/**
	 * perform load 
	 * @author Wei Dai
	 *
	 */
	class LoadListener implements ActionListener{
		public  void actionPerformed(ActionEvent e) {
			JFileChooser chooser =new JFileChooser();
			chooser.setDialogTitle("Please select your file");
			int result=chooser.showOpenDialog(new TextTranslator());
			if(result==JFileChooser.APPROVE_OPTION) {
				File file=chooser.getSelectedFile();
				try {
					FileInputStream fis=new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					upperText.setText("");
					while (dis.available() != 0) {
						upperText.append(dis.readLine());
						upperText.append("\n");
					}
					fis.close();
				    bis.close();
				    dis.close();
				}catch(FileNotFoundException fnfe){
					 fnfe.printStackTrace();
			    } catch (IOException ioe) {
			    	ioe.printStackTrace();
			    }
				
			}
		}
	}
	/**
	 * perform quit
	 * @author Wei Dai
	 *
	 */

	/**
	 * perform save
	 * @author Wei Dai
	 *
	 */
	class SaveAsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("Save file as?");
			int result=chooser.showSaveDialog(new TextTranslator());
			if(result==JFileChooser.APPROVE_OPTION) {
				File file=chooser.getSelectedFile();
				try {
					if(!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file);  
		            PrintWriter pw = new PrintWriter(fw);  
		            pw.println(lowerText.getText());  
		            pw.close();  
		            fw.close();				
				}catch (IOException ioe) {
					System.out.println("Error! Unsuccessful saving!");  
		            ioe.printStackTrace();  
				}
			}
		}
	}
	/**
	 * perform the button action
	 * @author Wei Dai
	 *
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(trans==null) {
				label.setText("Please choose one translator");
			}
			else {
				lowerText.setText(trans.translate(upperText.getText()));
			}
		}
	}
	

}
