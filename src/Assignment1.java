import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;


////////////////////////////////////////
// REFERENCES used for this exercise: //
////////////////////////////////////////
// 1 Java™ Platform, Standard Edition 7 API Specification, Oracle, 2016, https://docs.oracle.com/javase/7/docs/api/overview-summary.html
// 2 The Java (TM) Tutorials, Oracle, 2015, http://docs.oracle.com/javase/tutorial/uiswing/components/index.html
////////////////////////////////////////


public class Assignment1 extends JFrame implements ActionListener {

	public static JLabel statusBarText;
	
	public static JToggleButton btnCategory1 = new JToggleButton("Family",true);
	public static JToggleButton btnCategory2 = new JToggleButton("Vacation",true);
	public static JToggleButton btnCategory3 = new JToggleButton("Studies",true);
	public static JToggleButton btnCategory4 = new JToggleButton("Myself",true);
	
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame();	
		Container mainContainer = mainWindow.getContentPane();
		mainWindow.setVisible(true);
		mainWindow.setMinimumSize(new Dimension(400,400));
		mainWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addElementsToFrame(mainContainer);
		
		mainWindow.pack();
	}
	
	public static void addElementsToFrame(Container mainContainer) {
		
		///////////////
		//  MENUBAR  //
		///////////////
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);
		menuBar.setBackground(Color.WHITE);
		mainContainer.add(menuBar, BorderLayout.PAGE_START);
	
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuItemImport = new JMenuItem("Import");
		JMenuItem menuItemDelete = new JMenuItem("Delete");
		JMenuItem menuItemQuit = new JMenuItem("Quit");
		
		menuFile.add(menuItemImport);
		menuFile.add(menuItemDelete);
		menuFile.add(menuItemQuit);
		
		//Radio Button Menu
		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);
		
		ButtonGroup rbGroup = new ButtonGroup();
		
		JRadioButtonMenuItem rbMenuItemPhoto = new JRadioButtonMenuItem("Photo viewer");
		JRadioButtonMenuItem rbMenuItemBrowser = new JRadioButtonMenuItem("Browser");
		JRadioButtonMenuItem rbMenuItemSplit = new JRadioButtonMenuItem("Split mode");
		rbMenuItemPhoto.setSelected(true);
		rbGroup.add(rbMenuItemPhoto);
		rbGroup.add(rbMenuItemBrowser);
		rbGroup.add(rbMenuItemSplit);
		menuView.add(rbMenuItemPhoto);
		menuView.add(rbMenuItemBrowser);
		menuView.add(rbMenuItemSplit);
		
		/////////////
		// TOOLBAR //
		/////////////
		
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new BoxLayout(toolbar,BoxLayout.Y_AXIS));
		// ASK how to define maximum/minimum size ... ASK why I have to use MaximumSize
		toolbar.setPreferredSize(new Dimension(100, 100));
		btnCategory1.setMaximumSize(new Dimension(100, 25));
		btnCategory2.setMaximumSize(new Dimension(100, 25));
		btnCategory3.setMaximumSize(new Dimension(100, 25));
		btnCategory4.setMaximumSize(new Dimension(100, 25));
		toolbar.add(btnCategory1);
		toolbar.add(btnCategory2);
		toolbar.add(btnCategory3);
		toolbar.add(btnCategory4);

		mainContainer.add(toolbar, BorderLayout.WEST);
		
		//////////////////
		// MAIN SECTION //
		//////////////////
		
		JPanel mainSection = new JPanel();
		mainSection.setBackground(Color.GRAY);
		mainSection.setMinimumSize(new Dimension(600,600));
		mainSection.setPreferredSize(new Dimension(200, 100));
		mainContainer.add(mainSection, BorderLayout.CENTER);
		
		////////////////
		// STATUS BAR //
		////////////////
		
		JPanel statusBar = new JPanel();
		statusBar.setBackground(Color.lightGray);
		statusBarText = new JLabel("Status Bar");
		statusBar.add(statusBarText);
		mainContainer.add(statusBar, BorderLayout.PAGE_END);
		
		/////////////////////
		// Event Listeners //
		/////////////////////
		
		// Old Approach for Event Listeners (explains better)
		menuItemImport.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	statusBarText.setText("Import Photo");
	        	importPhoto();
	        }
	    });
		
		// New Lambda Approach for Event Listeners
		menuItemDelete.addActionListener(event -> {
			statusBarText.setText("Delete Photo");
			deletePhoto();
		});
		menuItemQuit.addActionListener(event -> {
			System.exit(0);
			// Ask how to properly exit the application in Java Swing
			//JFrame frame = ((JFrame) mainContainer.getParent());
			//frame.dispose();
		});
		
		rbMenuItemPhoto.addActionListener(event -> {
			statusBarText.setText("Changed to Photo Viewer mode");
			changeView("Photo Viewer");
		});
		rbMenuItemBrowser.addActionListener(event -> {
			statusBarText.setText("Changed to Browser mode");
			changeView("Browser");
		});
		rbMenuItemSplit.addActionListener(event -> {
			statusBarText.setText("Changed to Split mode");
			changeView("Split");
		});
		
		btnCategory1.addActionListener(event -> changeFilter(btnCategory1));
		btnCategory2.addActionListener(event -> changeFilter(btnCategory2));
		btnCategory3.addActionListener(event -> changeFilter(btnCategory3));
		btnCategory4.addActionListener(event -> changeFilter(btnCategory4));
	}
	
	public static void importPhoto(){
		statusBarText.setText("Import Photo");
		JFileChooser photoPicker = new JFileChooser();
		int selectedPhoto = photoPicker.showOpenDialog(null);
	}
	
	public static void deletePhoto(){
		statusBarText.setText("Delete Photo");
	}
	
	public static void changeView(String mode){
		switch(mode)
	    {
	        case "Photo Viewer":
	        	statusBarText.setText("Changed to: Photo Viewer mode");
	            break;
	        case "Browser":
	        	statusBarText.setText("Changed to: Browser mode");
	            break;
	        case "Split":
	            statusBarText.setText("Changed to: Split mode");
	            break;	            
	        default:
	    } 
	}
	
	public static void changeFilter(JToggleButton button) {
		if(button.isSelected()){
			statusBarText.setText("Added Filter for: " + button.getText());
		} else {
			statusBarText.setText("Removed Filter for: " + button.getText());
		}
	}
	
	// ASK: Is this required?
	public void actionPerformed(ActionEvent e) {}

}
