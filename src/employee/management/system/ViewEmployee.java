package employee.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("ALL")
public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceemp;
    JButton searchbtn, print, update, back;

    ViewEmployee() {
        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel search = new JLabel("Search by employee ID");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceemp = new Choice();
        choiceemp.setBounds(180, 20, 150, 20);
        add(choiceemp);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT empid FROM employee");
            while (resultSet.next()) {
                choiceemp.add(resultSet.getString("empid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20, 70, 80, 20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn) {
            String empid = choiceemp.getSelectedItem();
            if (empid != null && !empid.isEmpty()) {
                String query = "SELECT * FROM employee WHERE empid = ?";
                try {
                    conn c = new conn();
                    PreparedStatement preparedStatement = c.connection.prepareStatement(query);
                    preparedStatement.setString(1, empid);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No Employee ID selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            String empid = choiceemp.getSelectedItem();
            if (empid != null && !empid.isEmpty()) {
                setVisible(false);
                new UpdateEmployee(empid);
            } else {
                JOptionPane.showMessageDialog(this, "Select an Employee ID before updating.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
