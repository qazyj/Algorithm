import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Student> students = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Student student = new Student(st.nextToken(), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
            students.add(student);
        }

        Collections.sort(students);

        for (Student student : students) {
            sb.append(student.name).append("\n");
        }

        System.out.println(sb);
    }
}

class Student implements Comparable<Student> {

    String name;
    int korean, english, math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Student o) {
        if (this.korean != o.korean) {
            return Integer.compare(o.korean, this.korean);
        }

        if (this.english != o.english) {
            return Integer.compare(this.english, o.english);
        }

        if (this.math != o.math) {
            return Integer.compare(o.math, this.math);
        }

        return this.name.compareTo(o.name);
    }
}