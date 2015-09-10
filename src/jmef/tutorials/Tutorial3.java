package jmef.tutorials;

import jmef.BregmanHardClustering;
import jmef.Clustering.CLUSTERING_TYPE;
import jmef.MixtureModel;
import jmef.tools.Image;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tutorial3 {


    /**
     * Main function.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Display
        String title = "";
        title += "+-----------------------------------------------+\n";
        title += "| Mixture simplification and image segmentation |\n";
        title += "+-----------------------------------------------+\n";
        System.out.print(title);

        // Variables
        int n = 32;
        int m = 8;

        // Image/texture information (to be changed to fit your configuration)
        String inputFolder = "resources";
        String outputFolder = "out";
        String imageName = "Baboon";

        // Read the input image
        Path imagePath = Paths.get(inputFolder, imageName + ".png").toAbsolutePath();
        System.out.printf("Read input image (%s): ", imagePath);
        BufferedImage image = Image.readImage(imagePath.toString());
        System.out.println("ok");

        // Read or generate the mixture model
        Path mixturePath = Paths.get(outputFolder, String.format("%s_3D_%03d.mix", imageName, n)).toAbsolutePath();
        System.out.printf("Read/generate mixture model (%s): ", mixturePath.toString());
        MixtureModel mm1 = Image.loadMixtureModel(mixturePath.toString(), image, 3, n);
        System.out.println("ok");

        // Compute the image segmentation based on the mixture mm1
        Path outSegmentationBasedMM1Img = Paths.get(outputFolder, String.format("Tutorial3_%s_%03d.png", imageName, n)).toAbsolutePath();
        System.out.printf("Segment image (mixture model) (%s): ", outSegmentationBasedMM1Img);
        BufferedImage seg1 = Image.segmentColorImageFromMOG(image, mm1);
        Image.writeImage(seg1, outSegmentationBasedMM1Img.toString());
        System.out.println("ok");

        // Simplify mm1 in a mixture mm2 of m components and compute the image segmentation based on mm2
        Path outSegmentationBasedMM2Img = Paths.get(outputFolder, String.format("Tutorial3_%s_%03d.png", imageName, m)).toAbsolutePath();
        System.out.printf("Segment image (simplified mixture model) (%s): ", outSegmentationBasedMM2Img);
        MixtureModel mm2 = BregmanHardClustering.simplify(mm1, m, CLUSTERING_TYPE.LEFT_SIDED);
        BufferedImage seg2 = Image.segmentColorImageFromMOG(image, mm2);
        Image.writeImage(seg2, outSegmentationBasedMM2Img.toString());
        System.out.println("ok");
    }

}
