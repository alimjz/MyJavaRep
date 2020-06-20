package com.clinic.royan.Model;

public class Users {
    private int emp_id;
    private String emp_Account;
    private String emp_Name;
    private String emp_Lname;
    private String emp_email;
    private String emp_password;

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public Users(String emp_Account, String emp_password) {
        this.emp_Account = emp_Account;
        this.emp_password = emp_password;
    }

    public Users(String emp_Account, String emp_Name, String emp_Lname, String emp_email, String emp_password) {
        this.emp_Account = emp_Account;
        this.emp_Name = emp_Name;
        this.emp_Lname = emp_Lname;
        this.emp_email = emp_email;
        this.emp_password = emp_password;
    }

    public Users(int emp_id, String emp_Account, String emp_Name, String emp_Lname, String emp_email, String emp_password) {
        this.emp_id = emp_id;
        this.emp_Account = emp_Account;
        this.emp_Name = emp_Name;
        this.emp_Lname = emp_Lname;
        this.emp_email = emp_email;
        this.emp_password = emp_password;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_Account() {
        return emp_Account;
    }

    public void setEmp_Account(String emp_Account) {
        this.emp_Account = emp_Account;
    }

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getEmp_Lname() {
        return emp_Lname;
    }

    public void setEmp_Lname(String emp_Lname) {
        this.emp_Lname = emp_Lname;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

}
