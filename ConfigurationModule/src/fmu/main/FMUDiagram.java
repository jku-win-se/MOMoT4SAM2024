package fmu.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FMUDiagram extends JPanel {
   public static void main(final String[] args) {
      final JFrame frame = new JFrame("FMU Diagram");
      final FMUDiagram diagram = new FMUDiagram();
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.add(diagram);
      frame.setSize(400, 300);
      frame.setVisible(true);

      // Save the diagram as an image
      final BufferedImage image = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
      final Graphics2D g2d = image.createGraphics();
      diagram.paint(g2d);
      g2d.dispose();
      try {
         ImageIO.write(image, "png", new File("fmu_diagram.png"));
         final File outputFile = new File("fmu_diagram.jpg");
         final Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
         final ImageWriter writer = writers.next();
         final ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
         writer.setOutput(ios);
         final ImageWriteParam param = writer.getDefaultWriteParam();
         param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
         param.setCompressionQuality(1.0f); // Set to highest quality
         writer.write(null, new IIOImage(image, null, null), param);
         ios.close();
         writer.dispose();

      } catch(final Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   protected void paintComponent(final Graphics g) {
      super.paintComponent(g);
      final Graphics2D g2d = (Graphics2D) g;

      // Draw the FMU as a rectangle
      final int fmuX = 50;
      final int fmuY = 50;
      final int fmuWidth = 200;
      final int fmuHeight = 100;
      g2d.drawRect(fmuX, fmuY, fmuWidth, fmuHeight);

      // Label the FMU
      g2d.drawString("FMU", fmuX + fmuWidth / 2 - 10, fmuY + fmuHeight / 2);

      // Label the inputs
      g2d.drawString("Input1", fmuX - 40, fmuY + 20);
      g2d.drawString("Input2", fmuX - 40, fmuY + 50);
      g2d.drawLine(fmuX - 30, fmuY + 15, fmuX, fmuY + 15);
      g2d.drawLine(fmuX - 30, fmuY + 45, fmuX, fmuY + 45);

      // Label the outputs
      g2d.drawString("Output1", fmuX + fmuWidth + 10, fmuY + 20);
      g2d.drawString("Output2", fmuX + fmuWidth + 10, fmuY + 50);
      g2d.drawLine(fmuX + fmuWidth, fmuY + 15, fmuX + fmuWidth + 30, fmuY + 15);
      g2d.drawLine(fmuX + fmuWidth, fmuY + 45, fmuX + fmuWidth + 30, fmuY + 45);

      // Add text below the FMU
      g2d.drawString("This is a functional mockup unit (FMU).", fmuX, fmuY + fmuHeight + 30);
   }
}
