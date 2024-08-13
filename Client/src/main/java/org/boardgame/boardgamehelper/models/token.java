package org.boardgame.boardgamehelper.models;

import javafx.scene.image.ImageView;

public class token {
    private ImageView image;
    private Integer xCord;
    private Integer yCord;
    private char size;

    public token(ImageView image, Integer xCord, Integer yCord) {
        this.image = image;
        this.xCord = xCord;
        this.yCord = yCord;
        this.size = 'n';
    }
    public token(ImageView image, Integer xCord, Integer yCord, char size) {
        this.image = image;
        this.xCord = xCord;
        this.yCord = yCord;
        this.size = size;
    }

    public ImageView getImage() {
        return image;
    }
    public void setImage(ImageView image) {
        this.image = image;
    }
    public Integer getXCord() {
        return xCord;
    }
    public void setXCord(Integer xCord) {
        this.xCord = xCord;
    }

    public Integer getYCord() {
        return yCord;
    }

    public void setYCord(Integer yCord) {
        this.yCord = yCord;
    }
    public char getSize() {
        return size;
    }
    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "token{" +
                "image=" + image +
                ", xCord=" + xCord +
                ", yCord=" + yCord +
                ", size=" + size +
                '}';
    }
}
