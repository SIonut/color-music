package saci.backend.eight.tracks;

import java.util.List;

/**
 * @author Stănilă Ioan, 6/19/2017.
 */
public interface EightTracksService {

    List<String> findByMoods(List<String> moods);
}
