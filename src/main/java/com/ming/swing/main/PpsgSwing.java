package com.ming.swing.main;

import com.ming.ppsg2.main.Main2;
import com.ming.ppsg2.utils.ThreadPoolUtils;
import com.ming.swing.assembly.MultiComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PpsgSwing {
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    /*private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("啪啪三国小助手");
        frame.setBounds(300, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        *//*JLabel label = new JLabel("Hello ");
        frame.getContentPane().add(label);*//*


        Object[] values=new Object[] {"全选","单位1","单位2","单位3","单位4","单位5","单位6"};
        MultiComboBox comboxstatus = new MultiComboBox(values);
        frame.getContentPane().add(comboxstatus);

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        frame.add(jPanel1, BorderLayout.NORTH);
        frame.add(jPanel2,BorderLayout.WEST);
        frame.add(jPanel3,BorderLayout.CENTER);
        frame.add(jPanel4,BorderLayout.EAST);
        frame.add(jPanel5,BorderLayout.SOUTH);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
*/
    public static void create(){
        JFrame frame = new JFrame("啪啪三国小助手");
        JPanel jp = new JPanel();    //创建面板
        JTextArea jTextArea = new JTextArea();

        Object[] values = getSelects();
        MultiComboBox comboxstatus = new MultiComboBox(values);

        JLabel label1=new JLabel("选择指定的武将：");    //创建标签
        JLabel label2=new JLabel();    //创建标签
        JLabel label = new JLabel();    //创建标签
        JButton btn1 = new JButton("计算");//创建JButton对象
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] selects = comboxstatus.getSelectedValues();
                label.setText("已选择武将："+ Arrays.toString(selects));
                ThreadPoolUtils.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            label2.setText("计算中，请稍后...");
                            Main2.start(selects,jTextArea);
                            label2.setText("已在桌面生成excel!");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });

            }
        });

        jp.add(label1);
        jp.add(comboxstatus);
        jp.add(btn1);

        JPanel jp2 = new JPanel();
        jp2.add(label);
        jp2.add(label2);


        frame.setBounds(300,200,400,200);
        frame.setLayout(new BorderLayout(10,5)); //默认为0，0；水平间距10，垂直间距5
//        frame.add(btn1,BorderLayout.EAST);
        //frame.add(btn2,BorderLayout.SOUTH);
        //frame.add(btn3,BorderLayout.WEST);
        frame.add(jp,BorderLayout.NORTH);
        frame.add(jp2,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                create();
            }
        });
    }

    public static Object[] getSelects(){
        Object[] values=new Object[] {"全选","砺战赵云",
                "御甲张辽",
                "桀骜孙策",
                "飞将吕布",
                "灵雎吕姬",
                "芳华大乔",
                "悦灵蔡文姬",
                "河间双雄",
                "奋威袁绍",
                "灵刃吕玲绮",
                "劫国董卓",
                "顾影貂蝉",
                "虎涧典韦",
                "霸业曹操",
                "墨衍荀彧",
                "诡骑张飞",
                "神武刘备",
                "陨星庞统",
                "狂澜吕蒙",
                "命世孙权",
                "烈胆凌统",
                "驭胜曹丕",
                "蜀魂姜维",
                "秉义太史慈",
                "猿戏华佗",
                "擎苍周泰",
                "淑懿甄姬",
                "回禄魏延",
                "奋武公孙瓒",
                "剑引张郃",
                "射日黄忠",
                "飞琼黄舞蝶",
                "灵泽孙尚香",
                "御战曹仁",
                "武帝曹操",
                "奸雄曹操",
                "宣王司马懿",
                "狼顾司马懿",
                "振威张辽",
                "召虎张辽",
                "独目夏侯惇",
                "苍狼夏侯惇",
                "混沌许褚",
                "英灵典韦",
                "恶来典韦",
                "鬼谋郭嘉",
                "天妒郭嘉",
                "征南曹仁",
                "疾风曹仁",
                "乱武贾诩",
                "毒士贾诩",
                "文帝曹丕",
                "灭奏曹丕",
                "典军夏侯渊",
                "虎步夏侯渊",
                "令香荀彧",
                "驱虎荀彧",
                "伶歌卞夫人",
                "绝情张春华",
                "汉寿亭侯云长",
                "洛神甄姬",
                "灵蛇甄姬",
                "龙胆赵云",
                "神威赵云",
                "一骑关羽",
                "武圣关羽",
                "七星诸葛亮",
                "卧龙诸葛亮",
                "凤雏庞统",
                "智极庞统",
                "锦狮马超",
                "铁骑马超",
                "斗胆姜维",
                "麒麟姜维",
                "仁德刘备",
                "昭烈刘备",
                "暴怒张飞",
                "桓侯张飞",
                "讨虏黄忠",
                "弓神黄忠",
                "狂骨魏延",
                "骁勇魏延",
                "落英黄舞蝶",
                "惊鸿黄舞蝶",
                "弓腰姬孙尚香",
                "郡主孙尚香",
                "阿丑黄月英",
                "杰女黄月英",
                "龙驹马云禄",
                "奇谋法正",
                "业炎周瑜",
                "顾曲周瑜",
                "克己吕蒙",
                "虎威吕蒙",
                "忠魂鲁肃",
                "缔盟鲁肃",
                "焚天陆逊",
                "儒将陆逊",
                "若虎孙权",
                "吴王孙权",
                "猛虎孙坚",
                "武烈孙坚",
                "驰骋孙策",
                "霸王孙策",
                "游侠甘宁",
                "斗将甘宁",
                "感古太史慈",
                "矢志太史慈",
                "浴血凌统",
                "国士凌统",
                "挽歌大乔",
                "望月大乔",
                "临江小乔",
                "玉琼小乔",
                "凤仪步练师",
                "诛心孙大虎",
                "赤忠黄盖",
                "血痕周泰",
                "赤羽朱然",
                "双子吕姬",
                "猩红吕姬",
                "双子吕玲绮",
                "战姬吕玲绮",
                "倾世貂蝉",
                "绝舞貂蝉",
                "浊世董卓",
                "魔君董卓",
                "思召袁绍",
                "贵胄袁绍",
                "霸音文丑",
                "猎狐文丑",
                "恶目颜良",
                "鸦杀颜良",
                "修罗吕布",
                "鬼神吕布",
                "符咒张角",
                "天公张角",
                "雅歌张郃",
                "天子汉献帝",
                "妙手华佗",
                "妖仙于吉",
                "斩锋高顺",
                "豹魂祝融夫人",
                "白马公孙瓒",
                "天照卑弥呼",
                "风舞张星彩",
                "胡韵蔡文姬",
                "战魂华雄",
                "刀魂关平",
                "舜华吴夫人",
                "战锤许褚",
                "安乐公刘禅",
                "灵思何太后",
                "瞳心孙小虎",
                "潋滟步练师",
                "道玄诸葛果",
                "先勇张郃",
                "厉箭韩当",
                "筹谋程昱",
                "驭灵董白"
        };
        return values;
    }
}
