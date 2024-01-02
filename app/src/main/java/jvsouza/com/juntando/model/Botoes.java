package jvsouza.com.juntando.model;

import android.widget.ImageButton;

public class Botoes {

    int image;
    int id;

    public Botoes() {
    }

    public Botoes(int image, int id) {
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Botoes(int image) {
        this.image = image;
    }
}
