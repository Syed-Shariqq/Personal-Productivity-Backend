package com.example.demo.service;

import com.example.demo.entity.Folder;
import com.example.demo.repository.FolderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Transactional
    public void saveFolder(Folder folder){
        folderRepository.save(folder);
    }

    public List<Folder> showAllFolders(){
        return folderRepository.findAll();
    }

    public void deleteFolder(long id){
        folderRepository.deleteById(id);
    }

    public Optional<Folder> findMyFolder(long id){
        return folderRepository.findById(id);

    }
}
