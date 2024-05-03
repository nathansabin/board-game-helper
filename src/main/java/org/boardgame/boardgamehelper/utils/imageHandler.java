package org.boardgame.boardgamehelper.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;

public class imageHandler {
    public File[] pullLocalImages(String path) throws IOException, URISyntaxException {
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

    public VBox convertImage(File[] imgFolder, String title) throws IOException {
        Image img;
        ImageView imageV;
        Text displayTitle = new Text(title);
        VBox gro = new VBox(3);

        displayTitle.getStyleClass().add("title");
        gro.getChildren().add(displayTitle);

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

}
