package saci.backend.playlist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<PlaylistDto> findAll() {
        List<PlaylistDto> dtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        playlistRepository.findAll().forEach(it -> dtoList.add(modelMapper.map(it, PlaylistDto.class)));
        return dtoList;
    }

    public PlaylistDto findById(String id) {
        Playlist one = playlistRepository.findOne(id);
        if (one == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(one, PlaylistDto.class);
    }

    public PlaylistDto create(PlaylistDto dto) {
        if (dto.getId() != null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<Playlist> allOfUser = playlistRepository.findByUserId(dto.getUserId());
        boolean isUnique = allOfUser.stream().noneMatch(it -> it.getName().equals(dto.getName()));
        if (!isUnique) {
            return null;
        }
        Playlist entity = modelMapper.map(dto, Playlist.class);
        Playlist saved = playlistRepository.save(entity);
        return modelMapper.map(saved, PlaylistDto.class);
    }

    public PlaylistDto update(PlaylistDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        List<Playlist> allOfUser = playlistRepository.findByUserId(dto.getUserId());
        boolean isUnique = allOfUser.stream().noneMatch(it -> it.getName().equals(dto.getName()));
        if (!isUnique) {
            return null;
        }
        Playlist saved = playlistRepository.save(modelMapper.map(dto, Playlist.class));
        return modelMapper.map(saved, PlaylistDto.class);
    }

    public void delete(String id) {
        playlistRepository.delete(id);
    }
}
