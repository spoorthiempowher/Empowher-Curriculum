package com.coursedataapi.repository;

import com.coursedataapi.repository.api.*;
import com.coursedataapi.repository.helpers.BindSpecHelper;
import com.coursedataapi.repository.helpers.PreDeleteHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class CourseUpdateRepositoryImpl implements CourseUpdateRepository {

    @Autowired
    private DatabaseClient dbClient;

    @Autowired
    private PreDeleteHelper preDeleteHelper;

    private static final String CANDIDATE_ID = "candidateId";
    private static final String CANDIDATE_NAME = "candidateName";
    private static final String MOBILE_NO = "mobileNo";
    private static final String EMAIL_ID = "emailId";
    private static final String CANDIDATE_REMARK = "candidateRemark";

    private static final String COURSE_ID = "courseId";
    private static final String COURSE_NAME = "courseName";
    private static final String COURSE_PRICE = "coursePrice";
    private static final String COURSE_REMARK = "courseRemark";

    private static final String INSERT_CANDIDATE_QUERY = """
                                                      INSERT INTO wiki.candidate("candidateId",
                                                             "candidateName",
                                                             "mobileNo",
                                                             "emailId",
                                                             "candidateRemark")
                                                      SELECT nextval('wiki."candidate_seq"'),
                                                             :candidateName,
                                                             :mobileNo,
                                                             :emailId,
                                                             :candidateRemark
                                                      RETURNING "candidateId"
                                                      """;

    private static final String INSERT_COURSE_QUERY = """
                                                      INSERT INTO wiki.course("courseId",
                                                             "courseName",
                                                             "coursePrice",
                                                             "courseRemark")
                                                      SELECT nextval('wiki."course_seq"'),
                                                             :courseName,
                                                             :coursePrice,
                                                             :courseRemark
                                                      RETURNING "courseId"
                                                      """;

    private static final String INSERT_CANDIDATE_QUERY_WITH_ID = """
                                                                 INSERT INTO wiki.candidate("candidateId",
                                                                        "candidateName",
                                                                        "mobileNo",
                                                                        "emailId",
                                                                        "candidateRemark")
                                                                 SELECT :candidateId,
                                                                        :candidateName,
                                                                        :mobileNo,
                                                                        :emailId,
                                                                        :candidateRemark
                                                                 """;

    private static final String INSERT_COURSE_QUERY_WITH_ID = """
                                                              INSERT INTO wiki.course("courseId",
                                                                     "courseName",
                                                                     "coursePrice",
                                                                     "courseRemark")
                                                              SELECT :courseId,
                                                                     :courseName,
                                                                     :coursePrice,
                                                                     :courseRemark
                                                              """;

    private static final String DELETE_CANDIDATE_QUERY = """
            DELETE FROM wiki.candidate where "candidateId" = :candidateId
            """;

    private static final String DELETE_COURSE_QUERY = """
            DELETE FROM wiki.course where "courseId" = :courseId
            """;

    // =================================================================================================================
    // Create: Candidate
    // =================================================================================================================

    @Modifying
    @Transactional
    public Mono<Long> insertCandidate(CandidateDO candidateDO) {

        return new BindSpecHelper(dbClient.sql(INSERT_CANDIDATE_QUERY))
                .bindIf(CANDIDATE_NAME, candidateDO.getCandidateName(), String.class)
                .bindIf(MOBILE_NO, candidateDO.getMobileNo(), String.class)
                .bindIf(EMAIL_ID, candidateDO.getEmailId(), String.class)
                .bindIf(CANDIDATE_REMARK, candidateDO.getCandidateRemark(), String.class)

                .fetch()
                .one()
                .mapNotNull(row -> row.get(CANDIDATE_ID))
                .cast(Integer.class)
                .map(Long::valueOf);
    }

    // =================================================================================================================
    // Create: Course
    // =================================================================================================================

    @Modifying
    @Transactional
    public Mono<Long> insertCourse(CourseDO courseDO) {

        return new BindSpecHelper(dbClient.sql(INSERT_COURSE_QUERY))
                .bindIf(COURSE_NAME, courseDO.getCourseName(), String.class)
                .bindIf(COURSE_PRICE, courseDO.getCoursePrice(), Long.class)
                .bindIf(COURSE_REMARK, courseDO.getCourseRemark(), String.class)

                .fetch()
                .one()
                .mapNotNull(row -> row.get(COURSE_ID))
                .cast(Integer.class)
                .map(Long::valueOf);
    }


    // =================================================================================================================
    // Update: Candidate
    // =================================================================================================================

    @Override
    public Mono<Void> insertCandidate(Long candidateId, CandidateDO candidateDO) {

        return new BindSpecHelper(dbClient.sql(INSERT_CANDIDATE_QUERY_WITH_ID))
                .bindIf(CANDIDATE_ID, candidateId, Long.class)
                .bindIf(CANDIDATE_NAME, candidateDO.getCandidateName(), String.class)
                .bindIf(MOBILE_NO, candidateDO.getMobileNo(), String.class)
                .bindIf(EMAIL_ID, candidateDO.getEmailId(), String.class)
                .bindIf(CANDIDATE_REMARK, candidateDO.getCandidateRemark(), String.class)

                .fetchAndValidateUpdatedRowCount("insert-candidate", 1);
    }

    // =================================================================================================================
    // Update: Course
    // =================================================================================================================

    @Override
    public Mono<Void> insertCourse(Long courseId, CourseDO courseDO) {

        return new BindSpecHelper(dbClient.sql(INSERT_COURSE_QUERY_WITH_ID))
                .bindIf(COURSE_ID, courseId, Long.class)
                .bindIf(COURSE_NAME, courseDO.getCourseName(), String.class)
                .bindIf(COURSE_PRICE, courseDO.getCoursePrice(), Long.class)
                .bindIf(COURSE_REMARK, courseDO.getCourseRemark(), String.class)

                .fetchAndValidateUpdatedRowCount("insert-course", 1);
    }

    // =================================================================================================================
    // Delete: Candidate
    // =================================================================================================================

    @Modifying
    @Transactional
    public Mono<Void> deleteCandidate(Long candidateId) {

        return preDeleteHelper.runPreDelete("candidate")
                .then(new BindSpecHelper(dbClient.sql(DELETE_CANDIDATE_QUERY))
                        .bindIf(CANDIDATE_ID, candidateId, Long.class)
                        .then());
    }

    // =================================================================================================================
    // Delete: Course
    // =================================================================================================================

    @Modifying
    @Transactional
    public Mono<Void> deleteCourse(Long courseId) {

        return preDeleteHelper.runPreDelete("course")
                .then(new BindSpecHelper(dbClient.sql(DELETE_COURSE_QUERY))
                        .bindIf(COURSE_ID, courseId, Long.class)
                        .then());
    }

}
