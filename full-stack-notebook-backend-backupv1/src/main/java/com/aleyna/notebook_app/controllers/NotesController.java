package com.aleyna.notebook_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleyna.notebook_app.models.NotesModel;
import com.aleyna.notebook_app.security.CustomUserDetails;
import com.aleyna.notebook_app.services.NotesServiceImpl;

@RestController
public class NotesController {
	
	@Autowired
	private NotesServiceImpl noteserv;

	@PostMapping("/user/notepage")
	public boolean addNotes(@RequestBody NotesModel notemodel)
	{
		NotesModel note = new NotesModel(notemodel.getNote(),notemodel.getTitle());
		note.setUser_id(UserInfoController.userDetailId);
		try {
			noteserv.addNote(note);
			return true;
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
	}
	
	
	@GetMapping("/user/notepage")
	public List<NotesModel> getNotes()
	{
		try {
			return noteserv.showUserNotes(UserInfoController.userDetailId);
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return null;
			}
	}
	
	@GetMapping("user/notepage/{note_id}")
	public String deleteNote(@PathVariable long note_id)
	{
		try {
			noteserv.deleteNoteById(note_id);
			return "success";
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return "fail";
			}
	}
	
	@PostMapping("user/notepage/{note_id}")
	public String editNote(@RequestBody NotesModel editedNote,@PathVariable long note_id)
	{
		try {
			noteserv.updateById(editedNote,note_id);
			return "success";
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return "fail";
			}
	}
	
}
