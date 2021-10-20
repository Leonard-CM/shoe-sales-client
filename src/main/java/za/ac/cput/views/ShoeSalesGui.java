package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoeSalesGui extends JFrame implements ActionListener {

    private JLabel lblMenuName;
    private JButton btnAddShoe;
    private JButton btnGetShoe;
    private JButton btnUpdateShoe;
    private JButton btnDeleteShoe;
    private JLabel lblPadding;
    private JButton btnExit;

    public ShoeSalesGui(){
        super("Shoe Sales Application");
        lblMenuName = new JLabel("Shoe Menu", (int) CENTER_ALIGNMENT);
        btnAddShoe = new JButton("Add a New Shoe");
        btnGetShoe = new JButton("View All Shoe");
        btnUpdateShoe = new JButton("Update Shoe Details");
        btnDeleteShoe = new JButton("Delete Shoe");
        lblPadding = new JLabel("             ");
        btnExit = new JButton("Exit");
    }

    public void setShoeGUI(){
        this.setLayout(new GridLayout(7,1));
        this.add(lblMenuName);

        this.add(btnAddShoe);
        btnAddShoe.addActionListener(this);
        this.add(btnGetShoe);
        //actionList
        this.add(btnUpdateShoe);
        //actionList
        this.add(btnDeleteShoe);
        //actionList


        this.add(lblPadding);
        this.add(btnExit);
        btnExit.addActionListener(this);
        this.setPreferredSize(new Dimension(300, 300));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddShoe) {
            JOptionPane.showMessageDialog(null,"Shoe Menu Option Selected");
        }
        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ShoeSalesGui().setShoeGUI();
    }
}
