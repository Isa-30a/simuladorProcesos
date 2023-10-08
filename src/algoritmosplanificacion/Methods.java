package algoritmosplanificacion;


public interface Methods {
    void planificationMethod();

    void calculateStartTime(Processes processes);

    void calculateEndTime(Process process);

    void calculateWaitTime(Process process);
}
