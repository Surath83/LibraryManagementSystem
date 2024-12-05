package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI frame = new UI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = JOptionPane.showInputDialog(UI.this, "Enter Admin Password:", "Admin Login", JOptionPane.PLAIN_MESSAGE);
                if ("admin123".equals(password)) { // Replace "admin123" with your desired password
                    showAdminFrame();
                } else {
                    JOptionPane.showMessageDialog(UI.this, "Invalid Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnAdmin);

        JButton btnStudent = new JButton("Student");
        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStudentFrame();
            }
        });
        contentPane.add(btnStudent);
    }

    private void showAdminFrame() {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setBounds(100, 100, 500, 300);
        JPanel panel = new JPanel(new BorderLayout());

        // Display current date in the top-right corner
        JLabel lblDate = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblDate, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInsertPanel();
            }
        });
        buttonPanel.add(btnInsert);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeletePanel();
            }
        });
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.CENTER);
        adminFrame.getContentPane().add(panel);
        adminFrame.setVisible(true);
    }

    private void showInsertPanel() {
        JFrame insertFrame = new JFrame("Insert Panel");
        insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        insertFrame.setBounds(100, 100, 400, 300);
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

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

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Index.Admin admin = new Index().new Admin();
                try {
                    admin.insert(txtUSN.getText(), txtName.getText(), txtBranch.getText(), txtBooksNo.getText(), txtBookName.getText(), txtBookAuthor.getText());
                    JOptionPane.showMessageDialog(insertFrame, "Record Inserted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(insertFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnSubmit);

        insertFrame.getContentPane().add(panel);
        insertFrame.setVisible(true);
    }

    private void showDeletePanel() {
        JFrame deleteFrame = new JFrame("Delete Panel");
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setBounds(100, 100, 300, 150);
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(new JLabel("Enter USN:"));
        JTextField txtUSN = new JTextField(15);
        panel.add(txtUSN);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Index.Admin admin = new Index().new Admin();
                try {
                    admin.delete(txtUSN.getText());
                    JOptionPane.showMessageDialog(deleteFrame, "Record Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(deleteFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnSubmit);

        deleteFrame.getContentPane().add(panel);
        deleteFrame.setVisible(true);
    }

    private void showStudentFrame() {
        JFrame studentFrame = new JFrame("Student Panel");
        studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentFrame.setBounds(100, 100, 500, 300);
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(new JLabel("Enter USN:"));
        JTextField txtUSN = new JTextField(15);
        panel.add(txtUSN);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Index.Student student = new Index().new Student();
                try {
                    String details = student.getDetails(txtUSN.getText());
                    JOptionPane.showMessageDialog(studentFrame, details, "Student Details", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(studentFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnSubmit);

        studentFrame.getContentPane().add(panel);
        studentFrame.setVisible(true);
    }
}
