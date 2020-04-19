package com.example.notes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notes.beans.Note;
import com.example.notes.service.NoteService;

@RestController
public class NotesController {
	
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/getAllNotes")
	public ResponseEntity<List<Note>> getAllNotes() {
		return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/post")
	public ResponseEntity<Note> createNote(@RequestBody String noteText) {
		Note note = noteService.createNote(noteText);
		return new ResponseEntity<>(note, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/update/{noteId}")
	public ResponseEntity<Note> updateNote(@PathVariable long noteId, @RequestBody String noteText) {
		Note updatedNote = noteService.updateNote(noteId, noteText);
	    return new ResponseEntity<>(updatedNote, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "delete/{noteId}")
	public ResponseEntity<Note> deleteNote(@PathVariable long noteId) {
		Note note = noteService.deleteNote(noteId);
		return new ResponseEntity<>(note, HttpStatus.ACCEPTED);
	}
	
}
