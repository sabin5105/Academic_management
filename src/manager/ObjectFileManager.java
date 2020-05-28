package manager;
import vo.*;
import java.util.*;
import java.io.*;

public class ObjectFileManager implements Manager{

    ArrayList<Person> persons;

    public ObjectFileManager() {
        File file = new File("persons.dat");
        if (file.exists()) {
            persons = loadFromFile();
        } else {
            persons = new ArrayList();
            saveToFile(persons);
        }
    }

    @Override
    public boolean Registrate(Person newPerson) {
        persons = loadFromFile();
        for (Person person : persons) {
            if (person.getID_number() == newPerson.getID_number()) {
                return false;
            }
        }
        persons.add(newPerson);
        saveToFile(persons);
        return true;
    }

    @Override
    public boolean Delete(int personId) {
        persons = loadFromFile();
        for (Person person : persons) {
            if (person.getID_number() == personId) {
                persons.remove(person);
                saveToFile(persons);
                return true;
            }
        }
        return false;
    }

    @Override
    public Person inquire(int personId) {
        persons = loadFromFile();
        for (Person person : persons) {
            if (person.getID_number() == personId) {
                return person;
            }
        }
        return null;
    }

    @Override
    public ArrayList Printall() {
        persons = loadFromFile();
        return persons;
    }

    public void saveToFile(ArrayList persons) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("persons.dat"));
            out.writeObject(persons);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList loadFromFile() {
        ObjectInputStream in = null;
        ArrayList result = null;

        try {
            in = new ObjectInputStream(new FileInputStream("persons.dat"));
            result = (ArrayList) in.readObject();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } // inner try
        } // outer try
        return null;
    }
}
