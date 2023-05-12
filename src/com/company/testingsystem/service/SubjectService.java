package com.company.testingsystem.service;

import com.company.testingsystem.db.Database;
import com.company.testingsystem.dto.Response;
import com.company.testingsystem.files.WorkWithFiles;

import java.util.ArrayList;

public class SubjectService {
    public Response add(String subjectName){
        subjectName=subjectName.trim().toUpperCase();
        if(Database.questionMap.containsKey(subjectName)){
            return new Response("This subjectName exists already",false);
        }
        Database.questionMap.put(subjectName,new ArrayList<>());

        WorkWithFiles.writeToSubject(subjectName);
        return new Response(subjectName+" added",true);
    }
}
