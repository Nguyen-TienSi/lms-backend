package group14.backend.lms.service;

public interface IDataInitializerService {
    void init();
    void createUsers();
    void createSemesters();
    void createClasses();
    void createSubjects();
}
