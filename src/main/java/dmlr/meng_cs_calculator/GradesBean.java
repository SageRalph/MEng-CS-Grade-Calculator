package dmlr.meng_cs_calculator;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author David Ralph <David.M.L.Ralph@Gmail.com>
 */
@Named(value = "gradesBean")
@SessionScoped
public class GradesBean implements Serializable {

private Integer[] y2Grades, y3Grades, y4Grades;
private Integer projectGrade;
private double y2Ave, y3Ave, y4Ave, method1, method2;
private String award;

/**
 * Creates a new instance of FirstBean
 */
public GradesBean() {

    // Define units
    y2Grades = new Integer[6];
    y3Grades = new Integer[4];
    y4Grades = new Integer[4];

    // Set defaults
    Arrays.fill(y2Grades, 0);
    Arrays.fill(y3Grades, 0);
    Arrays.fill(y4Grades, 0);
    projectGrade = 0;
}

public void calc() {

    // Grades for each year
    y2Ave = yearGrade(y2Grades, 20);
    y3Ave = yearGrade(y3WithProject(), 20);
    y4Ave = yearGrade(y4Grades, 30);

    // Potential final grades
    method1 = (y2Ave * 0.2) + (y3Ave * 0.4) + (y4Ave * 0.4);
    method2 = (y3Ave * 0.5) + (y4Ave * 0.5);

    // Award name
    double best = method1 > method2 ? method1 : method2;
    if (best > 69) {
        award = "Distinction";
    } else if (best > 59) {
        award = "Merit";
    } else if (best > 39) {
        award = "Pass";
    } else {
        award = "Fail";
    }
}

// Utility
private Integer[] y3WithProject() {
    // Project counts double
    Integer[] pg = {projectGrade, projectGrade};
    return (Integer[]) ArrayUtils.addAll(y3Grades, pg);
}

/**
 * Calculates an average grade from an array of weighted unit grades, ignoring
 * the worst 20 credits. The total number of credits for the units must be 120.
 * Units must be worth at least 20 units each.
 *
 * @param arr Array of grades for each unit
 * @param weighting Credit weighting of each unit
 * @return The average grade, discarding the worst 20 credits
 */
private double yearGrade(Integer[] arr, int weighting) {

    if (arr.length * weighting != 120) {
        return -1;
    }

    double sum = 0, lowest = 100;

    for (Integer d : arr) {

        // Count total marks
        sum += d * weighting;

        // Find worst unit
        if (d < lowest) {
            lowest = d;
        }
    }

    // Remove worst 20 credits
    sum -= lowest * 20;

    // Calculate average
    return sum / 100;
}

// Accessors and mutators
public void setProjectGrade(Integer projectGrade) {
    this.projectGrade = projectGrade;
}
public Integer[] getY2Grades() {
    return y2Grades;
}
public Integer[] getY3Grades() {
    return y3Grades;
}
public Integer[] getY4Grades() {
    return y4Grades;
}
public void setY2Grades(Integer[] grades) {
    this.y2Grades = grades;
}
public void setY3Grades(Integer[] grades) {
    this.y3Grades = grades;
}
public void setY4Grades(Integer[] grades) {
    this.y4Grades = grades;
}
public Integer getProjectGrade() {
    return projectGrade;
}
public double getY2Ave() {
    return y2Ave;
}
public double getY3Ave() {
    return y3Ave;
}
public double getY4Ave() {
    return y4Ave;
}
public double getMethod1() {
    return method1;
}
public double getMethod2() {
    return method2;
}
public String getAward() {
    return award;
}

}
