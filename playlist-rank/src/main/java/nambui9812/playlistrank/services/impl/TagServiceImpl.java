package nambui9812.playlistrank.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.repositories.TagRepository;
import nambui9812.playlistrank.services.TagService;
import nambui9812.playlistrank.exceptions.TagNotFoundException;

@Service
public class TagServiceImpl implements TagService {
  @Autowired
  private TagRepository tagRepository;

  @Override
  public List<Tag> findAll() {
    return tagRepository.findAll();
  }

  @Override
  public HashMap<String, Integer> findGroupTagName() {
    HashMap<String, Integer> map = new HashMap<>();

    List<Tag> tags = tagRepository.findAll();

    for (int i = 0; i < tags.size(); ++i) {
      Integer index = map.get(tags.get(i).getName());

      map.put(tags.get(i).getName(), (index == null) ? 1 : index + 1);
    }

    return map;
  }

  @Override
  public Tag findById(String id) {
    return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException());
  }

  @Override
  public List<Tag> findByName(String name) {
    return tagRepository.findByName(name);
  }

  @Override
  public List<Tag> findByPlaylistId(String id) {
    return tagRepository.findByPlaylistId(id);
  }

  @Override
  public Tag createTag(Tag tag) {
    return tagRepository.save(tag);
  }

  @Override
  public Tag likeTag(String username, Tag existing) {
    ArrayList<String> list = existing.getLikes();

    for (int i = 0; i < list.size(); ++i) {
      if (list.get(i).equals(username)) {
        return existing;
      }
    }

    list.add(username);

    existing.setLikes(list);

    return tagRepository.save(existing);
  }

  @Override
  public Tag dislikeTag(String username, Tag existing) {
    ArrayList<String> list = existing.getDislikes();

    for (int i = 0; i < list.size(); ++i) {
      if (list.get(i).equals(username)) {
        return existing;
      }
    }

    list.add(username);

    existing.setDislikes(list);

    return tagRepository.save(existing);
  }

  @Override
  public void updateTagPopular() {
    List<Tag> tags = tagRepository.findAll();

    for (int i = 0; i < tags.size(); ++i) {
      float popular = (float) tags.get(i).getLikes().size() * 1.5f - tags.get(i).getDislikes().size();

      tags.get(i).setPopular(popular);
    }

    tagRepository.saveAll(tags);
  }

  @Override
  public void deleteTag(String id) {
    tagRepository.deleteById(id);
  }
}