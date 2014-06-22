// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 *
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public static int mMaxIterations = 10;
    
    /**
     * Latch that will be decremented each time a thread exits.
     */
//    public static CountDownLatch latch = null; // TODO - You fill in here
    public static CountDownLatch mlatch = new CountDownLatch(2);

    /**
     * @class PlayPingPongThread
     *
     * @brief This class implements the ping/pong processing algorithm
     *         using the SimpleSemaphore to alternate printing "ping"
     *         and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread
    {
        private String sPrompt;
        private SimpleSemaphore pingSema ;
        private SimpleSemaphore pongSema ;
        /**
         * Constructor initializes the data member.
         */
        public PlayPingPongThread (/* TODO - You fill in here */ String Prompt , SimpleSemaphore s1 , SimpleSemaphore s2 )
        {
            // TODO - You fill in here.
        	sPrompt = Prompt ;
        	pingSema = s1 ;
        	pongSema = s2 ;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run () 
        {
            // TODO - You fill in here.
            for (int i = 1 ; i <= mMaxIterations ; i++) {
                acquire( pingSema );
				printout( sPrompt , i );
				release( pongSema );
            }
            mlatch.countDown();        	
        }

        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
public void printout( String prompt , int iteration )
{
	System.out.println(prompt + "(" + iteration + ")");
	
}

        /**
         * The two SimpleSemaphores use to alternate pings and pongs.
         * @return 
         */
        // TODO - You fill in here.
public void acquire( SimpleSemaphore s )
{
	s.acquireUninterruptibly();
}
public void release( SimpleSemaphore s )
{
	s.release();
}

        
    }

    /**
     * The main() entry point method into PingPongRight program. 
     */
    public static void main(String[] args) {
        try {         
            // Create the ping and pong SimpleSemaphores that control
            // alternation between threads.

            // TODO - You fill in here.
            SimpleSemaphore s1 = new SimpleSemaphore( 1 , true) ;
            SimpleSemaphore s2 = new SimpleSemaphore( 0 , true) ;

            System.out.println("Ready...Set...Go!");

            // Create the ping and pong threads, passing in the string
            // to print and the appropriate SimpleSemaphores.
            PlayPingPongThread ping =
                new PlayPingPongThread("Ping!" , s1 , s2 /* TODO - You fill in here */);
            PlayPingPongThread pong =
                new PlayPingPongThread("Pong!" , s2 , s1 /* TODO - You fill in here */);
            
            // Initiate the ping and pong threads, which will call the
            // run() hook method.
            ping.start();
            pong.start();

            // Use barrier synchronization to wait for both threads to
            // finish.

            // TODO - replace replace the following line with a
            // CountDownLatch barrier synchronizer call that waits for
            // both threads to finish.
            //   throw new java.lang.InterruptedException();
            
             mlatch.await();

        } 
        catch (java.lang.InterruptedException e)
            {}

        System.out.println("Done!");
    }
}
