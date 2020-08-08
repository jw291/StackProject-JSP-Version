package javabean;

public class ThDataBean {
	int t_no;
	String threadname;
	String t_master;
	String t_category;
	String t_intro;
	

	public ThDataBean(int t_no, String threadname, String t_master,String t_category, String t_intro) {
		super();
		this.t_no = t_no;
		this.threadname = threadname;
		this.t_master = t_master;
		this.t_category = t_category;
		this.t_intro = t_intro;
	}

	public String getT_intro() {
		return t_intro;
	}
	
	public void setT_intro(String t_intro) {
		this.t_intro = t_intro;
	}
	
	public String getT_category() {
		return t_category;
	}
	
	public void setT_category(String t_category) {
		this.t_category = t_category;
	}
	
	public int getT_no() {
		return t_no;
	}


	public void setT_no(int t_no) {
		this.t_no = t_no;
	}


	public String getThreadname() {
		return threadname;
	}


	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}



	public String getT_master() {
		return t_master;
	}


	public void setT_master(String t_master) {
		this.t_master = t_master;
	}
	
	@Override
	public String toString() {
		return "ThDataBean [t_no=" + t_no + ", threadname=" + threadname + ", t_master="
				+ t_master + ", t_category"+t_category+ ", t_intro"+t_intro+"]";
	}

}
