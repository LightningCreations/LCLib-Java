package github.lightningcreations.lclib;

import java.util.concurrent.locks.Lock;

public interface Locks {

    public static void lock(Lock... locks){
        for(int  i = 0;i<locks.length;i++){
            try{
                locks[i].lock();
            }catch(RuntimeException|Error e){
                for(int j = i-1;j>=0;j--)
                    locks[j].unlock();
                throw e;
            }
        }
    }

    public static void unlock(Lock... locks){
        for(int i = locks.length;i>0;i--){
            locks[i-1].unlock();
        }
    }
}
