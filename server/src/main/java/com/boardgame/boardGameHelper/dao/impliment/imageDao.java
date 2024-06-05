package com.boardgame.boardGameHelper.dao.impliment;

import com.boardgame.boardGameHelper.dao.config;
import com.boardgame.boardGameHelper.models.images;
import com.boardgame.boardGameHelper.utils.auth.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;

@Service
public class imageDao {
    @Autowired
    private jwtUtils jwt = new jwtUtils();
    private final config connection = new config();
    private final Connection DB = this.connection.getConn();

    public images GetAllImages() {
        return new images();
    }

    public images getAllMaps() {
        return new images();
    }

    public images getAllToken() {
        return new images();
    }

    public boolean addMap() {
        return false;
    }

    public boolean addToken() {
        return false;
    }

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

    public boolean removeMap() {
        return false;
    }

    public boolean removeToken() {
        return false;
    }
}
