import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class SpecialView {

    private JFrame frame;
    private JPanel gridPanel1, gridPanel2, panel3, buttonPanel;
    private JCheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    private ButtonGroup ramens;
    private JRadioButton ramen, ramen2, ramen3;
    private JTextField text;
    private Border blackBorder;
    private JButton button, button2, button3, button4;

    public SpecialView() {

        frame = new JFrame("Special Vending Machine");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        gridPanel1 = new JPanel();
        gridPanel1.setLayout(new GridLayout(2, 5, 10, 10));

        checkBox = new JCheckBox();
        checkBox.setText("Ajitsuke Tamago");
        checkBox.setFocusable(false);
        checkBox.setOpaque(true);
        checkBox.setBackground(Color.lightGray);
        gridPanel1.add(checkBox);

        checkBox2 = new JCheckBox();
        checkBox2.setText("Fish Cake");
        checkBox2.setFocusable(false);
        checkBox2.setOpaque(true);
        checkBox2.setBackground(Color.lightGray);
        gridPanel1.add(checkBox2);

        checkBox3 = new JCheckBox();
        checkBox3.setText("Fried Tofu");
        checkBox3.setFocusable(false);
        checkBox3.setOpaque(true);
        checkBox3.setBackground(Color.lightGray);
        gridPanel1.add(checkBox3);

        checkBox4 = new JCheckBox();
        checkBox4.setText("Tempura");
        checkBox4.setFocusable(false);
        checkBox4.setOpaque(true);
        checkBox4.setBackground(Color.lightGray);
        gridPanel1.add(checkBox4);

        checkBox5 = new JCheckBox();
        checkBox5.setText("Tonkotsu");
        checkBox5.setFocusable(false);
        checkBox5.setOpaque(true);
        checkBox5.setBackground(Color.lightGray);
        gridPanel1.add(checkBox5);

        checkBox6 = new JCheckBox();
        checkBox6.setText("Gyoza");
        checkBox6.setFocusable(false);
        checkBox6.setOpaque(true);
        checkBox6.setBackground(Color.lightGray);
        gridPanel1.add(checkBox6);

        checkBox7 = new JCheckBox();
        checkBox7.setText("Karaage");
        checkBox7.setFocusable(false);
        checkBox7.setOpaque(true);
        checkBox7.setBackground(Color.lightGray);
        gridPanel1.add(checkBox7);

        checkBox8 = new JCheckBox();
        checkBox8.setText("Mentaiko");
        checkBox8.setFocusable(false);
        checkBox8.setOpaque(true);
        checkBox8.setBackground(Color.lightGray);
        gridPanel1.add(checkBox8);

        checkBox9 = new JCheckBox();
        checkBox9.setText("Black Garlic Oil");
        checkBox9.setFocusable(false);
        checkBox9.setOpaque(true);
        checkBox9.setBackground(Color.PINK);
        gridPanel1.add(checkBox9);

        checkBox10 = new JCheckBox();
        checkBox10.setText("Green Onion");
        checkBox10.setFocusable(false);
        checkBox10.setOpaque(true);
        checkBox10.setBackground(Color.PINK);
        gridPanel1.add(checkBox10);

        frame.add(gridPanel1, BorderLayout.CENTER);

        gridPanel2 = new JPanel();
        gridPanel2.setLayout(new GridLayout(1, 3));

        ramens = new ButtonGroup();

        ramen = new JRadioButton("Tonkotsu Ramen");
        ramen.setSelected(true);
        ramens.add(ramen);
        gridPanel2.add(ramen);

        ramen2 = new JRadioButton("Shoyu Ramen");
        ramens.add(ramen2);
        gridPanel2.add(ramen2);

        ramen3 = new JRadioButton("Hakata Ramen");
        ramens.add(ramen3);
        gridPanel2.add(ramen3);

        frame.add(gridPanel2, BorderLayout.NORTH);

        panel3 = new JPanel(new BorderLayout());

        text = new JTextField();
        text.setPreferredSize(new Dimension(400,200));

        blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        text.setBorder(blackBorder);

        button = new JButton("Order");
        button2 = new JButton("Cancel");
        button3 = new JButton("Insert Cash");
        button4 = new JButton("Maintenance");

        buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(button);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        panel3.add(text, BorderLayout.WEST);
        panel3.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel3, BorderLayout.SOUTH);

        frame.setVisible(false);
    }

    public void btn1Listener(ActionListener actionListener) {
        this.button.addActionListener(actionListener);
    }

    public void btn2Listener(ActionListener actionListener) {
        this.button2.addActionListener(actionListener);
    }

    public void btn3Listener(ActionListener actionListener) {
        this.button3.addActionListener(actionListener);
    }


    //public void btn4Listener(ActionListener actionListener) {
    //    this.button4.addActionListener(actionListener);
   // }

    public void btn1RamenListener(ActionListener actionListener) {
        this.ramen.addActionListener(actionListener);
    }

    public void btn2RamenListener(ActionListener actionListener) {
        this.ramen2.addActionListener(actionListener);
    }

    public void btn3RamenListener(ActionListener actionListener) {
        this.ramen3.addActionListener(actionListener);
    }
    /*

    public void btnAO1Listener(ActionListener actionListener) {
        this.checkBox.addActionListener(actionListener);
    }
    public void btnAO2Listener(ActionListener actionListener) {
        this.checkBox2.addActionListener(actionListener);
    }

    public void btnAO3Listener(ActionListener actionListener) {
        this.checkBox3.addActionListener(actionListener);
    }

    public void btnAO4Listener(ActionListener actionListener) {
        this.checkBox4.addActionListener(actionListener);
    }

    public void btnAO5Listener(ActionListener actionListener) {
        this.checkBox5.addActionListener(actionListener);
    }

    public void btnAO6Listener(ActionListener actionListener) {
        this.checkBox6.addActionListener(actionListener);
    }

    public void btnAO7Listener(ActionListener actionListener) {
        this.checkBox7.addActionListener(actionListener);
    }

    public void btnAO8Listener(ActionListener actionListener) {
        this.checkBox8.addActionListener(actionListener);
    }

    public void btnAO9Listener(ActionListener actionListener) {
        this.checkBox9.addActionListener(actionListener);
    }

    public void btnAO10Listener(ActionListener actionListener) {
        this.checkBox10.addActionListener(actionListener);
    }

     */

    public JFrame getFrame() {
        return this.frame;
    }

    public static void main(String[] args){
        SpecialView rvmView = new SpecialView();
    }
}