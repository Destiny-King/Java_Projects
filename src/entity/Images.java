package entity;

public class Images {
    private int imgid;
    private String imgtitle;
    private String introduce;
    private String image;

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getImgtitle() {
        return imgtitle;
    }

    public void setImgtitle(String imgtitle) {
        this.imgtitle = imgtitle;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Images{" +
                "imgid=" + imgid +
                ", imgtitle='" + imgtitle + '\'' +
                ", introduce='" + introduce + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
