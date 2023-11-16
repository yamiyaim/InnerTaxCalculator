package calc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int width = 300;
	int height = 180;

	String[] list = { "8%", "10%" };

	JTextField price = new JTextField(15);
	JComboBox<String> dropdown = new JComboBox<>(list);
	JButton btnOutput = new JButton("計算");
	JLabel cost = new JLabel("原価");
	JLabel costText = new JLabel("");
	JLabel tax = new JLabel("消費税");
	JLabel taxText = new JLabel("");

	JPanel firstLine = new JPanel();
	JPanel secondLine = new JPanel();
	JPanel packOne = new JPanel();
	JPanel packTwo = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel answerPanel = new JPanel();

	public GUI() throws Exception {
		this.setTitle("内税計算機");
		this.setSize(width, height);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.panelinit();
		this.set();
		this.setVisible(true);
	}

	// set panel in main panel
	public void panelinit() {
		firstLine.add(price);
		secondLine.setLayout(new BorderLayout());
		packOne.add(dropdown);
		secondLine.add(packOne, BorderLayout.NORTH);
		packTwo.add(btnOutput);
		secondLine.add(packTwo);
		answerPanel.add(cost);
		answerPanel.add(costText);
		answerPanel.add(tax);
		answerPanel.add(taxText);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(firstLine, BorderLayout.NORTH);
		mainPanel.add(secondLine, BorderLayout.CENTER);
		mainPanel.add(answerPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}

	public void set() {
		btnOutput.addActionListener(this);
	}

	// when the botton is pressed
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnOutput) {
			String target = price.getText().replaceAll("[^0-9]", "");
			System.out.println(target);
			try {
				int[] calc = Calc.calculateTaxAndCost(Integer.parseInt((target == "") ? "0" : target));
				costText.setText(Integer.valueOf(calc[0]).toString());
				taxText.setText(Integer.valueOf(calc[1]).toString());
			} catch (Exception e) {
				System.out.println(e);
				costText.setText("");
				taxText.setText("");
			}
		}
	}

}