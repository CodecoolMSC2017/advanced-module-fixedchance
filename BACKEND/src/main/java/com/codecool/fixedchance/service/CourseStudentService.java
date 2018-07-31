package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.CourseStudent;
import com.codecool.fixedchance.domain.CourseStudentIdentity;
import org.springframework.stereotype.Component;

@Component
public class CourseStudentService extends AbstractService {

    public void add(CourseStudent courseStudent) {
        CourseStudentIdentity csi = new CourseStudentIdentity();
        csi.setCourseId(courseStudent.getCourseId());
        csi.setStudentId(courseStudent.getStudentId());
        courseStudent.setCourseStudentIdentity(csi);
        courseStudentRepository.save(courseStudent);
    }
}
