package javabean;

public class CalendarDataBean {

	private int id;
	private String title;
	private String start;
	private String end;
	private String threadname;

	public CalendarDataBean() {
		this(0 , "", null, null,"");
	}
	
	public CalendarDataBean(int id, String title, String start, String end, String threadname) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.threadname = threadname;
	}
	

	public String getThreadname() {
		return threadname;
	}

	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "CalendarDataBean [id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", threadname"+threadname +"]";
	}
}
