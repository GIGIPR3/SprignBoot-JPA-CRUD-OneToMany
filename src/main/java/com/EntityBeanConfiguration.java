package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.entity.Course;
import com.entity.Review;

@Configuration
public class EntityBeanConfiguration {

	@Bean(name = "courseToInsert1")
	public Course getCourseToInsert1() {
		return new Course("Java Base", "Corso su Java Base - Base level");
	}

	@Bean(name = "courseToInsert2")
	public Course getCourseToInsert2() {
		return new Course("Java Spring", "Corso su Java Spring - Advanced level");
	}

	@Bean(name = "courseToInsert3")
	public Course getCourseToInsert3() {
		return new Course("Angular", "Corso su Java Spring - Frontend");
	}

	@Bean(name = "courseToInsert4")
	public Course getCourseToInsert4() {
		return new Course("Filosofia", "Monografia su Schopenhauer");
	}

	@Bean(name = "courseToUpdate")
	public Course getCourseToUpdate() {
		return new Course("Angular", "Corso su Angular - Frontend");
	}

	@Bean(name = "reviewToUpdate")
	public Review getReviewToUpdate() {
		return new Review("Angular e' meraviglioso!");
	}

	@Bean(name = "reviewToInsert1")
	public Review getReviewToInsert1() {
		return new Review("Il docente di Java Base e' bravissimo ed il corso e' strafico");
	}

	@Bean(name = "reviewToInsert2")
	public Review getReviewToInsert2() {
		return new Review("Il corso su Java Base e' noioso, ma il docente e' cmq bravissimo");
	}

	@Bean(name = "reviewToInsert3")
	public Review getReviewToInsert3() {
		return new Review("Java Spring mi ha cotto il cervello");
	}

	@Bean(name = "reviewToInsert4")
	public Review getReviewToInsert4() {
		return new Review("Java Spring e' meraviglioso");
	}

	@Bean(name = "reviewToInsert5")
	public Review getReviewToInsert5() {
		return new Review("Angular fa schifo...");
	}

	@Bean(name = "reviewToInsert6")
	public Review getReviewToInsert6() {
		return new Review("Il filosofo di Danzica sprizza vitalita' da tutti i pori");
	}

	@Bean(name = "reviewToInsert7")
	public Review getReviewToInsert7() {
		return new Review("Questo corso e' un pendolo che oscilla tra la noia e la morte");
	}

	@Bean(name = "reviewToInsert8")
	public Review getReviewToInsert8() {
		return new Review("Il corso su Angular e' il miglior corso di frontend in circolazione!");
	}

}
