public class Ticket {
    int seats=5;
    synchronized void book(String name)
    {
        if(seats>0)
        {
            System.out.println(name+"booked");
            seats--;
        }
        else{
            System.out.println("no seats ");
        }
    }
    public static void main(String[] args) {
        Ticket obj=new Ticket();
        Thread t1=new Thread(()->{
           obj.book("prasad");
           obj.book("Sita");
            obj.book("Aman");
           System.out.println(obj.seats);
        });
          Thread t2=new Thread(()->{
           obj.book("prasad");
            obj.book("Priya");
            obj.book("David");
           System.out.println(obj.seats);
        });

        t1.start();
        t2.start();
        System.out.println(obj.seats);
    }
}
