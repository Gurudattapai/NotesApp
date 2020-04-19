package com.example.notes.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.notes.beans.Note;

public interface NotesRepository extends CrudRepository<Note, Long> {
	
	

}
