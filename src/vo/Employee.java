package vo;
// Employee 클래스
// 부서(Department)
public class Employee extends Person{
    private String Department;

    public Employee(){
        super();
    }
    public Employee(int ID_number, String name, int age, String address, String Department){
       super(ID_number, name, age, address);
       this.Department = Department;
    }

    public String getDepartment() {
        return Department;
    }

    @Override
    public String toString(){
        return super.toString() + "|" + Department;
    }
}
