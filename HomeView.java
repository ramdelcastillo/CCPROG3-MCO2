import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class HomeView {
    private JFrame frame;
    private JLabel title;
    private JButton rvm, svm, exit;
    private RegularView rv;
    private SpecialView sv;

    public HomeView() {
        this.frame = new JFrame("Vending Machine Factory Simulator");

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.frame.setSize(500, 165);

        this.title = new JLabel("Vending Machine Factory Simulator");
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        frame.add(title);

        this.rvm = new JButton("Regular Vending Machine");
        this.rvm.setPreferredSize(new Dimension(220, 30));
        frame.add(rvm);

        this.svm = new JButton("Special Vending Machine");
        this.svm.setPreferredSize(new Dimension(220, 30));
        frame.add(svm);

        this.exit = new JButton("Exit");
        this.exit.setPreferredSize(new Dimension(220, 30));
        frame.add(exit);

        this.frame.setVisible(true);
        rv = new RegularView();
        sv = new SpecialView();
    }

    public void rvmBtnListener(ActionListener actn) {
        this.rvm.addActionListener(actn);
    }

    public void svmBtnListener(ActionListener actn) {
        this.svm.addActionListener(actn);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public RegularView getRv() {
        return this.rv;
    }

    public SpecialView getSv() {
        return this.sv;
    }
}