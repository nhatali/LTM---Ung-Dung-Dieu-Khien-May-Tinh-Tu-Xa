package remote;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {
    private Robot robot;
    private Dimension screenSize;

    public RemoteServiceImpl() throws RemoteException {
        super();
        try {
            robot = new Robot();
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] captureScreen() throws RemoteException {
        try {
            BufferedImage screen = robot.createScreenCapture(new Rectangle(screenSize));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screen, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void mouseMove(int x, int y) throws RemoteException {
        robot.mouseMove(x, y);
    }

    @Override
    public void mouseClick() throws RemoteException {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void keyPress(int keyCode) throws RemoteException {
        robot.keyPress(keyCode);
    }

    @Override
    public void keyRelease(int keyCode) throws RemoteException {
        robot.keyRelease(keyCode);
    }
}
