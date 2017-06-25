import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class DrawWindow extends JFrame {
	// label displays mouse coordinates
	private JLabel mouseLocation;
	private PaintPanel panel;
	private JComboBox colorSelector;
	private String colorChoices[] = { "Black", "Blue", "Green", "Red" };
	private Color colorArray[] = { Color.BLACK, Color.BLUE, Color.GREEN, Color.RED };
	private JComboBox shapeSelector;
	private JButton undoButton;
	private JButton clearButton;
	private String shapeChoices[] = { "Line", "Rectangle", "Oval" };
	private JCheckBox filled;

	public DrawWindow() {
		// sets the name of DrawFrame
		super("Paint Program");
		JFrame window = new JFrame("Paint Program");
		JLabel mouseLocation = new JLabel("");
		window.setLayout(new BorderLayout());
		
		panel = new PaintPanel(mouseLocation);

		undoButton = new JButton("Undo");
		clearButton = new JButton("Clear");
		colorSelector = new JComboBox(colorChoices);
		shapeSelector = new JComboBox(shapeChoices);
		filled = new JCheckBox("Filled");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout());
		
		menuBar.add(undoButton);
		menuBar.add(clearButton);
		menuBar.add(colorSelector);
		menuBar.add(shapeSelector);
		menuBar.add(filled);
		
		window.add(menuBar, BorderLayout.NORTH);
		window.add(panel, BorderLayout.CENTER);
		
		//I added instant actionListener handlers here because it's far easier to program it
		//this way as opposed to making a whole class for it - especially if the class is void
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Undone");
				panel.clearLastShape(); 
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cleared");
				panel.clearDrawing();
			}
		});
		
		//Adding the item listener here is the best way to ensure that every action is caught
		ItemListenerHandler handler = new ItemListenerHandler();
		colorSelector.addItemListener(handler);
		shapeSelector.addItemListener(handler);
		filled.addItemListener(handler);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 600);
		window.setResizable(false);
		window.setVisible(true);
	}


	//This class is MAINLY for the selecting of the comboBoxes. It will test to see what box
	//was used to create a State Change and based off that information then it will be able to
	//enable the logic attached
	private class ItemListenerHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == filled) {
				boolean checkFill = filled.isSelected() ? true : false; //
				panel.setCurrentShapeFilled(checkFill);
			}
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getSource() == colorSelector) {
					panel.setCurrentShapeColor(colorArray[colorSelector.getSelectedIndex()]);
				}
				else if (e.getSource() == shapeSelector) {
					panel.setCurrentShapeType(shapeSelector.getSelectedIndex());
				}
			}
		}
	}
}