package Examples;

public class ShortEmpInfo {
    private Long empID;
    private String empNO;
    private String empName;

    public ShortEmpInfo(Long empID, String empNO, String empName) {
        this.empID = empID;
        this.empNO = empNO;
        this.empName = empName;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public String getEmpNO() {
        return empNO;
    }

    public void setEmpNO(String empNO) {
        this.empNO = empNO;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
