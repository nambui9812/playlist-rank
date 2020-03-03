package nambui9812.playlistrank.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.repositories.PlaylistRepository;
import nambui9812.playlistrank.services.PlaylistService;
import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;
import nambui9812.playlistrank.validations.SharePlaylistValidation;

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
  public Playlist updateDescription(Playlist existing, UpdateDescriptionPlaylistValidation info) {

    existing.setDescription(info.getDescription());

    return playlistRepository.save(existing);
  }

  @Override
  public Playlist sharePlaylist(String authorUsername, SharePlaylistValidation info) {
    Playlist newPlaylist = new Playlist(authorUsername, info.getDescription(), info.getAuthorUsername(), null, null, info.getTracks());

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
  public void deletePlaylist(String id) {
    playlistRepository.deleteById(id);
  }
}