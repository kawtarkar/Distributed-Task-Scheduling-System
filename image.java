import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;


import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class image {
    public static void sent(DataOutputStream dos,String path)throws Exception{
        BufferedImage image = ImageIO.read(new File(path));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String format = path.substring(path.lastIndexOf('.') + 1);
        ImageIO.write(image, format, byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        dos.write(size);
        dos.write(byteArrayOutputStream.toByteArray());
        dos.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());

      
        System.out.println("Closing: " + System.currentTimeMillis());
           }

    
    public static  BufferedImage Receive(DataInputStream dis)throws Exception {
        
        System.out.println("Reading: " + System.currentTimeMillis());
        byte[] sizeAr = new byte[4];
        dis.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];
        dis.readFully(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());

        return image ;
    }


    public static void send(BufferedImage image, DataOutputStream dos) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String format = "png"; // default to PNG format
        ImageIO.write(image, format, byteArrayOutputStream);
    
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        dos.write(size);
        dos.write(byteArrayOutputStream.toByteArray());
        dos.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());
    
        System.out.println("Closing: " + System.currentTimeMillis());
    }


    
    public static void displayImage(BufferedImage image) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        
        frame.add(panel);
     
        frame.setSize(image.getWidth(), image.getHeight());
        frame.setVisible(true);

    }
    public static BufferedImage applyConvolution(BufferedImage image, int[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int kernelWidth = kernel[0].length;
        int kernelHeight = kernel.length;
        int kernelHalfWidth = kernelWidth / 2;
        int kernelHalfHeight = kernelHeight / 2;

        // Apply convolution to each pixel of the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sumR = 0, sumG = 0, sumB = 0;
                for (int j = 0; j < kernelHeight; j++) {
                    int yk = y + j - kernelHalfHeight;
                    if (yk < 0 || yk >= height) continue;

                    for (int i = 0; i < kernelWidth; i++) {
                        int xk = x + i - kernelHalfWidth;
                        if (xk < 0 || xk >= width) continue;

                        int argb = image.getRGB(xk, yk);
                        //int a = (argb >> 24) & 0xFF;
                        int r = (argb >> 16) & 0xFF;
                        int g = (argb >> 8) & 0xFF;
                        int b = (argb) & 0xFF;

                        int weight = kernel[j][i];
                        sumR += weight * r;
                        sumG += weight * g;
                        sumB += weight * b;
                    }
                }

                // Set the result pixel value
                int argb = (sumR << 16) | (sumG << 8) | (sumB);
                result.setRGB(x, y, argb);
            }
        }

        return result;
    }



    public static BufferedImage[] getSubImage(BufferedImage image, int cols){
        BufferedImage[] parts= new BufferedImage[cols];
        int index=0;
        int height=image.getHeight();
        int width=image.getWidth()/cols;
        for (int abscise = 0; abscise < image.getWidth() && index<cols; abscise+=width) {
            parts[index++]=image.getSubimage(abscise, 0, width, height);
        }
        return parts;
      }


    public static  BufferedImage mergeImage(BufferedImage[] parts){
        int width=parts[0].getWidth()*parts.length;
        int height=parts[0].getHeight();
        int position = 0;
        BufferedImage concatImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for (BufferedImage bufferedImage : parts) {
            g2d.drawImage(bufferedImage, position, 0, null);
            position += bufferedImage.getWidth();
        }
        return concatImage;
      }
      



}

