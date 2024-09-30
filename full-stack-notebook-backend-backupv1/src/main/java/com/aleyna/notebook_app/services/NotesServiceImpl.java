package com.aleyna.notebook_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleyna.notebook_app.models.NotesModel;
import com.aleyna.notebook_app.repositories.INotesRepository;

@Service
public class NotesServiceImpl {
	@Autowired
	private INotesRepository notesrepo;

	public NotesModel addNote(NotesModel note)
	{
		note.setCreationTime();
		return notesrepo.save(note);
	}
	
	public List<NotesModel> showUserNotes(Long Id)
	{
		List<NotesModel> notlist = new ArrayList<>();
		for(NotesModel model:notesrepo.findAll())
		{
			if(model.getUser_id() == Id)
			{
				notlist.add(model);
			}
		}
		return notlist;
	}

	public void deleteNoteById(long note_id) {
		notesrepo.deleteById(note_id);
	}
	
	public void updateById(NotesModel edited,long note_id) {
		NotesModel note = notesrepo.findByNoteId(note_id);
		note.setTitle(edited.getTitle());
		note.setNote(edited.getNote());
		notesrepo.save(note);
		
	}
}
