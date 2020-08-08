package javabean;

public class FreeBoardDataBean implements java.io.Serializable{

	private int bnum;
	private String btitle;
	private String bid;
	private String bdate;
	private int bhit;
	private String bcontent;
	
	public FreeBoardDataBean() {
		this(-1, "", "", "", -1, "");
	}
	public FreeBoardDataBean(int bnum, String btitle, String bid, String bdate, int bhit, String bcontent) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bid = bid;
		this.bdate = bdate;
		this.bhit = bhit;
		this.bcontent = bcontent;
	}
	

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	
	@Override
	public String toString() {
		return "FreeBoardDataBean [bnum=" + bnum + ", btitle=" + btitle + ", bid=" + bid + ", bdate=" + bdate
				+ ", bhit=" + bhit + ", bcontent=" + bcontent + "]";
	}
}