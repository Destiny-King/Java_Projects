package entity;

public class Articles {
    private int aid;
    private String category;
    private String atitle;
    private String contents;
    private String publishdate;
    private int readnum;
    private String articleimage;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public String getArticleimage() {
        return articleimage;
    }

    public void setArticleimage(String articleimage) {
        this.articleimage = articleimage;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", category='" + category + '\'' +
                ", atitle='" + atitle + '\'' +
                ", contents='" + contents + '\'' +
                ", publishdate='" + publishdate + '\'' +
                ", readnum=" + readnum +
                ", articleimage='" + articleimage + '\'' +
                '}';
    }
}
