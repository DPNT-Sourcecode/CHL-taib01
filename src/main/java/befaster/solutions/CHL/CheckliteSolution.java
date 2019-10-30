package befaster.solutions.CHL;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        if (skus == null || skus.equals("")) {
            return 0;
        }

        char[] skus_chars = skus.toCharArray();
        int[] skus_chars_count = new int[4];
        for (int i = 0, length = skus_chars.length; i < length; i += 1) {
            int index = skus_chars[i] - 'A';
            if (index < 0 || index > 3) {
                return -1;
            }

            skus_chars_count[index] += 1;
        }

        int price = 0;
        for (int i = 0, length = skus_chars_count.length; i < length; i += 1) {
            price += getPrice(i, skus_chars_count[i]);
        }

        return price != 0 ? price : -1;
    }

    private Integer getPrice(int char_index, int count) {
        switch(char_index) {
            case 0:
                return (count / 3) * 130 + (count % 3) * 50;
            case 1:
                return (count / 2) * 45 + (count % 2) * 30;
            case 2:
                return count > 0 ? count * 20 : 0;
            case 3:
                return count > 0 ? count * 15 : 0;
            case 4:
                return count > 0 ? count * 40 : 0;
            default:
                return 0;
        }
    }
}

