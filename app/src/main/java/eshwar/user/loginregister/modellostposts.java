package eshwar.user.loginregister;

public class modellostposts {


    public static String FullName  ,Message ,Imagelink, date ,time ,PhoneNumber,uid;






    public modellostposts(String fullName, String message, String date, String time, String imagelink,String phoneNumber) {
        this.FullName = fullName;
        this.Imagelink = imagelink;


        this.Message = message;
        this.date = date;
        this.time = time;
        this.PhoneNumber = phoneNumber;
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
    public static String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setImagelink(String imagelink) {
        Imagelink = imagelink;
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
