package pkg;
public class DayBirth {
    int year;
    int month;
    int day;
    String gender;
    int birth;
    public DayBirth(int y, int m, int d, String g, int b) {
        year = y;
        month = m;
        day = d;
        gender = g;
        birth = b;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public String getGender() {
        return gender;
    }
    public int getBirths() {
        return birth;
    }
    public String toString() {
        return year + ", " + month + ", " + day + ", " + gender + ", " + birth;
    }
}