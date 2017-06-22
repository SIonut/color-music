package scraper.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by corina on 22.06.2017.
 */
public class Subsets<T> {

    private List<T> items;

    public Subsets(List<T> items) {
        this.items = items;
    }

    public List<List<T>> generateSubsets() {
        List<List<T>> subsets = new ArrayList<>();

        subsets.addAll(generateKSubsets(1));
        subsets.addAll(generateKSubsets(2));
        subsets.addAll(generateKSubsets(3));

        return subsets;
    }

    public List<List<T>> generateKSubsets(int k) {
        List<Set<T>> subsets = new ArrayList<>();

        if (k == 1) {
            return items.stream()
                    .map(it -> Collections.singletonList(it))
                    .collect(Collectors.toList());
        } else if (k == 2) {
            return  generate2Subsets();
        } else {
            return generate3Subsets();
        }

    }

    private List<List<T>> generate2Subsets() {
        List<List<T>> subsets = new ArrayList<>();

        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                List<T> set = new ArrayList<>();
                set.add(items.get(i));
                set.add(items.get(j));
                subsets.add(set);
            }
        }

        return subsets;
    }

    private List<List<T>> generate3Subsets() {
        List<List<T>> subsets = new ArrayList<>();

        for (int i = 0; i < items.size() - 2; i++) {
            for (int j = i + 1; j < items.size() - 1; j++) {
                for (int k = j + 1; k < items.size(); k++) {
                    List<T> set = new ArrayList<>();
                    set.add(items.get(i));
                    set.add(items.get(j));
                    set.add(items.get(k));
                    subsets.add(set);
                }
            }
        }
        return subsets;

    }
}
