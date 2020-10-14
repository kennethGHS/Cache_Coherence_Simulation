package Components;

import java.util.ArrayList;

public class Bus {
    public static ArrayList<BusRequestHandler> busRequestHandlerList;

    public static void initBusHandlers(int procQuantity,int X,int Y){
        busRequestHandlerList = new ArrayList<>();
        for ( int i = 0;i<procQuantity;i+=1){
        busRequestHandlerList.add(new BusRequestHandler(X,Y,i));
        X+=400;
        }
    }
}
