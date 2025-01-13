package com.github.eldred777.dasts.stats.relics;

public enum Refinement {
    INTACT(25. + 1. / 3., 11., 2., "Intact"),
    EXCEPTIONAL(23 + 1. / 3., 13., 4., "Exceptional"),
    FLAWLESS(20., 17., 6., "Flawless"),
    RADIANT(16. + 2. / 3., 20, 10, "Radiant");

    public final double common;
    public final double uncommon;
    public final double rare;
    public final String name;
    public final int index;

    Refinement(double common, double uncommon, double rare, String name) {
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;
        this.name = name;
        this.index = this.ordinal();
    }


    public double get(RewardRarity rarity) {
        switch (rarity) {
            case COMMON -> {
                return this.common;
            }
            case UNCOMMON -> {
                return this.uncommon;
            }
            case RARE -> {
                return this.rare;
            }
        }
        throw new RuntimeException("DropChance.get failed to match argument: " + rarity);
    }
}

