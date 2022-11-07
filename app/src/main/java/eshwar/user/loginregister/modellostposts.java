package eshwar.user.loginregister;

public class modellostposts {


    String FullName , Imagelink , Location,Message , date ,time ,uid ;
    public modellostposts(){

    }

    public modellostposts(String fullName, String imagelink, String location, String message, String date, String time, String uid) {
        this.FullName = fullName;
        this.Imagelink = imagelink;
        this.Location = location;
        this.Message = message;
        this.date = date;
        this.time = time;
        this.uid = uid;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getImagelink() {
        return Imagelink;
    }

    public void setImagelink(String imagelink) {
        Imagelink = imagelink;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
