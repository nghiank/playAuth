package models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nghian on 3/8/16.
 */

@Entity
public class Problem {

    @Id
    public ObjectId id;

    public String name;
}
