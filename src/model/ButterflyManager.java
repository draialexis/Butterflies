package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ButterflyManager {

    private final ObservableList<Butterfly> butterfliesObs = FXCollections.observableArrayList();
    private final ListProperty<Butterfly> butterflies = new SimpleListProperty<>(butterfliesObs);
    public ObservableList<Butterfly> getButterflies() {return FXCollections.unmodifiableObservableList(butterflies.get());}
    public ReadOnlyListProperty<Butterfly> butterfliesProperty() {return butterflies;}

    public void addButterfly(String toAdd){
        butterfliesObs.add(new Butterfly(toAdd));
    }

    public void removeButterfly(Butterfly toRemove){
        butterfliesObs.remove(toRemove);
    }
}