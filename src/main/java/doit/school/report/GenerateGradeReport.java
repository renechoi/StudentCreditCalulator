package doit.school.report;

import doit.grade.BasicEvaluation;
import doit.grade.GradeEvaluation;
import doit.grade.MajorEvaluation;
import doit.school.School;
import doit.school.Score;
import doit.school.Student;
import doit.school.Subject;
import doit.utils.Define;

import java.util.ArrayList;

public class GenerateGradeReport {
	School school = School.getInstance();
	public static final String TITLE = "수강생 학점\t\t\n";
	public static final String HEADER = " 이름 | 학번 | 필수과목 | 점수  \n";
	public static final String LINE = "--------------------------------------";
	public StringBuffer buffer = new StringBuffer();

	public String getReport() {
		ArrayList<Subject> subjectList = school.getSubjectList();

		for (Subject subject : subjectList) {
			makeHeader(subject);
			makeBody(subject);
			MakeFooter();
		}

		return buffer.toString();
	}

	private void makeHeader(Subject subject) {
		buffer.append(GenerateGradeReport.LINE);
		buffer.append("\t" + subject.getSubjectName());
		buffer.append(GenerateGradeReport.TITLE);
		buffer.append(GenerateGradeReport.HEADER);
		buffer.append(GenerateGradeReport.LINE);
	}

	private void makeBody(Subject subject) {
		ArrayList<Student> studentList = subject.getStudentList();

		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			buffer.append(student.getStudentName());
			buffer.append("|");
			buffer.append(student.getStudentId());
			buffer.append("|");
			buffer.append(student.getMajorSubject().getSubjectName() + "\t");
			buffer.append("|");


			getScoreGrade(student, subject.getSubjectId());

			buffer.append("\n");
			buffer.append(LINE);

		}
	}

	public void getScoreGrade(Student student, int subjectId) {
		ArrayList<Score> scoreList = student.getScoreList();
		int majorId = student.getMajorSubject().getSubjectId();

		GradeEvaluation[] gradeEvaluation ={new BasicEvaluation(), new MajorEvaluation()};

		for (int i =0; i<scoreList.size(); i++){
			Score score = scoreList.get(i);
			if (score.getSubject().getSubjectId() == subjectId){
				String grade;
				if (score.getSubject().getSubjectId() == majorId){
					grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());
				} else {
					grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint());
				}

				buffer.append(score.getPoint());
				buffer.append(":");
				buffer.append(grade);
				buffer.append("|");
	}
		}

	}

	private void MakeFooter() {
		buffer.append("\n");
	}


}
