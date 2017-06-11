package me.rishabhkhanna.multicopy.model;


import io.realm.RealmObject;

/**
 * Created by rishabhkhanna on 14/05/17.
 */

public class NotesModel extends RealmObject {

    public String note;

    public String createdAt;

    public NotesModel() {
        super();
    }

    public NotesModel(String note, String createdAt) {
        this.note = note;
        this.createdAt = createdAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
