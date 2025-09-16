package remote_rmi;

import remote.RemoteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClient {
    public static void main(String[] args) {
        try {
            // Kết nối tới RMI server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            RemoteService service = (RemoteService) registry.lookup("RemoteService");

            // GUI
            JFrame frame = new JFrame("Remote Control RMI");
            JLabel label = new JLabel();
            frame.add(new JScrollPane(label));
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Mouse
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        service.mouseClick();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            label.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent e) {
                    try {
                        service.mouseMove(e.getX(), e.getY());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Keyboard
            frame.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    try {
                        service.keyPress(e.getKeyCode());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                public void keyReleased(KeyEvent e) {
                    try {
                        service.keyRelease(e.getKeyCode());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            frame.setVisible(true);

            // Thread update screen
            new Thread(() -> {
                while (true) {
                    try {
                        byte[] data = service.captureScreen();
                        if (data != null) {
                            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(data));
                            label.setIcon(icon);
                            frame.repaint();
                        }
                        Thread.sleep(200); // 5 fps
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
