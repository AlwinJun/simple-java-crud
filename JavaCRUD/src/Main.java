import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

class JavaFrame extends JFrame{
	
	 JPanel topPanel, tablePanel, formPanel;
	 JLabel titleLabel, lblId, lblName, lblAuthor, lblPrice;
	 JTextField txtId, txtName, txtAuthor, txtPrice;
	 JButton btnAdd, btnUpdate, btnClear, btnDelete;
	 DefaultTableModel model;
	 JTable table;
	
	//int initialKey = 1;
	
	JavaFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Book Store");
		this.setSize(897,563);
		this.setResizable(false);
		this.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setBackground(new Color(0,153,204));
		topPanel.setBounds(0,0,897,80);
		
		titleLabel = new JLabel();
		titleLabel.setText("BookStore");
		titleLabel.setFont(new Font("Sans Serif", Font.PLAIN,36));
		titleLabel.setForeground(Color.white);
		titleLabel.setIconTextGap(20);
		
		//Table
		tablePanel = new JPanel();
		//tablePanel.setBackground(Color.red);
		tablePanel.setBounds(10, 90, 540, 420);
		
		model = new DefaultTableModel(new Object[]{
					/*"Book Id",*/
					"Book Name",
					"Author",
					"Price"
				},0);
		table = new JTable(model) {
			public Component prepareRenderer(TableCellRenderer x,int data, int col) {
				Component c = super.prepareRenderer(x, data, col);
				
				// Stripe row
				if(data % 2 == 0) {
					c.setBackground(Color.white);
				}else {
					c.setBackground(Color.LIGHT_GRAY);
				}
				
				//Selected row
				if(isCellSelected(data, col)) {
					c.setBackground(new Color(0,153,204));
				}
				
				return c;
			}
		};
		table.setDefaultEditor(Object.class, null); // Prevent data to be edited on the taable itself when click
			
		
		//Form
		formPanel = new JPanel();
		//formPanel.setBackground(Color.green);
		formPanel.setBounds(550, 90, 280, 420);
		
	/*	JPanel idPanel = new JPanel(new GridLayout(2,1));
		idPanel.setPreferredSize(new Dimension(270, 60));
		//idPanel.setBackground(Color.yellow);
		lblId = new JLabel();
		lblId.setText("Book ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN,16));
		lblId.setForeground(Color.black);
		txtId = new JTextField();
		txtId.setEditable(false); //readonly
		txtId.setBackground(Color.white);
		txtId.setForeground(Color.black);
		txtId.setFont(new Font("Tahoma", Font.PLAIN,14));
		txtId.setPreferredSize(new Dimension(60, 15));   */
		
		JPanel namePanel = new JPanel(new GridLayout(2,1));
		namePanel.setPreferredSize(new Dimension(270, 60));
		//namePanel.setBackground(Color.yellow);
		lblName = new JLabel();
		lblName.setText("Book Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN,16));
		lblName.setForeground(Color.black);	
		txtName = new JTextField();
		txtName.setBackground(Color.white);
		txtName.setForeground(Color.black);
		txtName.setFont(new Font("Tahoma", Font.PLAIN,14));
		txtName.setPreferredSize(new Dimension(60, 15));
		
		JPanel authorPanel = new JPanel(new GridLayout(2,1));
		authorPanel.setPreferredSize(new Dimension(270, 60));
		//authorPanel.setBackground(Color.yellow);
		lblAuthor = new JLabel();
		lblAuthor.setText("Book Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN,16));
		lblAuthor.setForeground(Color.black);
		txtAuthor = new JTextField();
		txtAuthor.setBackground(Color.white);
		txtAuthor.setForeground(Color.black);
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN,14));
		txtAuthor.setPreferredSize(new Dimension(60, 15));
		
