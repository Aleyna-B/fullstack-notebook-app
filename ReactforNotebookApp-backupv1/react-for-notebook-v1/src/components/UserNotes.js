import { useEffect } from 'react';
import { useState } from 'react';
import React, { useRef } from 'react';
import axiosInstance from '../axiosConfig';
import '../css/Note.css'

export default function UserNotes(){
  const [notes,setNotes] = useState([]);
  const titleRef = useRef();
  const noteRef = useRef();
  const notebody = {
    title: "",
    note:""
  };

  const handleAddNote = async (event) => {
    event.preventDefault();       //bunun yukarıda olması önemli
    notebody.title = titleRef.current.value
    notebody.note = noteRef.current.value
    console.log('Submitted title: ' + notebody.title);
    console.log('Submitted note: ' + notebody.note);
    try {
    const addNoteRes = await axiosInstance.post('/user/notepage',notebody)
                                          
    if (addNoteRes.status === 200) {
      console.log("post method was succesful");
      console.log('add note data:', addNoteRes.data);
      if (addNoteRes.data) {
        console.log("new note added");
        titleRef.current.value = ""
        noteRef.current.value = ""
      //yenilensin diye tekrar get atılıyor
        axiosInstance.get('/user/notepage')
        .then(response => {
          setNotes(response.data);
          console.error('response:', response.data);
        })
        .catch(error => {
          console.error('Error fetching:', error);
        });
      }
      else alert("Save data response is faulty");
    }
    else {
      alert("post unsuccessful");
      console.log('Response status:', addNoteRes.status);
    }
    } catch (error) {
      console.error('Error adding note:', error);
    }
  }

  useEffect(() => {       //renderlanırken notlar çekilsin diye
    axiosInstance.get('/user/notepage')
      .then(response => {
        setNotes(response.data);
        console.error('response:', response.data);
      })
      .catch(error => {
        console.error('Error fetching:', error);
      });
  }, []);

  const handleEditNote = async (note,index) => {
    titleRef.current.value = note.title;
    noteRef.current.value = note.note;
    handleDeleteNote(note.id,index);
  };

  const handleDeleteNote = async (note_id,index) => {
    try {
    const delres = await axiosInstance.get("user/notepage/"+note_id);
                                          
    if (delres.status === 200 && delres.data==="success") {
      console.log("get method was succesful,item deleted");
      console.log('del val:', delres.data);
    }
    else {
      alert("get unsuccessful");
      console.log('Response status:', delres.status);
    }
    } catch (error) {
      console.error('Error fetching notes:', error);
    }  
    const updatedNotes = notes.slice();   //*slice()* arrayleri kopyalamak için
    updatedNotes.splice(index, 1);    //indexdeki elemandan itibaren 1 eleman silinecek *splice()*
    setNotes(updatedNotes);
  };

  return (
    <>
  <meta charSet="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Modern Note Taking App</title>
  <link rel="stylesheet" href="../css/Note.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
    <div style={{ display: 'flex', height: '100vh', width: '1910px',backgroundColor:'#dbccf9'}}>
      <div style={{ width: '100%', padding: '20px', display:'inline-block'}}>
      <h3 style={{ color: '#260863', paddingLeft:'30px'}}>Manage your notes here!</h3>
        <textarea
          name="title" 
          placeholder="Enter the title here" 
          ref={titleRef}
          style={{ width: '100%', height: '10%' ,resize: 'none'}}
        />
        <textarea
          name="note" 
          placeholder="Enter your new note here" 
          ref={noteRef}
          style={{ width: '100%', height: '75%' ,resize: 'none'}}
        />
        <button id='addBtn'
          onClick={handleAddNote}
          style={{ width: '100%', height: '7%', backgroundColor: 'purple', color: 'white', border: 'none', fontSize: '1rem', cursor: 'pointer' }}
        >
          Add New Note
        </button>
        <p style={{ color: '#ec76ad' }}>Image by juicy_fish on Freepik</p>
      </div>
      
      <div style={{overflowY: 'auto',width:'50%' , padding: '10px', display: 'inline-block', justifyContent: 'space-between',backgroundColor:'#dbccf9',alignItems: 'center',paddingTop:'10px', border: '1px solid #ccc' }}>
        <h4 style={{ color: '#260863', textAlign:'center'}}>Your Other Notes</h4>
        {notes.map((note,index) => (
          <div key={index} style={{ marginBottom: '20px', padding: '10px'}} id='list-container'> 
            <ul style={{ listStyleType: "none" }} >        
              <li key={note.id}>
                <h5>{note.title}</h5>
                <p>{note.note}</p>
                <p>{note.creationTime}</p>
              </li> 
            </ul>           
            <div style={{alignItems:'center'}}>
              <button id='editBtn'
                  onClick={() => handleEditNote(note,index)}
                  style={{padding: '10px 10px', cursor: 'pointer'}}
                >
                  Edit
                </button>
                <button id='deleteBtn'
                  onClick={() => handleDeleteNote(note.id,index)}
                  style={{padding: '10px 10px', cursor: 'pointer'}}
                >
                  Delete
                </button>
                
            </div>
          </div>
          ))}
       </div>
    </div>
        
      
      
    </>
  );
}
