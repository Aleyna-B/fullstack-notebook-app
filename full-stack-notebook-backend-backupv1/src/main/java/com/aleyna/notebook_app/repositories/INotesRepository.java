package com.aleyna.notebook_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aleyna.notebook_app.models.NotesModel;

public interface INotesRepository extends JpaRepository<NotesModel,Long>{
	@Query("SELECT u FROM NotesModel u WHERE u.id = :id")
	public NotesModel findByNoteId(@Param("id") long id);
	public List<NotesModel> findAll();
	public void deleteNoteById(long note_id);
}
