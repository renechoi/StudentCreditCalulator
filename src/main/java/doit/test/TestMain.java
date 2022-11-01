package doit.test;

import doit.school.School;
import doit.school.Score;
import doit.school.Student;
import doit.school.Subject;
import doit.school.report.GenerateGradeReport;
import doit.utils.Define;

public class TestMain {
	School goodSchool = School.getInstance();
	Subject korean;
	Subject math;

	GenerateGradeReport gradeReport = new GenerateGradeReport();

	public static void main(String[] args) {
		TestMain test = new TestMain();

		test.createSubject();
		test.createStudent();

		String report = test.gradeReport.getReport();
		System.out.println(report);
	}


	private void createSubject() {
		korean = new Subject("Korean", Define.KOREAN);
		math = new Subject("Math", Define.MATH);

		goodSchool.addSubject(korean);
		goodSchool.addSubject(math);
	}

	public void createStudent(){

		Student student1 = new Student(181213, "Ahn", korean  );
		Student student2 = new Student(181518, "Oh", math  );
		Student student3 = new Student(171230, "Lee", korean  );
		Student student4 = new Student(171255, "Cho", korean  );
		Student student5 = new Student(171590, "Choi", math );

		goodSchool.addStudent(student1);
		goodSchool.addStudent(student2);
		goodSchool.addStudent(student3);
		goodSchool.addStudent(student4);
		goodSchool.addStudent(student5);

		korean.register(student1);
		korean.register(student2);
		korean.register(student3);
		korean.register(student4);
		korean.register(student5);

		math.register(student1);
		math.register(student2);
		math.register(student3);
		math.register(student4);
		math.register(student5);


		addScoreForStudent(student1, korean, 95);
		addScoreForStudent(student1, math, 56);

		addScoreForStudent(student2, korean, 95);
		addScoreForStudent(student2, math, 95);

		addScoreForStudent(student3, korean, 100);
		addScoreForStudent(student3, math, 88);

		addScoreForStudent(student4, korean, 89);
		addScoreForStudent(student4, math, 95);

		addScoreForStudent(student5, korean, 85);
		addScoreForStudent(student5, math, 56);


	}

	private void addScoreForStudent(Student student, Subject subject, int point) {

		Score score = new Score(student.getStudentId(), subject, point);
		student.addSubjectScore(score);

	}
}
