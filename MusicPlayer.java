/**
 * @author 李君 2008-6-16 13:39:56 Blog:http://hi.baidu.com/joxiao
 */
import sun.audio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class MusicPlayer extends JPanel implements FilenameFilter,
        ActionListener
{
    private GridBagLayout layout;

    private GridBagConstraints constraints;

    JButton openButton;

    JButton playButton;

    JButton loopButton;

    JButton stopButton;

    JLabel musicNameLabel;

    File theFile;

    AudioData theData;

    InputStream nowPlaying;

    JFrame frame;

    public MusicPlayer(JFrame frame)
    {
        this.frame = frame;
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        openButton = new JButton("选择音乐");
        openButton.setDefaultCapable(true);
        playButton = new JButton("播放");
        playButton.setFont(new Font("Serif", Font.BOLD, 20));
        loopButton = new JButton();
        loopButton.setLayout(new BoxLayout(loopButton, BoxLayout.Y_AXIS));
        loopButton.add(new JLabel("循"));
        loopButton.add(new JLabel("环"));
        stopButton = new JButton();
        stopButton.setLayout(new BoxLayout(stopButton, BoxLayout.Y_AXIS));
        stopButton.add(new JLabel("停"));
        stopButton.add(new JLabel("止"));
        musicNameLabel = new JLabel("音乐：             ");
        constraints.fill = GridBagConstraints.NONE;
        this.addComponent(musicNameLabel, 0, 0, 4, 1);
        constraints.fill = GridBagConstraints.NONE;
        this.addComponent(stopButton, 1, 0, 1, 2);
        constraints.fill = GridBagConstraints.BOTH;
        this.addComponent(playButton, 1, 1, 2, 2);
        constraints.fill = GridBagConstraints.NONE;
        this.addComponent(loopButton, 1, 3, 1, 2);
        constraints.fill = GridBagConstraints.BOTH;
        this.addComponent(openButton, 3, 0, 4, 1);
		/*
         * this.add(musicNameLabel, BorderLayout.NORTH); this.add(playButton,
         * BorderLayout.CENTER); this.add(stopButton, BorderLayout.WEST);
         * this.add(loopButton, BorderLayout.EAST); this.add(openButton,
         * BorderLayout.SOUTH);
         */
        // 为按钮添加事件监听
        openButton.addActionListener(this);
        playButton.addActionListener(this);
        loopButton.addActionListener(this);
        stopButton.addActionListener(this);
        this.setVisible(true);
    }

    // 打开文件
    public void open()
    {
        FileDialog fd = new FileDialog(this.frame, "please select a file");
        fd.setFilenameFilter(this);
        fd.setVisible(true);
        try
        {
            theFile = new File(fd.getDirectory() + "/" + fd.getFile());
            if(theFile != null)
            {
                musicNameLabel.setText("音乐: " + theFile.getName());
                FileInputStream fis = new FileInputStream(theFile);
                AudioStream as = new AudioStream(fis);
                theData = as.getData();
            }
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        catch(SecurityException e)
        {
            System.out.println("security exception");
        }
    }

    // 开始播放
    public void play()
    {
        stop();
        if(theData == null)
            open();
        if(theData != null)
        {
            AudioDataStream ads = new AudioDataStream(theData);
            AudioPlayer.player.start(ads);
            nowPlaying = ads;
        }
    }

    // 停止播放
    public void stop()
    {
        if(nowPlaying != null)
        {
            AudioPlayer.player.stop(nowPlaying);
            nowPlaying = null;
        }
    }

    // 循环播放
    public void loop()
    {
        stop();
        if(theData == null)
            open();
        if(theData != null)
        {
            ContinuousAudioDataStream cads = new ContinuousAudioDataStream(
                    theData);
            AudioPlayer.player.start(cads);
            nowPlaying = cads;
        }
    }

    // 事件响应
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == playButton)
        {
            play();
        }
        if(e.getSource() == openButton)
        {
            open();
        }
        if(e.getSource() == loopButton)
        {
            loop();
        }
        if(e.getSource() == stopButton)
        {
            stop();
        }
    }

    public boolean accept(File dir, String name)
    {
        return true;
    }

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
        this.add(component);
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        MusicPlayer player = new MusicPlayer(frame);
        frame.add(player);
        frame.setSize(180, 120);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
