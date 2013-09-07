/**
 * @author 李君 2008-6-18 13:28:33 Blog:http://hi.baidu.com/joxiao
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StyleCombo
{
    JComboBox styleCombo;

    String lnfName;

    final Frame frame;

    public StyleCombo(final Frame frame)
    {
        this.frame = frame;
        String[] styleStr = { "Metal", "Motif", "Windows" };
        styleCombo = new JComboBox(styleStr);
        // styleCombo.setSelectedIndex(0);
        styleCombo.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent arg0)
            {
                if(styleCombo.getSelectedItem().equals(styleCombo.getItemAt(0)))
                {
                    lnfName = "javax.swing.plaf.metal.MetalLookAndFeel";
                    // lnfName =
                    // "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                }
                else if(styleCombo.getSelectedItem().equals(
                        styleCombo.getItemAt(1)))
                {
                    lnfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                }
                else
                {
                    lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                    // lnfName =
                    // "javax.swing.plaf.metal.MetalLookAndFeel";
                }
                try
                {
                    UIManager.setLookAndFeel(lnfName);
                    SwingUtilities.updateComponentTreeUI(frame);
                }
                catch(UnsupportedLookAndFeelException ex1)
                {
                    System.err.println("Unsupported LookAndFeel: " + lnfName);
                }
                catch(ClassNotFoundException ex2)
                {
                    System.err.println("LookAndFeel class not found: "
                            + lnfName);
                }
                catch(InstantiationException ex3)
                {
                    System.err
                            .println("Could not load LookAndFeel: " + lnfName);
                }
                catch(IllegalAccessException ex4)
                {
                    System.err.println("Cannot use LookAndFeel: " + lnfName);
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        StyleCombo panel = new StyleCombo(frame);
        frame.add(panel.styleCombo);
        frame.setVisible(true);
        frame.setSize(200, 100);
    }
}
