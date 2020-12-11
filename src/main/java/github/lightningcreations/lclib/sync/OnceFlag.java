package github.lightningcreations.lclib.sync;

import java.util.concurrent.atomic.AtomicBoolean;


public class OnceFlag {
	private final AtomicBoolean val = new AtomicBoolean();

	public void callOnce(Runnable r){
	    if(!val.getAcquire()){
	        synchronized(this){
	            if(!val.getAcquire()){
	                r.run();
	                val.setRelease(true);
                }
            }
        }
    }

}
