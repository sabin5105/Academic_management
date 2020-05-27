package vo;
//Professor 클래스
//강의(Course)
public class Prof extends Person{
    private String Course;

    public Prof(){  //default
        super();
    }
    public Prof(int ID_number, String name, int age, String address, String Course){
        super(ID_number, name, age, address);
        this.Course = Course;
    }

    public String getCourse() {
        return Course;
    }

    @Override
    public String toString(){
        return super.toString() + "|" + Course;
    }
}
