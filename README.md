
# Dining Philosophers
This project tackles the classic "Dining Philosophers" problem, exploring both sequential and multithreaded solutions. The primary objective is to model a scenario where a group of philosophers sits around a dining table, alternating between thinking and eating. The challenge lies in preventing deadlocks and resource conflicts as philosophers contend for shared resources (in this case, forks).

## Components:
* **SequentialNoThread:** A class representing the **_sequential solution_** to the Dining Philosophers problem without using threads.
Utilizes a simple algorithm to manage the dining process for each philosopher sequentially.

  * **FilosofoSeq:** A class defining the behavior of a philosopher in the sequential solution.
Manages the states of the philosopher (thinking, hungry, eating) and measures the time spent in each state.

* **FilosofosFamintosMultithread:** A class representing the **_multithreaded solution_** to the Dining Philosophers problem.
Utilizes Java threads and semaphores to enable concurrent dining activities, improving efficiency.

  * **Filosofo:** A class defining the behavior of a philosopher in the multithreaded solution.
Implements a thread-safe approach to ensure proper synchronization and avoid conflicts between philosophers.

  * **TFilosofoMulti:** A runnable task used by the multithreaded solution to manage the dining process for each philosopher concurrently.

## How it Works:
* **Sequential Solution:** Philosophers take turns in a sequential manner to pick up forks, eat, and release forks.  
Execution times are measured to evaluate performance.

* **Multithreaded Solution:** Philosophers run concurrently as separate threads, utilizing semaphores to manage access to shared forks.
Thread-safe mechanisms are employed to prevent conflicts and ensure a proper dining experience.  
The simulation outputs essential metrics, including the average waiting time, average execution time, and total execution time, providing insights into the performance of both solutions.

This project serves as an educational exploration of concurrent programming challenges and solutions, showcasing the nuances of the Dining Philosophers problem in both sequential and multithreaded contexts.





