package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.repositories.TagRepository;
import nambui9812.playlistrank.exceptions.TagNotFoundException;

@RestController
public class TagController {
  private final TagRepository tagsRepository;

  // Constructor
  public TagController(TagRepository tagsRepository) {
    this.tagsRepository = tagsRepository;
  }

  // Get all tags
  @GetMapping("/tags")
  List<Tag> getAllTags() {
    return tagsRepository.findAll();
  }

  // Get a tag by id
  @GetMapping("/tags/{id}")
  Tag getTag(@PathVariable String id) {
    return tagsRepository.findById(id).orElseThrow(() -> new TagNotFoundException());
  }

  // Create a new tag
  @PostMapping("/tags")
  Tag createTag(@RequestBody Tag newTag) {
    return tagsRepository.save(newTag);
  }

  // Update a tag
  @PutMapping("/tags/{id}")
  Tag updateTag(@RequestBody Tag newTag) {
    Tag existing = tagsRepository.findById(newTag.getId()).orElseThrow(() -> new TagNotFoundException());

    existing.setLikes(newTag.getLikes());
    existing.setDislikes(newTag.getDislikes());

    return tagsRepository.save(existing);
  }

  // Delete a tag
  @DeleteMapping("/tags/{id}")
  void deleteTag(@PathVariable String id) {
    tagsRepository.deleteById(id);
  }
}