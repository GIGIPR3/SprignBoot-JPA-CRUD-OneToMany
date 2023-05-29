package com.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Course;
import com.entity.Review;
import com.vo.CourseReviewVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Repository
public class CourseReviewRepositoryImpl implements CourseReviewRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void insertCourse(Course course) {
		em.persist(course);
	}

	@Override
	public void insertReview(String courseTitle, Review review) {
		// Recupero il Course in base al titolo (unique)
		String courseCode = readCourseCodeByTitle(courseTitle);
		Course course = em.find(Course.class, courseCode);
		// Associo la Review che voglio inserire al suo Course
		review.setCourse(course);
		// Associo al Course la Review che voglio inserire aggiungendola alla sua lista
		// di Review
		course.getReviews().add(review);
		// Infine, inserisco la Review
		em.persist(review);
	}

	@Override
	public void insertCourseWithReviews(Course course, List<Review> reviews) {
		// Prima inserisco il Course
		em.persist(course);
		for (Review r : reviews) {
			// Poi associo ad ogni Review il Course
			r.setCourse(course);
			// E aggiungo alla lista di Review del Course la Review in questione
			course.getReviews().add(r);
			// Eseguo l'insert della Review in questione
			em.persist(r);
		}

		// Se sull'entity Course avessi avuto il cascade = CascateType.PERSIST, il
		// metodo andava scritto cosi':
//		course.setReviews(reviews);
//		for (Review r : reviews) {
//			r.setCourse(course);
//		}
//		em.persist(course);

	}

	@Override
	// Dal momento che per il Course non abbiamo un id alfanumerico noto a priori,
	// dobbiamo recuperare l'informazione dell'id da usare per il merge
	// tramite il titolo del Course
	public void updateCourse(String courseTitle, Course course) {
		// Prima dobbiamo trovare la chiave primaria (il code) del Course che
		// vogliamo aggiornare:
		String oldCourseCode = readCourseCodeByTitle(courseTitle);
		Course oldCourse = em.find(Course.class, oldCourseCode);

		// Ora dobbiamo impostare tale code nel Course passato come argomento
		// poiche' tale Course NON contiene questa informazione, pertanto
		// il merge fallirebbe
		course.setCode(oldCourse.getCode());

		// Infine posso fare il merge del Course passato come argomento
		// e 'aggiornato' dal nostro setCode
		em.merge(course);
	}

	@Override
	// In questa Review da aggiornare non e' valorizzato il campo Course.
	// Quel che succede sara' andare sul DB a trovare il record corrispondente
	// per prenderne il campo Course, che poi settiamo sulla Review
	// che stiamo aggiornando
	public void updateReview(Review review) {
		// Recupero la Review del DB con lo stesso id della Review che voglio aggiornare
		Review oldReview = em.find(Review.class, review.getId());

		// Sulla Review che voglio aggiornare valorizzo il campo Course
		// con il Course della Review recuperata dal DB
		review.setCourse(oldReview.getCourse());

		// Infine posso fare il merge della Review
		em.merge(review);
	}

	@Override
	// Qui il piano e' recuperare il Course dal suo title e la Review dal suo id.
	// Assegno alla Review il Course e come al solito aggiungo alla lista di
	// Review del Course, la Review in questione. Infine aggiorno la Review
	public void updateReviewCourseReference(String courseTitle, Integer reviewId) {
		// Mi recupero il code del Course dal titolo
		String courseCode = readCourseCodeByTitle(courseTitle);
		// Quindi mi recupero il Course stesso
		Course course = em.find(Course.class, courseCode);
		// Mi recupero la Review
		Review review = em.find(Review.class, reviewId);
		// Associo alla Review il Course
		review.setCourse(course);
		// Aggiungo alla lista di Review del Course la Review in questione
		course.getReviews().add(review);
		// Aggiorno la Review in questione
		em.merge(review);
	}

	@Override
	public void deleteCourse(String courseTitle) {
		// Come al solito, dal title si passa al code del Course
		String courseCode = readCourseCodeByTitle(courseTitle);
		// Quindi si recupera il Course in questione
		Course course = em.find(Course.class, courseCode);
		// Dal momento che non ho il delete on cascade sulle reviews di Course,
		// mi scorro tutte le reviews di Course per rimuoverle
		for (Review r : course.getReviews()) {
			// Elimino ogni Review
			em.remove(r);
		}
		// Infine elimino il Course
		em.remove(course);
	}

	@Override
	// La rimozione di un record in relazione con un altro record non e' banale:
	// bisogna vedere qual e' il fetchType del campo relazionale
	// (https://www.baeldung.com/hibernate-lazy-eager-loading) e vedere se c'e'
	// il CascadeType.PERSIST
	public void deleteReview(Integer reviewId) {
		em.remove(em.find(Review.class, reviewId));
	}

	@Override
	public List<Course> readAllCourses() {
		return em.createNamedQuery("Course.findAll", Course.class).getResultList();
	}

	@Override
	public List<Review> readAllReviews() {
		return em.createNamedQuery("Review.findAll", Review.class).getResultList();
	}

	@Override
	public List<CourseReviewVO> readAllReviewsByCourse(String courseTitle) {
		return em.createNamedQuery("Review.findAllByCourse", CourseReviewVO.class).setParameter("title", courseTitle)
				.getResultList();
	}

	@Override
	public String readCourseCodeByTitle(String courseTitle) {
		// Esegue questa JPQL: SELECT c.code FROM Course c WHERE c.title = :title"
		// Dato che il campo title di Course e' unique, tale query restituira' sempre un
		// solo record (di tipo String) per questo usiamo il metodo getSingleResult
		return em.createNamedQuery("Course.findCodeByTitle", String.class).setParameter("title", courseTitle)
				.getSingleResult();
	}

}
