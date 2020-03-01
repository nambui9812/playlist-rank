package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.repositories.PlaylistRepository;
import nambui9812.playlistrank.services.PlaylistService;
import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;

@Service
public class PlaylistServiceImpl implements PlaylistService {
  @Autowired
  private PlaylistRepository playlistRepository;

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
  public Playlist createPlaylist(Playlist playlist) {
    return playlistRepository.save(playlist);
  }

  @Override
  public Playlist updatePlaylist(Playlist existing, Playlist newPlaylist) {

    existing.setDescription(newPlaylist.getDescription());

    return playlistRepository.save(existing);
  }

  @Override
  public void deletePlaylist(String id) {
    playlistRepository.deleteById(id);
  }
}