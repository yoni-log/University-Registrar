import java.util.ArrayList;
import java.util.List;

public class Registry {
    private List<Course> courseList = new ArrayList<>();
    private List<Professor> professorList = new ArrayList<Professor>();
    private List<Person> personList = new ArrayList<Person>();
    private List<Student> registeredStudents = new ArrayList<Student>();     

    public Registry() {

    }
  
    public boolean recordCourse(String courseDept, int courseNum, String courseName, int maxCapacity) {
        Course course = new Course(courseDept, courseNum, courseName, maxCapacity);
        if (!courseList.contains(course)) {
            courseList.add(course);
            return true;
        }
        return false;
    }
    
    public boolean recordStudent(String lastName, String firstName, int studentID, int day, int month, int year) {
        Student student = new Student(lastName, firstName, studentID, day, month, year);
        if (!registeredStudents.contains(student)) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean recordPerson(String lastName, String firstName, int day, int month, int year) {
        Person person = new Person(lastName, firstName, day, month, year);
        if (!personList.contains(person)) {
            personList.add(person);
            return true;
        }
        return false;
    }
    public boolean createProfessor(String lastName, String firstName, int employID, int day, int month, int year) {
        Professor professor = new Professor(lastName, firstName, employID, day, month, year);             
        if (!professorList.add(professor)) {
            professorList.add(professor);
            return true;
        }
        return false;
    }
    
    public boolean recordAtLarge(String lastName, String firstName, int studentID, int day, int month, int year) {
        Student student = new Student(lastName, firstName, studentID, day, month, year);
        if (!registeredStudents.contains(student)) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }
    public boolean recordUnderGradStudent(String lastName, String firstName, int studentID, int day, int month, int year) {
        Student student = new Student(lastName, firstName, studentID, day, month, year);
        if (!registeredStudents.contains(student)) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }
  
    public boolean recordGradStudent(String lastName, String firstName, int studentID, int day, int month, int year) {
        Student student = new Student(lastName, firstName, studentID, day, month, year);
        if (!registeredStudents.contains(student)) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }
    
    public List<Course> getCourseList() {
        return this.courseList;                 
    }
    
    public Professor getProfessor(String firstName, String lastName) {
        for (Professor professor : professorList) {
            if (professor.getFirstName().equals(firstName) && professor.getLastName() == lastName) {
                return professor;
            }
        }
        return null;
    }

    public Person find(String name) {
        for (Person p : personList) {
            if (p.getName().equals(name.toUpperCase())) {
                return p;
            }
        }
        for (Student s : registeredStudents) {
            if (s.getName().equals(name.toUpperCase())) {
                return s;
            }
        }
        for (Professor pr : professorList) {
            if (pr.getName().equals(name.toUpperCase())) {
                return pr;
            }
        }
        return null;
    }
    public Student findStudent(String name) {
        for (Student s : registeredStudents) {

            if (s.getName().equals(name.toUpperCase())) {
                return s;
            }
        }
        return null;
    }

    public Course getCourse(String deptName, int deptNum) {
        for (Course course : courseList) {
            if (course.getCourseDept().equals(deptName) && course.getCourseNum() == deptNum) {
                return course;
            }
        }
        return null;
    }

    public void enrollStudent(String ln,String fn, String department, int num) {
        Student s = findStudent( fn + " " + ln);
        Course c = getCourse(department,num);
        c.addStudent(s);
        s.addCourse(c);
    }

    public boolean removeStudent(String lastName, String firstName, String courseDept, int courseNum) {
        Student s = findStudent( firstName + " " + lastName);
        Course c = getCourse(courseDept,courseNum);
        if (c.removeStudent(s)) {
            c.removeStudent(s);
            s.dropCourse(c);
            return true;
        }
        return false;
    }

    public String reportRegistrations() {
        String str = "";
        int i = 0;
        while (i < courseList.size()) {
            Course c = courseList.get(i);
            str += c.getCourseName();
            str += c.registeredStudents();
            i++;
        }
        return str;
    }

    public String reportWaitListed() {
        String str = "";
        int i = 0;
        while(i < courseList.size()) {
            Course c = courseList.get(i);
            str += c.getCourseName();
            str += c.waitListed();
            i++;
        }

        return str;
    }
  
    public String getALlCourseInfo(){
        String str = "";
        int i = 0;
        while (i < courseList.size()) {
            Course c = courseList.get(i);
            str += c;
            i++;
        }
        return str;
    }
}
