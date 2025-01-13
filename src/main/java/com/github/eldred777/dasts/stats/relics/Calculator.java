package com.github.eldred777.dasts.stats.relics;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private int numPeople;
    private RewardRarity rarity;

    public Calculator() {
        // Default args
        numPeople = 1;
        rarity = RewardRarity.COMMON;
    }

    public void setRarity(RewardRarity rarity) {
        this.rarity = rarity;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    /**
     * Computes the probability with parameter refinement, internal parameter numPeople, evaluated at runNumber.
     * <p>
     * This is the geometric distribution under the number set {1,2,...} i.e. n-1 failures and the nth trial is a
     * success.
     *
     * @return Probability as a percentage.
     */
    private double computePdf(Refinement refinement, int runNumber) {
        double prob = refinement.get(rarity) / 100;
        double probComplement = 1 - prob;
        if (numPeople > 1) {
            probComplement = Math.pow(probComplement, numPeople);
            prob = 1 - probComplement;
        }
        return Math.pow(probComplement, runNumber - 1) * prob * 100;
    }

    public List<Double> computeProbabilities(int n) {
        ArrayList<Double> p = new ArrayList<>();
        p.add(computePdf(Refinement.INTACT, n));
        p.add(computePdf(Refinement.EXCEPTIONAL, n));
        p.add(computePdf(Refinement.FLAWLESS, n));
        p.add(computePdf(Refinement.RADIANT, n));
        return p;
    }

    public Run computeRun(int n) {
        return new Run(n, computeProbabilities(n));
    }

    public void updateRun(Run run) {
        int n = run.getRunNum();
        run.update(computeProbabilities(n));
    }
}
