package org.boardgame.boardgamehelper.models;

import java.io.File;

public class token {
    private File image;
    private Integer xCord;
    private Integer yCord;
    private char size;

    token(File image, Integer xCord, Integer yCord) {
        this.image = image;
        this.xCord = xCord;
        this.yCord = yCord;
        this.size = 'n';
    }
    token(File image, Integer xCord, Integer yCord, char size) {
        this.image = image;
        this.xCord = xCord;
        this.yCord = yCord;
        this.size = size;
    }

    public File getImage() {
        return image;
    }
    public void setImage(File image) {
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
