package jmef.tutorials;

import jmef.BregmanHierarchicalClustering;
import jmef.HierarchicalMixtureModel;
import jmef.MixtureModel;
import jmef.BregmanHierarchicalClustering.LINKAGE_CRITERION;
import jmef.Clustering.CLUSTERING_TYPE;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import jmef.tools.Image;

public class Tutorial4 {


    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {

        // Display
        String title = "";
        title += "+----------------------------------------------------+\n";
        title += "| Hierarchical mixture models and image segmentation |\n";
        title += "+----------------------------------------------------+\n";
        System.out.print(title);

        // Variables
        int n = 32;
        int m = 8;

        // Image/texture information (to be changed to fit your configuration)
        String inputFolder  = "resources";
        String outputFolder = "out";
        String imageName    = "Baboon";

        // Read the input image
        Path imagePath = Paths.get(inputFolder, imageName + ".png").toAbsolutePath();
        System.out.printf("Read input image (%s): ", imagePath);
        BufferedImage image = Image.readImage(imagePath.toString());
        System.out.println("ok");

        // Read or generate the mixture model
        Path mixturePath  = Paths.get(outputFolder, String.format("%s_3D_%03d.mix", imageName, n)).toAbsolutePath();
        System.out.printf("Read/generate mixture model (%s): ", mixturePath.toString());
        MixtureModel mm1 = Image.loadMixtureModel(mixturePath.toString(), image, 3, n);
        System.out.println("ok");

        // Initial segmentation from MoG
        Path outSegmentImgPath = Paths.get(outputFolder, String.format("Tutorial4_%s_%03d.png", imageName, n)).toAbsolutePath();
        System.out.printf("Segment image (mixture model) (%s): ", outputFolder.toString());
        BufferedImage seg1 = Image.segmentColorImageFromMOG(image, mm1);
        Image.writeImage(seg1, outSegmentImgPath.toString());
        System.out.println("ok");

        // Build hierarchical mixture model
        System.out.print("Create hierarchical mixture model: ");
        HierarchicalMixtureModel hmm = BregmanHierarchicalClustering.build(mm1, CLUSTERING_TYPE.SYMMETRIC, LINKAGE_CRITERION.MAXIMUM_DISTANCE);
        System.out.println("ok");

        // Initial segmentation from simplified MoG
        Path outSegmentationFromSimpleMoG = Paths.get(outputFolder, String.format("Tutorial4_%s_%03d.png", imageName, m)).toAbsolutePath();
        System.out.printf("Segment image (hierarchical mixture model) (%s): ", outSegmentationFromSimpleMoG.toString());
        MixtureModel  mm2  = hmm.getResolution(m);
        //MixtureModel  mm2  = hmm.getOptimalMixtureModel(0.5);
        BufferedImage seg2 = Image.segmentColorImageFromMOG(image, mm2);
        Image.writeImage(seg2, outSegmentationFromSimpleMoG.toString());
        System.out.println("ok");
    }
}

