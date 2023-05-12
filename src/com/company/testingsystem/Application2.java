package com.company.testingsystem;

import com.company.testingsystem.db.Database;
import com.company.testingsystem.dto.QuestionDTO;
import com.company.testingsystem.dto.Response;
import com.company.testingsystem.enums.Role;
import com.company.testingsystem.models.Question;
import com.company.testingsystem.models.User;
import com.company.testingsystem.service.QuestionService;
import com.company.testingsystem.service.SubjectService;
import com.company.testingsystem.service.UserService;
import com.company.testingsystem.utils.ScannerUtil;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogRecord;
import java.util.stream.Stream;


public class Application2 {
    static {
        Database.loadData();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(
                    """
                            1) Login
                            2) Registration
                            0) Exit
                                """
            );
            String option = ScannerUtil.SCANNER_STR.nextLine();
            if(option.equals("0")) break;
            switch (option) {

                case "1" -> login();
                case "2" -> register();

            }

        }

    }


    private static void register() {
        System.out.println("Enter the ");
    }

    private static void login() {












        try {
            System.out.println("username:  ");
            String username = ScannerUtil.SCANNER_STR.nextLine();
            System.out.println("password:  ");
            String password = ScannerUtil.SCANNER_STR.nextLine();
            UserService userService = new UserService();
            User sessionUser = userService.login(username, password);
            if (sessionUser == null) {
                System.out.println("User not found");
            } else {
                cabinet(sessionUser);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private static void cabinet(User sessionUser) {
        System.out.println("Welcome " + sessionUser.getFullName());
        if (sessionUser.getRole().equals(Role.TEACHER)) {
            teacherPage(sessionUser);
        } else {
            studentPage(sessionUser);
        }
    }
    private static void studentPage(User sessionUser) {
        while(true){
            System.out.println("""
                    1) Test performance
                    2) Show test history
                    3) Change password
                    4) Exit             
                    """);
            String option = ScannerUtil.SCANNER_STR.nextLine();
            if(option.equals("0")) break;
            switch (option){
                case "1"-> testPerformance();
                case "2"-> showQuestionList();
                case "3"-> changePassword(sessionUser);
            }
        }
    }

    private static void testPerformance() {
        System.out.println("Enter subject");
        String subject_name = ScannerUtil.SCANNER_STR.nextLine();
        if (subject_name.equals("MATH")){

        }


    }

    private static void teacherPage(User sessionUser) {
        while (true) {
            System.out.println("""
                    1) Add subject
                    2) Show subject list
                    3) Add question
                    4) Show question list
                    5) Show user list
                    6) Change password
                    0) Log out            
                     """);
            String option = ScannerUtil.SCANNER_STR.nextLine();
            if(option.equals("0")) break;
            switch (option){
                case "1"-> addSubject();
                case "2"-> showSubjectList();
                case "3"-> addQuestion();
                case "4"-> showQuestionList();
                case "5"-> showUserList();
                case "6"-> changePassword(sessionUser);
            }
        }
    }
    private static void changePassword(User sessionUser) {
        while (true) {
            System.out.println("Enter old password");
            String password = ScannerUtil.SCANNER_STR.nextLine();
            if (!password.equals(sessionUser.getPassword())) {
                System.out.println("Incorrect password");
                break;
            }
             else {
                System.out.println("Enter new password");
                String newPassword = ScannerUtil.SCANNER_STR.nextLine();
                newPassword= sessionUser.getPassword();
                System.out.println("Password changed successfully");
                break;
            }
        }
    }
    private static void showUserList() {
        Stream<User> sorted = Database.solveMap.keySet().stream().sorted();
        sorted.forEach(System.out::println);
    }

    private static void showQuestionList() {
        Set<String> subjects = Database.questionMap.keySet();
        if (subjects.isEmpty()) {
            System.out.println("No questions");
        } else {
            subjects.forEach(System.out::println);
            System.out.println("Enter subject name");
            String subject = ScannerUtil.SCANNER_STR.nextLine();
            subject=subject.trim().toUpperCase();
            if (subjects.contains(subject)) {

                List<Question> questions = Database.questionMap.get(subject);
                if(questions.isEmpty()){
                    System.out.println("No questions by "+ subject);
                }
                questions.forEach(System.out::println);
            }
            else {
                System.out.println("No that subject");
            }
        }

    }

    private static void showSubjectList() {

        Set<String> subjects = Database.questionMap.keySet();
        if (subjects.isEmpty()) {
            System.out.println("No subjects");
        } else{
            subjects.forEach(System.out::println);
        }
    }

    private static void addSubject() {
        System.out.println("Enter subject name ");
        String subject = ScannerUtil.SCANNER_STR.nextLine();
        SubjectService subjectService = new SubjectService();
        Response response = subjectService.add(subject);
        System.out.println(response);


    }

    private static void addQuestion() {
        Set<String> subjects = Database.questionMap.keySet();
        if (subjects.isEmpty()) {
            System.out.println("No subjects");
            return;
        }

        subjects.forEach(System.out::println);
        System.out.print("Enter subject name: ");
        String subject = ScannerUtil.SCANNER_STR.nextLine();
        subject = subject.trim().toUpperCase();

        if (!subjects.contains(subject)) {
            System.out.println("No that subject");
            return;
        }
        System.out.println("Enter description");
        String description = ScannerUtil.SCANNER_STR.nextLine();
        System.out.println("Enter correct answer");
        String correctAnswer = ScannerUtil.SCANNER_STR.nextLine();
        System.out.println("Enter 1-wrong answer");
        String wa1 = ScannerUtil.SCANNER_STR.nextLine();
        System.out.println("Enter 2-wrong answer");
        String wa2 = ScannerUtil.SCANNER_STR.nextLine();

        QuestionService questionService=new QuestionService();
        QuestionDTO questionDTO=new QuestionDTO(subject,description,correctAnswer,wa1,wa2);
        Response response=questionService.add(questionDTO);
        System.out.println("response = " + response);

    }
}