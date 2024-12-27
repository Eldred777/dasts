package com.github.eldred777.dasts.stats.dice;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.StatsView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class Dice extends Distribution<Integer, Double> implements StatsView {
    // TODO: add to results, add probabilities
    Dice() {
        super();
    }

    Dice(HashMap<Integer, Double> hm) {
        super(hm);
    }

    public void offset(Integer x) {
        // TODO offsets results list by x, e.g. change a d2 = {1,2} to {0,1}
    }

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

    public StackPane view() {

        return null; // TODO
    }
}
