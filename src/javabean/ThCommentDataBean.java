package javabean;

public class ThCommentDataBean implements java.io.Serializable{

	private int co_no;
	private int b_no;
	private int co_order;
	private String co_content;
	private String co_id;
	
	public ThCommentDataBean() {
		this(-1, -1, -1, "","");
	}
	

	public ThCommentDataBean(int co_no, int b_no, int co_order, String co_content, String co_id) {
		super();
		this.co_no = co_no;
		this.b_no = b_no;
		this.co_order = co_order;
		this.co_content = co_content;
		this.co_id = co_id;
	}
	

	public int getCo_no() {
		return co_no;
	}


	public void setCo_no(int co_no) {
		this.co_no = co_no;
	}


	public int getB_no() {
		return b_no;
	}


	public void setB_no(int b_no) {
		this.b_no = b_no;
	}


	public int getCo_order() {
		return co_order;
	}


	public void setCo_order(int co_order) {
		this.co_order = co_order;
	}


	public String getCo_content() {
		return co_content;
	}


	public void setCo_content(String co_content) {
		this.co_content = co_content;
	}


	public String getCo_id() {
		return co_id;
	}


	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}
	

	@Override
	public String toString() {
		return "ThCommentDataBean [co_no=" + co_no + ", b_no=" + b_no + ", co_order=" + co_order + ", co_content="
				+ co_content + ", co_id=" + co_id + "]";
	}
}
