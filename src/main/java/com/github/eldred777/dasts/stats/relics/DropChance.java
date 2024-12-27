package com.github.eldred777.dasts.stats.relics;

interface DropChance {
    // Probabilities of each rarity expressed as a percentage
    double COMMON = 0;
    double UNCOMMON = 0;
    double RARE = 0;

}

class Exceptional implements DropChance {
    final double COMMON = 23. + 1. / 3.;
    final double UNCOMMON = 13.;
    final double RARE = 4.;
}

class Flawless implements DropChance {
    final double COMMON = 20.;
    final double UNCOMMON = 17.;
    final double RARE = 6.;
}

class Intact implements DropChance {
    final double COMMON = 25. + 1. / 3.;
    final double UNCOMMON = 11.;
    final double RARE = 2.;
}

class Radiant implements DropChance {
    final double COMMON = 16. + 2. / 3.;
    final double UNCOMMON = 20;
    final double RARE = 10;
}

