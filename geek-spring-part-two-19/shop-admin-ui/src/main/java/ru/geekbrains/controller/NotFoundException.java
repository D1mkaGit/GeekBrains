package ru.geekbrains.controller;

public class NotFoundException extends RuntimeException {

    private String notFoundEntity;
    private int notFoundId;

    public NotFoundException() {
    }

    public NotFoundException(String notFoundEntity) {
        this.notFoundEntity = notFoundEntity;
    }

    public NotFoundException(String notFoundEntity, int notFoundId) {
        this.notFoundEntity = notFoundEntity;
        this.notFoundId = notFoundId;
    }

    public String getNotFoundEntity() {
        return notFoundEntity;
    }

    public void setNotFoundEntity(String notFoundEntity) {
        this.notFoundEntity = notFoundEntity;
    }

    public int getNotFoundId() {
        return notFoundId;
    }

    public void setNotFoundId(int notFoundId) {
        this.notFoundId = notFoundId;
    }
}
