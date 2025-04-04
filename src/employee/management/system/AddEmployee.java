package employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@SuppressWarnings("ALL")
public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();

    int number = ran.nextInt(999999);

    JTextField tname, tfname, taddress, tphone, tnid, temail, tsalary, tdesignation;
    JLabel tempid;

    JButton add,back;


     JDateChooser tdob;

     JComboBox teducation;

    AddEmployee(){

        getContentPane().setBackground(new Color(163,255,188));


        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);




        JLabel name = new JLabel("Name");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(name);


        tname = new JTextField();
        tname.setBounds(200,150,150,30);
        tname.setBackground(new Color(177,252,197));
        add(tname);



        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(400,150,150,30);
        fname.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(fname);


        tfname = new JTextField();
        tfname.setBounds(600,150,150,30);
        tfname.setBackground(new Color(177,252,197));
        add(tfname);




        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(50,200,150,30);
        dob.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200,200,150,30);
        tdob.setBackground(new Color(177,252,197));
        add(tdob);



        JLabel salary = new JLabel("Salary");
        salary.setBounds(400,200,150,30);
        salary.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(salary);


        tsalary = new JTextField();
        tsalary.setBounds(600,200,150,30);
        tsalary.setBackground(new Color(177,252,197));
        add(tsalary);


        JLabel address = new JLabel("Address");
        address.setBounds(50,250,150,30);
        address.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(address);


        taddress = new JTextField();
        taddress.setBounds(200,250,150,30);
        taddress.setBackground(new Color(177,252,197));
        add(taddress);


        JLabel phone = new JLabel("Phone No.");
        phone.setBounds(400,250,150,30);
        phone.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(phone);


        tphone = new JTextField();
        tphone.setBounds(600,250,150,30);
        tphone.setBackground(new Color(177,252,197));
        add(tphone);


        JLabel email = new JLabel("Email");
        email.setBounds(50,300,150,30);
        email.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(email);


        temail = new JTextField();
        temail.setBounds(200,300,150,30);
        temail.setBackground(new Color(177,252,197));
        add(temail);


        JLabel education = new JLabel("Education");
        education.setBounds(400,300,150,30);
        education.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(education);


        String[] degrees = {"CSE","SWE","BBA","MBA","LLB","BA","MA"};
        teducation = new JComboBox(degrees);
        teducation.setBackground(new Color(177,252,197));
        teducation.setBounds(600,300,150,30);
        add(teducation);



        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50,400,150,30);
        empid.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(empid);


        tempid = new JLabel(" "+number);
        tempid.setBounds(200,400,150,30);
        tempid.setFont(new Font("SAN_SARIF",Font.BOLD,20));
        tempid.setForeground(Color.BLACK);
        add(tempid);



        JLabel nid = new JLabel("NID No.");
        nid.setBounds(400,350,150,30);
        nid.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(nid);


        tnid = new JTextField();
        tnid.setBounds(600,350,150,30);
        tnid.setBackground(new Color(177,252,197));
        add(tnid);


        JLabel designation = new JLabel("Designation");
        designation.setBounds(50,350,150,30);
        designation.setFont(new Font("SAN_SARIF", Font.BOLD,20));
        add(designation);


        tdesignation = new JTextField();
        tdesignation.setBounds(200,350,150,30);
        tdesignation.setBackground(new Color(177,252,197));
        add(tdesignation);


        add = new JButton("Add Employee");
        add.setBounds(450,550,150,40);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("Go Back");
        back.setBounds(250,550,150,40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);




        setSize(900,700);
        setLayout(null);
        setLocation(300,50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
            String name = tname.getText();
            String fname = tfname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = (String) teducation.getSelectedItem();
            String designation = tdesignation.getText();
            String nid = tnid.getText();
            String empid = tempid.getText();


            try {
                conn c = new conn();


                String query = "INSERT INTO employee (name, fname, dob, salary, address, phone, email, education, designation, nid, empid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, fname);
                pstmt.setString(3, dob);
                pstmt.setString(4, salary);
                pstmt.setString(5, address);
                pstmt.setString(6, phone);
                pstmt.setString(7, email);
                pstmt.setString(8, education);
                pstmt.setString(9, designation);
                pstmt.setString(10, nid);
                pstmt.setString(11, empid);

                int rowsInserted = 0;
                try {
                    rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Employee added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add employee. Try again!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }


                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Details added successfully");
                    setVisible(false);
                    new Main();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to add employee details");
            }
        }else {
            setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args){
        new AddEmployee();
    }
}