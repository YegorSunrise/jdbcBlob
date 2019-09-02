package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Model implements Serializable {

    private int id;

    private String name;

    private LocalDate date;

    private boolean isActive;

    private byte[] someBytes;

    public Model(int id, String name, LocalDate date, boolean isActive, byte[] someBytes) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.isActive = isActive;
        this.someBytes = someBytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public byte[] getSomeBytes() {
        return someBytes;
    }

    public void setSomeBytes(byte[] someBytes) {
        this.someBytes = someBytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isActive=" + isActive +
                '}';
    }
}
