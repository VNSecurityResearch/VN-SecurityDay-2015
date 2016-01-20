package com.hptsec.vulnlab.Model;

public class M7MyNoteModel {

	private int noteId;
	private String noteData;
	private boolean isPrivate;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteData() {
		return noteData;
	}

	public void setNoteData(String noteData) {
		this.noteData = noteData;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public M7MyNoteModel() {
		// TODO Auto-generated constructor stub
		isPrivate = false;
	}

}
