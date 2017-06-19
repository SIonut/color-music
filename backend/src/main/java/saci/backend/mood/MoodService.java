package saci.backend.mood;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corina on 5/20/2017.
 */
@Service
public class MoodService {

    private final MoodRepository moodRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public List<MoodDto> findAll() {
        List<MoodDto> dtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        moodRepository.findAll().forEach(mood -> dtoList.add(modelMapper.map(mood, MoodDto.class)));
        return dtoList;
    }

    public MoodDto findByColor(String color) {
        Mood mood = moodRepository.findOne("#" + color);
        if (mood == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mood, MoodDto.class);
    }
}
