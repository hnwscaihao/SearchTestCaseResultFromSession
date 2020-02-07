package com.gw.ui;

import com.gw.service.ImportService;
import com.gw.ui.swingUI.InfiniteProgressPanel;
import com.gw.util.MKSCommand;
import com.gw.util.Result;
import com.mks.api.response.APIException;
import com.sun.xml.internal.ws.api.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static javax.swing.SpringLayout.*;

/**
 * Swing面板 lxg
 *
 */
public class ImportGUI extends JFrame {

    private static final Log log = LogFactory.getLog(ImportService.class);

    public File selectedFile = new File(""); //导入文件
    public InfiniteProgressPanel glasspane = new InfiniteProgressPanel(); //加载中

    //流式布局
    SpringLayout springLayout = new SpringLayout();
    // 创建一个进度条
    public JProgressBar progressBar = new JProgressBar();
    private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 100;
    private static int currentProgress = MIN_PROGRESS;
    //选项卡
    JTabbedPane jtp = new JTabbedPane();
    Container con = getContentPane();//获得窗体容器对象

    public JComboBox cmb=new JComboBox();    //创建JComboBox
    JTextField txtfield1=new JTextField(20);    //创建文本框

    public String sessionid = "";

    Map<String,String> testReulst; //查询返回的结果
    Map<String,String> CaseReulst;

    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box5 = Box.createVerticalBox();
    Box box4 = Box.createHorizontalBox();
    Box box6= Box.createVerticalBox();
    Box box7= Box.createVerticalBox();
    Box box8= Box.createHorizontalBox();
    Box box9= Box.createVerticalBox();
    Box box10= Box.createVerticalBox();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();

