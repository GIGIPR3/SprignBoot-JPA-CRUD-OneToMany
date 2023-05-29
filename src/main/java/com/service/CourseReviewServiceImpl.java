package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Course;
import com.entity.Review;
import com.repository.CourseReviewRepository;
import com.vo.CourseReviewVO;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TransactionRequiredException;

@Service
public class CourseReviewServiceImpl implements CourseReviewService {

	@Autowired
	CourseReviewRepository courseReviewRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void insertCourse(Course course) {
		try {
			courseReviewRepository.insertCourse(course);
			log.info("Inserimento Course avvenuto con successo");
		} catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
			log.error("Inserimento Course fallito");
			e.printStackTrace();
		}

	}

	@Override
	public void insertReview(String courseTitle, Review review) {
		courseReviewRepository.insertReview(courseTitle, review);

	}

	@Override
	public void insertCourseWithReviews(Course course, List<Review> reviews) {
		courseReviewRepository.insertCourseWithReviews(course, reviews);
	}

	@Override
	public void updateCourse(String courseTitle, Course course) {
		courseReviewRepository.updateCourse(courseTitle, course);

	}

	@Override
	public void updateReview(Review review) {
		courseReviewRepository.updateReview(review);

	}

	@Override
	public void updateReviewCourseReference(String courseTitle, Integer reviewId) {
		courseReviewRepository.updateReviewCourseReference(courseTitle, reviewId);
	}

	@Override
	public void deleteCourse(String courseTitle) {
		courseReviewRepository.deleteCourse(courseTitle);

	}

	@Override
	public void deleteReview(Integer reviewId) {
		courseReviewRepository.deleteReview(reviewId);

	}

	@Override
	public List<Course> readAllCourses() {
		return courseReviewRepository.readAllCourses();
	}

	@Override
	public List<Review> readAllReviews() {
		return courseReviewRepository.readAllReviews();
	}

	@Override
	public List<CourseReviewVO> readAllReviewsByCourse(String courseTitle) {
		return courseReviewRepository.readAllReviewsByCourse(courseTitle);
	}

	@Override
	public String readCourseCodeByTitle(String courseTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
