package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("ALL")
public class RemoveEmployee extends JFrame implements ActionListener {


    Choice choiceempid = new Choice();

    JButton delete,back;


    RemoveEmployee(){

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Tahoma",Font.BOLD,15));
        add(label);


        choiceempid = new Choice();
        choiceempid.setBounds(200,50,150,30);
        add(choiceempid);



        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while(resultSet.next()){
                choiceempid.add(resultSet.getString("empid"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50,100,100,30);
        labelname.setFont(new Font("Tahoma",Font.BOLD,15));
        add(labelname);


        JLabel textname = new JLabel();
        textname.setBounds(200,100,100,30);
        add(textname);


        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50,150,100,30);
        labelphone.setFont(new Font("Tahoma",Font.BOLD,15));
        add(labelphone);


        JLabel textphone = new JLabel();
        textphone.setBounds(200,150,100,30);
        add(textphone);



        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        labelemail.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelemail);

        JLabel textemail = new JLabel();
        textemail.setBounds(200,200,100,30);
        add(textemail);



        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee where empId = '"+choiceempid.getSelectedItem()+"'");
            while (resultSet.next()){
                textname.setText(resultSet.getString("name"));
                textphone.setText(resultSet.getString("phone"));
                textemail.setText(resultSet.getString("email"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        choiceempid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from employee where empId = '"+choiceempid.getSelectedItem()+"'");
                    while (resultSet.next()){
                        textname.setText(resultSet.getString("name"));
                        textphone.setText(resultSet.getString("phone"));
                        textemail.setText(resultSet.getString("email"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);


        back = new JButton("Back");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(700,80,200,200);
        add(img);



        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i12 = i11.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel image = new JLabel(i13);
        image.setBounds(0,0,1120,630);
        add(image);



        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == delete){
            try{
                conn c = new conn(); // Ensure your `conn` class handles DB connection properly

                // Use PreparedStatement for security and to prevent syntax errors
                String query = "DELETE FROM employee WHERE empid = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, choiceempid.getSelectedItem()); // Assuming empid is a String

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID not found");
                }

                setVisible(false);
                new Main().setVisible(true); // Ensure Main GUI is visible

            } catch(Exception ex) {
                ex.printStackTrace(); // Print error for debugging
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }



        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
