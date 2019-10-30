package befaster.solutions.CHL;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        // 1. count occurence of each character
        // 2. compare the items with price table and calculate price for individual item
        // 3. sum all and return
        char[] skus_chars = skus.toCharArray();
        int[] skus_chars_count = new int[4];
        for (int i = 0, length = skus_chars.length; i < length; i += 1) {
            int index = skus_chars[i] - 'A';
            if (index < 0 || index > 3) {
                continue;
            }

            skus_chars_count[index] += 1;
        }

        int price = -1;
        for (int i = 0, length = skus_chars_count.length; i < length; i += 1) {
            price += getPrice(i, skus_chars_count[i]);
        }

        return price;
    }

    private Integer getPrice(int char_index, int count) {
        switch(char_index) {
            case 2:
                return 20;
            case 3:
                return 15;
            case 1:
                return (count / 2) * 45 + (count % 2) * 30;
            case 0:
                return (count / 3) * 130 + (count % 3) * 50;
            default:
                return 0;
        }
    }
}

