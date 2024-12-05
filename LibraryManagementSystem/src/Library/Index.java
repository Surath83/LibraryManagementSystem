package Library;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.util.Scanner;

public class Index {
    static String jdbcurl = "jdbc:mysql://localhost:3306/librarymanagementsystem";
    static String username = "root";
    static String password = "root";
    static LocalDate currDate = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static String currentDate = currDate.format(formatter);

    public class Admin {
        public void insert() {
            JFrame insertFrame = new JFrame("Insert Panel");
            insertFrame.setBounds(100, 100, 400, 400);
            JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));

            panel.add(new JLabel("USN:"));
            JTextField txtUSN = new JTextField();
            panel.add(txtUSN);

            panel.add(new JLabel("Name:"));
            JTextField txtName = new JTextField();
            panel.add(txtName);

            panel.add(new JLabel("Branch:"));
            JTextField txtBranch = new JTextField();
            panel.add(txtBranch);

            panel.add(new JLabel("Books No:"));
            JTextField txtBooksNo = new JTextField();
            panel.add(txtBooksNo);

            panel.add(new JLabel("Book Name:"));
            JTextField txtBookName = new JTextField();
            panel.add(txtBookName);

            panel.add(new JLabel("Book Author:"));
            JTextField txtBookAuthor = new JTextField();
            panel.add(txtBookAuthor);

            JButton btnSubmit = new JButton("Insert");
            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
                         Statement statement = connection.createStatement()) {

                        LocalDate issueDate = LocalDate.now();
                        LocalDate returnDate = issueDate.plusDays(15);

                        String insertData = "INSERT INTO LIBRARY(Usn, Name, Branch, BookSNo, Bookname, Authorname, Issuedata, Returndate) " +
                                "VALUES ('" + txtUSN.getText() + "', '" + txtName.getText() + "', '" + txtBranch.getText() + "', '" +
                                txtBooksNo.getText() + "', '" + txtBookName.getText() + "', '" + txtBookAuthor.getText() + "', '" +
                                issueDate.format(formatter) + "', '" + returnDate.format(formatter) + "')";

                        int rowAffected = statement.executeUpdate(insertData);
                        if (rowAffected > 0) {
                            JOptionPane.showMessageDialog(insertFrame, "Record Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(insertFrame, "Record Not Inserted", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(insertFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            panel.add(btnSubmit);

            insertFrame.getContentPane().add(panel);
            insertFrame.setVisible(true);
        }

        public void delete() {
            JFrame deleteFrame = new JFrame("Delete Panel");
            deleteFrame.setBounds(100, 100, 300, 150);
            JPanel panel = new JPanel(new FlowLayout());

            panel.add(new JLabel("Enter USN:"));
            JTextField txtUSN = new JTextField(15);
            panel.add(txtUSN);

            JButton btnDelete = new JButton("Delete");
            btnDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
                         Statement statement = connection.createStatement()) {

                        String deleteData = "DELETE FROM LIBRARY WHERE Usn='" + txtUSN.getText() + "'";

                        int rowAffected = statement.executeUpdate(deleteData);
                        if (rowAffected > 0) {
                            JOptionPane.showMessageDialog(deleteFrame, "Record Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(deleteFrame, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(deleteFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            panel.add(btnDelete);

            deleteFrame.getContentPane().add(panel);
            deleteFrame.setVisible(true);
        }
    }

    public class Student {
        public void showDetails() {
            JFrame studentFrame = new JFrame("Student Panel");
            studentFrame.setBounds(100, 100, 500, 300);
            JPanel panel = new JPanel(new FlowLayout());

            panel.add(new JLabel("Enter USN:"));
            JTextField txtUSN = new JTextField(15);
            panel.add(txtUSN);

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
                         Statement statement = connection.createStatement()) {

                        String query = "SELECT * FROM LIBRARY WHERE Usn='" + txtUSN.getText() + "'";
                        ResultSet rs = statement.executeQuery(query);

                        if (rs.next()) {
                            StringBuilder details = new StringBuilder();
                            details.append("USN: ").append(rs.getString("Usn"));
                            details.append("\nName: ").append(rs.getString("Name"));
                            details.append("\nBranch: ").append(rs.getString("Branch"));
                            details.append("\nBooks No: ").append(rs.getString("BookSNo"));
                            details.append("\nBook Name: ").append(rs.getString("Bookname"));
                            details.append("\nBook Author: ").append(rs.getString("Authorname"));
                            details.append("\nIssue Date: ").append(rs.getString("Issuedata"));
                            details.append("\nReturn Date: ").append(rs.getString("Returndate"));

                            JOptionPane.showMessageDialog(studentFrame, details.toString(), "Student Details", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(studentFrame, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(studentFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            panel.add(btnSubmit);

            studentFrame.getContentPane().add(panel);
            studentFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame("Library Management System");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 400, 200);
                    JPanel panel = new JPanel(new FlowLayout());

                    JButton btnAdmin = new JButton("Admin");
                    btnAdmin.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Index.Admin admin = new Index().new Admin();
                            JFrame adminFrame = new JFrame("Admin Panel");
                            adminFrame.setBounds(100, 100, 400, 200);
                            JPanel adminPanel = new JPanel(new FlowLayout());

                            JButton btnInsert = new JButton("Insert");
                            btnInsert.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    admin.insert();
                                }
                            });
                            adminPanel.add(btnInsert);

                            JButton btnDelete = new JButton("Delete");
                            btnDelete.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    admin.delete();
                                }
                            });
                            adminPanel.add(btnDelete);

                            adminFrame.getContentPane().add(adminPanel);
                            adminFrame.setVisible(true);
                        }
                    });
                    panel.add(btnAdmin);

                    JButton btnStudent = new JButton("Student");
                    btnStudent.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Index.Student student = new Index().new Student();
                            student.showDetails();
                        }
                    });
                    panel.add(btnStudent);

                    frame.getContentPane().add(panel);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
