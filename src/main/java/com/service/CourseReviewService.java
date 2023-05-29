package com.service;

import java.util.List;

import com.entity.Course;
import com.entity.Review;
import com.vo.CourseReviewVO;

public interface CourseReviewService {

	public void insertCourse(Course course);

	public void insertReview(String courseTitle, Review review);

	public void insertCourseWithReviews(Course course, List<Review> reviews);

	public void updateCourse(String courseTitle, Course course);

	public void updateReview(Review review);

	public void updateReviewCourseReference(String courseTitle, Integer reviewId);

	public void deleteCourse(String courseTitle);

	public void deleteReview(Integer reviewId);

	public List<Course> readAllCourses();

	public List<Review> readAllReviews();

	public List<CourseReviewVO> readAllReviewsByCourse(String courseTitle);

	public String readCourseCodeByTitle(String courseTitle);

}
