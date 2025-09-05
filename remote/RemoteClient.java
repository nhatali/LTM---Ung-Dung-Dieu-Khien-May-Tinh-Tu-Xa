package remote;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RemoteClient {

	public static void main(String[] args) throws Exception {
        String host = "127.0.0.1"; // đổi thành IP của server
        Socket socketImage = new Socket(host, 5000);
        Socket socketCmd = new Socket(host, 5001);

        ObjectInputStream in = new ObjectInputStream(socketImage.getInputStream());
        PrintWriter out = new PrintWriter(socketCmd.getOutputStream(), true);

        JFrame frame = new JFrame("Remote Control");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // gửi lệnh chuột khi click vào panel
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                out.println("CLICK");
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                out.println("MOVE:" + e.getX() + ":" + e.getY());
            }
        });

        frame.setVisible(true);

        // nhận và hiển thị hình ảnh
        while (true) {
            try {
                byte[] data = (byte[]) in.readObject();
                Image img = Toolkit.getDefaultToolkit().createImage(data);
                ImageIcon icon = new ImageIcon(img);
                label.setIcon(icon);
                frame.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
