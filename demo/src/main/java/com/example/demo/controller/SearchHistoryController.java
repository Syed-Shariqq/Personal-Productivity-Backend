package com.example.demo.controller;

import com.example.demo.entity.SearchHistory;
import com.example.demo.service.SearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchHistoryController {


    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping("/history")
    public ResponseEntity<?> saveHistory(@RequestParam String word){
     try{
         searchHistoryService.saveHistory(word);
         return new ResponseEntity<>(HttpStatus.CREATED);
     }catch (Exception e){
         log.error("Not saved due to ",e);
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

    }
    @GetMapping
    public ResponseEntity<List<?>> getSearchHistory(){
        try{
            List<SearchHistory> allHistory = searchHistoryService.getAllHistory();
            return new ResponseEntity<>(allHistory, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/clear")
    public ResponseEntity<?> deleteHistory(){
        List<SearchHistory> allHistory = searchHistoryService.getAllHistory();
        if(allHistory.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        searchHistoryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/clear/{id}")
    public ResponseEntity<?> deleteHistoryById(@PathVariable long id){
        try{
            SearchHistory searchHistory = searchHistoryService.getSearchHistoryId(id);
            if(searchHistory.getWord() == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            searchHistoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("This history does not exist");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}
