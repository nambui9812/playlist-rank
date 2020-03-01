package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.Playlist;

public interface PlaylistService {
  List<Playlist> findAll();

  Playlist findById(String id);

  List<Playlist> findByAuthorUsername(String authorUsername);

  Playlist createPlaylist(Playlist playlist);

  Playlist updatePlaylist(Playlist existing, Playlist newPlaylist);

  void deletePlaylist(String id);
}