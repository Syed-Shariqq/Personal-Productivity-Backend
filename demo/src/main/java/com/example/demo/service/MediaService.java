package com.example.demo.service;

import com.example.demo.entity.Media;
import com.example.demo.entity.Note;
import com.example.demo.repository.MediaRepository;
import com.example.demo.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class MediaService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private NoteRepository noteRepository;

    public void storeFile(long id, MultipartFile file){
        try{
            if(!Files.exists(root))
                Files.createDirectories(root);
            String filename = System.currentTimeMillis() + " " + file.getOriginalFilename();

            Path destination = this.root.resolve(filename);
            Files.copy(file.getInputStream(),destination);

            Note note = noteRepository.findById(id).orElseThrow();

            Media media = new Media();
            media.setFilename(filename);
            media.setFiletype(file.getContentType());
            media.setFilepath(String.valueOf(destination));
            media.setNote(note);

            mediaRepository.save(media);

        }catch (Exception e){
            log.error("Failed to store file",e);
        }
    }
}
