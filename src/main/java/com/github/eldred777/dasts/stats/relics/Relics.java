package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.StatsView;
import javafx.scene.layout.StackPane;

public class Relics extends Distribution<Integer, Double> implements StatsView {
    public Relics() {
        super();
    }
    // TODO implement
    // TODO multiple people
    // TODO dynamically add lines based off # of runs and desire probability
    // Geometric distribution

    public StackPane view() {
        return new StackPane(); // TODO
    }
}
