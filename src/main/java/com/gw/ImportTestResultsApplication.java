package com.gw;

import com.gw.service.ImportService;
import com.gw.ui.ImportGUI;
import com.gw.util.MKSCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mks.api.commands.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gw.util.MKSCommand.getSelectedIdList;
import static com.gw.util.MKSCommand.initMksCommand;

@SpringBootApplication
public class ImportTestResultsApplication {

    private static final Log log = LogFactory.getLog(ImportService.class);

    public static void main(String[] args) {
        try {
            ImportGUI imp = new ImportGUI();
            imp.ImportGUI();
            imp.glasspane.start();//开始动画加载效果
            MKSCommand m = new MKSCommand();
            initMksCommand();//初始化MKSCommand中的参数，并获得连接
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//设置与本机适配的swing样式
            Map<String,List<String>> l = getSelectedIdList();//获取到当前选中的id添加进集合Ids集合
            if(l.get("tsIds")!=null){
                log.info("连接成功！");
                if(l.get("tsIds").size()>1){
                    JOptionPane.showMessageDialog(null, "暂不支持多选！","错误",0);
                    System.exit(0);
                }else {
                    List<String> caseIds = l.get("caseIds");
                    Map<String,String> ids = new HashMap<String,String>();
                    List<Map<String, String>> caseName = m.getItemByIds(caseIds, Arrays.asList("Text","id"));//查询文档id包含字段heading
                    for(Map<String, String> s  : caseName){
                        String casename = imp.textxz(s.get("Text"));
                        imp.cmb.addItem(casename);
                        ids.put(s.get("id"),casename);
                    }
                    imp.sessionid = l.get("tsIds").get(0);
                    imp.caseIds = ids;
                    imp.glasspane.stop();
                }
            }else {
                log.info("连接失败（根据id查询出错）！");
                JOptionPane.showMessageDialog(null, "连接失败...","错误",0);
                System.exit(0);
            }
            SpringApplication.run(ImportTestResultsApplication.class, args);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"错误",0);
            System.exit(0); //关闭主程序
        }
    }
}
