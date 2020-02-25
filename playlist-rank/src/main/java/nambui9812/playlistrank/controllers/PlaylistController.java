package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.repositories.PlaylistRepository;
import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
  private final PlaylistRepository playlistRepository;

  // Constructor
  public PlaylistController(PlaylistRepository playlistRepository) {
    this.playlistRepository = playlistRepository;
  }

  // Get all playlists
  @GetMapping("/")
  ResponseEntity<List<Playlist>> getAllPlaylists() {
    List<Playlist> playlists = playlistRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(playlists);
  }

  // Get a playlist by id
  @GetMapping("/{id}")
  ResponseEntity<Playlist> getPlaylist(@PathVariable String id) {
    Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(playlist);
  }

  // Create a new playlist
  @PostMapping("/")
  ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist newPlaylist) {
    playlistRepository.save(newPlaylist);

    return ResponseEntity.status(HttpStatus.CREATED).body(newPlaylist);
  }

  // Update a playlist
  @PutMapping("/{id}")
  ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist newPlaylist) {
    Playlist existing = playlistRepository.findById(newPlaylist.getId()).orElseThrow(() -> new PlaylistNotFoundException());

    existing.setDescription(newPlaylist.getDescription());
    existing.setLoves(newPlaylist.getLoves());
    existing.setComments(newPlaylist.getComments());
    existing.setShares(newPlaylist.getShares());
    existing.setTags(newPlaylist.getTags());    

    playlistRepository.save(existing);

    return ResponseEntity.status(HttpStatus.OK).body(existing);
  }

  // Delete a playlist
  @DeleteMapping("/{id}")
  void deletePlaylist(@PathVariable String id) {
    playlistRepository.deleteById(id);
  }
}