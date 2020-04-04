import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 4, 2020
 *
 */
public class StudentRecordView extends JFrame
{
	//For the main panel
	private JLabel title = new JLabel("An Application to Maintain Student Records");
	
	private JButton insertButton = new JButton("Insert");
	private JButton findButton = new JButton("Find");
	private JButton browseButton = new JButton("Browse");
	private JButton createTreeButton = new JButton("Create Tree from File");
	
	private JTextField studentRecords = new JTextField();
	
	//For the input panel
	private JLabel fileName = new JLabel("Enter the file name:");
	
	private JTextArea inputtedFile = new JTextArea();
	
	private JButton cancelButton = new JButton("Cancel");
	private JButton okButton = new JButton("OK");
	
	
	//For the 
	public StudentRecordView()
	{
		super("Main window");
		
		JPanel mainText = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane textArea = new JScrollPane();
		
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainText.setLocation(0, 0);
		mainText.add(title);
		
		buttonPanel.setSize(600, 50);
		buttonPanel.setLocation(0, 0);
		buttonPanel.add(insertButton);
		buttonPanel.add(findButton);
		buttonPanel.add(browseButton);
		buttonPanel.add(createTreeButton);
		
		textArea.setSize(600, 300);
		textArea.setLocation(0, 50);
		textArea.add(studentRecords);
		
		add(textArea);
		add(mainText);
		add(buttonPanel);
	}
	
	/**
	 * 
	 * @param records
	 */
	public void setStudentRecords(String records)
	{
		studentRecords.setText(records);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName()
	{
		return getTextFromTextBox(inputtedFile);
	}
	
	/**
	 * 
	 * @param errorMessage
	 */
	public void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	/**
	 * 
	 * @param textField
	 * @return
	 */
	public String getTextFromTextBox(JTextArea textField)
	{
		return textField.getText();
	}
	
	/**
	 * 
	 * @param textField
	 * @return
	 */
	public int getIntFromTextBox(JTextField textField)
	{
		return Integer.parseInt(textField.getText());
	}
	
	//Button Listener Functions
	
	/**
	 * 
	 * @param listenForInsertButton
	 */
	public void addInsertButtonListener(ActionListener listenForInsertButton)
	{
		insertButton.addActionListener(listenForInsertButton);
	}
	
	/**
	 * 
	 * @param listenForBrowseButton
	 */
	public void addBrowseButtonListener(ActionListener listenForBrowseButton)
	{
		browseButton.addActionListener(listenForBrowseButton);
	}
	
	/**
	 * 
	 * @param listenForFindButton
	 */
	public void addFindButtonListener(ActionListener listenForFindButton)
	{
		findButton.addActionListener(listenForFindButton);
	}
	
	/**
	 * 
	 * @param listenForCreateTreeButton
	 */
	public void addCreateTreeButtonListener(ActionListener listenForCreateTreeButton)
	{
		createTreeButton.addActionListener(listenForCreateTreeButton);
	}
	
}
