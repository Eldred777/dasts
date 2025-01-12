package com.github.eldred777.dasts.stats.relics;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Data model for Relics class.
 */
public class Run {
    private final SimpleIntegerProperty runNumber;
    final ObservableList<SimpleDoubleProperty> probabilities;
    // Holds probabilities for Intact, Exceptional, Flawless, Radiant relics in that order.

    public Run(int num, SimpleDoubleProperty intact, SimpleDoubleProperty exceptional,
               SimpleDoubleProperty flawless, SimpleDoubleProperty radiant) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = FXCollections.observableArrayList(intact, exceptional, flawless, radiant);
    }

    public Run(int num, ObservableList<SimpleDoubleProperty> list) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = list;
    }

    public Run(int num, List<Double> list) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = FXCollections.observableArrayList(list.stream().map(SimpleDoubleProperty::new).toList());
    }

    public SimpleIntegerProperty getRunNumProperty() {
        return runNumber;
    }

    public Integer getRunNum() {
        return runNumber.get();
    }

    public SimpleDoubleProperty get(Refinement refinement) {
        return switch (refinement) {
            case INTACT -> probabilities.get(0);
            case EXCEPTIONAL -> probabilities.get(1);
            case FLAWLESS -> probabilities.get(2);
            case RADIANT -> probabilities.get(3);
        };
    }

    public void update(Refinement refinement, double d) {
        SimpleDoubleProperty dd = new SimpleDoubleProperty(d);
        switch (refinement) {
            case INTACT -> probabilities.set(0, dd);
            case EXCEPTIONAL -> probabilities.set(1, dd);
            case FLAWLESS -> probabilities.set(2, dd);
            case RADIANT -> probabilities.set(3, dd);
        }
    }

    public void update(List<SimpleDoubleProperty> probabilities) {
        if (probabilities.size() != 4) {
            throw new IllegalArgumentException("Expected probability list of length 4 but got " + probabilities.size());
        }
        this.probabilities.setAll(probabilities);
    }

    /**
     * Convenience method to convert a list of doubles to SimpleDoubleProperties.
     */
    public void updateFromDoubles(List<Double> probabilities) {
        List<SimpleDoubleProperty> newList = probabilities.stream().map(SimpleDoubleProperty::new).toList();
        this.update(newList);
    }

}
