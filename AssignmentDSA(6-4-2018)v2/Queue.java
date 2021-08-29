/**
 * assigment
 * ITP4510 Data Structures and Algorithms
 * 2017-18 Sem2
 * 
 * @Name       :Fu Ka Hing
 * @Class      :IT114105/1C
 * @Student ID :170309843
 * @Date       :5-April-2018
 * 
 * DECLARATION
 * I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement 
 * and collusion. I am aware of the consequences if found to be involved in these misconducts.
 * I hereby declare that the work submitted for the "IT4510 Data Structures & Algorithms" is 
 * authentic record of my own work.
 */
public interface Queue {
    public abstract boolean isEmpty();

    public abstract int size();

    public abstract Object front() throws QueueEmptyException;

    public abstract void enqueue(Object item) throws QueueFullException;

    public abstract Object dequeue() throws QueueEmptyException;
}
