package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;
import nambui9812.playlistrank.validations.SharePlaylistValidation;

public interface PlaylistService {
  List<Playlist> findAll();

  Playlist findById(String id);

  List<Playlist> findByAuthorUsername(String authorUsername);

  Playlist createPlaylist(Playlist playlist);

  Playlist updateDescription(Playlist existing, UpdateDescriptionPlaylistValidation info);

  Playlist sharePlaylist(String authorUsername, SharePlaylistValidation info);

  Playlist lovePlaylist(String username, Playlist existing);

  void deletePlaylist(String id);
}