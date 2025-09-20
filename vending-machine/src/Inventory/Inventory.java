package Inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {
    private Map<Integer, Product> aisleProductMap;
    private Map<Integer, Integer> productIdToCountMap;
    Queue<Integer> availableAisles;

    public Inventory(int aisleCount){
        availableAisles = new LinkedList<>();
        for(int aisleNo = 1;aisleNo<=aisleCount;aisleNo++){
            availableAisles.add(aisleNo);
        }
        aisleProductMap = new HashMap<>();
        productIdToCountMap = new HashMap<>();
    }

    public void addProduct(Product product) throws Exception{
        int productId = product.getId();
        int productCount = productIdToCountMap.getOrDefault(productId,0);
        if(productCount==0){
            if(availableAisles.isEmpty()){
                throw new Exception("Out of space to add product!!");
            }
            aisleProductMap.put(availableAisles.poll(),product);
        }
        productIdToCountMap.put(productId,productCount+1);
    }

    public Product getProductAt(int aisleNumber){
        return aisleProductMap.get(aisleNumber);
    }

    public boolean checkIfProductAvailable(int productId){
        int productCount = productIdToCountMap.getOrDefault(productId,0);
        return productCount>0;
    }

    public void deductProductCount(int aisleNumber){
        Product product = aisleProductMap.get(aisleNumber);
        int productId = product.getId();
        int updatedProductCount = productIdToCountMap.get(productId)-1;

        if(updatedProductCount==0){
            productIdToCountMap.remove(productId);
            aisleProductMap.remove(aisleNumber);
            availableAisles.add(aisleNumber);
        }else{
            productIdToCountMap.put(productId,updatedProductCount);
        }
    }

}
