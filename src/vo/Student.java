package vo;
//Student 클래스
//학번(Stud_id)
public class Student extends Person{
    private String Stud_id;

    public Student(){
        super();
    }  //default

    public Student(int ID_number, String name, int age, String address,String Stud_id){
        super(ID_number, name, age, address);
        this.Stud_id = Stud_id;
    }

    public String getStud_id(){
        return Stud_id;
    }
    @Override
    public String toString(){
        return super.toString() + "|" + Stud_id;
    }
}
