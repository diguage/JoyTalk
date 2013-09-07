package com.diguage.joytalk;

/**
 * @author D瓜哥，http://www.diguage.com/
 *
 * Date: 2008-6-19 7:30:46
 */


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ClockPanel extends JPanel implements Runnable {

    Thread timer = null;
    int lastxs = 0, lastys = 0, lastxm = 0, lastym = 0, lastxh = 0,
            lastyh = 0;
    Date dummy = new Date();

    //	@SuppressWarnings("deprecation")
    String lastdate = dummy.toLocaleString();
    private int xcenter;
    private int ycenter;
    private int mlong;


    // 初始化小程序
    //@SuppressWarnings("deprecation")
    public ClockPanel(int xcenter, int ycenter, int mlong) {
        this.setXYCenter(xcenter, ycenter);
        this.setMlong(mlong);

        start();
    }

    public void setMlong(int mlong) {
        this.mlong = mlong > 0 ? mlong : 40;
    }

    public void setXcenter(int xcenter) {
        this.xcenter = xcenter > 65 ? xcenter : 75;
    }

    public void setYcenter(int ycenter) {
        this.ycenter = ycenter > 0 ? ycenter : 60;
    }

    public void setXYCenter(int x, int y) {
        this.setXcenter(x);
        this.setYcenter(y);
    }

    // 制作时钟界面
    public void plotpoints(int x0, int y0, int x, int y, Graphics g) {

        g.drawLine(x0 + x, y0 + y, x0 + x, y0 + y);
        g.drawLine(x0 + y, y0 + x, x0 + y, y0 + x);
        g.drawLine(x0 + y, y0 - x, x0 + y, y0 - x);
        g.drawLine(x0 + x, y0 - y, x0 + x, y0 - y);
        g.drawLine(x0 - x, y0 - y, x0 - x, y0 - y);
        g.drawLine(x0 - y, y0 - x, x0 - y, y0 - x);
        g.drawLine(x0 - y, y0 + x, x0 - y, y0 + x);
        g.drawLine(x0 - x, y0 + y, x0 - x, y0 + y);
    }

    // 画表盘
    public void circle(int x0, int y0, int r, Graphics g) {
        int x, y;
        float d;

        x = 0;
        y = r;
        d = 5 / 4 - r;
        plotpoints(x0, y0, x, y, g);

        while (y > x) {
            if (d < 0) {
                d = d + 2 * x + 3;
                x++;
            } else {
                d = d + 2 * (x - y) + 5;
                x++;
                y--;
            }
            plotpoints(x0, y0, x, y, g);
        }
    }

    // 画屏函数
    //@SuppressWarnings("deprecation")
    public void paint(Graphics g) {
        super.paint(g);

        int xh, yh, xm, ym, xs, ys, s, m, h;

        String today;
        Date dat = new Date();

        s = dat.getSeconds();
        m = dat.getMinutes();
        h = dat.getHours();
        today = dat.toLocaleString();


        xs = (int) (Math.cos(s * 3.14f / 30 - 3.14f / 2) * (mlong + 5) + xcenter);
        ys = (int) (Math.sin(s * 3.14f / 30 - 3.14f / 2) * (mlong + 5) + ycenter);
        xm = (int) (Math.cos(m * 3.14f / 30 - 3.14f / 2) * mlong + xcenter);
        ym = (int) (Math.sin(m * 3.14f / 30 - 3.14f / 2) * mlong + ycenter);
        xh = (int) (Math.cos((h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2)
                * (mlong - 10) + xcenter);
        yh = (int) (Math.sin((h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2)
                * (mlong - 10) + ycenter);

        // 画表盘

        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.setColor(Color.blue);
        circle(xcenter, ycenter, (mlong + 10), g);

        g.setColor(Color.darkGray);
        g.drawString("9", xcenter - (mlong + 5), ycenter + 3);
        g.drawString("3", xcenter + mlong, ycenter + 3);
        g.drawString("12", xcenter - 6, ycenter - (mlong - 3));
        g.drawString("6", xcenter - 2, ycenter + (mlong + 5));

        // 重画

        g.setColor(getBackground());
        if (xs != lastxs || ys != lastys) {
            g.drawLine(xcenter, ycenter, lastxs, lastys);
            g.drawString(lastdate, 5, 125);
        }
        if (xm != lastxm || ym != lastym) {
            g.drawLine(xcenter, ycenter - 1, lastxm, lastym);
            g.drawLine(xcenter - 1, ycenter, lastxm, lastym);
        }
        if (xh != lastxh || yh != lastyh) {
            g.drawLine(xcenter, ycenter - 1, lastxh, lastyh);
            g.drawLine(xcenter - 1, ycenter, lastxh, lastyh);
        }
        g.setColor(Color.darkGray);

        //用数字显示时间
        g.drawString(today, xcenter - 65, 2 * ycenter + 8);
        g.drawLine(xcenter, ycenter, xs, ys);
        g.setColor(Color.blue);
        g.drawLine(xcenter, ycenter - 1, xm, ym);
        g.drawLine(xcenter - 1, ycenter, xm, ym);
        g.drawLine(xcenter, ycenter - 1, xh, yh);
        g.drawLine(xcenter - 1, ycenter, xh, yh);
        lastxs = xs;
        lastys = ys;
        lastxm = xm;
        lastym = ym;
        lastxh = xh;
        lastyh = yh;
        lastdate = today;
    }

    public void start() {
        if (timer == null) {
            timer = new Thread(this);
            timer.start();
        }
    }

    public void stop() {
        timer = null;
    }

    public void run() {
        while (timer != null) {
            try {
                Thread.sleep(100);
                SwingUtilities.invokeAndWait(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO 自动生成 catch 块
                // e.printStackTrace();
            }
            repaint();
        }
        timer = null;
    }

    public void update(Graphics g) {
        paint(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ClockPanel clock = new ClockPanel(75, 60, 40);
        frame.add(clock, BorderLayout.CENTER);

        //frame.add(button,BorderLayout.SOUTH);
        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}