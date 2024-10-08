package org.boardgame.boardgamehelper.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;

public class imageHandler {
    public static File[] pullLocalImages(String path) throws IOException, URISyntaxException {
        File dir = new File("src/main/resources/" + path);


        String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp"
        };
        FilenameFilter IMAGE_FILTER = new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };

        if (!dir.isDirectory()) {
            return null;
        }

        File[] imageFiles = dir.listFiles(IMAGE_FILTER);
        return imageFiles;
    }

    public static HBox convertImage(File[] imgFolder, String title) throws IOException {
        Image img;
        ImageView imageV;
        var gro = new HBox(3);


        if (title != null) {
            Text displayTitle = new Text(title);
            displayTitle.getStyleClass().add("title");
            gro.getChildren().add(displayTitle);
        }

        for (int i=0; i<imgFolder.length; i++) {
            imageV = new ImageView();
            img = SwingFXUtils.toFXImage(ImageIO.read(imgFolder[i]), null);

            imageV.setImage(img);
            imageV.getStyleClass().add("image-v");

            gro.getChildren().add((Node) imageV);
        }
        if (gro.getChildren().size() < 0) {
            return null;
        }

        return gro;
    }

    public static ImageView oneImage(File imgPath, String category) throws IOException {
        ImageView imgView = new ImageView();
        Image img = SwingFXUtils.toFXImage(loadAndResizeImage(imgPath.getPath(), 80, 80), null);


        imgView.setImage(img);
        imgView.setFitWidth(60.0);
        imgView.setFitHeight(60.0);
        imgView.getStyleClass().add(category);
        imgView.getStyleClass().add("image");
        imgView.setUserData(imgPath.toString());
        return imgView;
    }

    private static BufferedImage loadAndResizeImage(String filePath, int width, int height) {
        BufferedImage originalImage = null;
        try {
            // Load the image
            originalImage = ImageIO.read(new File(filePath));

            // Create a new BufferedImage with the desired dimensions
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Draw the original image onto the new BufferedImage, resizing it
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();

            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
