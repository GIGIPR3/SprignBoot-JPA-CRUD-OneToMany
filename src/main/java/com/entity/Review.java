package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@NamedQueries({ @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
		@NamedQuery(name = "Review.findAllByCourse", query = "SELECT new com.vo.CourseReviewVO(r.course.title, r.message) FROM Review r WHERE r.course.title = :title") })
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String message;

	@ManyToOne
	@JoinColumn(name = "code", nullable = false) // non ha senso inserire una review che non fa riferimento ad alcun
													// corso, ecco perche' nullable = false
	private Course course;

	public Review(Integer id, String message) {
		this.id = id;
		this.message = message;
	}

	public Review(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", message=" + message + "]";
	}

}
