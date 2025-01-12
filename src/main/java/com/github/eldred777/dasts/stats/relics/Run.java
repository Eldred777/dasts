package com.github.eldred777.dasts.stats.relics;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Data model for Relics class.
 */
public class Run {
    private final SimpleIntegerProperty runNumber;
    final ObservableList<Double> probabilities;
    // Holds probabilities for Intact, Exceptional, Flawless, Radiant relics in that order.

    public Run(int num, double intact, double exceptional,
               double flawless, double radiant) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = FXCollections.observableArrayList(intact, exceptional, flawless, radiant);
    }

    public Run(int num, ObservableList<Double> list) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = list;
    }

    public Run(int num, List<Double> list) {
        runNumber = new SimpleIntegerProperty(num);
        probabilities = FXCollections.observableArrayList(list);
    }

    public SimpleIntegerProperty getRunNumProperty() {
        return runNumber;
    }

    public Integer getRunNum() {
        return runNumber.get();
    }

    public double get(Refinement refinement) {
        return switch (refinement) {
            case INTACT -> probabilities.get(0);
            case EXCEPTIONAL -> probabilities.get(1);
            case FLAWLESS -> probabilities.get(2);
            case RADIANT -> probabilities.get(3);
        };
    }

    public void update(Refinement refinement, double d) {
        switch (refinement) {
            case INTACT -> probabilities.set(0, d);
            case EXCEPTIONAL -> probabilities.set(1, d);
            case FLAWLESS -> probabilities.set(2, d);
            case RADIANT -> probabilities.set(3, d);
        }
    }

    public void update(List<Double> probabilities) {
        if (probabilities.size() != 4) {
            throw new IllegalArgumentException("Expected probability list of length 4 but got " + probabilities.size());
        }
        this.probabilities.setAll(probabilities);
    }
}
