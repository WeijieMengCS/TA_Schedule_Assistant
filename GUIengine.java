

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JButton;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GUIengine {

	private JFrame frame;
	private  File inputFile;
	private JTextField titleTextField;
	private JTextField fileTextField;
	private int numberOfTAsInEachSection = -3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIengine window = new GUIengine();
					window.frame.setSize(520, 300);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.frame.setTitle("TA Schedule Assistant");
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIengine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		JLabel mainTitleLabel = new JLabel("TA Schedule Assistant");
		mainTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		mainTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitleLabel.setBounds(124, 6, 269, 38);
		frame.getContentPane().add(mainTitleLabel);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(93, 59, 86, 23);
		frame.getContentPane().add(titleLabel);

		titleTextField = new JTextField();
		titleTextField.setBounds(176, 56, 186, 28);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);

		JLabel fileLabel = new JLabel("File");
		fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileLabel.setBounds(103, 100, 61, 16);
		frame.getContentPane().add(fileLabel);

		JButton startButton = new JButton("START");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			//TODO
			public void mouseClicked(MouseEvent e) {
				if(inputFile!=null) {
					FileParser fp = new FileParser(inputFile);
					fp.csvReader();
					Application app = new Application();
					app.run("tempFile.csv");
				}
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		startButton.setBounds(49, 216, 101, 34);
		frame.getContentPane().add(startButton);

		JButton cancelButton = new JButton("CANCEL");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		cancelButton.setBounds(178, 216, 101, 34);
		frame.getContentPane().add(cancelButton);

		JButton helpButton = new JButton("HELP");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		helpButton.setBounds(308, 216, 101, 34);
		frame.getContentPane().add(helpButton);

		JButton chooseFileButton = new JButton("Choose File");
		chooseFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  
				//TODO
				
	            JFileChooser chooser = new JFileChooser();
	            
	            chooser.setAcceptAllFileFilterUsed(false);
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv file", "csv");
	            chooser.addChoosableFileFilter(filter);
	            chooser.showOpenDialog(null);

                if (chooser.getSelectedFile()!=null){

                    inputFile = chooser.getSelectedFile();
                    fileTextField.setText(inputFile.getName());

                }
				
			}
		});
		chooseFileButton.setBounds(374, 97, 101, 24);
		frame.getContentPane().add(chooseFileButton);

		fileTextField = new JTextField();
		fileTextField.setEditable(false);
		fileTextField.setHorizontalAlignment(JTextField.LEFT);
		fileTextField.setColumns(10);
		fileTextField.setBounds(176, 94, 186, 28);
		frame.getContentPane().add(fileTextField);

		String[] options = {  "1", "2", "3", "4", "5","Want to be different" };
		@SuppressWarnings("unchecked")
		JComboBox comboBox = new JComboBox(options);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox.getSelectedItem();
				switch (s) {
					case "Want to be different":
						numberOfTAsInEachSection=-1;
						break;
					case "1":
						numberOfTAsInEachSection=1;
						break;
					case "2":
						numberOfTAsInEachSection=2;
						break;
					case "3":
						numberOfTAsInEachSection=3;
						break;
					case "4":
						numberOfTAsInEachSection=4;
						break;
					case "5":
						numberOfTAsInEachSection=5;
						break;
					default:
						break;
				}

			}
		});
		
		
		comboBox.setBounds(263, 139, 116, 23);
		frame.getContentPane().add(comboBox);

		JLabel TAsInEachSectionLabel = new JLabel("Number of TAs in each section:");
		TAsInEachSectionLabel.setBounds(49, 138, 202, 23);
		frame.getContentPane().add(TAsInEachSectionLabel);
	}
}
