package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Butterfly {

    private final StringProperty name = new SimpleStringProperty();
    public String getName() {return name.get();}
    public StringProperty nameProperty() {return name;}
    public void setName(String name) {this.name.set(name);}

    private final ObservableList<Color> colorsObs = FXCollections.observableArrayList();
    private final ListProperty<Color> colors = new SimpleListProperty<>(colorsObs);
    public ObservableList<Color> getColors() {return FXCollections.unmodifiableObservableList(colors.get());}
    public ReadOnlyListProperty<Color> colorsProperty() {return colors;}

    public Butterfly(String name) {
        this.name.set(name);
    }

    public void addColor(int r, int g, int b) {
        colorsObs.add(new Color(r, g, b));
    }

    public void removeColor(Color toRemove) {
        colorsObs.remove(toRemove);
    }
}
