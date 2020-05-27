package vo;
//Person 클래스
//주민번호(ID_number), 이름(name), 나이(age), 주소(address)

public class Person {
    private int ID_number;
    private String name;
    private int age;
    private String address;

    public Person(){}   //default

    public Person(int ID_number, String name, int age, String address){
        this.ID_number = ID_number;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getID_number() {
        return ID_number;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getAddress(){
        return address;
    }

    //파일에 저장 할 때 형식을 맞추기 위해 '|'를 구분자로 사용하여 모든 객체를 String 객체로 변환하여 반환한다.
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s", ID_number, name, age, address);
    }

}
