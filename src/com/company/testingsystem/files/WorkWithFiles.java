package com.company.testingsystem.files;

import com.company.testingsystem.db.Database;
import com.company.testingsystem.models.Question;

import java.io.*;
import java.util.*;

public class WorkWithFiles {
    static final File BASE_FOLDER = new File("src/com/company/testingsystem/docs");
    public static void writeToSubject(String subject) {
        File file = new File(BASE_FOLDER, "subjects.txt");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.println(subject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromSubject() {
        File file = new File(BASE_FOLDER, "subjects.txt");
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()){
                String subject = scanner.nextLine();
                Database.questionMap.put(subject, new ArrayList<>());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        readFromQuestion();

    }
    public static void readFromQuestion() {
        File file = new File(BASE_FOLDER, "questions");
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.equals("***")) {
                    String idString = scanner.nextLine();
                    String subject = scanner.nextLine();
                    String desc = scanner.nextLine();
                    String ca = scanner.nextLine();
                    String wa1 = scanner.nextLine();
                    String wa2 = scanner.nextLine();
                    Question question = new Question(UUID.fromString(idString), desc,
                            new HashSet<>(Set.of(ca, wa2, wa1)), ca);
                    Database.questionMap.get(subject).add(question);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeToQuestion(String subject, Question question) {
        File file = new File(BASE_FOLDER, "questions");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.println("***");
            printWriter.println(question.getId().toString());
            printWriter.println(subject);
            printWriter.println(question.getDescription());
            printWriter.println(question.getCorrectAnswer());
            for (String variant : question.getVariants()) {
                if (!variant.equals(question.getCorrectAnswer())){
                    printWriter.println(variant);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
