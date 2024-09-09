import java.util.ArrayList;

public class Student extends Person {
    private int iD;
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Student(String ln,String fn, int iD, int day, int month, int year) {
        super(ln,fn,day,month,year,0.07);
        this.iD = iD;
    }
  
    public Student(String ln,String fn, int iD, int day, int month, int year,double discount) {
        super(ln,fn,day,month,year,discount);
        this.iD = iD;
    }

    public boolean addCourse(Course c) { //Adds student to a course
        if(c.addStudent(this)) {       //Checks to see if the student is already in the course
            courses.add(c);               //Adds course to the students courses list
            return true;                  //Returns true
        }
        return false;                     //Returns false if the student is already in the course
    }

    public boolean dropCourse(Course c) { //Removes student from a course
        if(c.removeStudent(this)) {     //Checks to see if the student is in the course
            courses.remove(c);             //Removes course from student's course list
            return true;                   //Returns true
        }
        return false;                     //Returns false if the student is not in the course they are removing
    }

    public String toString() {
        return(getFirstName() + " " + getLastName() + " | " + getDob() + " | Current enrolled classes: " + courses.toString());
    }
}
