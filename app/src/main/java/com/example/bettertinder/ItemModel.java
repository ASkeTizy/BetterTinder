package com.example.bettertinder;

public class ItemModel {

    private String title;

    private int imageUrl;
    private boolean overlay;
    private boolean isKiller;

    public ItemModel(String title, int imageUrl) {
        this.title = title;

        this.imageUrl = imageUrl;
    }

    public ItemModel(String title,  int imageUrl, boolean isKiller)
    {
        this(title,imageUrl);
        this.isKiller = isKiller;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOverlay() {
        return overlay;
    }

    public void setOverlay(boolean overlay) {
        this.overlay = overlay;
    }

    public boolean isKiller() {
        return isKiller;
    }

    public void setKiller(boolean killer) {
        isKiller = killer;
    }
}
