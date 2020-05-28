package manager;
import java.util.*;
import vo.Person;

public class ArrayManager implements Manager{
    int index;
    Person[] persons;

    public ArrayManager(){} //Default

    public ArrayManager(int length){
        persons = new Person[length];
    }

    @Override
    public boolean Registrate(Person newPerson){    //등록
        if(persons.length == index){
            return false;
        }
        for(int i=0;i<index;i++){
            if(persons[i].getID_number() == newPerson.getID_number()){
                return false;
            }
        }
        persons[index] = newPerson;
        index++;
        return true;
    }

    @Override
    public boolean Delete(int ID_number) {  //삭제
        for(int i=0;i<index;i++){
            if(persons[i].getID_number() == ID_number){
                persons[i] = persons[i+1];
                index--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Person inquire(int ID_number) {
        for(int i=0;i<index;i++){
            if(persons[i].getID_number() == ID_number){
                return persons[i];
            }
        }
        return null;
    }

    @Override
    public ArrayList Printall() {
        ArrayList result = new ArrayList();
        for(int i=0;i<index;i++){
            result.add(persons[i]);
        }
        return result;
    }
}
