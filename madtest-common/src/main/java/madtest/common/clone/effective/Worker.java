package madtest.common.clone.effective;

/**
 * <p>Created by Damon.Q on 2017/2/27.
 */
public class Worker extends Person implements Cloneable{
    private String occupation;

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public Worker clone() throws CloneNotSupportedException {
        return (Worker) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Worker worker1 = new Worker();
        worker1.setOccupation("Teacher");
        Worker clone = worker1.clone();
        System.out.println(clone.getClass() == worker1.getClass());
    }
}
