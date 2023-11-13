package org.acm.ShoppingStore;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acm.Entities.Item;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

    public Item findByCode(int code){
        return find("code" , code).firstResult();
    }
    public long RemoveByCode(int code){
        return delete("code" , code);
    }

    }
