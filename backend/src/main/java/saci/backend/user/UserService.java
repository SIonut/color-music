package saci.backend.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import saci.backend.playlist.PlaylistDto;
import saci.backend.playlist.PlaylistService;
import saci.backend.song.SongDto;
import saci.backend.song.SongService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Stănilă Ioan, 5/23/2017.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlaylistService playlistService;
    private final SongService songService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       PlaylistService playlistService,
                       SongService songService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.playlistService = playlistService;
        this.songService = songService;
    }

    public UserDto save(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        User saved = userRepository.save(user);
        UserDto savedDto = new UserDto();
        savedDto.setUsername(saved.getUsername());
        savedDto.setId(user.getId());
        PlaylistDto playlistDto = new PlaylistDto();
        playlistDto.setName("Likes");
        playlistDto.setUserId(user.getId());
        playlistDto.setSongs(Collections.emptyList());
        playlistDto.setFollowing(Collections.emptyList());
        playlistService.create(playlistDto);
        return savedDto;
    }

    public UserDto findByUsername(UserDto dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        if (user == null) {
            return null;
        }
        return new ModelMapper().map(user, UserDto.class);
    }

    public UserDto findById(String id) {
        return new ModelMapper().map(userRepository.findOne(id), UserDto.class);
    }

    public Boolean isSongLiked(String userId, String songId) {
        PlaylistDto likes = getLikes(userId);
        List<SongDto> songs = likes.getSongs();
        return songs.stream().anyMatch(it -> it.getId().equals(songId));
    }

    public Boolean addToLikes(String userId, String songId) {
        PlaylistDto likes = getLikes(userId);
        if (likes.getSongs().stream().anyMatch(it -> it.getId().equals(songId))) {
            return false;
        }
        SongDto songDto = songService.findById(songId);
        likes.getSongs().add(songDto);
        return playlistService.update(likes) != null;
    }

    public Boolean removeFromLikes(String userId, String songId) {
        PlaylistDto likes = getLikes(userId);
        if (likes.getSongs().stream().noneMatch(it -> it.getId().equals(songId))) {
            return false;
        }
        likes.setSongs(likes.getSongs().stream()
                .filter(it -> !it.getId().equals(songId))
                .collect(Collectors.toList()));
        return playlistService.update(likes) != null;
    }

    public PlaylistDto getLikes(String userId) {
        List<PlaylistDto> playlists = playlistService.findByUserId(userId);
        Optional<PlaylistDto> likes = playlists.stream().filter(it -> it.getName().equals("Likes")).findFirst();
        return likes.orElse(null);
    }
}
