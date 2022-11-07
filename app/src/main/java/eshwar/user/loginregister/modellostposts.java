package eshwar.user.loginregister;

public class modellostposts {


    public static String FullName , Imagelink , Location,Message , date ,time ,uid;



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

    public static String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public static String getImagelink() {
        return
                Imagelink;
    }

    public void setImagelink(String imagelink) {
        Imagelink = imagelink;
    }

    public  static String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public static String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public static String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public  static String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
