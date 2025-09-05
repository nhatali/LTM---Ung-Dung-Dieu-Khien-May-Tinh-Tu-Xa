package remote;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class RemoteServer {

	public static void main(String[] args) throws Exception {
        ServerSocket serverImage = new ServerSocket(5000); // gửi hình
        ServerSocket serverCmd = new ServerSocket(5001);   // nhận lệnh
        System.out.println("Server started. Waiting for client...");

        Socket socketImage = serverImage.accept();
        Socket socketCmd = serverCmd.accept();

        System.out.println("Client connected!");

        ObjectOutputStream out = new ObjectOutputStream(socketImage.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socketCmd.getInputStream()));

        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // luồng gửi hình ảnh liên tục
        new Thread(() -> {
            try {
                while (true) {
                    BufferedImage img = robot.createScreenCapture(
                            new Rectangle(screenSize));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(img, "jpg", baos);
                    byte[] data = baos.toByteArray();
                    out.writeObject(data);
                    out.reset();
                    Thread.sleep(50); // ~20 FPS
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // luồng nhận lệnh
        String line;
        while ((line = in.readLine()) != null) {
            String[] cmd = line.split(":");
            switch (cmd[0]) {
                case "MOVE":
                    int x = Integer.parseInt(cmd[1]);
                    int y = Integer.parseInt(cmd[2]);
                    robot.mouseMove(x, y);
                    break;
                case "CLICK":
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    break;
                case "TYPE":
                    robot.keyPress(cmd[1].charAt(0));
                    robot.keyRelease(cmd[1].charAt(0));
                    break;
            }
        }
    }

}
