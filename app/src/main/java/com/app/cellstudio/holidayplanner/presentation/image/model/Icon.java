package com.app.cellstudio.holidayplanner.presentation.image.model;

/**
 * Created by coyan on 13/09/2018.
 */

public enum Icon {
    HOME("Home"),
    CREDITS("Credits"),
    SETTINGS("Settings"),
    LISTING("Listing"),
    CALENDAR("Calendar"),
    STAR("Star"),
    UNKNOWN("Unknown");

    private String iconName;

    Icon(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName() {
        return iconName;
    }

    public static Icon fromString(String text) {
        if (text != null) {
            for (Icon icon : Icon.values()) {
                if (icon.getIconName().equalsIgnoreCase(text)) {
                    return icon;
                }
            }
        }
        return Icon.UNKNOWN;
    }

}