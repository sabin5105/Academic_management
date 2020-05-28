package UI.TUI;

import java.util.*;
import manager.*;
import vo.*;

public class TUI {
    private String doubleLine = "=========================================================";
    private String singleLine = "---------------------------------------------------------";
    private String listFormat = "%10s%10s%10s%20s%20s\n";

    Scanner scanner = new Scanner(System.in);
    Manager manager = new CollectionManager();  // Manager Package에서 원하는 동작 선택

    public TUI( ) {
        while (true) {
            printTitle();
            printSubTitle("메 인 메 뉴");
            int mainTask = inputFromList(new String[] {"프로그램 종료", "등록", "삭제", "조회", "전체 조회"});
            switch (mainTask) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Registrate();
                    break;
                case 2:
                    Delete();
                    break;
                case 3:
                    inquire();
                    break;
                case 4:
                    Printall();
                    break;
                default :
                    System.out.println("Please enter properly");
                    break;
            }
        }
    }
    /**
     * 등록
     * 직업, 이름, 나이, 주소, 주민번호, 교과목, 부서를 항목에 따라 입력한다.
     * 등록할 수 없을 경우 오류 메세지를 출력한다.
     */
    public void Registrate(){
        while(true){
            printTitle();
            printSubTitle("직 업 선 택");
            int occupation = inputFromList(new String[] {"이전메뉴", "학생", "교수", "임직원"});
            if (occupation == 0) {
                return;
            }

            Person newPerson = null;

            String name = inputString(" 이름 : ");
            int age = inputInt(" 나이 :");
            String address = inputString(" 주소 : ");
            int ID_number = inputInt(" 주민번호 :");

            String additionalInfo = "";

            switch (occupation) {
                case 1:
                    additionalInfo = inputString(" 학번 : ");
                    newPerson = new Student(ID_number, name, age, address, additionalInfo);
                    break;
                case 2:
                    additionalInfo = inputString(" 교과목 : ");
                    newPerson = new Prof(ID_number, name, age, address, additionalInfo);
                    break;
                case 3:
                    additionalInfo = inputString(" 부서 : ");
                    newPerson = new Employee(ID_number, name, age, address, additionalInfo);
                    break;
            } // switch

            if (manager.Registrate(newPerson)) {
                printSystemMessage("등록되었습니다.", true);
            } else {
                printSystemMessage("등록할 수 없습니다.", true);
            }
        }
    }//Registration
    /**
     * 삭제
     * 입력된 주민번호로 해당되는 사람의 정보를 삭제한다.
     * 해당하는 ID가 없을 경우 오류 메세지를 출력한다.
     */
    public void Delete() {
        while (true) {
            printTitle();
            printSubTitle(" 삭 제 하 기");
            int ID_number = inputInt(" 삭제할 사람의 주민번호를 입력하여 주십시오. (0 : 이전메뉴) ");
            if (ID_number == 0) {
                return;
            }

            if (manager.Delete(ID_number)) {
                printSystemMessage("삭제되었습니다.", true);
            } else {
                printSystemMessage("존재하는 ID가 없습니다.", true);
            }
        }
    } // Delete()

    /**
     * 조회
     * 입력된 주민번호로 해당되는 사람의 정보를 출력한다.
     * 해당되는 사람이 없을 경우 오류 메세지를 출력한다.
     */
    public void inquire() {
        printTitle();
        printSubTitle(" 조 회 하 기");
        int ID_number = inputInt(" 조회할 사람의 주민번호를 입력하여 주십시오. (0 : 이전메뉴) ");
        if (ID_number == 0) {
            return;
        }

        Person person = manager.inquire(ID_number);
        if (person != null) {
            System.out.println(singleLine);
            System.out.printf(listFormat, "Name", "ID N.O", "Age", "Address", "AdditionalInfo");
            System.out.println(singleLine);

            System.out.println(personToString(person));

            printSystemMessage("조회된 데이터 입니다.", true);
        } else {
            printSystemMessage("존재하는 ID가 없습니다.", true);
        }

    } // selectPerson()

    /**
     * 등록된 모든 사람을 출력한다.
     */
    public void Printall() {
        printTitle();
        printSubTitle(" 전 체 조 회");

        ArrayList<Person> persons = manager.Printall();

        System.out.println(singleLine);
        System.out.printf(listFormat, "Name", "ID N.O", "Age", "Address", "AdditionalInfo");
        System.out.println(singleLine);

        for (Person person : persons) {
            System.out.println(personToString(person));
        }
        printSystemMessage("조회된 데이터 입니다.", true);
    } // PrintAll()

    /**
     * 리스트로 된 질문의 답을 입력받는다.
     * @param lists 질문 리스트
     * @return 답변
     */
    public int inputFromList(String[] lists) {
        int result = 0;
        for (int i = 0; i < lists.length; i++) {
            System.out.printf(" %d. %s\n", i, lists[i]);
        }
        System.out.println(singleLine);
        while (true) {
            result = inputInt("무엇을 하시겠습니까 ?");
            if (result > lists.length-1) {
                printSystemMessage("잘못된 번호입니다.");
                continue;
            }
            return result;
        }

    }

    /**
     * Person 객체를 String포맷에 맞도록 출력한다. (포맷은 listFormat이다.)
     * Person.toString()은 각각의 정보가 "|"를 구분자로 하여 리턴한다.
     * @param person 출력할 객체
     * @return listFormat에 맞춘 출력물
     */
    public String personToString(Person person) {
        StringTokenizer st = new StringTokenizer(person.toString(), "|");
        return String.format(listFormat, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
    }

    /**
     * 숫자를 입력받는다.
     * 숫자가 아닐 경우 InputMismatchException을 예외처리하여 재입력 받는다.
     * @param inputMessage 입력 받을 시 출력할 메세지
     * @return 입력된 값
     */
    public int inputInt(String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage + " ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                printSystemMessage("숫자를 입력하여 주십시오.");
                scanner.next();
            } // try
        } // while
    } // inputInt()

    /**
     * 문자열을 입력받는다.
     * @param inputMessage 입력 시 출력할 메세지
     * @return 입력받은 값
     */
    public String inputString(String inputMessage) {
        System.out.print(inputMessage);
        return scanner.next();
    } // inputString()

    /**
     * 제목을 출력한다.
     */
    public void printTitle() {
        clearScreen();
        System.out.println(doubleLine);
        System.out.println(" 학 사 관 리 ");
        System.out.println(doubleLine);
    } // printTitle()

    /**
     * 서브 제목을 출력한다.
     * @param subTitle
     */
    public void printSubTitle(String subTitle) {
        System.out.println(singleLine);
        System.out.println(" " + subTitle);
        System.out.println(singleLine);
    }

    /**
     * 시스템 메세지를 출력한다.
     * 오버로딩 : isPause가 true일 경우 입력 대기 상태에서 기다린다.
     * @param message
     */
    public void printSystemMessage(String message) {
        System.out.println(" System : " + message);
    }
    public void printSystemMessage(String message, boolean isPause) {
        System.out.println(singleLine);
        printSystemMessage(message);
        System.out.println(singleLine);
        if (isPause) {
            System.out.println(" 계속하시려면 아무거나 입력하여 주십시오.");
            //scanner.skip("[\\n\\r]");
            scanner.next();
            //System.out.println("DEBUG @22222");
        }
    }

    // clearScreen
    public void clearScreen() {
        for (int i = 0; i < 50; i ++) {
            System.out.println();
        }
    }

}
