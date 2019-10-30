package befaster.solutions.CHL;

public class CheckliteSolution {
    private final int MAX_SKU_RANGE = 25;

    private int[] prices = {50, 30, 20, 15, 40, 10, 20, 10, 35, 60, 80, 90, 15, 40, 10, 50, 30, 50, 30, 20, 40, 50, 20, 90, 10, 50};

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

            if (isEligibleForEDiscount(index, skus_chars_count[index])) {
                skus_chars_count[1] -= 1; // give 1 B for free
            }
        }

        skus_chars_count[5] = skus_chars_count[5] - (skus_chars_count[5] / 3);
        
        int price = 0;
        for (int i = 0, length = skus_chars_count.length; i < length; i += 1) {
            price += getPrice(i, skus_chars_count[i]);
        }

        return price != 0 ? price : -1;
    }

    private boolean isEligibleForEDiscount (int index, int count) {
        return index == 4 && count % 2 == 0;
    }

    private int getPrice(int char_index, int count) {
        if (count <= 0 || char_index < 0 || char_index > MAX_SKU_RANGE) {
            return 0;
        }

        switch(char_index) {
            case 0:
                return getPriceOfA(count);
            case 1:
                return getPriceOfB(count);
            default:
                return count * prices[char_index];
        }
    }

    private int getPriceOfA(int count) {
        int price = 0;
        while (count > 0) {
            if (count >= 5) {
                price += (count / 5) * 200;
                count = count % 5;
            } else if (count >= 3) {
                price += (count / 3) * 130;
                count = count % 3;
            } else if (count > 0) {
                price += count * 50;
                count = 0;
            }
        }

        return price;
    }

    private int getPriceOfB(int count) {
        return (count / 2) * 45 + (count % 2) * 30;
    }
}
