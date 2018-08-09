package me.txie.mongotemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ExampleDataMongoTests {

    @Autowired
    private MongoOperations mongoOps;

    @Test
    public void givenSpecifiedCollection_whenInsertOrSave_thenIndexedAnnotationNotWork() {
        Person p = new Person("Joe", 34);

        mongoOps.insert(p, "person888");
        assertEquals(1, mongoOps.indexOps("person888").getIndexInfo().size());
        
        // List<IndexInfo> indexInfo = mongoOps.indexOps("person888").getIndexInfo();
        // indexInfo.forEach(info -> System.out.println(info));

        mongoOps.save(p, "person777");
        assertEquals(1, mongoOps.indexOps("person777").getIndexInfo().size());
        assertEquals(1, mongoOps.count(new Query(), "person888"));
        assertEquals(1, mongoOps.count(new Query(), "person777"));
    }

    @Test
    public void givenEntity_whenInsertOrSave_thenIndexedAnnotationWork() {
        Person p = new Person("Joe", 34);

        mongoOps.insert(p);
        assertEquals(2, mongoOps.indexOps(Person.class).getIndexInfo().size());
        
        Person q = new Person("Amy", 28);
        mongoOps.save(q);
        assertEquals(2, mongoOps.indexOps(Person.class).getIndexInfo().size());

        assertEquals(2, mongoOps.count(new Query(), Person.class));
    }
}