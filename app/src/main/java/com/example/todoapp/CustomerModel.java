package com.example.todoapp;

public class CustomerModel {
    private int id;
    private String name;
    private String phoneNumber;
    private String description;
    private boolean isActive;

    // конструктори

    public CustomerModel(int id, String name, String phoneNumber, String description, boolean isActive) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.isActive = isActive;
    }

    public CustomerModel() {
    }
// toString е необходим за да се принтират потребители

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    //гетъри и сетъри
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
