package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;

public interface PlaylistService {
  List<Playlist> findAll();

  Playlist findById(String id);

  List<Playlist> findByAuthorUsername(String authorUsername);

  List<Playlist> findByTagName(String tagName);

  Playlist createPlaylist(Playlist playlist);

  Playlist updateDescription(Playlist existing, UpdateDescriptionPlaylistValidation info);

  Playlist sharePlaylist(String authorUsername, Playlist existing);

  Playlist lovePlaylist(String username, Playlist existing);

  void updatePlaylistPopular();

  void deletePlaylist(String id);
}