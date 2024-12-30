package com.github.eldred777.dasts.stats.dice;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.Viewable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class Dice extends Distribution<Integer, Double> implements Viewable {
    // TODO: add to results, add probabilities
    Dice() {
        super();
    }

    Dice(HashMap<Integer, Double> map) {
        super(map);
    }

    public void offset(Integer x) {
        // TODO offsets results list by x, e.g. change a d2 = {1,2} to {0,1}
    }

    /**
     * Adds a set of dice to the current set of dice, in the sense of adding probability distributions (i.e. by
     * convolution).
     *
     * @param dice
     *         The distribution of dice to be added to the current distribution.
     */
    public Dice add(Dice dice) {

        HashMap<Integer, Double> newDist = new HashMap<>();

        for (int i : this.distribution.keySet()) {
            double thisProb = this.distribution.get(i);
            for (int j : dice.distribution.keySet()) {
                double diceProb = dice.distribution.get(j);
                double combinedProb = thisProb * diceProb; // Leveraging independence of dice

                int sumKey = i + j;
                newDist.compute(sumKey, (k, v) -> (v == null) ? combinedProb : v + combinedProb);
            }
        }

        return new Dice(newDist);
    }

    /**
     * Rescales the dice key values by an integral scaling factor.
     */
    public Dice multiplyScalar(int scalar) {
        // TODO
        return this;
    }

    /**
     * Adds a constant to the dice key values. Can be negative.
     */
    public Dice addScalar(int scalar) {
        // TODO
        return this;
    }

    /**
     * Creates a statistical summary in an HBox for the chosen key.
     *
     * @param i
     *         Key
     *
     * @return HBox with all required information
     */
    private HBox viewSingle(int i) {
        HBox hbox = new HBox();
        // TODO
        return hbox;
    }

    public StackPane view() {

        StackPane stackPane = new StackPane();
        VBox vbox = new VBox();

        stackPane.getChildren().add(vbox);

        for (var key : this.distribution.keySet()) {
            HBox hbox = new HBox();
            // TODO: fill information in hbox
            vbox.getChildren().add(hbox);
        }

        // TODO: make graph
        // include pdf via vertical(?) bars and cdf in the background

        return stackPane;
    }
}
