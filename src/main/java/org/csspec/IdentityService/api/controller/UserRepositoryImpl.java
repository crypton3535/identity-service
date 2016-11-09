package org.csspec.IdentityService.api.controller;

import javafx.util.Pair;
import org.csspec.IdentityService.api.repo.CourseRepository;
import org.csspec.IdentityService.api.repo.UserOperations;
import org.csspec.IdentityService.db.CoursesOpted;
import org.csspec.IdentityService.db.Faculty;
import org.csspec.IdentityService.db.Student;
import org.csspec.IdentityService.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserRepositoryImpl implements UserOperations {
    @Autowired
    private MongoOperations mongo;
    private CourseRepository repo;

    public Pair<User, Faculty> findSpecificFaculty(String userId) {
        List<User> tempUser = mongo.find(Query.query(Criteria.where("userId").is(userId)), User.class);
        List<Faculty> tempFaculty = mongo.find(Query.query(Criteria.where("userId").is(userId)), Faculty.class);
        if (tempFaculty.size() == 0) {
            return new Pair<User, Faculty>(tempUser.get(0), new Faculty());
        } else {
            return new Pair<User, Faculty>(tempUser.get(0), tempFaculty.get(0));
        }
    }

    public Pair<User, Student> findSpecificStudent(String userId) {
        List<User> tempUser = mongo.find(Query.query(Criteria.where("userId").is(userId)), User.class);
        List<Student> tempStudent = mongo.find(Query.query(Criteria.where("userId").is(userId)), Student.class);
        if (tempStudent.size() == 0) {
            return new Pair<User, Student>(tempUser.get(0), new Student());
        } else {
            return new Pair<User, Student>(tempUser.get(0), tempStudent.get(0));
        }
    }

    public String[] findStudentsOfCourse(String courseId) {
        List<CoursesOpted> tempList = mongo.find(Query.query(Criteria.where("courseId").is(courseId)), CoursesOpted.class);
        String[] answerList = new String[tempList.size()];
        System.out.println(tempList);
        for (int i = 0; i < tempList.size(); i++) {
            answerList[i] = tempList.get(i).getStudentId();
        }
        return answerList;
    }

    public ResponseEntity<?> findSpecificTeacherSpecial(String userId, String courseId) {
        CoursesOpted tempVariable = mongo.findOne(Query.query(Criteria.where("courseId").is(courseId).andOperator(Criteria.where("studentId").is(userId))), CoursesOpted.class);
        //  System.out.println(tempVariable);
        if (tempVariable == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(mongo.findOne(Query.query(Criteria.where("userId").is(tempVariable.getTeacherId())), User.class), HttpStatus.OK);
    }

}
