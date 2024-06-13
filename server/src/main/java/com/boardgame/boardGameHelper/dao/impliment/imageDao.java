package com.boardgame.boardGameHelper.dao.impliment;

import com.boardgame.boardGameHelper.dao.config;
import com.boardgame.boardGameHelper.dao.imageInterface;
import com.boardgame.boardGameHelper.models.images;
import com.boardgame.boardGameHelper.utils.auth.jwtUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class imageDao implements imageInterface {
    @Autowired
    private jwtUtils jwt = new jwtUtils();
    private final config connection = new config();
    private final Connection DB = this.connection.getConn();

    @Override
    public images GetAllImages() {
        return new images();
    }
    @Override
    public images getAllMaps() {
        return new images();
    }
    @Override
    public images getAllToken() {
        return new images();
    }
    @Override
    public boolean addMap(String token, String image) throws IOException {
        try {
            byte[] bytes = Base64.decodeBase64(image);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage bImage = ImageIO.read(inputStream);

            Integer userId = jwt.extractId(token);
            Integer imageId = null;

            String imageQuery = "INSERT INTO image(imagetype, location, height, width) VALUES(?, ?, ?, ?)";
            try (PreparedStatement stmt = this.DB.prepareStatement(imageQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, "map");
                stmt.setString(2, "");
                stmt.setInt(3, bImage.getWidth());
                stmt.setInt(4, bImage.getHeight());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    imageId = rs.getInt(1);
                }

            }

            String userImageQuery = "INSERT INTO userimage(userID, ImageID) VALUES(?, ?)";
            try (PreparedStatement stmt = this.DB.prepareStatement(userImageQuery)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, imageId);

                stmt.execute();
            }

            String path = "src/main/map" + imageId + ".png";
            File location = new File(path);

            String locationUpdate = "UPDATE image SET location=? WHERE id=?";
            try (PreparedStatement stmt = this.DB.prepareStatement(locationUpdate)) {
                stmt.setString(1, path);
                stmt.setInt(2, imageId);
                stmt.execute();
            }

            ImageIO.write(bImage, "png", location);
            return true;
        } catch (Exception e) {
            throw new IOException(e);
//            return false;
        }
    }
    @Override
    public boolean addToken() {
        return false;
    }
    @Override
    public Image getOneMap () {
        return new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };
    }
    @Override

    public Image getOneToken() {
        return new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };
    }
    @Override
    public boolean removeMap() {
        return false;
    }
    @Override
    public boolean removeToken() {
        return false;
    }
}
