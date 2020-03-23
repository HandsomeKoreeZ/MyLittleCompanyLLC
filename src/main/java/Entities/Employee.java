package Entities;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMP_NO"})})
public class Employee {
    private Long empID;
    private String empNO;
    private String empName;
    private String job;
    private Employee manager;
    private Date hireDate;
    private Float salary;
    private byte[] image;

    private Department department;
    private Set<Employee> employees = new HashSet<Employee>(0);

    public Employee(){}

    public Employee(Long empID, String empName, String job, Employee manager, Date hireDate, Float salary, Department department) {
        this.empID = empID;
        this.empNO = "E"+this.empID;
        this.empName = empName;
        this.job = job;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
        this.department = department;
    }

    @Id
    @Column(name = "EMP_ID")
    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    @Column(name = "EMP_NO", length = 20, nullable = false)
    public String getEmpNO() {
        return empNO;
    }

    public void setEmpNO(String empNO) {
        this.empNO = empNO;
    }

    @Column(name = "EMP_NAME",length = 50, nullable = false)
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Column(name = "JOB",length = 30,nullable = false)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MNG_ID")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Column(name = "HIRE_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Column(name = "SALARY", nullable = false)
    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Column(name = "IMAGE", length = 1111111, nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empID")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
