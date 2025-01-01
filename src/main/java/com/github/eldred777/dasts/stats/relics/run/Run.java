package com.github.eldred777.dasts.stats.relics.run;

/**
 * Helper class for constructing a TableView in Relics. This class is only used to contain data and is not responsible
 * for computing probabilities.
 */
public class Run {
    int runNumber;
    Probabilities probabilities = new Probabilities(); // Holds probabilities for Intact, Exceptional, Flawless, Radiant relics in that order.

    Run(int num, Probabilities probabilities) {
        runNumber = num;
        updateProbabilities(probabilities);
    }

    public void updateProbabilities(Probabilities probabilities) {
        this.probabilities.updateProbabilities(probabilities);
    }

    // Getters for properties

    public double intact() {
        return probabilities.intact;
    }

    public double exceptional() {
        return probabilities.exceptional;
    }

    public double flawless() {
        return probabilities.flawless;
    }

    public double radiant() {
        return probabilities.radiant;
    }


}
