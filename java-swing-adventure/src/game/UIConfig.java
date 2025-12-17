package game;

import java.awt.*;

public final class UIConfig {

    // Skalierungsfaktor für die UI-Elemente
    public static final double SCALE = 1.6;

    public static int scale(int value) {
        return (int) Math.round(value * SCALE);
    }

    public static Font scaledFont(String name, int style, int size) {
        return new Font(name, style, scale(size));
    }

    public static Insets scaledInsets(int top, int left, int bottom, int right) {
        return new Insets(scale(top), scale(left), scale(bottom), scale(right));
    }

    public static Dimension scaledDimension(int w, int h) {
        return new Dimension(scale(w), scale(h));
    }
}