/**
 * @author 李君 2008-6-16 22:52:17 Blog:http://hi.baidu.com/joxiao
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInFrame extends JFrame
{
    private Container container;

    private GridBagLayout layout;

    private GridBagConstraints constraints;

    JPanel footPanel;

    JLabel userNameLabel;

    JLabel passwordLabel;

    JLabel imageLabel;

    JLabel welcomeLabel;

    JLabel joyTalkLabel;

    JTextField userNameField;

    JPasswordField passwordField;

    JButton logInButton;

    JButton registerButton;

    JButton forgetButton;

    JButton quitButton;

    JButton setButton;

    JComboBox styleCombo;

    String lnfName;

    Frame frame;

    private int baseY = 2; // 设置组件的基本参照位置！

    private int baseX = 2;

    private int textlength = 9;

    SetPanel setPanel;

    boolean openSetPanel = false;

    // set up GUI
    public LogInFrame()
    {
        super("JoyTalk-欢迎您！");
        frame = this;
        container = getContentPane();
        layout = new GridBagLayout();
        container.setLayout(layout);
        constraints = new GridBagConstraints();
        ActionHandler actionHandler = new ActionHandler();
        welcomeLabel = new JLabel(new ImageIcon("images/welcome.gif"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(welcomeLabel, baseY - 2, baseX - 2, 5, 2);
        joyTalkLabel = new JLabel(new ImageIcon("images/joyTalk2.gif"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(joyTalkLabel, baseY, baseX - 2, 1, 2);
        userNameLabel = new JLabel("用户名:");
        userNameLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userNameLabel, baseY, baseX - 1, 1, 1);
        passwordLabel = new JLabel(" 密码：");
        passwordLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(passwordLabel, baseY + 1, baseX - 1, 1, 1);
        userNameField = new JTextField(textlength);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userNameField, baseY, baseX, 1, 1);
        passwordField = new JPasswordField(textlength);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(passwordField, baseY + 1, baseX, 1, 1);
        logInButton = new JButton("登陆");
        logInButton.addActionListener(actionHandler);
        constraints.fill = GridBagConstraints.NONE;
        addComponent(logInButton, baseY, baseX + 2, 1, 1);
        registerButton = new JButton("注册");
        registerButton.addActionListener(actionHandler);
        constraints.fill = GridBagConstraints.NONE;
        addComponent(registerButton, baseY + 1, baseX + 2, 1, 1);
        footPanel = new JPanel();
        footPanel.setLayout(new FlowLayout());
        forgetButton = new JButton("忘记密码？");
        forgetButton.addActionListener(actionHandler);
        footPanel.add(forgetButton);
        quitButton = new JButton("退出");
        quitButton.addActionListener(actionHandler);
        footPanel.add(quitButton);
        setButton = new JButton("其他设置");
        setButton.addActionListener(actionHandler);
        footPanel.add(setButton);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(footPanel, baseY + 2, baseX - 2, 5, 1);
        setPanel = new SetPanel(this);
        this.setIconImage(new ImageIcon("images/head.jpg").getImage());
        addTray(); // 添加系统托盘图标
        this.setSize(280, 173);
        // this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);// 设为null，
        // 将窗口置于屏幕的中央
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } // end constructor

    // method to set constraints on
    private void addComponent(Component component, int row, int column,
                              int width, int height)
    {
        // set gridx and gridy
        constraints.gridx = column;
        constraints.gridy = row;
        // set gridwidth and gridheight
        constraints.gridwidth = width;
        constraints.gridheight = height;
        // set constraints and add component
        layout.setConstraints(component, constraints);
        container.add(component);
    }

    private class ActionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == logInButton)
            {
                new TalkingFrame();
            }
            else if(event.getSource() == registerButton)
            {
                dispose();
                // System.exit(0); //为什么不 能用这个呢？？
                new RegisterFrame();
            }
            else if(event.getSource() == forgetButton)
                JOptionPane.showMessageDialog(null, event.getSource()
                        .getClass()
                        + "忘记");
            else if(event.getSource() == quitButton)
            {
                int i = JOptionPane.showConfirmDialog(null, "你确定退出吗？", "退出",
                        JOptionPane.OK_CANCEL_OPTION);
                if(i == JOptionPane.YES_NO_OPTION)
                    System.exit(0);
            }
            else
            {
                if(!openSetPanel)
                {
                    addComponent(setPanel, baseY + 3, baseX - 2, 5, 2);
                    setSize(280, 228);
					/*
                     * if(setPanel.styleCombo.styleCombo.getSelectedItem().equals(setPanel.styleCombo.styleCombo.getItemAt(1)))
                     * setSize(300,235);
                     */
                }
                else
                {
                    remove(setPanel);
                    setSize(280, 173);
                }
                openSetPanel = !openSetPanel;
            }
        }
    }

    // 此方法是添加系统托盘图片！
    // 并非我自创！
    // 是从《Java JDK6 学习笔记》从学习来的！
    public void addTray()
    {
        if(SystemTray.isSupported()) // isSuported()测试系统是否支持系统工具栏图标
        {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(
                    "images/tray3.gif");
            TrayIcon trayIcon = new TrayIcon(image, "JoyTalk");
            try
            {
                tray.add(trayIcon);
            }
            catch(AWTException e)
            {
                System.err.println("无法加入系统工具栏图标");
                e.printStackTrace();
            }
        }
        else
        {
            System.err.println("无法取得系统工具栏");
        }
    }

    public static void main(String args[])
    {
        // LogInFrame logIn =
        new LogInFrame();
    }
}