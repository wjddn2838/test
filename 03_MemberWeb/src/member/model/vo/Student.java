package member.model.vo;

import java.sql.Date;

public class Student {
	
	// 멤버변수
	private String studentId;
	private String studentPwd;
	private String studentName;
	private String studentGender;
	private int studentAge;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String studentHobby;
	private Date enrollDate;
	// 생성자
	public Student() {}
	
	//매개변수가 있는 생성자
	public Student(String studentId, String studentPwd, String studentName, String studentGender, int studentAge,
			String studentEmail, String studentPhone, String studentAddress, String studentHobby) {
		super();
		this.studentId = studentId;
		this.studentPwd = studentPwd;
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.studentAge = studentAge;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.studentHobby = studentHobby;

	}
	
	// 게터/세터

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentPwd() {
		return studentPwd;
	}
	public void setStudentPwd(String studentPwd) {
		this.studentPwd = studentPwd;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentHobby() {
		return studentHobby;
	}
	public void setStudentHobby(String studentHobby) {
		this.studentHobby = studentHobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	// toString
	@Override
	public String toString() {
		return "STUDENT [아이디 = " + studentId + ", 비번 = " + studentPwd + ", 이름 = " + studentName
				+ ", 성별 = " + studentGender + ", 나이 = " + studentAge + ", 이메일 = " + studentEmail
				+ ", 폰번호 = " + studentPhone + ", 주소 = " + studentAddress + ", 취미 = "
				+ studentHobby + ", 등록일 = " + enrollDate + "]";
	}
	
	
	
	
}
