/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in MyungJi University 
 */

package Components.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentComponent {
	protected ArrayList<Student> vStudent;
	
	public StudentComponent(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (bufferedReader.ready()) {
			String stuInfo = bufferedReader.readLine();
			if (!stuInfo.equals("")) this.vStudent.add(new Student(stuInfo));
		}
		bufferedReader.close();
	}
	public ArrayList<Student> getStudentList() { return vStudent; }
	public void setvStudent(ArrayList<Student> vStudent) { this.vStudent = vStudent; }
	public Student getStudent(String sID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.match(sID)) return student;
		}
		return null;
	}
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if (((Student) this.vStudent.get(i)).match(sSID)) return true;
		}
		return false;
	}
	public boolean deleteStudent(String sID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.match(sID)) 
				if (this.vStudent.remove(student)) return true;
			else return false;
		}
		return false;
	}
//	public boolean deleteStudent(String sID) { 안 되는 이유 고민중 ....
//	for (int i = 0; i < this.vStudent.size(); i++) {
//		Student student = this.vStudent.get(i);
//		if (student.match(sID)) this.vStudent.remove(student);
//		else return false;
//	}
//	return true;
//}
}
