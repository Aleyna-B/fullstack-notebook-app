package com.aleyna.notebookapp_v1.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aleyna.notebookapp_v1.connection.RetrofitInstance
import com.aleyna.notebookapp_v1.models.NotesModel
import com.aleyna.notebookapp_v1.models.NotesResponseModel
import kotlinx.coroutines.launch

@Composable
fun NotesList(notesList: List<NotesResponseModel>) {
    val showDialog = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    val note = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LazyColumn {
        items(notesList) { noteItem ->
            Text(text = "Title: ${noteItem.title}")
            Text(text = "Description: ${noteItem.note}")
            Text(text = "Creation Date: ${noteItem.creationTime}")
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        title.value = noteItem.title
                        note.value = noteItem.note
                        scope.launch {
                            val editres = RetrofitInstance.api.deleteNote(noteItem.id)
                            if (editres.isSuccessful && editres.body()=="success"){
                                Log.i("Edit","${noteItem.id} editing item deleted")
                                showDialog.value = true
                            }
                        }
                    }
                ) {
                    Text(text = "Edit", color = Color.White)
                }

                Button(
                    onClick = {
                        scope.launch {
                            val delres = RetrofitInstance.api.deleteNote(noteItem.id)
                            if (delres.isSuccessful && delres.body()=="success"){
                                Log.i("Delete","${noteItem.id} Item deleted")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text(text = "Delete", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    if (showDialog.value) {
        NewNoteDialog(
            title = title.value,
            onTitleChange = { title.value = it },
            desc = note.value,
            onDescriptionChange = { note.value = it },
            onSaveClick = {
                if (title.value.isNotBlank() && note.value.isNotBlank()) {
                    val newNote = NotesModel(title.value, note.value)
                    showDialog.value = false
                }
            },
            onCancelClick = { showDialog.value = false }
        )
    }
}