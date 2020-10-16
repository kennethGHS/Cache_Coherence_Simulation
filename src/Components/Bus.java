package Components;

import java.util.ArrayList;

public class Bus {
    public static ArrayList<BusRequestHandler> busRequestHandlerList;
    public static ArrayList<Cache> cacheList = new ArrayList<>();

    public static void initBusHandlers(int procQuantity, int X, int Y) {
        busRequestHandlerList = new ArrayList<>();
        for (int i = 0; i < procQuantity; i += 1) {
            busRequestHandlerList.add(new BusRequestHandler(X, Y, i));
            X += 400;
        }
    }

    public static void fixCacheStates() {
        executeInvalidations();
        releaseHoldedAccesses();
    }

    private static void releaseHoldedAccesses() {
        for (BusRequestHandler handler:busRequestHandlerList){
            handler.release();
            for (BusRequestHandler handler1:busRequestHandlerList){
                if (!handler1.equals(handler)){
                    if (handler1.signalNameString == "WMISS" && handler1.signalNameString== "WHIT"){
                        if(handler1.dirHexSignalString==handler.dirHexSignalString){
                            handler.setOnUse();
                        }
                    }
                }
            }
        }
    }

    public static synchronized void executeInvalidations() {
        int i = 0;
        for (BusRequestHandler handler : busRequestHandlerList) {
            if (handler.signalNameString != null && !handler.invalidated) {
                if (handler.signalNameString == "WHIT") {
                    invalidateMessages(handler.dirHexSignalString, handler);
                    //Aqui se evita el mensaje a los otros caches de invalidacion
                    if(!isSharedCacheBlock(i,handler.dirHexSignalString)) {
                        invalidateSharedCacheBlocks(i, handler.dirHexSignalString);
                    }
                }
                //Se hacen las mismas invalidaciones
                if (handler.signalNameString == "WMISS") {
                    invalidateMessages(handler.dirHexSignalString, handler);
                    invalidateSharedCacheBlocks(i, handler.dirHexSignalString);
                    handler.toMemory = true;
                }
                if (handler.signalNameString == "RMISS") {
                    if (isSharedCacheBlock(i, handler.dirHexSignalString)){
                        handler.toMemory = true;
                    }
                }
            }
            i += 1;
        }

    }

    public static  synchronized void invalidateMessages(String directions, BusRequestHandler owner) {
        for (BusRequestHandler handler : busRequestHandlerList) {
            if (handler.dirHexSignalString == directions && !owner.equals(handler)) {
                if (handler.signalNameString=="RHIT"|| handler.signalNameString=="RMISS"){
                    handler.setOnUse();
                }
                else 
                handler.invalidate();
            }
        }
    }

    public static synchronized void invalidateSharedCacheBlocks(int jump, String dir) {
        int i = 0;
        for (Cache cache : cacheList) {
            if (i != jump) {
                int isHit = cache.getBlockHit(dir);
                if (isHit >= 0) {
                    cache.changeBlockState(dir, isHit, 3);
                }
            }
            i += 1;
        }

    }

    public static synchronized boolean isSharedCacheBlock(int jump,String dir) {
        int i = 0;
        for (Cache cache : cacheList) {
            if (i!=jump) {
                if (cache.getBlockHit(dir)>=0){
                    return true;
                }
            }
            i += 1;
        }
        return false;
    }
    public static synchronized void setOthersToShared(int jump,String dir){
        int i = 0;
        for (Cache cache : cacheList) {
            if (i!=jump) {
                int cacheBlock = cache.getBlockHit(dir);
                if (cacheBlock>=0){
                    cache.changeBlockState(dir,cacheBlock,2);
                }
            }
            i += 1;
        }
        return ;
    }
    public static synchronized Cache isInOtherCache(int jump,String dir){
        int i = 0;
        for (Cache cache : cacheList) {
            if (i!=jump) {
                int cacheBlock = cache.getBlockHit(dir);
                if (cacheBlock>=0){
                    return cache;
                }
            }
            i += 1;
        }
        return null;
    }
}
