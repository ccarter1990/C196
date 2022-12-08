package C196.mainactivity.Database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import C196.mainactivity.DAO.AssessmentDAO;
import C196.mainactivity.DAO.CourseDAO;
import C196.mainactivity.DAO.MentorDAO;
import C196.mainactivity.DAO.TermDAO;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.Entity.Mentor;
import C196.mainactivity.Entity.Term;

public class Repository {
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private MentorDAO mMentorDAO;
    private TermDAO mTermDAO;

    private List<Assessment> mAllAssessments;
    private List<Course> mAllCourses;
    private List<Mentor> mAllMentors;
    private List<Term> mAllTerms;

    private static int NUMBER_OF_THREADS=2;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        ClassSchedulerDatabase db = ClassSchedulerDatabase.getDatabase(application);

        mAssessmentDAO = db.assessmentDAO();
        mCourseDAO = db.courseDAO();
        mMentorDAO = db.mentorDAO();
        mTermDAO = db.termDAO();
    }

    public void insert(Term assessment){
        databaseExecutor.execute(() ->{
          mAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
