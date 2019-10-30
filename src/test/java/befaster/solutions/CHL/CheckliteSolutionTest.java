package befaster.solutions.CHL;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckliteSolutionTest {
    private CheckliteSolution checkLiteSol;

    @Before
    public void setUp() {
        checkLiteSol = new CheckliteSolution();
    }

    @Test
    public void compute_checklite_error() {
        assertThat(checkLiteSol.checklite("K"), equalTo(-1));
    }

    @Test
    public void compute_checklite_empty_or_null_string() {
        assertThat(checkLiteSol.checklite(null), equalTo(0));
        assertThat(checkLiteSol.checklite(""), equalTo(0));
    }

    @Test
    public void compute_checklite_success_single_sku() {
        assertThat(checkLiteSol.checklite("A"), equalTo(50));
    }

    @Test
    public void compute_checklite_discounted_sku() {
        assertThat(checkLiteSol.checklite("AAAA"), equalTo(180));
    }

    @Test
    public void compute_checklite_complex_discounted_sku() {
        assertThat(checkLiteSol.checklite("AABBCCDD"), equalTo(215));
        assertThat(checkLiteSol.checklite("BABDDCAC"), equalTo(215));
        assertThat(checkLiteSol.checklite("ABCDCBAABCABBAAA"), equalTo(505));
    }

    @Test
    public void compute_checklite_skus_with_one_invalid_sku() {
        assertThat(checkLiteSol.checklite("AABDKBACA"), equalTo(-1));
        assertThat(checkLiteSol.checklite("aACD"), equalTo(-1));
    }

    @Test
    public void compute_checklite_freebies() {
        assertThat(checkLiteSol.checklite("AABDEACA"), equalTo(255));
    }
}

