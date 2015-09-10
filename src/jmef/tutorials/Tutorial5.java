package jmef.tutorials;

import jmef.MixtureModel;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import jmef.tools.Image;

public class Tutorial5{


    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {

        // Display
        String title = "";
        title += "+----------------------------------------+\n";
        title += "| Statistical images from mixture models |\n";
        title += "+----------------------------------------+\n";
        System.out.print(title);

        // Variables
        int n = 32;

        // Image/texture information (to be changed to fit your configuration)
        String inputFolder  = "resources";
        String outputFolder = "out";
        String imageName = "Baboon";

        // Read the input image
        Path imagePath = Paths.get(inputFolder, imageName + ".png").toAbsolutePath();
        System.out.printf("Read input image (%s): ", imagePath.toString());
        BufferedImage image = Image.readImage(imagePath.toString());
        System.out.println("ok");

        // Read or generate the mixture model
        Path mixturePath  = Paths.get(outputFolder, String.format("%s_5D_%03d.mix", imageName, n)).toAbsolutePath();
        System.out.printf("Read/generate mixture model (%s): ", mixturePath.toString());
        MixtureModel f = Image.loadMixtureModel(mixturePath.toString(), image, 5, n);
        System.out.println("ok");

        // Creates and save the statistical image
        Path outStatImgPath = Paths.get(outputFolder, String.format("Tutorial5_%s_statistical_%03d.png", imageName, n)).toAbsolutePath();
        System.out.printf("Create statistical image (%s): ", outStatImgPath.toString());
        BufferedImage stat = Image.createImageFromMixtureModel(image.getWidth(), image.getHeight(), f);
        Image.writeImage(stat, outStatImgPath.toString());
        System.out.println("ok");

        // Creates and save the ellipse image
        Path outEllipseImgPath = Paths.get(outputFolder, String.format("Tutorial5_%s_ellipses_%03d.png", imageName, n)).toAbsolutePath();
        System.out.printf("Create ellipse image (%s): ", outEllipseImgPath.toString());
        BufferedImage ell = Image.createEllipseImage(image.getWidth(), image.getHeight(), f, 2);
        Image.writeImage(ell, outEllipseImgPath.toString());
        System.out.println("ok");

    }

}
