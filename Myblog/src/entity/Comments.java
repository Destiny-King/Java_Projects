package entity;

public class Comments {
    private int cid;
    private String cusername;
    private String cdate;
    private String commentcontents;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCommentcontents() {
        return commentcontents;
    }

    public void setCommentcontents(String commentcontents) {
        this.commentcontents = commentcontents;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "cid=" + cid +
                ", cusername='" + cusername + '\'' +
                ", cdate='" + cdate + '\'' +
                ", commentcontents='" + commentcontents + '\'' +
                '}';
    }
}
