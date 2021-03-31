package lesson5;

public class Employee {
    private String name;
    private int age;
    private String position;
    private String email;
    private String phonenumber;
    private int salary;

    public Employee(String name, int age, String position, String email, String phonenumber, int salary) {
        this.name = name;
        this.age  = age;
        this.position = position;
        this.email = email;
        this.phonenumber = phonenumber;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void showEmployee(){
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Position: " + this.position);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phonenumber);
        System.out.println("Salary: " + this.salary);
        System.out.println();
    }

    public String getEmployeeOver40() {
        return getAge() > 40 ? this.name : null;
    }
}

