package befaster.solutions.CHL;

import java.util.Map;
import java.util.HashMap;

public class CheckliteSolution {
    private final int MAX_SKU_RANGE = 25;

    private int[] prices = {50, 30, 20, 15, 40, 10, 20, 10, 35, 60, 80, 90, 15, 40, 10, 50, 30, 50, 30, 20, 40, 50, 20, 90, 10, 50};

    private Map<Integer, Integer[]> basic_offers = new HashMap<Integer, Integer[]>();

    private Map<Integer, Integer[]> advanced_offers = new HashMap<Integer, Integer[]>();

    private Map<Integer, Integer[]> free_goodies_offers = new HashMap<Integer, Integer[]>();

    private int[][] free_self_offers = {new int[] {5, 2}, new int[] {20, 3}};

    public CheckliteSolution() {
        basic_offers.put(1, new Integer[] {2, 45});
        basic_offers.put(10, new Integer[] {2, 150});
        basic_offers.put(15, new Integer[] {5, 200});
        basic_offers.put(16, new Integer[] {3, 80});

        advanced_offers.put(0, new Integer[] {3, 130, 5, 200});
        advanced_offers.put(7, new Integer[] {5, 45, 10, 80});
        advanced_offers.put(21, new Integer[] {2, 90, 3, 130});

        free_goodies_offers.put(4, new Integer[] {2, 1});
        free_goodies_offers.put(13, new Integer[] {3, 12});
        free_goodies_offers.put(17, new Integer[] {3, 16});
    }

    public Integer checklite(String skus) {
        if (skus == null || skus.equals("")) {
            return 0;
        }

        char[] skus_chars = skus.toCharArray();
        int[] skus_chars_count = new int[MAX_SKU_RANGE + 1];
        for (int i = 0, length = skus_chars.length; i < length; i += 1) {
            int index = skus_chars[i] - 'A';
            if (index < 0 || index > MAX_SKU_RANGE) {
                return -1;
            }

            skus_chars_count[index] += 1;

            if (isEligibleForFreeItem(index, skus_chars_count[index])) {
                skus_chars_count[free_goodies_offers.get(index)[1]] -= 1; // give 1 B for free
            }
        }

        for (int i = 0, length = free_self_offers.length; i < length; i += 1) {
            int item_index = free_self_offers[i][0];
            int item_count = free_self_offers[i][1] + 1;

            skus_chars_count[item_index] -= skus_chars_count[item_index] / item_count;
        }

        int price = 0;
        for (int i = 0, length = skus_chars_count.length; i < length; i += 1) {
            price += getPrice(i, skus_chars_count[i]);
        }

        return price != 0 ? price : -1;
    }

    private boolean isEligibleForFreeItem(int index, int count) {
        return free_goodies_offers.containsKey(index) && count % free_goodies_offers.get(index)[0] == 0;
    }

    private int getPrice(int char_index, int count) {
        if (count <= 0 || char_index < 0 || char_index > MAX_SKU_RANGE) {
            return 0;
        }

        if (basic_offers.containsKey(char_index)) {
            Integer[] value = basic_offers.get(char_index);
            return (count / value[0]) * value[1] + (count % value[0]) * prices[char_index];
        } else if (advanced_offers.containsKey(char_index)) {
            return getAdvancedPrice(count, prices[char_index], advanced_offers.get(char_index));
        }

        return count * prices[char_index];
    }

    private int getAdvancedPrice(int count, int item_price, Integer[] value) {
        int price = 0;
        while (count > 0) {
            if (count >= value[2]) {
                price += (count / value[2]) * value[3];
                count = count % value[2];
            } else if (count >= value[0]) {
                price += (count / value[0]) * value[1];
                count = count % value[0];
            } else if (count > 0) {
                price += count * item_price;
                count = 0;
            }
        }

        return price;
    }
}
