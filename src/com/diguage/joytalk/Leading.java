package com.diguage.joytalk;

/**
 * @author D瓜哥，http://www.diguage.com/
 *
 * Date: 2008-6
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Leading extends JFrame {
    private JLabel label1, label2;
    private JButton button;
    private JProgressBar progressBar;
    int progressNumber;
    JPanel progressPanel;

    public Leading() {
        setTitle("正在登录");

        progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));


        setIconImage(new ImageIcon("Image/QQ.gif").getImage());
        try {
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Timer timer = new Timer(1000, new TimerActionListener());
        timer.setRepeats(true);
        timer.start();

        progressNumber = 0;
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progressNumber);
        progressBar.setStringPainted(true);
        progressBar.setBounds(new Rectangle(150, 20));
        //progressPanel.add(progressBar);


        Icon icon = new ImageIcon("Images/progress.gif");
        label1 = new JLabel(icon);
        //label1.setBounds(40, 150, 120, 80);
        progressPanel.add(label1);

        label2 = new JLabel("正在登录，请稍等...");
        // label2.setBounds(50, 230, 220, 20);
        progressPanel.add(label2);

        //	Container container = getContentPane();
        //	container.setLayout(null);
        //container.add(label1);
        //container.add(label2);
        //	container.add(progressBar);
        button = new JButton("取消登录");
        progressPanel.add(progressBar);
        progressPanel.add(button);
        //	button.setBounds(50, 280, 100, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                timer.stop();
                new LogInFrame();
            }
        });
        //	container.add(button);
        //	container.
        setBackground(new Color(224, 244, 244));

        setBounds(750, 20, 220, 550);
        this.add(progressPanel);

        this.setSize(220, 660);
        // this.setLocationRelativeTo(null); // 设为null，
        // 将窗口置于屏幕的中央
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class TimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            progressNumber += 10;
            progressBar.setValue(progressNumber);
            if (progressNumber == 100) {
                dispose();
                new TalkingFrame();
            }
        }
    }

    public static void main(String args[]) {
        new Leading();

    }

}
