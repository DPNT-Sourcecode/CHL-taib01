package befaster.solutions.CHL;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        if (skus == null || skus.equals("")) {
            return 0;
        }

        char[] skus_chars = skus.toCharArray();
        int[] skus_chars_count = new int[5];
        for (int i = 0, length = skus_chars.length; i < length; i += 1) {
            int index = skus_chars[i] - 'A';
            if (index < 0 || index > 4) {
                return -1;
            }

            skus_chars_count[index] += 1;

            // give one B for free
            if (
                index == 4 &&
                skus_chars_count[index] % 2 == 0 &&
                skus_chars_count[1] > 0
            ) {
                skus_chars_count[1] -= 1;
            }
        }

        int price = 0;
        for (int i = 0, length = skus_chars_count.length; i < length; i += 1) {
            price += getPrice(i, skus_chars_count[i]);
        }

        return price != 0 ? price : -1;
    }

    private int getPrice(int char_index, int count) {
        if (count <= 0) {
            return 0;
        }

        switch(char_index) {
            case 0:
                return getPriceOfA(count);
            case 1:
                return getPriceOfB(count);
            case 2:
                return count * 20;
            case 3:
                return count * 15;
            case 4:
                return count * 40;
            default:
                return 0;
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
            } else {
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




