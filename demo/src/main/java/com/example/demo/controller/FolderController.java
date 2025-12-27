package com.example.demo.controller;

import com.example.demo.entity.Folder;
import com.example.demo.service.FolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping
    public ResponseEntity<?> createFolder(@RequestBody Folder folder){
        try{
            folderService.saveFolder(folder);
            return new ResponseEntity<>(folder , HttpStatus.CREATED);

        }catch(Exception ignored){
            log.error("Something went wrong");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<?>> getAllFolders(){
        List<Folder> all = folderService.showAllFolders();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFolder(@PathVariable long id){
        folderService.deleteFolder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFolderById(@PathVariable long id){
        Optional<Folder> myId = folderService.findMyFolder(id);
        if(myId.isPresent()){
            return new ResponseEntity<>(myId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFolderById(@PathVariable long id, @RequestBody Folder folder){
        Folder old = folderService.findMyFolder(id).orElse(null);
        if(old != null) {
            old.setName(folder.getName());
            folderService.saveFolder(old);
            return new ResponseEntity<>(old , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
