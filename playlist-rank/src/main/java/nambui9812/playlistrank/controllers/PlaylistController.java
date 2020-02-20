package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.repositories.PlaylistRepository;
import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;

@RestController
public class PlaylistController {
  private final PlaylistRepository playlistRepository;

  // Constructor
  public PlaylistController(PlaylistRepository playlistRepository) {
    this.playlistRepository = playlistRepository;
  }

  // Get all playlists
  @GetMapping("/playlists")
  List<Playlist> getAllPlaylists() {
    return playlistRepository.findAll();
  }

  // Get a playlist by id
  @GetMapping("/playlists/{id}")
  Playlist getPlaylist(@PathVariable String id) {
    return playlistRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException());
  }

  // Create a new playlist
  @PostMapping("/playlists")
  Playlist createPlaylist(@RequestBody Playlist newPlaylist) {
    return playlistRepository.save(newPlaylist);
  }

  // Update a playlist
  @PutMapping("/playlists/{id}")
  Playlist updatePlaylist(@RequestBody Playlist newPlaylist) {
    Playlist existing = playlistRepository.findById(newPlaylist.getId()).orElseThrow(() -> new PlaylistNotFoundException());

    existing.setDescription(newPlaylist.getDescription());
    existing.setLoves(newPlaylist.getLoves());
    existing.setComments(newPlaylist.getComments());
    existing.setShares(newPlaylist.getShares());
    existing.setTags(newPlaylist.getTags());    

    return playlistRepository.save(existing);
  }

  // Delete a playlist
  @DeleteMapping("/playlists/{id}")
  void deletePlaylist(@PathVariable String id) {
    playlistRepository.deleteById(id);
  }
}