package com.boardgame.boardGameHelper.dao;

import com.boardgame.boardGameHelper.models.images;

import java.awt.*;
import java.io.IOException;

public interface imageInterface {
    public images GetAllImages();

    public images getAllMaps();

    public images getAllToken();
    public boolean addImage(String token, String image, String type) throws IOException;
    public Image getOneMap ();
    public Image getOneToken();

    public boolean removeMap();
    public boolean removeToken();


}
