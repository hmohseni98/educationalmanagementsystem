import Entity.Student;
import Entity.University;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static University university = new University();

    public static void main(String[] args) {
        welcomeMenu();
    }

    public static void welcomeMenu() {
        System.out.println("default login ---> employee - admin - admin");
        System.out.println("1:Sign In");
        System.out.println("2:Sign Up");
        System.out.print("select one option:");
        int input = scanner.nextInt();
        if (input == 1)
            loginMenu();
        else if (input == 2)
            signupMenu();
    }

    public static void signupMenu() {
        System.out.print("type(student - teacher - employee):");
        String type = scanner.next();
        System.out.print("username:");
        String username = scanner.next();
        System.out.print("password:");
        String password = scanner.next();
        university.addUser(type, username, password);
        welcomeMenu();
    }

    public static void loginMenu() {
        System.out.print("type(student - teacher - employee):");
        String type = scanner.next();
        System.out.print("Enter username:");
        String username = scanner.next();
        System.out.print("Enter password:");
        String password = scanner.next();
        String result;
        result = checkUser(type, username, password);
        if (result.equals("employee"))
            employeeMenu();
        else if (result.equals("teacher"))
            teacherMenu();
        else if (result.equals("student"))
            studentMenu();
    }

    static String checkUser(String type, String username, String password) {
        boolean isLogin = university.checkUserLogin(type, username, password);
        if (isLogin == true && type.equals("employee"))
            return "employee";
        else if (isLogin == true && type.equals("teacher"))
            return "teacher";
        else if (isLogin == true && type.equals("student"))
            return "student";
        else
            return "false";
    }

    static void employeeMenu() {
        System.out.println("select one option:");
        System.out.println("student:");
        System.out.println(
                "          add student(student add nationalCode firstName Lastname)" + "\n" +
                        "          edit student(student edit nationalCode newFirstName NewLastName)" + "\n" +
                        "          remove student(student remove nationalCode)"
        );
        System.out.println("teacher:");
        System.out.println(
                "          add teacher(teacher add nationalCode firstName Lastname typeOfEmployment('S' For science Committee Or 'T' For Tuition )" + "\n" +
                        "          edit teacher(teacher edit nationalCode newFirstName newLastName newTypeOfEmployment)" + "\n" +
                        "          add presenting lesson(presenting year term lessonName teacherNationalCode)" + "\n" +
                        "          remove teacher(teacher remove nationalCode)"
        );
        System.out.println("employee:");
        System.out.println(
                "          add employee(employee add nationalCode firstName Lastname)" + "\n" +
                        "          edit employee(employee edit nationalCode newFirstName newLastName)" + "\n" +
                        "          remove employee(employee remove nationalCode)"
        );
        System.out.println("lesson:");
        System.out.println(
                "          add lesson(lesson add lessonName numberOfUnit)" + "\n" +
                        "          show lesson(lesson show)" + "\n" +
                        "          edit lesson(lesson edit lessonId newlessonName newNumberOfNumt)" + "\n" +
                        "          remove lesson(lesson  remove lessonName)"
        );
        System.out.println("salary:");
        System.out.println(
                "          show salary(salary nationalCode)" + "\n"
        );
        System.out.println("exit");
        System.out.println(
                "          exit" + "\n"
        );
        System.out.println();
        System.out.print("input command:");
        String command = scanner.nextLine().trim();
        String[] commandList = command.split(" ");
        if (commandList[0].equals("student")) {
            if (commandList[1].equals("add"))
                university.addStudent(commandList[2], commandList[3], commandList[4]);
            else if (commandList[1].equals("edit"))
                university.editStudent(commandList[2], commandList[3], commandList[4]);
            else if (commandList[1].equals("remove"))
                university.removeStudent(commandList[2]);
            else
                System.out.println("Invalid parameter:" + commandList[1]);
        } else if (commandList[0].equals("teacher")) {
            if (commandList[1].equals("add"))
                university.addTeacher(commandList[2], commandList[3], commandList[4], commandList[5]);
            else if (commandList[1].equals("edit"))
                university.editTeacher(commandList[2], commandList[3], commandList[4], commandList[5]);
            else if (commandList[1].equals("remove"))
                university.removeTeacher(commandList[2]);
            else
                System.out.println("Invalid parameter:" + commandList[1]);
        } else if (commandList[0].equals("employee")) {
            if (commandList[1].equals("add"))
                university.addEmployee(commandList[2], commandList[3], commandList[4]);
            else if (commandList[1].equals("edit"))
                university.editEmployee(commandList[2], commandList[3], commandList[4]);
            else if (commandList[1].equals("remove"))
                university.removeEmployee(commandList[2]);
            else
                System.out.println("Invalid parameter:" + commandList[1]);
        } else if (commandList[0].equals("lesson")) {
            if (commandList[1].equals("add"))
                university.addLesson(commandList[2], Integer.valueOf(commandList[3]));
            else if (commandList[1].equals("show"))
                university.showLessons();
            else if (commandList[1].equals("edit"))
                university.editLesson(commandList[2], commandList[3], Integer.valueOf(commandList[4]));
            else if (commandList[1].equals("remove"))
                university.removeLesson(commandList[2]);
            else
                System.out.println("Invalid parameter:" + commandList[1]);
        } else if (commandList[0].equals("presenting"))
            university.addPresentingLessons(commandList[1], commandList[2], commandList[3], commandList[4]);
        else if (commandList[0].equals("salary")) {
            System.out.println(university.calcEmployeeSalary(commandList[1]));
        } else if (commandList[0].equals("exit")) {
            welcomeMenu();
        }
    }

    public static void studentMenu() {
        System.out.println("select one option:");
        System.out.println("show information(student nationalCode):" + "\n" +
                "show lesson list(lesson show)" + "\n" +
                "select unit(select year term nationalCode lessonName)" + "\n" +
                "show take a lesson(lesson take nationalCode)" + "\n" +
                "exit");
        System.out.println();
        System.out.print("input command:");
        scanner.next();
        String command = scanner.nextLine().trim();
        String[] commandList = command.split(" ");
        if (commandList[0].equals("student")) {
            university.showStudent(commandList[1]);
        } else if (commandList[0].equals("lesson")) {
            if (commandList[1].equals("show"))
                university.showLessons();
            else if (commandList[1].equals("take"))
                university.searchByNationalCodeForLessonList(commandList[2]);
        } else if (commandList[0].equals("select"))
            university.addSelectUnit(commandList[1], commandList[2], commandList[3], commandList[4]);
        else if (commandList[0].equals("exit"))
            welcomeMenu();
        else {
            System.out.println("Invalid parameter");
            studentMenu();
        }
    }

    public static void teacherMenu() {
        System.out.println("select one option:");
        System.out.println("show information(teacher nationalCode):" + "\n" +
                "input score(submit year term lessonName nationalCodeStudent score)" + "\n" +
                "show salary(salary nationalCode year term typeOfEmployment('S' For science Committee Or 'T' For Tuition))" + "\n" +
                "exit");
        System.out.println();
        System.out.print("input command:");
        String command = scanner.nextLine().trim();
        String[] commandList = command.split(" ");
        if (commandList[0].equals("teacher")) {
            university.showTeacher(commandList[1]);
        } else if (commandList[0].equals("submit")) {
            university.addLessonScores(commandList[1], commandList[2], commandList[3], commandList[4], Float.valueOf(commandList[5]));
        } else if (commandList[0].equals("salary")) {
            if (commandList[4].equals("S") || commandList[4].equals("s"))
                System.out.println(university.calcScienceCommitteeSalary(commandList[1], commandList[2], commandList[3]));
            else if (commandList[4].equals("T") || commandList[4].equals("t"))
                System.out.println(university.calcTuitionSalary(commandList[1], commandList[2], commandList[3]));
        } else if (commandList[0].equals("exit"))
            welcomeMenu();
        else {
            System.out.println("Invalid parameter");

        }
    }
}