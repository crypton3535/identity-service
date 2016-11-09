package org.csspec.IdentityService.api.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.csspec.IdentityService.api.repo.CourseRepository;
import org.csspec.IdentityService.api.repo.CoursesOptedOperation;
import org.csspec.IdentityService.api.repo.CoursesOptedRepository;
import org.csspec.IdentityService.api.repo.UserRepository;
import org.csspec.IdentityService.db.Course;
import org.csspec.IdentityService.db.CoursesOpted;
import org.csspec.IdentityService.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoursesOptedRepositoryImpl implements CoursesOptedOperation {
    @Autowired
    private MongoOperations mongo;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursesOptedRepository coursesOptedRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Map<String, String>> findAllCourses(String userId) {
        List<CoursesOpted> answer = mongo.find(Query.query(Criteria.where("studentId").is(userId)), CoursesOpted.class);
        List<Map<String, String>> answerList = new ArrayList<>();
        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            Course tempCourse = courseRepository.findCourseByCourseId(answer.get(i).getCourseId());
            System.out.println(tempCourse);
            Map<String, String> map = new HashMap<>();
            map.put("CourseId", tempCourse.getCourseId());
            map.put("CourseName", tempCourse.getCourseName());
            String teacherId = coursesOptedRepository.findCoursesOptedByCourseIdAndStudentId(tempCourse.getCourseId(), userId).getTeacherId();
            map.put("TeacherId", teacherId);
            map.put("TeacherName", mongo.findOne(Query.query(Criteria.where("userId").is(teacherId)), User.class).getUserName());
            answerList.add(map);
        }
        return answerList;
    }

    public List<Course> findCoursesOfFaculty(String facultyId) {

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("teacherId", facultyId);
        List<Course> answerList = new ArrayList<>();
        DBCollection dBCollection = mongo.getCollection("coursesOpted");
        List id = dBCollection.distinct("courseId", dbObject);
        System.out.println(id);
        for (int i = 0; i < id.size(); i++) {
            answerList.add(courseRepository.findCourseByCourseId(id.get(i).toString()));
        }
        System.out.println(answerList);
        return answerList;
    }

    public List<User> findTeachersOfCourse(String courseId) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("courseId", courseId);
        List<User> answerList = new ArrayList<>();
        DBCollection dBCollection = mongo.getCollection("coursesOpted");
        List id = dBCollection.distinct("teacherId", dbObject);
        System.out.println(id);
        for (int i = 0; i < id.size(); i++) {
            answerList.add(userRepository.findUserByUserId(id.get(i).toString()));
        }
        return answerList;
    }
}
