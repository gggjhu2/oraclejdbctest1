package member.model.vo;

import java.sql.Date;


//VO클래스(객체)는 MEMBER테이블의 한 행과 대응한다.

public class Member {
	
	private String memberId;
	private String password;
	private String memberName;
//	private char gender;
	private String gender;//M OR F 지만 스트링으로담는다.
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate; // java.sql.Date =>자바유틸쩜데이트의 자식클래스이다.
	/* 
 * 이름          널?       유형             
----------- -------- -------------- 
MEMBER_ID   NOT NULL VARCHAR2(20)   
PASSWORD    NOT NULL VARCHAR2(20)   
MAMBER_NAME NOT NULL VARCHAR2(100)  
GENDER               CHAR(1)        
AGE                  NUMBER         
EMAIL                VARCHAR2(200)  
PHONE       NOT NULL CHAR(11)       
ADDRESS              VARCHAR2(1000) 
HOBBY                VARCHAR2(100)  
ENROLL_DATE          DATE 
 */
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Member(String memberId, String password, String memberName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", gender="
				+ gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
}
