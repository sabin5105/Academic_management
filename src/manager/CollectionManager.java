package manager;
import vo.Person;
import java.util.*;

//Arraylist 이용 Manger
public class CollectionManager implements Manager{
    ArrayList<Person> persons = new ArrayList<Person>();

    public CollectionManager(){};   //Default

    @Override
    public boolean Registrate(Person newPerson) {
        for (Person person : persons) {
            if (person.getID_number() == newPerson.getID_number()) {
                return false;
            }
        }
        persons.add(newPerson);
        return true;
    }

    public boolean Delete(int ID_number) {
        for (Person person : persons) {
            if (person.getID_number() == ID_number) {
                persons.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public Person inquire(int ID_number) {
        for (Person person : persons) {
            if (person.getID_number() == ID_number) {
                return person;
            }
        }
        return null;
    }

    @Override
    public ArrayList Printall() {
        return persons;
    }



}
