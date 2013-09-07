package com.diguage.joytalk;

/**
 * @author D瓜哥，http://www.diguage.com/
 *
 * Date: 2008-6-20 8:30:08
 *
 *JFontChooser.java Created on 2007年9月3日, 下午4:56 To change this
 *template, choose Tools | Template Manager and open the template in
 *the editor.
 *
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFontChooser extends JPanel implements ActionListener {
    /**
     * 字体选择器构造方法（可以传Font与Color的初始值，也可以是空构造函数，结果返回根据getFont与getColor得到相应的设置
     *
     * @author tewang
     */
    // 六个控件
    private Font font;

    private Color color;

    private JTextField jtFont = new JTextField(10); // 字体

    private JTextField jtFontStyle = new JTextField(10); // 样式

    private JTextField jtFontSize = new JTextField(10); // 大小

    private JList jlFontStyle;

    private JList jlFontSize;

    private JList jlFont;

    private JDialog jDialog;

    private JLabel showJLabel = new JLabel(); // 显示当前字体

    private JLabel instructionJLabel = new JLabel(); // 对当前字体进行说明

    private GraphicsEnvironment ge = GraphicsEnvironment
            .getLocalGraphicsEnvironment();

    private String[] jfontStr = ge.getAvailableFontFamilyNames();

    private String[] fontStyle = {"常规", "斜体", "粗体", "粗斜体"};

    private String[] fontSize = {"8", "9", "10", "11", "12", "14", "16", "18",
            "20", "22", "24", "26", "28", "36", "48", "72", "初号", "小初", "一号",
            "小一", "二号", "小二", "三号", "小三", "四号", "小四", "五号", "小五", "六号", "小六",
            "七号", "八号"};

    private int[] sizeValue = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26,
            28, 36, 48, 72, 42, 36, 26, 24, 22, 18, 16, 15, 14, 12, 11, 9, 7,
            6, 5, 4};

    private JButton okButton = new JButton("确定");

    private JButton cancelButton = new JButton("取消");

    // 删除线与下划线
    private JCheckBox jcDelLine = new JCheckBox("删除线(K)");

    private JCheckBox jcDownLine = new JCheckBox("下划线(U)");

    // 颜色
    private Object[] colorName = {"黑色", "蓝色", "青色", "深灰色", "灰色", "绿色", "浅灰色",
            "洋红色", "桔黄色", "粉红色", "红色", "白色", "黄色"};

    private Color[] colorValue = {Color.BLACK, Color.BLUE, Color.CYAN,
            Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE,
            Color.YELLOW};

    private JComboBox jComboBox = new JComboBox(colorName);

    /**
     * Creates a new instance of JFontChooser
     */
    public JFontChooser() {
        initJFont();
        initJFontStyle();
        initJFontSize();
        initOther();
        initComponentUpdate();
        initJDialog();
    }

    public JFontChooser(Font f, Color c) { // 根据传进来的字体与颜色进行设置
        initJFont();
        initJFontStyle();
        initJFontSize();
        initOther();
        this.font = f;
        this.color = c;
        setFontAndColor(f, c);
        initComponentUpdate();
        initJDialog();
    }

    private void initOther() {
        // 默认颜色与字体
        updateFont();// 设置一下默认字体与颜色
        // 效果与示例
        JLabel jl4 = new JLabel();
        jl4.setBorder(BorderFactory.createTitledBorder("效果"));
        jl4.setBounds(10, 160, 150, 130);
        this.add(jl4);
        JLabel jl5 = new JLabel();
        jl5.setBorder(BorderFactory.createTitledBorder("示例"));
        jl5.setBounds(170, 160, 220, 130);
        this.add(jl5);
        // ok与取消按钮
        okButton.setBounds(400, 30, 80, 25);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        cancelButton.setBounds(400, 60, 80, 25);
        this.add(okButton);
        this.add(cancelButton);
        // 示例
        showJLabel.setText((String) jlFont.getSelectedValue());
        showJLabel.setHorizontalAlignment(JLabel.CENTER);
        showJLabel.setBounds(180, 150, 200, 150);
        this.add(showJLabel);
        // 效果里的 删除线与下划线
        jcDelLine.setBounds(15, 180, 100, 25);
        jcDownLine.setBounds(15, 200, 100, 25);
        this.add(jcDelLine);
        this.add(jcDownLine);
        JLabel jl6 = new JLabel("颜色(C):");
        jl6.setBounds(15, 230, 100, 25);
        // 颜色
        jComboBox.addActionListener(this);
        jComboBox.setBounds(17, 255, 130, 22);
        jcDelLine.addActionListener(this);
        jcDownLine.addActionListener(this);
        this.add(jl6);
        this.add(jComboBox);
        // 下面的状态说明
        instructionJLabel.setText("该字体用于显示,打印时将使用最接近的匹配字体");
        instructionJLabel.setBounds(17, 290, 400, 25);
        this.add(instructionJLabel);
    }

    private void initComponentUpdate() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据传进来的font与color进行设置字体与颜色
     */
    private void setFontAndColor(Font f, Color c) {
        String fontStr = f.getFamily();
        int style = f.getStyle();
        int size = f.getSize();
        for (int i = 0; i < jfontStr.length; i++) {
            if (jfontStr.equals(fontStr)) {
                jlFont.setSelectedIndex(i);
            }
        }
        switch (style) {
            case Font.PLAIN:
                jlFontStyle.setSelectedIndex(0);
                break;
            case Font.ITALIC:
                jlFontStyle.setSelectedIndex(1);
                break;
            case Font.BOLD:
                jlFontStyle.setSelectedIndex(2);
                break;
            case Font.BOLD | Font.ITALIC:
                jlFontStyle.setSelectedIndex(3);
                break;
        }
        for (int i = 0; i < sizeValue.length; i++) {
            if (sizeValue[i] == size) {
                jlFontSize.setSelectedIndex(i);
            }
        }
        for (int i = 0; i < colorValue.length; i++) {
            if (colorValue.equals(c)) {
                jComboBox.setSelectedIndex(i);
            }
        }
        updateFont();
    }

    /**
     * 示例中改变showJLabel的字体样式
     */
    private Font updateFont() {
        String fontStr = (String) jlFont.getSelectedValue();
        String styleStr = (String) jlFontStyle.getSelectedValue();
        int style;
        int size = sizeValue[jlFontSize.getSelectedIndex()];
        if (styleStr.equals("常规")) {
            style = Font.PLAIN;
        } else if (styleStr.equals("斜体")) {
            style = Font.ITALIC;
        } else if (styleStr.equals("粗体")) {
            style = Font.BOLD;
        } else {
            style = Font.ITALIC | Font.BOLD;
        }
        Font f = new Font(fontStr, style, size);
        // 删除线与下划线
        String temp = (String) jlFont.getSelectedValue();
        if (jcDelLine.isSelected()) {
            if (jcDownLine.isSelected()) {
                showJLabel.setText("<html><s><u>" + temp + "</u></s><html>");
            } else if (!jcDownLine.isSelected()) {
                showJLabel.setText("<html><s>" + temp + "</s><html>");
            }
        } else if (!jcDelLine.isSelected()) {
            if (jcDownLine.isSelected()) {
                showJLabel.setText("<html><u>" + temp + "</u><html>");
            } else if (!jcDownLine.isSelected()) {
                showJLabel.setText(temp);
            }
        }
        // 颜色
        int select = jComboBox.getSelectedIndex();
        showJLabel.setForeground(colorValue[select]);
        return f;
    }

    /**
     * 初始化jfont 字体
     */
    private void initJFont() {
        jlFont = new JList(jfontStr);
        jlFont.setSelectedIndex(0);
        jtFont.setText((String) jlFont.getSelectedValue());
        JScrollPane jsp = new JScrollPane(jlFont);
        jlFont.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jtFont.setText((String) jlFont.getSelectedValue());
                showJLabel.setFont(updateFont());
            }
        });
        JLabel jl1 = new JLabel("字体(F:");
        jl1.setBounds(10, 0, 80, 30);
        jtFont.setBounds(10, 30, 150, 23);
        jsp.setBounds(10, 55, 150, 100);
        this.add(jl1);
        this.add(jtFont);
        this.add(jsp);
    }

    /**
     * 初始化字体样式
     */
    private void initJFontStyle() {
        jlFontStyle = new JList(fontStyle);
        jlFontStyle.setSelectedIndex(0);
        jtFontStyle.setText((String) jlFontStyle.getSelectedValue());
        jlFontStyle.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jtFontStyle.setText((String) jlFontStyle.getSelectedValue());
                showJLabel.setFont(updateFont());
            }
        });
        JLabel j2 = new JLabel("字形(Y): ");
        j2.setBounds(170, 0, 80, 30);
        jtFontStyle.setBounds(170, 30, 130, 23);
        jlFontStyle.setBounds(170, 55, 130, 100);
        this.add(j2);
        this.add(jtFontStyle);
        this.add(jlFontStyle);
    }

    /**
     * 初始化字体大小
     */
    private void initJFontSize() {
        jlFontSize = new JList(fontSize);
        jlFontSize.setSelectedIndex(0);
        jtFontSize.setText((String) jlFontSize.getSelectedValue());
        jlFontSize.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jtFontSize.setText((String) jlFontSize.getSelectedValue());
                showJLabel.setFont(updateFont());
            }
        });
        JScrollPane jspJL = new JScrollPane(jlFontSize);
        JLabel j3 = new JLabel("大小(S): ");
        j3.setBounds(310, 0, 80, 30);
        jtFontSize.setBounds(310, 30, 80, 23);
        jspJL.setBounds(310, 55, 80, 100);
        this.add(j3);
        this.add(jtFontSize);
        this.add(jspJL);
    }

    /**
     * 初始化JDialog
     */
    private void initJDialog() {
        this.setLayout(null);
        jDialog = new JDialog();
        jDialog.setResizable(false);
        jDialog.setTitle("字体选择器");
        jDialog.setModal(true);
        jDialog.setSize(510, 360);
        jDialog.add(this, BorderLayout.CENTER);
        jDialog.setLocationRelativeTo(null);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // 返回字体
            font = this.updateFont();
            int select = jComboBox.getSelectedIndex();
            showJLabel.setForeground(colorValue[select]);
            color = colorValue[select];
            jDialog.dispose();
        } else if (e.getSource() == cancelButton) {
            jDialog.dispose();
        } else if (e.getSource() == jcDelLine) {
            this.updateFont();
        } else if (e.getSource() == jcDownLine) {
            this.updateFont();
        } else if (e.getSource() == jComboBox) { // 颜色选择
            this.updateFont();
        }
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

//    public static void main(String[] args) {
//        // 两种,一种是传初始值进去
//        // JFontChooser jfc = new JFontChooser(f,c);
//        // System.out.println(jfc.getFont());
//        // System.out.println(jfc.getColor());
//        // 第二种是没有传初始值进去
//        JFontChooser jfc = new JFontChooser();
//        System.out.println(jfc.getFont());
//        System.out.println(jfc.getColor());
//    }
}
