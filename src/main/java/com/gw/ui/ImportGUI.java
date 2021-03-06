package com.gw.ui;

import com.gw.service.ImportService;
import com.gw.ui.swingUI.InfiniteProgressPanel;
import com.gw.util.MKSCommand;
import com.gw.util.Result;
import com.mks.api.response.APIException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Swing面板 lxg
 *
 */public class ImportGUI extends JFrame {

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
    JTextField txtfield1=new JTextField(30);    //创建文本框

    public String sessionid = "";
    public  Map<String,String> caseIds = new HashMap<String,String>();

    List<Map<String,String>> testReulst; //查询返回的结果
    List<Map<String,String>> CaseReulst;

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
    JPanel tabGUi1 = new JPanel();  //总容器
    JPanel tabUp1 = new JPanel();   //
    JPanel tabUp2 = new JPanel();

    //导入界面 lxg
    public void ImportGUI() {

        //替换java默认图标
        ImageIcon icon=new ImageIcon("client.jpg"); //图片和项目同一路径，故不用图片的路径
        setIconImage(icon.getImage());


        setTitle("查询测试实例");
        setBounds(0, 0, 580, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(springLayout);//设置窗体布局格式为弹簧式布局
        setLocationRelativeTo(null);//窗体居中显示
        setResizable(false);//窗体是否可以放大

        //加载组件
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
        setGlassPane(glasspane);

        tab1();//tab1

        tab2(); //tab2

        initBtn(0);//按钮
//        int tabIndex = jtp.getSelectedIndex();
        tabGUi1.setLayout(new BorderLayout(20,-5));
        jtp.setPreferredSize(new Dimension(563,320));
        tabUp1.add(jtp);
        tabGUi1.add(tabUp1);
        tabGUi1.add(tabUp2,BorderLayout.SOUTH);

        add(tabGUi1);
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
//        box9.add(Box.createVerticalStrut(60));
//        box9.add(Box.createHorizontalStrut(500));
//        box9.add(box4);
//        Listener1(button1,0);
        jp2.add(box9);
        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条按需显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                jp2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        jtp.addTab("info" ,scrollPane);
        jtp.setEnabledAt(1,false); //tab不可选
    }

    public void tab1(){
        JLabel btn1=new JLabel("test Case :");
//        cmb.addItem("--请选择--");    //向下拉列表中添加一项、
        JLabel btn2=new JLabel("test Case ID :");
        box1.add(Box.createHorizontalStrut(20));
        box1.add(btn1);
        box1.add(Box.createHorizontalStrut(25));
        box1.add(cmb);
        cmb.setPreferredSize(new Dimension (30,28));
        cmb.addItem("——请选择——");
        box1.add(Box.createHorizontalStrut(20));

        box2.add(Box.createHorizontalStrut(20));
        box2.add(btn2);
        box2.add(Box.createHorizontalStrut(10));
        txtfield1.setPreferredSize(new Dimension (30,26));
        box2.add(txtfield1);
        box2.add(Box.createHorizontalStrut(20));

//        box3.add(Box.createHorizontalStrut(200));
//        box3.add(Box.createVerticalStrut(80));

//        log.info("窗口宽高"+getSize());
//        box5.add(Box.createRigidArea(getSize()));
//        box5.add(Box.createVerticalStrut(-350));
//        box5.add(Box.createVerticalGlue());
        box5.add(Box.createVerticalStrut(60));
        box5.add(box1);
        box5.add(Box.createVerticalStrut(20));
        box5.add(box2);
//        box5.add(Box.createVerticalStrut(160));
//        box5.add(box3);

        jp1.add(box5);
        jtp.addTab("Search" ,jp1);
        jtp.setEnabledAt(0,false);

    }


    //搜索按钮监听 lxg
    public void Listener1(JButton btn1, final int  index) {
        btn1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(index == 1){
                    MKSCommand m = new MKSCommand();
                    //获取session判断是否为空
                    String caseName = cmb.getSelectedItem().toString();
                    String testSession = "";
                    String testSessionId = txtfield1.getText();
                    int fla = 0;
                    for(String s : caseIds.keySet()){
                        if(caseName.equals(caseIds.get(s))){
                            testSession = s;
                        }
                        if(testSessionId.equals(s)){
                            fla ++;
                        }
                    }

                    testReulst = new ArrayList<Map<String,String>>();

                    box6.removeAll();
                    box7.removeAll();
                    box10.removeAll();

//                    CaseReulst = new HashMap<>();
                    if(testSession.equals("") && testSessionId.equals("")){
                        JOptionPane.showMessageDialog(null,"请填写test Session ID !","错误",0);
                        return;
                    }else if(!testSession.equals("")){
                        try {
                            testReulst = m.viewresultByCaseID(sessionid,testSession);   //测试结果数据
//                            CaseReulst =  m.viewIssueBySessionId(testSession,"caseId");
//                            log.info("点击搜索session ID : " + sessionid+","+testSessionId);
                            jtp.setSelectedIndex(index);
                            initBtn(1);
                            Swingfy();
                        } catch (APIException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",0);
                            ex.printStackTrace();
                        }
                    }else {
                        try {
                            if(fla == 0){
                                JOptionPane.showMessageDialog(null,"填写的CaseID没有与testSession关联，请重新输入 !","错误",0);
                                return;
                            }
                            testReulst = m.viewresultByCaseID(sessionid,testSessionId);     //测试结果数据
//                            CaseReulst =  m.viewIssueBySessionId(testSession,"caseId"); //case数据
//                             m.getResult("",testSession,"Test Case");
//                            log.info("点击搜索session : " + sessionid+","+testSession);
                            jtp.setSelectedIndex(index);
                            initBtn(1);
                            Swingfy();

                        } catch (APIException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",0);
                            ex.printStackTrace();
                        }
                    }
                }else {
                    jtp.setSelectedIndex(index);
                    initBtn(0);
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
        for(Map<String,String> testMap : testReulst){
            for(String s : testMap.keySet()){
                String value  = testMap.get(s);
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
                if(s.equals("Annotation")){      //文本域单独box
                    JTextArea jta = new JTextArea(value,4,54);
                    jta.setEnabled(false);
                    JPanel jpl = new JPanel();
                    jpl.add(jl);
                    jpl.add(jta);
                    box10.add(jpl);
                }else {                     //输入框每竖行一个box
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
//大于10的字体缩略
    public String textxz(String str){
        String  r = "";
        log.info("字符串长度："+str.length());
        if(str.length()>20){
            r = str.substring(0,20) + "...";
        }else {
            r = str;
        }
        log.info("缩略后字符串："+r );
        return r;
    }

    public void initBtn(int index){
        tabUp2.removeAll();
        JPanel jp = new JPanel();
        if(index == 0){
            JButton button = new JButton("Search");
            button.setFocusPainted(false);  //去掉按钮字体焦点框
            button.setPreferredSize(new Dimension(78,34));
            button.setLocation(120,0);
            Listener1(button,1);
            jp.add(button);
            tabUp2.setLayout(new BorderLayout());
            tabUp2.add(jp,BorderLayout.EAST);
        }else {
            JButton button = new JButton("back");
            button.setFocusPainted(false);  //去掉按钮字体焦点框
            button.setPreferredSize(new Dimension(81,32));
            Listener1(button,0);
            jp.add(button);
            tabUp2.setLayout(new BorderLayout());
            tabUp2.add(jp,BorderLayout.EAST);
        }

    }
}
