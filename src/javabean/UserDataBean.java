package javabean;

public class UserDataBean implements java.io.Serializable{
	
	private String user_id;
	private String user_name;
	private String user_email;
	private String user_password;
	private String user_question;
	private String user_answer;
	private String user_threadname;
	public UserDataBean() {
		this("","","","","","","");
	}
	public UserDataBean(String user_id, String user_name, String user_email, String user_password, String user_question,
			String user_answer, String user_threadname) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_question = user_question;
		this.user_answer = user_answer;
		this.user_threadname = user_threadname;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_question() {
		return user_question;
	}
	public void setUser_question(String user_question) {
		this.user_question = user_question;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public String getUser_threadname() {
		return user_threadname;
	}
	public void setUser_threadname(String user_threadname) {
		this.user_threadname = user_threadname;
	}
	@Override
	public String toString() {
		return "UserDataBean [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_password=" + user_password + ", user_question=" + user_question + ", user_answer="
				+ user_answer + ", user_threadname="+user_threadname+ "]";
	}
}
