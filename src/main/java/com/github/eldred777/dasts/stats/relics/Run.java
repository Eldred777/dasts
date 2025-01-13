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
        return probabilities.get(refinement.index);
    }

    public void update(Refinement refinement, double d) {
        probabilities.set(refinement.index, d);
    }

    public void update(List<Double> probabilities) {
        if (probabilities.size() != 4) {
            throw new IllegalArgumentException("Expected probability list of length 4 but got " + probabilities.size());
        }
        this.probabilities.setAll(probabilities);
    }
}
