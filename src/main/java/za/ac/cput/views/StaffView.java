package za.ac.cput.views;

import javax.swing.*;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.entity.Staff;
import za.ac.cput.factory.StaffFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StaffView extends JFrame implements ActionListener {

     public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    private static  OkHttpClient client = new OkHttpClient();

    private JPanel panelNorth, panelCenter, panelSouth;

    private JLabel lblTitle;
    private ImageIcon icon;
    private JLabel lblStaffId;
    private JTextField txtStaffId;
    private JLabel lblFirstName, lblRequiredFirstName;
    private JTextField txtFirstName, txtLastName;
    private JLabel lblLastName, lblRequiredLastName;

    private JButton btnSave, btnClear, btnExit;
    private Font ftTitle, ftRequired, ftMain;


    public StaffView() {
        super("Shoe Management system");
        panelNorth = new JPanel();
        panelNorth.setBackground(Color.white);
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        panelCenter.setBackground(new Color(3, 252, 240));
        icon = new ImageIcon("em.png");
        lblTitle = new JLabel("Cool-Kicks Staff", SwingConstants.CENTER);
        lblTitle.setForeground(Color.black);
        lblTitle.setIcon(icon);
        lblStaffId = new JLabel("Staff Id:", SwingConstants.RIGHT);
        txtStaffId = new JTextField("Auto-generated");
        lblRequiredFirstName = new JLabel();
        txtStaffId.setEnabled(false);
        lblFirstName = new JLabel("First Name:", SwingConstants.RIGHT);
        txtFirstName = new JTextField();
        lblLastName = new JLabel("Last Name:", SwingConstants.RIGHT);
        txtLastName = new JTextField();
        lblRequiredFirstName = new JLabel("first name is required");
        lblRequiredFirstName.setForeground(Color.RED);
        lblRequiredFirstName.setVisible(false);
        lblRequiredLastName = new JLabel("last name is requred");
        lblRequiredLastName.setForeground(Color.RED);
        lblRequiredLastName.setVisible(false);
        btnSave = new JButton("Create");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");
        ftTitle = new Font("Arial", Font.BOLD, 20);
        ftMain = new Font("Arial", Font.PLAIN, 15);
        ftRequired = new Font("Italic", Font.BOLD, 12);
    }

    public void setGUI() {
        panelNorth.setLayout(new GridLayout(1, 1));
        panelCenter.setLayout(new GridLayout(4, 4));
        panelSouth.setLayout(new GridLayout(1, 3));
        lblTitle.setFont(ftTitle);
        lblStaffId.setFont(ftMain);
        lblFirstName.setFont(ftMain);
        lblLastName.setFont(ftMain);

        btnSave.setFont(ftMain);
        btnClear.setFont(ftMain);
        btnExit.setFont(ftMain);
        lblRequiredFirstName.setFont(ftRequired);
        lblRequiredLastName.setFont(ftRequired);
        panelNorth.add(lblTitle);
        panelCenter.add(lblStaffId);
        panelCenter.add(txtStaffId);

        panelCenter.add(lblFirstName);
        panelCenter.add(txtFirstName);
        panelCenter.add(lblLastName);
        panelCenter.add(txtLastName);


        panelSouth.add(btnSave);
        panelSouth.add(btnClear);
        panelSouth.add(btnExit);


        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create")) {
            staff(txtFirstName.getText(),txtLastName.getText());
            JOptionPane.showMessageDialog(null, "Staff has been created!");

        } else if (e.getActionCommand().equals("Clear")) {
            txtFirstName.setText("");
            txtLastName.setText("");

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);

        }
    }

    public void staff(String firstName, String lastName) {
        try {
            final String URL = "http://localhost:8080/staff/create";
            Staff staff = new StaffFactory().createStaff(firstName, lastName);
            Gson g = new Gson();
            String jsonString = g.toJson(staff);
            String s = post(URL, jsonString);
            if (s != null)
                JOptionPane.showMessageDialog(null, "Staff has been created!");
            else
                JOptionPane.showMessageDialog(null, "Staff not created");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public String post(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static void main(String [] args){
        new StaffView().setGUI();

    }
}

