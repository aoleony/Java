package cocinfo;

public class Main {

    public static void main(String[] args) {
        int level = 1;
        int max = COCInfo.BARBARIAN.cost(level);

        System.out.println(max);
        for (COCInfo i : COCInfo.values()) {
            for (level = 1; level <= i.max(); level++) {
                System.out.printf("your level %d %s has a health of %d and a cost of %d %s.%n", level, i, i.health(level), i.cost(level), i.energySource());
            }
            System.out.println("");
        }
    }

}
