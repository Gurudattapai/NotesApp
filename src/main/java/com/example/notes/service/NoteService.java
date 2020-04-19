package com.example.notes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notes.beans.Note;
import com.example.notes.dao.NotesRepository;

@Service
public class NoteService {
	
	@Autowired
	NotesRepository repository;
	
	public Note createNote(String noteText) {
		Note note = new Note();
		note.setNote(noteText);
		return repository.save(note);
	}
	
	public List<Note> getAllNotes() {
		List<Note> notesList = new ArrayList<>();
		for (Note note : repository.findAll()) {
			notesList.add(note);
		}
		return notesList;
	}
	
	public Note updateNote(long noteId, String noteText) {
		Optional<Note> optional = repository.findById(noteId);
		if (optional.isPresent()) {
			Note note = optional.get();
			note.setNote(noteText);
			return repository.save(note);
		}
		return null;
	}
	
	public Note deleteNote(long noteId) {
		Optional<Note> optional = repository.findById(noteId);
		if (optional.isPresent()) {
			repository.delete(optional.get());
		}
		return null;
	}

}
