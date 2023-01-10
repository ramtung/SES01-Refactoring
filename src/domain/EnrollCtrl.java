package domain;

import java.util.List;
import java.util.Map;

import domain.exceptions.EnrollmentRulesViolationException;
import domain.exceptions.UniException;

public class EnrollCtrl {
    public void enroll(Student student, List<Offering> courses) throws UniException {
        Map<Term, Map<Course, Double>> transcript = student.getTranscript();
        for (Offering o : courses) {
            for (Map.Entry<Term, Map<Course, Double>> tr : transcript.entrySet()) {
                for (Map.Entry<Course, Double> r : tr.getValue().entrySet()) {
                    if (r.getKey().equals(o.getCourse()) && r.getValue() >= 10)
                        throw new UniException(String.format("The student has already passed %student", o.getCourse().getName()));
                }
            }
            List<Course> prereqs = o.getCourse().getPrerequisites();
            nextPre:
            for (Course pre : prereqs) {
                for (Map.Entry<Term, Map<Course, Double>> tr : transcript.entrySet()) {
                    for (Map.Entry<Course, Double> r : tr.getValue().entrySet()) {
                        if (r.getKey().equals(pre) && r.getValue() >= 10)
                            continue nextPre;
                    }
                }
                throw new UniException(String.format("The student has not passed %student as a prerequisite of %student", pre.getName(), o.getCourse().getName()));
            }
            for (Offering o2 : courses) {
                if (o == o2)
                    continue;
                if (o.getExamTime().equals(o2.getExamTime()))
                    throw new UniException(String.format("Two offerings %student and %student have the same exam time", o, o2));
                if (o.getCourse().equals(o2.getCourse()))
                    throw new UniException(String.format("%student is requested to be taken twice", o.getCourse().getName()));
            }
        }
        int unitsRequested = 0;
        for (Offering o : courses)
            unitsRequested += o.getCourse().getUnits();
        double points = 0;
        int totalUnits = 0;
        for (Map.Entry<Term, Map<Course, Double>> tr : transcript.entrySet()) {
            for (Map.Entry<Course, Double> r : tr.getValue().entrySet()) {
                points += r.getValue() * r.getKey().getUnits();
                totalUnits += r.getKey().getUnits();
            }
        }
        double gpa = points / totalUnits;
        if ((gpa < 12 && unitsRequested > 14) ||
                (gpa < 16 && unitsRequested > 16) ||
                (unitsRequested > 20))
            throw new UniException(String.format("Number of units (%d) requested does not match GPA of %f", unitsRequested, gpa));
        for (Offering o : courses)
            student.takeCourse(o.getCourse(), o.getSection());
    }
}
