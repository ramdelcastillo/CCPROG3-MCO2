import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegularView {

    private JFrame frame;
    private JPanel gridPanel1, panel3, buttonPanel;
    private JRadioButton btn, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    private JButton button, button2, button3, button4, button5, button6, button7, button8, button9;
    private ButtonGroup items;
    private JTextField text;
    private Border blackBorder;
    public RegularView() {

        frame = new JFrame("Regular Vending Machine");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        gridPanel1 = new JPanel();
        gridPanel1.setLayout(new GridLayout(2, 4, 10, 10));

        items = new ButtonGroup();

        btn = new JRadioButton();
        items.add(btn);
        btn.setText("Ajitsuke Tamago");
        btn.setFocusable(false);
        btn.setOpaque(true);
        btn.setBackground(Color.lightGray);
        btn.setSelected(true);
        gridPanel1.add(btn);

        btn2 = new JRadioButton();
        items.add(btn2);
        btn2.setText("Fish Cake");
        btn2.setFocusable(false);
        btn2.setOpaque(true);
        btn2.setBackground(Color.lightGray);
        gridPanel1.add(btn2);

        btn3 = new JRadioButton();
        items.add(btn3);
        btn3.setText("Fried Tofu");
        btn3.setFocusable(false);
        btn3.setOpaque(true);
        btn3.setBackground(Color.lightGray);
        gridPanel1.add(btn3);

        btn4 = new JRadioButton();
        items.add(btn4);
        btn4.setText("Tempura");
        btn4.setFocusable(false);
        btn4.setOpaque(true);
        btn4.setBackground(Color.lightGray);
        gridPanel1.add(btn4);

         btn5 = new JRadioButton();
        items.add(btn5);
        btn5.setText("Tonkotsu");
        btn5.setFocusable(false);
        btn5.setOpaque(true);
        btn5.setBackground(Color.lightGray);
        gridPanel1.add(btn5);

        btn6 = new JRadioButton();
        items.add(btn6);
        btn6.setText("Gyoza");
        btn6.setFocusable(false);
        btn6.setOpaque(true);
        btn6.setBackground(Color.lightGray);
        gridPanel1.add(btn6);

        btn7 = new JRadioButton();
        items.add(btn7);
        btn7.setText("Karaage");
        btn7.setFocusable(false);
        btn7.setOpaque(true);
        btn7.setBackground(Color.lightGray);
        gridPanel1.add(btn7);

        btn8 = new JRadioButton();
        items.add(btn8);
        btn8.setText("Mentaiko");
        btn8.setFocusable(false);
        btn8.setOpaque(true);
        btn8.setBackground(Color.lightGray);
        gridPanel1.add(btn8);

        frame.add(gridPanel1, BorderLayout.CENTER);

        panel3 = new JPanel(new BorderLayout());

        text = new JTextField();
        text.setPreferredSize(new Dimension(400, 200));

        blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        text.setBorder(blackBorder);

        button = new JButton("Order");
        button2 = new JButton("Refund");
        button3 = new JButton("Insert Cash");
        button4 = new JButton("Check Vending Machine Items");
        button5 = new JButton("Maintenance: Restock items");
        button6 = new JButton("Maintenance: Change item price");
        button7 = new JButton("Maintenance: Collect earned money");
        button8 = new JButton("Maintenance: Add vending machine change");
        button9 = new JButton("Maintenance: Summary of Transactions");

        buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(button);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);

        panel3.add(text, BorderLayout.WEST);
        panel3.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel3, BorderLayout.SOUTH);

        frame.setVisible(false);
    }

    public void newRB1Listener(ActionListener actionListener) {
        this.btn.addActionListener(actionListener);
    }

    public boolean isBtn1Selected(){
        return this.btn2.isSelected();
    }

    public void newRB2Listener(ActionListener actionListener) {
        this.btn2.addActionListener(actionListener);
    }

    public void newRB3Listener(ActionListener actionListener) {
        this.btn3.addActionListener(actionListener);
    }

    public void newRB4Listener(ActionListener actionListener) {
        this.btn4.addActionListener(actionListener);
    }

    public void newRB5Listener(ActionListener actionListener) {
        this.btn5.addActionListener(actionListener);
    }

    public void newRB6Listener(ActionListener actionListener) {
        this.btn6.addActionListener(actionListener);
    }

    public void newRB7Listener(ActionListener actionListener) {
        this.btn7.addActionListener(actionListener);
    }

    public void newRB8Listener(ActionListener actionListener) {
        this.btn8.addActionListener(actionListener);
    }
    public JFrame getFrame() {
        return this.frame;
    }

    public void button3Listener(ActionListener actionListener) {
        this.button3.addActionListener(actionListener);
    }

    public void buttonListener(ActionListener actionListener) {
        this.button.addActionListener(actionListener);
    }

    public void button2Listener(ActionListener actionListener) {
        this.button2.addActionListener(actionListener);
    }

    public void button4Listener(ActionListener actionListener) {
        this.button4.addActionListener(actionListener);
    }

    public void button5Listener(ActionListener actionListener) {
        this.button5.addActionListener(actionListener);
    }

    public void button6Listener(ActionListener actionListener) {
        this.button6.addActionListener(actionListener);
    }

    public void button7Listener(ActionListener actionListener) {
        this.button7.addActionListener(actionListener);
    }

    public void button8Listener(ActionListener actionListener) {
        this.button8.addActionListener(actionListener);
    }

    public void button9Listener(ActionListener actionListener) {
        this.button9.addActionListener(actionListener);
    }
}