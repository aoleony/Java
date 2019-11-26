package cocinfo;

public enum COCInfo {//max levels      cost in each levels                    health in each levels         elixir or dark elixir
    BARBARIAN(8, new int[]{25, 40, 60, 100, 150, 200, 250, 300}, new int[]{45, 54, 65, 78, 95, 110, 145, 205}, true),
    ARCHER(8, new int[]{50, 80, 120, 200, 300, 400, 500, 6000}, new int[]{20, 23, 28, 33, 40, 44, 48, 52}, true),
    BABYDRAGON(6, new int[]{5000, 6000, 7000, 8000, 9000, 10000}, new int[]{1200, 1300, 1400, 1500, 1600, 1700}, true),
    BALLOON(8, new int[]{1750, 2250, 2750, 3500, 4000, 4500, 5000, 5500}, new int[]{150, 180, 216, 280, 390, 545, 690, 840}, true),
    HOGRIDER(9, new int[]{30, 34, 38, 42, 48, 60, 80, 100, 120}, new int[]{270, 312, 360, 415, 480, 590, 700, 810, 890}, false),
    BOWLER(4, new int[]{70, 95, 115, 140}, new int[]{290, 310, 350, 390}, false),
    DRAGON(7, new int[]{10000, 12000, 14000, 16000, 18000, 20000, 22000}, new int[]{1900, 2100, 2300, 2600, 3100, 3400, 3900}, true),
    ELECTRODRAGON(3, new int[]{28000, 32000, 36000}, new int[]{3200, 3700, 4200}, true),
    MINION(8, new int[]{4, 5, 6, 7, 8, 9, 10, 11}, new int[]{55, 60, 66, 72, 78, 84, 90, 96}, false),
    GIANTS(9, new int[]{250, 750, 1250, 1750, 2250, 3000, 3500, 4000, 4500}, new int[]{300, 360, 430, 520, 720, 940, 1280, 1480, 1660}, true),
    GOBLIN(7, new int[]{25, 40, 60, 80, 100, 150, 200}, new int[]{25, 30, 36, 46, 56, 76, 101}, true),
    HEALER(5, new int[]{5000, 6000, 8000, 10000, 14000}, new int[]{500, 600, 840, 1200, 1500}, true),
    WIZARD(9, new int[]{1000, 1400, 1800, 2200, 2600, 3000, 3400, 3800, 4200}, new int[]{75, 90, 108, 130, 156, 175, 190, 210, 230}, true),
    WALLBREAKER(8, new int[]{600, 800, 1000, 1200, 1400, 1600, 1800, 2000}, new int[]{20, 24, 29, 35, 53, 72, 82, 92}, true),
    MINER(6, new int[]{4200, 4800, 5200, 5600, 6000, 6400}, new int[]{550, 610, 670, 730, 800, 870}, true);

    private final int maxLevels;
    private final int[] cost;
    private final int[] health;
    private final boolean energy;

    COCInfo(int maxLevels, int[] cost, int[] health, boolean energy) {
        this.cost = cost;
        this.health = health;
        this.maxLevels = maxLevels;
        this.energy = energy;
    }

    int max() {
        return maxLevels;
    }

    int cost(int level) {
        return cost[level - 1];
    }

    String energySource() {
        return (energy ? "elixir" : "dark elixir");
    }

    int health(int level) {
        return health[level - 1];
    }

    int[] cost() {
        return cost;
    }

    int[] health() {
        return health;
    }

}
