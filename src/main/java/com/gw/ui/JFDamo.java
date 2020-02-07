/*
 * Created by JFormDesigner on Wed Feb 05 15:22:54 CST 2020
 */

package com.gw.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author xinyu
 */
public class JFDamo extends JFrame {
    public JFDamo() {
        initComponents();
    }

    public static void main(String[] args){
        JFDamo f = new JFDamo();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
    }

//   点击查询case
    private void okButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String testSession = comboBox1.getSelectedItem().toString();
        String testSessionId = textField1.getText();
        if(testSession.equals("") && testSessionId.equals("")){
            JOptionPane.showMessageDialog(null,"请填写test Session ID !","错误",0);
            return;
        }else if(!testSession.equals("")){
//            searchCase(testSession);
            tabbedPane1.setSelectedIndex(1);
        }else {
//            searchCase(testSessionId);
            tabbedPane1.setSelectedIndex(1);
        }
    }
    //   点击返回查询
    private void backActionPerformed(ActionEvent e) {
        // TODO add your code here
        tabbedPane1.setSelectedIndex(0);
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        scrollPane1 = new JScrollPane();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel3 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();
        panel4 = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        scrollPane2 = new JScrollPane();
        panel5 = new JPanel();
        panel14 = new JPanel();
        label4 = new JLabel();
        textField2 = new JTextField();
        panel7 = new JPanel();
        back = new JButton();
        textField3 = new JTextField();
        label3 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(560, 400));
        setResizable(false);
        setTitle("test Session");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setMinimumSize(new Dimension(560, 400));
            tabbedPane1.setPreferredSize(new Dimension(131, 400));

            //======== scrollPane1 ========
            {
                scrollPane1.setMinimumSize(new Dimension(16, 500));

                //======== dialogPane ========
                {
                    dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
                    dialogPane.setMinimumSize(new Dimension(129, 500));
                    dialogPane.setLayout(new BorderLayout());

                    //======== contentPanel ========
                    {
                        contentPanel.setMaximumSize(new Dimension(560, 24));
                        contentPanel.setPreferredSize(new Dimension(162, 24));
                        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                        //======== panel3 ========
                        {
                            panel3.setPreferredSize(new Dimension(0, 60));
                            panel3.setMinimumSize(new Dimension(0, 60));
                            panel3.setMaximumSize(new Dimension(0, 60));
                            panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
                        }
                        contentPanel.add(panel3);

                        //======== panel1 ========
                        {
                            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                            //---- label1 ----
                            label1.setText("test Session    :");
                            label1.setMinimumSize(new Dimension(90, 10));
                            label1.setMaximumSize(new Dimension(90, 24));
                            label1.setPreferredSize(new Dimension(90, 24));
                            panel1.add(label1);

                            //---- comboBox1 ----
                            comboBox1.setMaximumSize(new Dimension(460, 24));
                            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                                "21204",
                                "21205",
                                "21206",
                                "21207",
                                "21208",
                                "21209",
                                "21210",
                                "21211",
                                "21212",
                                "21213"
                            }));
                            panel1.add(comboBox1);
                        }
                        contentPanel.add(panel1);

                        //======== panel4 ========
                        {
                            panel4.setMaximumSize(new Dimension(0, 30));
                            panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
                        }
                        contentPanel.add(panel4);

                        //======== panel2 ========
                        {
                            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

                            //---- label2 ----
                            label2.setText("test Session ID:");
                            label2.setMaximumSize(new Dimension(90, 24));
                            label2.setPreferredSize(new Dimension(90, 24));
                            label2.setMinimumSize(new Dimension(90, 17));
                            panel2.add(label2);

                            //---- textField1 ----
                            textField1.setMaximumSize(new Dimension(460, 24));
                            panel2.add(textField1);
                        }
                        contentPanel.add(panel2);
                    }
                    dialogPane.add(contentPanel, BorderLayout.CENTER);

                    //======== buttonBar ========
                    {
                        buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                        buttonBar.setLayout(new BorderLayout());

                        //---- okButton ----
                        okButton.setText("Search");
                        okButton.addActionListener(e -> okButtonActionPerformed(e));
                        buttonBar.add(okButton, BorderLayout.EAST);
                    }
                    dialogPane.add(buttonBar, BorderLayout.SOUTH);
                }
                scrollPane1.setViewportView(dialogPane);
            }
            tabbedPane1.addTab("Search", scrollPane1);
            tabbedPane1.setEnabledAt(0, false);

            //======== scrollPane2 ========
            {

                //======== panel5 ========
                {
                    panel5.setLayout(new BorderLayout());

                    //======== panel14 ========
                    {
                        panel14.setMaximumSize(new Dimension(400, 326));
                        panel14.setPreferredSize(new Dimension(400, 326));
                        panel14.setMinimumSize(new Dimension(148, 326));
                        panel14.setInheritsPopupMenu(true);
                        panel14.setLayout(new GridLayout());

                        //---- label4 ----
                        label4.setText("text");
                        panel14.add(label4);
                        panel14.add(textField2);
                    }
                    panel5.add(panel14, BorderLayout.CENTER);

                    //======== panel7 ========
                    {
                        panel7.setMinimumSize(new Dimension(105, 36));
                        panel7.setPreferredSize(new Dimension(105, 38));
                        panel7.setLayout(new BorderLayout());

                        //---- back ----
                        back.setText("back");
                        back.addActionListener(e -> backActionPerformed(e));
                        panel7.add(back, BorderLayout.EAST);
                        panel7.add(textField3, BorderLayout.NORTH);

                        //---- label3 ----
                        label3.setText("text");
                        panel7.add(label3, BorderLayout.WEST);
                    }
                    panel5.add(panel7, BorderLayout.SOUTH);
                }
                scrollPane2.setViewportView(panel5);
            }
            tabbedPane1.addTab("info", scrollPane2);
            tabbedPane1.setEnabledAt(1, false);
        }
        contentPane.add(tabbedPane1, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JScrollPane scrollPane1;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel3;
    private JPanel panel1;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    private JPanel panel4;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField1;
    private JPanel buttonBar;
    private JButton okButton;
    private JScrollPane scrollPane2;
    private JPanel panel5;
    private JPanel panel14;
    private JLabel label4;
    private JTextField textField2;
    private JPanel panel7;
    private JButton back;
    private JTextField textField3;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
