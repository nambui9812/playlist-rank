package nambui9812.playlistrank.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.repositories.PlaylistRepository;
import nambui9812.playlistrank.repositories.TagRepository;
import nambui9812.playlistrank.services.PlaylistService;
import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;

@Service
public class PlaylistServiceImpl implements PlaylistService {
  @Autowired
  private PlaylistRepository playlistRepository;
  
  @Autowired
  private TagRepository tagRepository;

  @Override
  public List<Playlist> findAll() {
    return playlistRepository.findAll();
  }

  @Override
  public Playlist findById(String id) {
    return playlistRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException());
  }

  @Override
  public List<Playlist> findByAuthorUsername(String authorUsername) {
    return playlistRepository.findByAuthorUsername(authorUsername);
  }

  @Override
  public List<Playlist> findByTagName(String tagName) {
    List<Tag> tags = tagRepository.findByName(tagName);

    ArrayList<String> playlistIds = new ArrayList<>();
    ArrayList<Playlist> playlists = new ArrayList<>();

    // Find all playlist's ids that has a specific tag name
    for (int i = 0; i < tags.size(); ++i) {
      playlistIds.add(tags.get(i).getPlaylistId());
    }

    // Find all playlists from id list
    for (int i = 0; i < playlistIds.size(); ++i) {
      Playlist playlist = playlistRepository.findById(playlistIds.get(i)).orElseThrow(() -> new PlaylistNotFoundException());
      playlists.add(playlist);
    }

    List<Playlist> converted = playlists;

    return converted;
  }

  @Override
  public Playlist createPlaylist(Playlist playlist) {
    return playlistRepository.save(playlist);
  }

  @Override
  public Playlist updateDescription(Playlist existing, UpdateDescriptionPlaylistValidation info) {

    existing.setDescription(info.getDescription());

    return playlistRepository.save(existing);
  }

  @Override
  public Playlist sharePlaylist(String authorUsername, Playlist existing) {
    Playlist newPlaylist = new Playlist(authorUsername, existing.getDescription(), existing.getAuthorUsername(), existing.getTracks());

    // Check if this user shared this playlist or not
    boolean shared = false;
    ArrayList<String> shares = existing.getShares();

    for (int i = 0; i < shares.size(); ++i) {
      if (shares.get(i).equals(authorUsername)) {
        shared = true;
        break;
      }
    }

    // Allow shares many time but not include in share list of original playlist
    if (!shared) {
      shares.add(authorUsername);
    }

    existing.setShares(shares);
    playlistRepository.save(existing);

    return playlistRepository.save(newPlaylist);
  }

  @Override
  public Playlist lovePlaylist(String username, Playlist existing) {
    ArrayList<String> list = existing.getLoves();

    for (int i = 0; i < list.size(); ++i) {
      if (list.get(i).equals(username)) {
        return existing;
      }
    }

    list.add(username);

    existing.setLoves(list);

    return playlistRepository.save(existing);
  }

  @Override
  public Playlist unlovePlaylist(String username, Playlist existing) {
    ArrayList<String> list = existing.getLoves();

    for (int i = 0; i < list.size(); ++i) {
      if (list.get(i).equals(username)) {
        list.remove(i);

        existing.setLoves(list);

        return playlistRepository.save(existing);
      }
    }

    return existing;
  }

  @Override
  public void updatePlaylistPopular() {
    List<Playlist> playlists = playlistRepository.findAll();

    for (int i = 0; i < playlists.size(); ++i) {
      float popular = (float) playlists.get(i).getLoves().size() * 2 + playlists.get(i).getShares().size();

      playlists.get(i).setPopular(popular);
    }

    playlistRepository.saveAll(playlists);
  }

  @Override
  public void deletePlaylist(String id) {
    playlistRepository.deleteById(id);
  }
}
