/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import autonet.CrawlerListener;
import autonet.RunnableCrawlerControl;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jur_1
 */
public class MainWindow extends JFrame {

  public MainWindow() {
    super("JButtonTable Example");

    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(new Object[][] { { "link 1", "button 1" },
        { "link 2", "button 2" } }, new Object[] { "Link", "Button" });

    JTable table = new JTable(dm);
    table.getColumn("Button").setCellRenderer(new ButtonRenderer());
    table.getColumn("Button").setCellEditor(
        new ButtonEditor(new JCheckBox()));
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add(scroll);
    setSize(1200, 800);
    setVisible(true);
  }

  public static void main(String[] args) {
  
   
    MainWindow frame = new MainWindow();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}
    
//
//    // Variables declaration - do not modify                     
//    // End of variables declaration                   
//
//    private void startCrawler(String text) 
//    {
////        scheduler =  Executors.newScheduledThreadPool(1);
////        scheduler.scheduleAtFixedRate(new RunnableCrawlerControl(this, text), 0, 1, TimeUnit.MINUTES);
//
//    }
//
//    public void stopCrawler() 
//    {
//
//    }
//
//    @Override
//    public void onError() {
//        
//        stopCrawler();
//    }
//}
