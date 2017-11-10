package com.example.amandaspolti.todoapp;

import java.io.Serializable;

/**
 * Classe responsável pelo Item.
 */
public class Item implements Serializable{
    private long _id;
    private boolean done;
    private String text;
    private String dueDate;
    private int nivel; //1 facil 2 medio 3 dificil

    public Item(String id, String text, String nivel, String data, String status) {
        _id = Long.parseLong(id);
        this.text = text;
        dueDate =  data.equals("null") ? null : data;
        done = status.equals("0")? false: true ;
        this.nivel = Integer.parseInt(nivel);
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
