import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Creating a To-Do List App GUI with SQL database to retrieve, save and delete task entries.
// Version 2.0: Added SQL database functionality
// Name: Humza Inam

public class GUI {
	JDBC jbdc = new JDBC();
	JFrame frame = new JFrame();
	JTextField textField = new JTextField();
	JTextField textField2 = new JTextField();                  
	JLabel label = new JLabel();
	JLabel label2 = new JLabel();                    
	JPanel panel = new JPanel();
	JButton button = new JButton("Add Task");  
	JButton button2 = new JButton("Delete Task");  
	JTable table = new JTable();
	String[] columnTitle = {"Task", "Date"};
	String[] row = new String[2];
	DefaultTableModel model = new DefaultTableModel(); 
	String tasks;
	String date;
	 boolean performed;


	public GUI() {
		textField.setPreferredSize(new Dimension(300,30));	  
		textField2.setPreferredSize(new Dimension(300,30));
		table.setPreferredScrollableViewportSize(new Dimension(300,table.getRowHeight() * 7));

		model.setColumnIdentifiers(columnTitle);
		table.setModel(model);;                           
		label.setText("Task:");
		label2.setText("Date:");
		button.setBackground(Color.CYAN);
		button2.setBackground(Color.CYAN);

		panel.add(label);
		panel.add(textField);
		panel.add(label2);
		panel.add(textField2);
		panel.add(button);
		panel.add(button2);
		panel.add(new JScrollPane(table));
		panel.setBackground(Color.LIGHT_GRAY);

		frame.setTitle("To-Do List App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(350,350);
		frame.setVisible(true);
		frame.setResizable(false);

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().toString().isEmpty()) {
					String message = textField.getText().toString();
					String message2 = textField2.getText().toString();    
					row[0] = message;
					row[1] = message2;                         
					model.addRow(row);
					try {
						JDBC.statement2.executeUpdate("INSERT INTO todo.list (tasks,date) VALUES('"+message+"', '"+message2+"');");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField2.setText(null);
				}
			}

		});

		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int index = table.getSelectedRow();
					tasks = table.getValueAt(index,0).toString();

					model.removeRow(table.getSelectedRow());

					
					
				try {
					JDBC.statement2.executeUpdate("DELETE FROM todo.list WHERE tasks='"+tasks+"';");

					}
				catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			catch(Exception e2) {
				return;
			}
			}
		});

	}

}


