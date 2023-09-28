package algoritmosplanificacion;

import java.util.ArrayList;

public interface Methods {
    void addProcess(Process process);

    void planificationMethod(Processes processes);

    void calculateStartTime(Processes processes);

    void calculateEndTime(Processes processes);

    void calculateWaitTime(Processes processes);
}
