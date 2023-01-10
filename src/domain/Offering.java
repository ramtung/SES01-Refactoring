package domain;

import java.util.Date;

public class Offering {
    private Course course;
    private int section;
    private Date examDate;

    public Offering(Course course) {
        this.course = course;
        this.section = 1;
        this.examDate = null;
    }

    public Offering(Course course, Date examDate) {
        this.course = course;
        this.section = 1;
        this.examDate = examDate;
    }

    public Offering(Course course, Date examDate, int section) {
        this.course = course;
        this.section = section;
        this.examDate = examDate;
    }

    public Course getCourse() {
        return course;
    }

    public String toString() {
        return course.getName() + " - " + section;
    }

    public Date getExamTime() {
        return examDate;
    }

    public int getSection() {
        return section;
    }

    public boolean isSameCourse(Offering offering) {
        return getCourse().equals(offering.getCourse());
    }

    public boolean hasExamsTimeOverlap(Offering offering) {
        return getExamTime().equals(offering.getExamTime());
    }
}
