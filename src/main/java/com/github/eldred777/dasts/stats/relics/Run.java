package com.github.eldred777.dasts.stats.relics;

/**
 * Helper class for constructing a TableView in Relics.
 */
class Run {
    int runNumber;
    double[] probabilities; // Holds probabilities for Intact, Exceptional, Flawless, Radiant relics in that order.

    Run(int num) {
        runNumber = num;
        probabilities = new double[4];
    }

    public void updateProbabilities(double[] probabilities) {
        if (probabilities.length != 4) {
            throw new IllegalArgumentException("Expected array of length 4 but received length " + probabilities.length);
        }

        System.arraycopy(probabilities, 0, this.probabilities, 0, 4);
    }


}
