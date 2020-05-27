package Manager;
import java.util.*;
import vo.Person;
// Person 객체와 통신
public interface Manager {
    public boolean Registrate(Person newPerson);
    public boolean Delete(int ID_number);
    public Person inquire(int ID_number);
    public ArrayList Printall();
}
