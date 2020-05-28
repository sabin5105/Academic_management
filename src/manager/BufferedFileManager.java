package manager;

import vo.*;
import java.util.*;
import java.io.*;

public class BufferedFileManager implements Manager{

    ArrayList<Person> persons;

    //Default
    public BufferedFileManager() {
        File file = new File("persons.dat");
        if (file.exists()) {
            persons = loadFromFile();
        }else {
            persons = new ArrayList<Person>();
            saveToFile(persons);
        }
    }


    @Override
    public boolean Registrate(Person newPerson) {
        persons = loadFromFile();
        for(Person person:persons){
            if(person.getID_number() == newPerson.getID_number()){
                return false;
            }
        }
        persons.add(newPerson);
        saveToFile(persons);
        return true;
    }

    @Override
    public boolean Delete(int ID_number) {
        persons = loadFromFile();
        for(Person person:persons){
            if(person.getID_number() == ID_number){
                persons.remove(person);
                saveToFile(persons);
                return true;
            }
        }
        return false;
    }

    @Override
    public Person inquire(int ID_number) {
        persons = loadFromFile();
        for(Person person:persons){
            if(person.getID_number() == ID_number){
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

    public ArrayList loadFromFile() {
        BufferedReader in = null;
        ArrayList<Person> result = new ArrayList<Person>();
        try{
            in = new BufferedReader(new FileReader("persons.dat"));
            String line = "";
            while((line = in.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line, "|");
                String name = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                int ID_number = Integer.parseInt(st.nextToken());
                String address = st.nextToken();
                String additionalInfo = st.nextToken();
                String occupation = st.nextToken();

                switch (occupation){
                    case "student":
                        result.add(new Student(ID_number,name,age,address, additionalInfo));
                        break;
                    case "Prof":
                        result.add(new Prof(ID_number,name,age,address,additionalInfo));
                        break;
                    case "Employee":
                        result.add(new Employee(ID_number,name,age,address,additionalInfo));
                        break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void saveToFile(ArrayList<Person> persons) {
        PrintWriter out = null;
        try{
            out = new PrintWriter(new BufferedWriter(new FileWriter("persons.dat")));
            for(Person person:persons){
                out.println(person.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }
}
