package model;

import javafx.beans.property.*;

public class Color {

    private final IntegerProperty red = new SimpleIntegerProperty();
    public int getRed() {return red.get();}
    public ReadOnlyIntegerProperty redProperty() {return red;}
    private void setRed(int red) {this.red.set(red);}

    private final IntegerProperty green = new SimpleIntegerProperty();
    public int getGreen() {return green.get();}
    public ReadOnlyIntegerProperty greenProperty() {return green;}
    private void setGreen(int green) {this.green.set(green);}

    private final IntegerProperty blue = new SimpleIntegerProperty();
    public int getBlue() {return blue.get();}
    public ReadOnlyIntegerProperty blueProperty() {return blue;}
    private void setBlue(int blue) {this.blue.set(blue);}

    private final ObjectProperty<javafx.scene.paint.Color> color = new SimpleObjectProperty<>();
    public javafx.scene.paint.Color getColor() {return color.get();}
    public ObjectProperty<javafx.scene.paint.Color> colorProperty() {return color;}
    private void setColor(javafx.scene.paint.Color color) {this.color.set(color);}

    public Color(int red, int green, int blue) {

        color.addListener((__, ___, newV)->{
            setRed((int)(newV.getRed() * 255));
            setGreen((int)(newV.getGreen() * 255));
            setBlue((int)(newV.getBlue() * 255));
        });

        color.set(new javafx.scene.paint.Color(
                red / 255.,
                green / 255.,
                blue / 255.,
                1)
        );
    }
}
