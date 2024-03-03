package org.example.tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }
}
