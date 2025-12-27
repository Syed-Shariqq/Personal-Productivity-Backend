package com.example.demo.service;

import com.example.demo.entity.SearchHistory;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public void saveHistory(String word){

        if(word == null || word.trim().isEmpty()){
            return;
        }

        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setWord(word);
        searchHistory.setSearchedAt(LocalDateTime.now());

        searchHistoryRepository.save(searchHistory);

    }
    public List<SearchHistory> getAllHistory(){

        List<SearchHistory> list = searchHistoryRepository.findAllByOrderBySearchedAtDesc().stream().limit(10).toList();
        if(!list.isEmpty()){
            return list;
        }
        return new ArrayList<>();
    }
    public void deleteAll(){
        searchHistoryRepository.deleteAll();
    }
    public void deleteById(long id){

        searchHistoryRepository.deleteById(id);
    }
    public SearchHistory getSearchHistoryId(long id){
       return searchHistoryRepository.getReferenceById(id);
    }

}
