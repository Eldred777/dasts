package com.github.eldred777.dasts.stats.relics.run;

/**
 * Essentially a fixed length array wrapper.
 */
public class Probabilities {
    double intact;
    double exceptional;
    double flawless;
    double radiant;

    public Probabilities() {
        intact = exceptional = flawless = radiant = 0.;
    }

    public Probabilities(double intact, double exceptional, double flawless, double radiant) {
        updateProbabilities(intact, exceptional, flawless, radiant);
    }


    public void updateProbabilities(double intact, double exceptional, double flawless, double radiant) {
        // TODO check that input probabilities are at least 4
        this.intact = intact;
        this.exceptional = exceptional;
        this.flawless = flawless;
        this.radiant = radiant;
    }

    public void updateProbabilities(Probabilities probabilities) {
        // TODO check that input probabilities are at least 4
        this.intact = probabilities.intact;
        this.exceptional = probabilities.exceptional;
        this.flawless = probabilities.flawless;
        this.radiant = probabilities.radiant;
    }
}