    //导入界面 lxg
    public void ImportGUI() {

        //替换java默认图标
        ImageIcon icon=new ImageIcon("client.jpg"); //图片和项目同一路径，故不用图片的路径
        setIconImage(icon.getImage());

        setTitle("查询测试实例");
        setBounds(0, 0, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(springLayout);//设置窗体布局格式为弹簧式布局
        setLocationRelativeTo(null);//窗体居中显示
        setResizable(false);//窗体是否可以放大

        tab1();//tab1

        tab2(); //tab2

        add(jtp);
        setVisible(true); //设置窗口是否可见

    }

    public void tab2(){
//        jp2.setLayout(new SpringLayout());
        JButton button1 = new JButton("返回");
        button1.setFocusPainted(false);  //去掉按钮字体焦点框
        box4.add(button1);

        box8.add(box6);
//        box8.add(Box.createHorizontalStrut(10));
        box8.add(box7);

        box9.add(box8);
        box9.add(box10);
        box9.add(Box.createVerticalStrut(60));
//        box9.add(Box.createHorizontalStrut(500));
        box9.add(box4);
        Listener1(button1,0);

        jp2.add(box9);
        jtp.addTab("info" ,jp2);
        jtp.setEnabledAt(1,false); //tab不可选
    }

    public void tab1(){
        JLabel btn1=new JLabel("test Case");
//        cmb.addItem("--请选择--");    //向下拉列表中添加一项、
        JLabel btn2=new JLabel("test Case ID");
        box1.add(Box.createHorizontalStrut(20));
        box1.add(btn1);
        box1.add(Box.createHorizontalStrut(25));
        box1.add(cmb);
        box1.add(Box.createHorizontalStrut(20));

        box2.add(Box.createHorizontalStrut(20));
        box2.add(btn2);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(txtfield1);
        box2.add(Box.createHorizontalStrut(20));

//        box3.add(Box.createHorizontalStrut(200));
        box3.add(Box.createVerticalStrut(80));
        JButton button = new JButton("Search");
        button.setFocusPainted(false);  //去掉按钮字体焦点框
        box3.add(button);
        Listener1(button,1);

        log.info("窗口宽高"+getSize());
        box5.add(Box.createRigidArea(getSize()));
        box5.add(Box.createVerticalStrut(-350));
//        box5.add(Box.createVerticalGlue());
        box5.add(box1);
        box5.add(Box.createVerticalStrut(20));
        box5.add(box2);
        box5.add(Box.createVerticalStrut(160));
        box5.add(box3);

        jp1.add(box5);
        jtp.addTab("Search" ,jp1);
        jtp.setEnabledAt(0,false);
    }


    //搜索按钮监听 lxg
    public void Listener1(JButton btn1,int index) {
        btn1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(index == 1){
                    MKSCommand m = new MKSCommand();
                    //获取session判断是否为空
                    String testSession = cmb.getSelectedItem().toString();
                    String testSessionId = txtfield1.getText();
                    testReulst = new HashMap<>();

                    box6.removeAll();
                    box7.removeAll();
                    box10.removeAll();

//                    CaseReulst = new HashMap<>();
                    if(testSession.equals("") && testSessionId.equals("")){
                        JOptionPane.showMessageDialog(null,"请填写test Session ID !","错误",0);
                        return;
                    }else if(!testSession.equals("")){
                        try {
                            testReulst = m.viewresultByCaseID(sessionid,testSession);     //测试结果数据
//                            CaseReulst =  m.viewIssueBySessionId(testSession,"caseId"); //case数据
//                             m.getResult("",testSession,"Test Case");
                            log.info("点击搜索session : " + sessionid+","+testSession);
                            jtp.setSelectedIndex(index);

                            Swingfy();
                        } catch (APIException ex) {
                            ex.printStackTrace();
                        }
                    }else {
                        try {
                            testReulst = m.viewresultByCaseID(sessionid,testSessionId);   //测试结果数据
//                            CaseReulst =  m.viewIssueBySessionId(testSession,"caseId");
                            log.info("点击搜索session ID : " + sessionid+","+testSessionId);
                            jtp.setSelectedIndex(index);

                            Swingfy();
                        } catch (APIException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else {
                    jtp.setSelectedIndex(index);
                }

//                log.info("点击搜索");
//                jtp.setSelectedIndex(index);
            }
        });
    }

    //info详情 复用代码
    public void Swingfy(){
        int i = 0;
        int box6L = 0;
        int box7L = 0;
        for(String s : testReulst.keySet()){
            String value  = testReulst.get(s);
//                                JLabel jl = new JLabel(s,JLabel.RIGHT);
            JTextField jl=new JTextField(s,16);
            jl.setEditable(false);  //不可编辑
            jl.setBorder(null);  //不显示边框
            jl.setHorizontalAlignment(JTextField.RIGHT);
            JTextField jt=new JTextField(value,24);
            jt.setEditable(false);  //不可编辑
//                                jt.setBorder(null);  //不显示边框
            JPanel jp = new JPanel();
            jp.setSize(30,1);
            if(s.equals("Annotation")){
                JTextArea jta = new JTextArea(value,4,54);
                jta.setEnabled(false);
                JPanel jpl = new JPanel();
                jpl.add(jl);
                jpl.add(jta);
                box10.add(jpl);
            }else {
                jp.add(jl);
                jp.add(jt);
                if(i<testReulst.size()/2){
                    box6.add(jp);
                    box6L++;
                }else {
                    box7.add(jp);
                    box7L++;
                }
            }
            i++;
        }
        if(box6L<box7L){  //保持2个box对称
            JPanel jpls = new JPanel();
            JTextField jl=new JTextField("",16);
            jl.setEditable(false);  //不可编辑
            jl.setBorder(null);  //不显示边框
            box6.add(jpls.add(jl));
        }else if(box6L>box7L){
            JPanel jpls = new JPanel();
            JTextField jl=new JTextField("",16);
            jl.setEditable(false);  //不可编辑
            jl.setBorder(null);  //不显示边框
            box7.add(jpls.add(jl));
        }
    }

}
