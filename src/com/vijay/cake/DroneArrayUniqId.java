package com.vijay.cake;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by vkbalakr on 6/19/17.
 */
public class DroneArrayUniqId {

    private static final int MAX_DRONES = 100;
    String[] deliveryIdConfirmations = new String[MAX_DRONES * 2];
    int currIdx = 0;
    static HashMap<String,Integer> map = new HashMap<>();

    public void addDeliveryId(String deliveryId) {
        deliveryIdConfirmations[currIdx++] = deliveryId;
        map.put(deliveryId, 1);
    }

    public void returnDeliveryId(String deliveryId) {
        deliveryIdConfirmations[currIdx++] = deliveryId;
        map.remove(deliveryId);
    }

    public String findUniqueDeliveryId() {

        for (Map.Entry<String,Integer> entry: map.entrySet()) {
            //System.out.println(map.entrySet().size());
            return entry.getKey();
        }
        return null;
    }

    public  int findUniqueDelivery(int[] deliveryIds) {
        int uniquedeliveryId = 0;
        for (int deliveryId = 0; deliveryId < deliveryIds.length; deliveryId++) {
            uniquedeliveryId ^= deliveryIds[deliveryId];
        }
        return uniquedeliveryId;
    }

    public static void main(String[] args) {
        DroneArrayUniqId drone = new DroneArrayUniqId();
        for (int i=0; i < MAX_DRONES; i++) {
            String deliveryId = "dId" + i;
            //System.out.println("1.deliveryId:"+deliveryId);
            drone.addDeliveryId(deliveryId);
        }
        //System.out.println("1.map.size():"+map.size());

        for (int i=MAX_DRONES; i > 0; i--) {
            int randomInt = new Random().nextInt(99);
            String deliveryId = "dId" + randomInt;
            //String deliveryId = "dId" + i;
            //System.out.println("2.deliveryId:"+deliveryId);
            drone.returnDeliveryId(deliveryId);
        }
        /*
        System.out.println("2.map.size():"+map.size());
        System.out.println("misbehaving drone:"+drone.findUniqueDeliveryId());*/

        System.out.println(drone.findUniqueDelivery(new int[]{1,2,3,4,3,2,1}));
    }
}
