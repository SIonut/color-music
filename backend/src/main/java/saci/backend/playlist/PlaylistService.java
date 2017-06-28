package saci.backend.playlist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saci.backend.pusher.PusherService;
import saci.backend.song.Song;
import saci.backend.user.User;
import saci.backend.user.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@Service
public class PlaylistService {

    private final PusherService pusherService;
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PusherService pusherService,
                           UserRepository userRepository,
                           PlaylistRepository playlistRepository) {
        this.pusherService = pusherService;
        this.userRepository = userRepository;
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
        one.setFollowing(Collections.emptyList());
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
        dto.setFollowing(Collections.emptyList());
        Playlist entity = modelMapper.map(dto, Playlist.class);
        Playlist saved = playlistRepository.save(entity);
        return modelMapper.map(saved, PlaylistDto.class);
    }

    public PlaylistDto update(PlaylistDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        Playlist old = playlistRepository.findOne(dto.getId());
        dto.setFollowing(dto.getFollowing().stream().distinct().collect(Collectors.toList()));
        Playlist saved = playlistRepository.save(modelMapper.map(dto, Playlist.class));
        PlaylistDto result = modelMapper.map(saved, PlaylistDto.class);

        saved.getFollowing().removeAll(old.getFollowing());
        Optional<String> newFollower = saved.getFollowing().stream().findFirst();
        if (newFollower.isPresent()) {
            User user = userRepository.findOne(newFollower.get());
            pusherService.emit(dto.getUserId(), PusherService.EVENT_LIKE,
                    user.getUsername() + " liked your playlist");
        }

        saved.getSongs().removeAll(old.getSongs());
        Optional<Song> newSongInPlaylist = saved.getSongs().stream().findFirst();
        if (newSongInPlaylist.isPresent()) {
            User user = userRepository.findOne(dto.getUserId());
            result.getFollowing().forEach(it -> {
                pusherService.emit(it, PusherService.EVENT_FOLLOW,
                        user.getUsername() + " added a new song into " + dto.getName());
            });
        }

        return result;
    }

    public void delete(String id) {
        playlistRepository.delete(id);
    }

    public List<PlaylistDto> findByUserId(String userId) {
        return playlistRepository.findByUserId(userId).stream()
                .map(it -> new ModelMapper().map(it, PlaylistDto.class))
                .collect(Collectors.toList());
    }

    public List<PlaylistDto> findByUserIdWithoutLikes(String userId) {
        return findByUserId(userId).stream()
                .filter(it -> !it.getName().equals("Likes"))
                .collect(Collectors.toList());
    }

    public List<PlaylistDto> getTopPlaylists(int limit) {
        return playlistRepository.findAll().stream()
                .filter(it -> !it.getName().equals("Likes"))
                .sorted((o1, o2) -> o2.getFollowing().size() - o1.getFollowing().size())
                .limit(limit)
                .map(it -> new ModelMapper().map(it, PlaylistDto.class))
                .collect(Collectors.toList());
    }
}
