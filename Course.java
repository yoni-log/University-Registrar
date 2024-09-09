import java.util.LinkedList;
import java.util.*;

public class Course implements Comparable{
    //fields
    private String courseDept;
    private int courseNum;
    private String courseName;
    private int maxCapacity;
    private int credits;
    private List<Student> enrolledStudents = new LinkedList<Student>();
    private List<Student> waitListedStudents = new LinkedList<Student>();
    private Professor professor;              //wait to initialize, and use setter method?

    //constructors
    public Course() {
        
    }
    
    public Course(String courseDept, int courseNum, String courseName,
                  int maxCapacity) {
        this.courseDept = courseDept;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
    }
    public Course(String courseDept, int courseNum, String courseName) {
        this.courseDept = courseDept;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.maxCapacity = 0;
    }

    //methods
    public boolean addStudent(Student student) {

        if (enrolledStudents.contains(student)) {
            return false;
        }
        if(atCapacity()) {
            waitListedStudents.add(student);
            return false;
        }
        enrolledStudents.add(student);
        return true;
    }

    public boolean removeStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            return false;
        }
        enrolledStudents.remove(student);
        if(!waitListedStudents.isEmpty()) {
            this.addStudent(waitListedStudents.get(0));
            waitListedStudents.remove(0);
        }
        return true;
    }

    public String getCourseDept() {
        return this.courseDept;
    }
    public int getCourseNum() {
        return this.courseNum;
    }
    public int getMaxCapacity() {
        return this.maxCapacity;
    }
    public boolean atCapacity(){return maxCapacity == enrolledStudents.size();}
    public String getName() {return courseName;}
    public String getCourseName() {return "|" + courseDept + " " + courseNum + " - " +courseName + "| : ";}
    public int getCredits() {
        return this.credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public List<Student> getEnrolledStudents() {
        return this.enrolledStudents;
    }
    public List<Student> getWaitListedStudents() {
        return this.waitListedStudents;
    }
    public Professor getProfessor() {
        return this.professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public String toString() {
        String result = "[ " + courseDept + " " + courseNum + ": ";
        for (Student student : this.enrolledStudents) {
            result += student + ", ";
        }
        result += "end of enrolled students. ]";
        return result;                                  //proper toString implementation?
    }
    public int compareTo(Course other) {
        if ((this.courseDept.length() > other.courseDept.length()) && (this.courseNum > other.courseNum)) {
            return 1;
        } else if ((this.courseDept.length() == other.courseDept.length()) && (this.courseNum == other.courseNum)) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String registeredStudents() {
        String str = "Currently Enrolled Students->[";
        int i = 0;
        while(i < enrolledStudents.size()) {
            Student s = enrolledStudents.get(i);
            str += s.getName();
            i++;
            if(i != enrolledStudents.size()) str += " , ";
        }
        str += "]\n";
        return str;
    }

    public String waitListed() {
        String str = "Currently Wait-Listed Students->[";
        int i = 0;
        while(i < waitListedStudents.size()) {
            str += waitListedStudents.get(i).getName();
            i++;
            if(i != waitListedStudents.size()) str += " , ";
        }
        str += "]\n";
        return str;
    }

}
