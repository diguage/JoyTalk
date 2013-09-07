/**
 * @author 李君 2008-5-17 23:34:44 Blog:http://hi.baidu.com/joxiao
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TalkingFrame extends JFrame
{
    JTabbedPane tabbedPane;

    JPanel talkPanel;

    JPanel playPanel;

    JPanel usePanel;

    JPanel toolPanel;

    JPanel eastPanel;

    ClockPanel clock;

    JPanel centerPanel;

    JTextArea acceptArea;

    JToolBar toolBar;

    JTextArea sendArea;

    String lnfName;

    StyleCombo styleCombo;

    Frame frame;

    JButton clearButton;

    JButton concelButton;

    JButton sendButton;

    String sendTip; // 输入框提示信息

    public TalkingFrame()
    {
        frame = this;
        this.setVisible(true);
        this.setIconImage(new ImageIcon("images/logo.ico").getImage());
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        talkPanel = new JPanel();
        talkPanel.setLayout(new FlowLayout());
        JButton talkButton[] = new JButton[6];
        for(int i = 1; i <= 6; i++)
        {
            talkButton[i - 1] = new JButton(new ImageIcon("images/1" + i
                    + ".jpg"));
            talkButton[i - 1].setBounds(new Rectangle(new Dimension(25, 26)));
            talkPanel.add(talkButton[i - 1]);
        }
        tabbedPane.addTab("聊天", null, talkPanel, "聊天选项");
        this.add(tabbedPane, BorderLayout.NORTH);
        playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        JButton playButton[] = new JButton[6];
        for(int i = 1; i <= 6; i++)
        {
            playButton[i - 1] = new JButton(new ImageIcon("images/2" + i
                    + ".jpg"));
            playPanel.add(playButton[i - 1]);
        }
        tabbedPane.addTab("娱乐", null, playPanel, "娱乐选项");
        usePanel = new JPanel();
        usePanel.setLayout(new FlowLayout());
        JButton useButton[] = new JButton[4];
        for(int i = 1; i <= 4; i++)
        {
            useButton[i - 1] = new JButton(new ImageIcon("images/3" + i
                    + ".jpg"));
            usePanel.add(useButton[i - 1]);
        }
        tabbedPane.addTab("应用", null, usePanel, "应用选项");
        toolPanel = new JPanel();
        toolPanel.setLayout(new FlowLayout());
        JButton toolButton[] = new JButton[3];
        for(int i = 1; i <= 3; i++)
        {
            toolButton[i - 1] = new JButton(new ImageIcon("images/4" + i
                    + ".jpg"));
            toolPanel.add(toolButton[i - 1]);
        }
        tabbedPane.addTab("工具", null, toolPanel, "工具选项");
        this.add(tabbedPane, BorderLayout.NORTH);
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        acceptArea = new JTextArea(13, 15);
        acceptArea.setLineWrap(true);
        acceptArea.setEditable(false);
        JScrollPane scrollAccept = new JScrollPane(acceptArea);
        scrollAccept
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollAccept
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollAccept);
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        JButton barButton[] = new JButton[10];
        for(int i = 1; i <= 10; i++)
        {
            barButton[i - 1] = new JButton(new ImageIcon("images/6" + i
                    + ".jpg"));
            toolBar.add(barButton[i - 1]);
        }
        styleCombo = new StyleCombo(this);
        toolBar.add(styleCombo.styleCombo);
        barButton[0].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JFontChooser fontChooser = new JFontChooser();
                Font font = fontChooser.getFont();
                acceptArea.setFont(font);
                sendArea.setFont(font);
            }
        });
        centerPanel.add(toolBar);
        sendTip = "请在这里输入文字，然后点击“发送”，发送信息！";
        sendArea = new JTextArea(sendTip, 4, 15);
        sendArea.setLineWrap(true);
        // 捕捉点击首次在输入框的点击事件，清除输入提示信息！
        sendArea.addMouseListener(new MouseAdapter()
        {
            boolean cleared = false;

            public void mouseClicked(MouseEvent e)
            {
                if(!cleared)
                {
                    if(sendArea.getText() != sendTip)
                    {
                        sendArea.setText(null);
                        cleared = !cleared;
                    }
                }
            }
        });
        JScrollPane scrollSend = new JScrollPane(sendArea); // 将输入框加入到滚动面板，并设置滚动条出现条件
        scrollSend
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollSend
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollSend);
        JPanel footPanel = new JPanel();
        footPanel.add(Box.createHorizontalGlue());
        footPanel.add(Box.createRigidArea(new Dimension(160, 0)));
        clearButton = new JButton("清空"); // 点击“清空”，清空输入框
        clearButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                sendArea.setText(null);
				/*
                 * setVisible(false); setSize(0,0);
                 */
            }
        });
        footPanel.add(clearButton);
        footPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        concelButton = new JButton("退出"); // 点击“退出”，显示确认对话框，点击“确定”，退出!
        concelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // dispose(); //使用这个方法似乎不是真正退出，为什么？？
                int i = JOptionPane.showConfirmDialog(null, "你确定退出吗？", "退出",
                        JOptionPane.OK_CANCEL_OPTION);
                if(i == JOptionPane.YES_NO_OPTION)
                    System.exit(0);
            }
        });
        footPanel.add(concelButton);
        // stringBuffer = new StringBuffer(10);
        footPanel.add(Box.createRigidArea(new Dimension(1, 0)));
        sendButton = new JButton("发送"); // 点击发送，将输入框的信息发送过去！
        sendButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(!(sendArea.getText() == null)) // 如何设置条件，使其在信息为空时不能发送？？
                {
                    // stringBuffer.append(sendArea.getText()
                    // + "\n\n");
                    // if(sendArea.getText()!=null)
                    // //如何让它为空是，不能发送？？
                    acceptArea.append("撒旦  " + new Date() + "\n"
                            + sendArea.getText() + "\n\n");
                    // acceptArea.setText(stringBuffer.toString());
                }
                sendArea.setText(null);
            }
        });
        sendButton.setDefaultCapable(true);
        footPanel.add(sendButton);
        centerPanel.add(footPanel);
        this.add(centerPanel, BorderLayout.CENTER);
        eastPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);
        // eastPanel.setLayout(new
        // BoxLayout(eastPanel,BoxLayout.Y_AXIS));
        eastPanel.setLayout(new BorderLayout());
        clock = new ClockPanel(80, 50, 35);
        // eastPanel.add(clock);
        eastPanel.add(clock, BorderLayout.CENTER);
        MusicPlayer musicPlayer = new MusicPlayer(this);
        // eastPanel.add(musicPlayer);
        eastPanel.add(musicPlayer, BorderLayout.SOUTH);
        this.setSize(560, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 设为null，
        // 将窗口置于屏幕的中央
    }
	/*
     * public static void main(String[] args) { new TalkingFrame(); }
     */
}
