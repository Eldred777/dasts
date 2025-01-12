package com.github.eldred777.dasts.stats.relics;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private int numPeople;
    private RewardRarity rarity;

    public Calculator() {
        numPeople = 1;
        rarity = RewardRarity.COMMON;
    }

    public void setRarity(RewardRarity rarity) {
        this.rarity = rarity;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    // TODO Geometric distribution
    private double computePdf(Refinement refinement, int runNumber) {
        return runNumber * 0.5; // TODO implement, chosen a random number to make sure its working
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
