package befaster.solutions.HLO;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HelloSolutionTest {
    private HelloSolution helloSol;

    @Before
    public void setUp() {

        helloSol = new HelloSolution();
    }

    @Test
    public void compute_hello() {
        assertThat(helloSol.hello("Test"), equalTo("Hello, Test!"));
    }

    @Test
    public void compute_hello_without_friend() {
        assertThat(helloSol.hello(""), equalTo("Hello, !"));
    }
}
