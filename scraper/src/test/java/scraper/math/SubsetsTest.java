package scraper.math;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Stănilă Ioan, 6/22/2017.
 */
public class SubsetsTest {
    @Test
    public void generateSubsets() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Subsets<Integer> integerSubsets = new Subsets<>(integers);
        List<List<Integer>> result = Arrays.asList(
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3),
                Arrays.asList(1, 2, 3)
        );
        assertTrue(integerSubsets.generateSubsets().equals(result));
    }
}