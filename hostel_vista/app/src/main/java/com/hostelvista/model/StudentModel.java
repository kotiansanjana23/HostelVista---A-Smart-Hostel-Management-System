package com.hostelvista.model;

public class StudentModel {
    private String fullName;
    private String email;
    private String program;
    private String admissionDate;

    public StudentModel(String fullName, String email, String program, String admissionDate) {
        this.fullName = fullName;
        this.email = email;
        this.program = program;
        this.admissionDate = admissionDate;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getProgram() { return program; }
    public String getAdmissionDate() { return admissionDate; }
}