		JPanel pricePanel = new JPanel(new GridLayout(2,1));
		pricePanel.setPreferredSize(new Dimension(270, 60));
		//pricePanel.setBackground(Color.yellow);
		lblPrice = new JLabel();
		lblPrice.setText("Book Pricer");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN,16));
		lblPrice.setForeground(Color.black);
		txtPrice = new JTextField();
		txtPrice.setBackground(Color.white);
		txtPrice.setForeground(Color.black);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN,14));
		txtPrice.setPreferredSize(new Dimension(60, 15));
		
		//Buttons
		JPanel btnPanel = new JPanel(new GridLayout(2,2,10,10));
		btnPanel.setPreferredSize(new Dimension(270, 80));
		//btnPanel.setBackground(Color.yellow);
		btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(0,153,204));
		btnAdd.setForeground(Color.white);
		btnAdd.setFont(new Font("Sans Serif", Font.BOLD,14));
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(0,153,204));
		btnUpdate.setForeground(Color.white);
		btnUpdate.setFont(new Font("Sans Serif", Font.BOLD,14));
		btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(0,153,204));
		btnClear.setForeground(Color.white);
		btnClear.setFont(new Font("Sans Serif", Font.BOLD,14));
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(0,153,204));
		btnDelete.setForeground(Color.white);
		btnDelete.setFont(new Font("Sans Serif", Font.BOLD,14));
		
		//Addbutonn
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//int bookId = initialKey;
				String bookName = txtName.getText();
				String author = txtAuthor.getText();
				double price;
				
				if (bookName.isEmpty() || author.isEmpty() || txtPrice.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(JavaFrame.this, "Make sure that all feild isn't emoty", "Error", JOptionPane.ERROR_MESSAGE);
			         return;
				}
				
				// Accept only numbers
				try {
					price = Double.parseDouble(txtPrice.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(JavaFrame.this, "Price field must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				model.addRow(new Object[]{/*bookId, */ bookName, author, price});
				//initialKey++;
				
				txtName.setText("");
				txtAuthor.setText("");
				txtPrice.setText("");
			}
		});
		
		//Get data
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		            // Get the selected row
		            int selectedRow = table.getSelectedRow();

		            // Get the data from the selected row
		           // String bookId = String.valueOf(model.getValueAt(selectedRow, 0));
		            String bookName = (String) model.getValueAt(selectedRow, 1);
		            String author = (String) model.getValueAt(selectedRow, 2);
		            String price = String.valueOf(model.getValueAt(selectedRow, 3));

		            // Set the data to the text fields
		          //  txtId.setText(bookId);
		            txtName.setText(bookName);
		            txtAuthor.setText(author);
		            txtPrice.setText(price);
		          //  txtId.setEditable(false);
		            txtName.setEditable(true);
		            txtAuthor.setEditable(true);
		            txtPrice.setEditable(true);
		        }
		    }
		});

		// Upate
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get selected row
		        int selectedRow = table.getSelectedRow();

		        // Get data from the text fields
		        String bookName = txtName.getText();
		        String author = txtAuthor.getText();
		        String price = txtPrice.getText();

		        // No empty text fields
		        if (bookName.isEmpty() || author.isEmpty() || price.isEmpty()) {
		            JOptionPane.showMessageDialog(JavaFrame.this, "Make sure that all field isn't emoty", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        	        
		        // Validate the price. Only accept numbers
		        try {
		        	 double p = Double.parseDouble(price);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(JavaFrame.this, "Price field must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        

		        // Update data in the selected row
		        table.setValueAt(bookName, selectedRow, 1);
		        table.setValueAt(author, selectedRow, 2);
		        table.setValueAt(price, selectedRow, 3);
		        
		        // Clear textfeilds
			//	txtId.setText("");
				txtName.setText("");
				txtAuthor.setText("");
				txtPrice.setText("");
		    }
		});

		//Clear textfeild
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				txtName.setText("");
				txtAuthor.setText("");
				txtPrice.setText("");
			}
		});
		
		// Delete rows
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get selected row
		        int selectedRow = table.getSelectedRow();

		        // Confirm deletion
		        int result = JOptionPane.showConfirmDialog(JavaFrame.this, "Are you sure you want to delete this record?", "Delete Record", JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION) {
		            // Delete the selected row from the table model
		            model.removeRow(selectedRow);
		        }
		        
			//	txtId.setText("");
				txtName.setText("");
				txtAuthor.setText("");
				txtPrice.setText("");
		    }
		});

		// Add all swing components to output on frame
		this.add(topPanel);
		topPanel.add(titleLabel);
		this.setVisible(true);
		this.add(tablePanel);
		tablePanel.add(new JScrollPane(table));
		this.add(formPanel);
	//	formPanel.add(idPanel);
	//	idPanel.add(lblId);
	//	idPanel.add(txtId);
		formPanel.add(namePanel);
		namePanel.add(lblName);
		namePanel.add(txtName);
		formPanel.add(authorPanel);
		authorPanel.add(lblAuthor);
		authorPanel.add(txtAuthor);
		formPanel.add(pricePanel);
		pricePanel.add(lblPrice);
		pricePanel.add(txtPrice);
		formPanel.add(btnPanel);
		btnPanel.add(btnAdd);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnClear);
		btnPanel.add(btnDelete);
		
		// Change The default java logo in title
		ImageIcon image = new ImageIcon("book-stack.png");
		this.setIconImage(image.getImage());
		
		titleLabel.setIcon(image);
		
	}
}

public class Main {
	
	public static void main(String[] args) {
		new JavaFrame();
	}
	
}
