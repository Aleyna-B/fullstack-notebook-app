package com.aleyna.notebookapp_v1.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aleyna.notebookapp_v1.connection.RetrofitInstance
import com.aleyna.notebookapp_v1.models.NotesModel
import com.aleyna.notebookapp_v1.models.UserInfos
import kotlinx.coroutines.launch

@Composable
fun NewNoteDialog(
    title: String,
    onTitleChange: (String) -> Unit,
    desc: String,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var responseMessage by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text(text = "New Note")},
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = "Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = desc,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = "Note") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                scope.launch {
                    try {
                        val newnote = NotesModel(desc, title)
                        val response = RetrofitInstance.api.addNote(newnote)

                        responseMessage = if (response.body() == true) {
                            "New note saved!"
                        } else {
                            "Save failed!"
                        }
                    } catch (e: Exception) {
                        responseMessage = "Exception: ${e.message}"
                    }
                    Log.i("Save info:", responseMessage)
                }
            }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Save")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.Add, contentDescription = "SAVE")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = "Cancel")
            }
        }
    )
}