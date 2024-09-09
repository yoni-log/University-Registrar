import java.util.Set;

public class Professor extends Person {
    private int iD;
    private Set<Course> coursesTaught;

    public Professor(String ln, String fn, int iD, int day, int month, int year) {
        super(ln, fn, day, month, year, 0.20);
        this.iD = iD;
    }

    public boolean addTaughtCourse(Course c){
        coursesTaught.add(c);
        c.setProfessor(this);
        return true;
    }

    public boolean removeTaughtCourse(Course c) {
        if(!coursesTaught.contains(c)) return false;
        coursesTaught.remove(c);
        c.setProfessor(null);
        return true;
    }

    public Set<Course> getTaughtCourses(){
        return coursesTaught;
    }
  
    public String toString(){
        String s = "Professor " + super.getName() + "teahces " + coursesTaught.size() + " courses.";

        return s;
    }
}
