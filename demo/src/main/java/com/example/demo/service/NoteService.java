package com.example.demo.service;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Transactional
    public void saveNote(Note note){
        noteRepository.save(note);
    }

    public List<Note> showAll(){
       return noteRepository.findAll();
    }

    public void deleteMyId(long id){
        noteRepository.deleteById(id);
    }
    
    public Optional<Note> findMyId(long id){
        return noteRepository.findById(id);

    }
    public List<Note> searchByWords(String keyword){
       return noteRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }
    public void pinNote(long id){
        try{
            Note note = noteRepository.findById(id).orElseThrow();

            note.setPin(true);
            noteRepository.save(note);
        }catch (Exception e){
            log.error("Something went wrong", e);
        }

    }
    public void unPinNote(long id) {
        Note note = noteRepository.findById(id).orElseThrow();

        note.setPin(false);
        noteRepository.save(note);
    }
    public List<Note> getAllPinnedNotes(){
        return noteRepository.findAll(Sort.by(Sort.Order.desc("pin"),Sort.Order.desc("id")));
    }

}
