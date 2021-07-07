package com.genius.ifbretailer.model;

public class DialogItemModule {
    String item,itemId;
    private boolean isSelected = false;

    public DialogItemModule(String item, String itemId) {
        this.item = item;
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
