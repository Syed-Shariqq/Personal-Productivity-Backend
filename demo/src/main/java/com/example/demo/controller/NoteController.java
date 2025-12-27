package com.example.demo.controller;

import com.example.demo.entity.Folder;
import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.FolderService;
import com.example.demo.service.NoteService;
import com.example.demo.service.SearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note){
        try{
           note.setDate(LocalDateTime.now());
           noteService.saveNote(note);
           return new ResponseEntity<>(note , HttpStatus.CREATED);

        }catch(Exception ignored){
            log.error("Something went wrong");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getNotesOfFolder(){
        List<Note> all = noteService.showAll();
         if(all != null && !all.isEmpty()){
             return new ResponseEntity<>(all , HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        noteService.deleteMyId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable long id){
          Optional<Note> myId = noteService.findMyId(id);
          if(myId.isPresent()){
              return new ResponseEntity<>(myId.get() , HttpStatus.OK);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody Note newNote){
        Note old = noteService.findMyId(id).orElse(null);
        if(old!= null){
            old.setTitle(newNote.getTitle() != null && !newNote.getTitle().isEmpty() ? newNote.getTitle() : old.getTitle());
            old.setContent(newNote.getContent() != null &&!newNote.equals("") ? newNote.getContent() : old.getContent());
            noteService.saveNote(old);
            return new ResponseEntity<>(old , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchByContentOrTitle(@RequestParam String keyword){
        searchHistoryService.saveHistory(keyword);
        List<Note> notes = noteService.searchByWords(keyword);
        if(!notes.isEmpty()){
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/pin/{id}")
    public ResponseEntity<?> pinById(@PathVariable long id){
        noteService.pinNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/unpin/{id}")
    public ResponseEntity<?> unpinById(@PathVariable long id){
        noteService.unPinNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/pinned")
    public ResponseEntity<?> pinnedNotes(){
        List<Note> allPinnedNotes = noteService.getAllPinnedNotes();
        if(!allPinnedNotes.isEmpty()){
            return new ResponseEntity<>(allPinnedNotes,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create/{folderId}")
    public ResponseEntity<?> createNoteInFolder(@PathVariable long folderId, @RequestBody Note note){
        Folder folder = folderService.findMyFolder(folderId).orElseThrow(() -> new RuntimeException("folder not found"));
        note.setFolder(folder);
        Note savedNote = noteRepository.save(note);
        return new ResponseEntity<>(savedNote, HttpStatus.OK);
    }
    @GetMapping("/upload/{id}")
    public ResponseEntity<?> getNoteWithMedia(@PathVariable long id){
        Note note = noteRepository.findNoteWithMedia(id);
                if(note != null ){
                    return new ResponseEntity<>(note,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
