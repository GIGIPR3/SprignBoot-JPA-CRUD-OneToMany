package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Course;
import com.entity.Review;
import com.service.CourseReviewService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CourseReviewService courseReviewService;

	@Resource(name = "courseToInsert1")
	private Course courseToInsert1;

	@Resource(name = "courseToInsert2")
	private Course courseToInsert2;

	@Resource(name = "courseToInsert3")
	private Course courseToInsert3;

	@Resource(name = "courseToInsert4")
	private Course courseToInsert4;

	@Resource(name = "reviewToInsert1")
	private Review reviewToInsert1;

	@Resource(name = "reviewToInsert2")
	private Review reviewToInsert2;

	@Resource(name = "reviewToInsert3")
	private Review reviewToInsert3;

	@Resource(name = "reviewToInsert4")
	private Review reviewToInsert4;

	@Resource(name = "reviewToInsert5")
	private Review reviewToInsert5;

	@Resource(name = "reviewToInsert6")
	private Review reviewToInsert6;

	@Resource(name = "reviewToInsert7")
	private Review reviewToInsert7;

	@Resource(name = "reviewToInsert8")
	private Review reviewToInsert8;

	@Resource(name = "courseToUpdate")
	private Course courseToUpdate;

	@Resource(name = "reviewToUpdate")
	private Review reviewToUpdate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Inserisco 3 corsi
		courseReviewService.insertCourse(courseToInsert1); // Java Base
		courseReviewService.insertCourse(courseToInsert2); // Java Spring
		courseReviewService.insertCourse(courseToInsert3); // Angular - Course da aggiornare: cerco l'id tramite title

		// Inserisco 5 recensioni
		courseReviewService.insertReview("Java Base", reviewToInsert1);
		courseReviewService.insertReview("Java Base", reviewToInsert2);
		courseReviewService.insertReview("Java Spring", reviewToInsert3);
		courseReviewService.insertReview("Java Spring", reviewToInsert4);
		courseReviewService.insertReview("Angular", reviewToInsert5); // Review da aggiornare: prendo direttamente l'id

		// Inserisco il Course di Filosofia con tre Review:
		List<Review> reviews = new ArrayList<>(); // ok ok, dovrei fare il bean
		reviews.add(reviewToInsert6);
		reviews.add(reviewToInsert7);
		reviews.add(reviewToInsert8); // questa Review sara' assegnata ad un Course diverso

		courseReviewService.insertCourseWithReviews(courseToInsert4, reviews); // Course di Filosofia

		// Aggiorno la descrizione del corso su Angular
		courseReviewService.updateCourse("Angular", courseToUpdate);

		// Aggiorno il contenuto della recensione con id = 5
		reviewToUpdate.setId(5);
		courseReviewService.updateReview(reviewToUpdate);

		// Aggiorno la Review con id = 8 per assegnarla al Course cui fa davvero
		// riferimento (Angular)
//		courseReviewService.updateReviewCourseReference("Angular", 8);
//
//		// Rimuovo il Course di filosofia :(
//		courseReviewService.deleteCourse("Filosofia");
//
//		// Rimuovo la Review con id = 2
//		courseReviewService.deleteReview(2);
//
//		// Stampo la lista di tutti i Course attualmente presenti sul DB:
//		System.out.println("Stampo lista di corsi:");
//		courseReviewService.readAllCourses().forEach(System.out::println);
//
//		// Stampo la lista di tutte le Review attualmente presenti sul DB:
//		System.out.println("Stampo lista di reviews:");
//		courseReviewService.readAllReviews().forEach(System.out::println);
//
//		// Stampo la lista di Reviews che afferiscono al Course di title 'Java Base':
//		System.out.println("Stampo lista di reviews del corso 'Java Spring':");
//		courseReviewService.readAllReviewsByCourse("Java Spring").forEach(System.out::println);

	}

}
