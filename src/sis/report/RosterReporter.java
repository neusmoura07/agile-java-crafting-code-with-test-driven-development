package sis.report;
import java.util.*;
import sis.studentinfo.*;
import java.io.*;class RosterReporter {
    static final String ROSTER_REPORT_HEADER = "Student%n-%n";
    static final String ROSTER_REPORT_FOOTER = "%n# students = %d%n";
    private Session session;
    private Writer writer;
    RosterReporter(Session session) {
        this.session = session;
    }
    void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }
    void writeReport(String filename) throws IOException {
        Writer bufferedWriter = new BufferedWriter(new FileWriter(filename));
        try {
            writeReport(bufferedWriter);
        }
        finally {
            bufferedWriter.close();
        }
    }
    private void writeHeader() throws IOException {
        writer.write(String.format(ROSTER_REPORT_HEADER));
    }
    private void writeBody() throws IOException {
        for (Student student: session.getAllStudents())
            writer.write(String.format(student.getName() + "%n"));
    }
    private void writeFooter() throws IOException {
        writer.write(
                String.format(ROSTER_REPORT_FOOTER,
                        session.getAllStudents().size()));
    }
}