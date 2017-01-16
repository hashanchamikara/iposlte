/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.hw;

import com.isimple.intelijpos_lite.util.Const;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.swing.JOptionPane;

/**
 *
 * @author Hashan Chamikara
 * @email hashanchamikara@googlemail.com
 * @contact 0094 778 62 57 57
* @copyright Best Life IT 
 */
public final class LineDisplay {

    private static LineDisplay instance;
    private CommPortIdentifier portIdentifier;
    private static OutputStream outputStream;

    private LineDisplay() throws NoSuchPortException, NullPointerException {

        boolean display_usb = Const.LINE_DISPLAY;
        String port_type;
        if (display_usb) {
            port_type = "/dev/ttyACM0";
        } else {
            port_type = "/dev/ttyS0";
        }
//        portIdentifier = CommPortIdentifier.getPortIdentifier("/dev/ttyS0"); // for serial port
        try {

            Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
            while (portIdentifiers.hasMoreElements()) {
                portIdentifier = (CommPortIdentifier) portIdentifiers.nextElement();
                System.out.println(portIdentifier.getName());

                if (port_type.equals(portIdentifier.getName())) {
                    System.out.println("Break");
                    break;
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Customer display not detected.!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Busy");
            } else {
                System.out.println("Available");
            }
            CommPort open = portIdentifier.open("ipos", 9200);
            if (open instanceof SerialPort) {
                SerialPort port = (SerialPort) open;
                outputStream = port.getOutputStream();

            }
        } catch (PortInUseException | IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Customer display already use another program.!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static LineDisplay getInstace() {
        if (instance == null) {
            try {
                instance = new LineDisplay();
            } catch (NoSuchPortException | NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Customer display not detected.!",
                        "Erorr", JOptionPane.ERROR_MESSAGE);
            }
        }
        return instance;
    }
    private static String TL;
    private static String TR;
    private static String BL;
    private static String BR;

    public static void showMessage(String TL1, String TR1, String BL1, String BR1) {
        if (Const.LINE_DISPLAY_PLUG) {
            getInstace();
            TL = TL1.toUpperCase();
            TR = TR1.toUpperCase();
            BL = BL1.toUpperCase();
            BR = BR1.toUpperCase();

            try {
                outputStream.write(new byte[]{0x0C});
                int lineWidth = 20;
                int topLeftLength = TL.length();
                int topRightLength = TR.length();

                int bottomLeftLength = BL.length();
                int bottomRightLength = BR.length();

                if (TR.isEmpty()) {
                    String clearance1 = "";
                    int length1 = (lineWidth - topLeftLength) % 2;
                    if (length1 == 1) {
                        length1 = ((lineWidth - topLeftLength) / 2) + 1;
                    } else {
                        length1 = (lineWidth - topLeftLength) / 2;
                    }
                    for (int i = 0; i < length1; i++) {
                        clearance1 += " ";
                    }
                    outputStream.write(clearance1.getBytes());
                    outputStream.write(TL.getBytes());
                    outputStream.write(clearance1.getBytes());
                } else {
                    String clearance1 = "";
                    int length1 = (lineWidth - (topLeftLength + topRightLength));
                    for (int i = 0; i < length1; i++) {
                        clearance1 += " ";
                    }
                    outputStream.write(TL.getBytes());
                    outputStream.write(clearance1.getBytes());
                    outputStream.write(TR.getBytes());
                }
                if (BR.isEmpty()) {
                    String clearance2 = "";
                    int length2 = (lineWidth - bottomLeftLength) % 2;
                    if (length2 == 1) {
                        length2 = ((lineWidth - bottomLeftLength) / 2) + 1;
                    } else {
                        length2 = (lineWidth - bottomLeftLength) / 2;
                    }
                    for (int i = 0; i < length2; i++) {
                        clearance2 += " ";
                    }

                    outputStream.write(clearance2.getBytes());
                    outputStream.write(BL.getBytes());
                    outputStream.write(clearance2.getBytes());
                } else {
                    String clearance2 = "";
                    int length2 = (lineWidth - (bottomLeftLength + bottomRightLength));

                    for (int i = 0; i < length2; i++) {
                        clearance2 += " ";
                    }
                    outputStream.write(BL.getBytes());
                    outputStream.write(clearance2.getBytes());
                    outputStream.write(BR.getBytes());
                }

                outputStream.flush();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Customer display data tansmission interupted.!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
