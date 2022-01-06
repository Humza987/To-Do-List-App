import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Creating a To-Do List App GUI
// Name: Humza Inam

public class GUI {
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

	public GUI() {
		textField.setPreferredSize(new Dimension(300,30));	  
		textField2.setPreferredSize(new Dimension(300,30));
		table.setPreferredScrollableViewportSize(new Dimension(300,table.getRowHeight() * 7));
		DefaultTableModel model = new DefaultTableModel(); 

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
		String[] row = new String[2];

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().toString().isEmpty()) {
					String message = textField.getText().toString();
					String message2 = textField2.getText().toString();     
					row[0] = message;
					row[1] = message2;                         
					model.addRow(row);
					textField.setText(null);
					textField2.setText(null);
				}
			}

		});

		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					model.removeRow(table.getSelectedRow());
				}
				catch(Exception e2) {
					return;
				}
			}
		});

	}

	public static void main(String[] args) {
		new GUI();
	}
}


